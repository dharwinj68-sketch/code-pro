package com.dharwinmart.shop.controller;

import com.dharwinmart.shop.model.User;
import com.dharwinmart.shop.service.CartService;
import com.dharwinmart.shop.service.CurrentUserService;
import com.dharwinmart.shop.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;
    private final CurrentUserService currentUserService;

    public CartController(CartService cartService, OrderService orderService,
                           CurrentUserService currentUserService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public String viewCart(Authentication authentication, Model model) {
        User user = currentUserService.getCurrentUser(authentication);
        model.addAttribute("cartItems", cartService.getCartItems(user));
        model.addAttribute("total", cartService.getCartTotal(user));
        model.addAttribute("user", user);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                             @RequestParam(defaultValue = "1") int quantity,
                             Authentication authentication) {
        User user = currentUserService.getCurrentUser(authentication);
        cartService.addToCart(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long cartItemId,
                                  @RequestParam int quantity) {
        cartService.updateQuantity(cartItemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long cartItemId) {
        cartService.removeItem(cartItemId);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String deliveryAddress,
                            Authentication authentication,
                            Model model) {
        User user = currentUserService.getCurrentUser(authentication);
        try {
            orderService.checkout(user, deliveryAddress);
            return "redirect:/orders?success=true";
        } catch (IllegalStateException ex) {
            model.addAttribute("cartItems", cartService.getCartItems(user));
            model.addAttribute("total", cartService.getCartTotal(user));
            model.addAttribute("checkoutError", ex.getMessage());
            return "cart";
        }
    }
}
