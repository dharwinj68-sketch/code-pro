import java.util.ArrayList;
import java.util.Scanner;
public class DharwinMart {
    static Scanner sc = new Scanner(System.in);
    static final String ADMIN_USERNAME = "Admin1234";
    static final String ADMIN_PASSWORD = "12345";
    static ArrayList<Buyer> buyers = new ArrayList<>();
    static ArrayList<Seller> sellers = new ArrayList<>();
    static class Buyer {
        String fullName;
        String username;
        String email;
        String phone;
        String password;
        Buyer(String fullName, String username, String email,
              String phone, String password) {
            this.fullName = fullName;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.password = password;
        }
    }
    static class Seller {
        String fullName;
        String username;
        String email;
        String phone;
        String shopName;
        String password;
        Seller(String fullName, String username, String email,
               String phone, String shopName, String password) {
            this.fullName = fullName;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.shopName = shopName;
            this.password = password;
        }
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n================================");
            System.out.println("         DHARWIN MART");
            System.out.println("================================");
            System.out.println("1. Admin Login");
            System.out.println("2. Buyer Login");
            System.out.println("3. Seller Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    buyerLogin();
                    break;
                case 3:
                    sellerLogin();
                    break;
                case 4:
                    System.out.println("\nThank you for using Dharwin Mart!");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice!");
            }
        }
    }
    static void adminLogin() {
        System.out.println("\n========== ADMIN LOGIN ==========");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if (username.equals(ADMIN_USERNAME)
                && password.equals(ADMIN_PASSWORD)) {
            System.out.println("\nAdmin Login Successful!");
            openEmptyPage();
        } else {
            System.out.println("\nInvalid Admin username or password!");
        }
    }
    static void buyerLogin() {
        while (true) {
            System.out.println("\n========== BUYER LOGIN ==========");
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine()
            boolean found = false;
            for (Buyer buyer : buyers) {
                if (buyer.username.equals(username)
                        && buyer.password.equals(password)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println("\nBuyer Login Successful!");
                openEmptyPage();
                return;
            } else {
                System.out.println(
                        "\nInvalid username or password."
                );
                System.out.println("1. Try Again");
                System.out.println("2. Register");
                System.out.println("3. Back to Home");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 2) {
                    registerBuyer();
                    return;
                }
                if (choice == 3) {
                    return;
                }
            }
        }
    }
    static void sellerLogin() {
        while (true) {
            System.out.println("\n========== SELLER LOGIN ==========");
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            boolean found = false;
            for (Seller seller : sellers) {
                if (seller.username.equals(username)
                        && seller.password.equals(password)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println("\nSeller Login Successful!");
                openEmptyPage();
                return;
            } else {
                System.out.println(
                        "\nInvalid username or password."
                );
                System.out.println("1. Try Again");
                System.out.println("2. Register");
                System.out.println("3. Back to Home");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 2) {
                    registerSeller();
                    return;
                }
                if (choice == 3) {
                    return;
                }
            }
        }
    }
    static void registerBuyer() {
        System.out.println("\n======= BUYER REGISTRATION =======");
        System.out.print("Full Name: ");
        String fullName = sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        for (Buyer buyer : buyers) {
            if (buyer.username.equals(username)) {
                System.out.println("\nUsername already exists!");
                return;
            }
        }
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Confirm Password: ");
        String confirmPassword = sc.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("\nPasswords do not match!");
            return;
        }
        Buyer buyer = new Buyer(
                fullName,
                username,
                email,
                phone,
                password
        );
        buyers.add(buyer);
        System.out.println(
                "\nBuyer registration successful!"
        );
        System.out.println("You can now login.");
    }
    static void registerSeller() {
        System.out.println("\n====== SELLER REGISTRATION ======");
        System.out.print("Full Name: ");
        String fullName = sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        for (Seller seller : sellers) {
            if (seller.username.equals(username)) {
                System.out.println("\nUsername already exists!");
                return;
            }
        }
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Shop Name: ");
        String shopName = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Confirm Password: ");
        String confirmPassword = sc.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("\nPasswords do not match!");
            return;
        }
        Seller seller = new Seller(
                fullName,
                username,
                email,
                phone,
                shopName,
                password
        );
        sellers.add(seller);
        System.out.println(
                "\nSeller registration successful!"
        );
        System.out.println("You can now login.");
    }
    static void openEmptyPage() {
        System.out.println("\n================================");
        System.out.println("       LOGIN SUCCESSFUL");
        System.out.println("================================");
        System.out.println("\nWelcome to Dharwin Mart!");
        System.out.println("\nPress Enter to return to Home...");
        sc.nextLine();
    }
}