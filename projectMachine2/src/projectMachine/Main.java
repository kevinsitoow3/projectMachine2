package projectMachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //instancia de la clase de Products
        Products product = new Products();
        Users user = new Users();

        //inicializar productos y usuarios predefinidos
        Users.initializeUsers();
        Products.initializeProducts();

        int option0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nMenu:");
            System.out.println("1. View products");
            System.out.println("2. CRUD Products");
            System.out.println("3. CRUD Users");
            System.out.println("4. Make a purchase--CRUD");
            System.out.println("5. View transactions");
            System.out.println("0. Exit");
            System.out.print("Enter the option: ");

            option0 = scanner.nextInt();

            switch (option0) {
                case 1:
                    Products.printProductList();
                    break;

                case 2:
                    while (true) {
                        System.out.println("\nPlease choose an option: ");
                        System.out.println("1. Create a new product.");
                        System.out.println("2. Consult product.");
                        System.out.println("3. Delete product.");
                        System.out.println("4. Update product.");
                        System.out.println("5. Go back to the main menu.");

                        int option2 = scanner.nextInt();

                        switch (option2) {
                            case 1:
                                product.createProduct();
                                break;
                            case 2:
                                System.out.println("\nWrite product code to search: ");
                                int code = scanner.nextInt();
                                Products.getProductByCode(code);
                                break;
                            case 3:
                                System.out.println("\nWrite product code to delete: ");
                                int code2 = scanner.nextInt();
                                product.deleteProduct(code2);
                                break;
                            case 4:
                                System.out.println("\nWrite product code to update: ");
                                int code3 = scanner.nextInt();

                                System.out.println("Write the new product name: ");
                                String newName = scanner.next();

                                System.out.println("Write the new product cost: ");
                                int newCost = scanner.nextInt();

                                System.out.println("Write the new product available quantity: ");
                                int newQuantity = scanner.nextInt();

                                product.updateProduct(code3, newName, newCost, newQuantity);
                                break;
                            case 5:
                                System.out.println("\nGoing back to the main menu....");
                                break;
                            default:
                                System.out.println("\nPlease choose the correct option.");
                                break;
                        }

                        if (option2 == 5) {
                            break; // Exit the while loop when option 5 is selected
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.println("\nPlease choose an option: ");
                        System.out.println("1. Show users.");
                        System.out.println("2. Create a new user.");
                        System.out.println("3. Consult user.");
                        System.out.println("4. Delete user.");
                        System.out.println("5. Update user.");
                        System.out.println("6. Go back to the main menu.");

                        int option3 = scanner.nextInt();

                        switch (option3) {
                            case 1:
                                Users.showUserList();
                                break;
                            case 2:
                                user.createUser();
                                break;
                            case 3:
                                System.out.println("\nWrite user ID to search: ");
                                int id = scanner.nextInt();
                                user.getUserById(id);
                                break;
                            case 4:
                                System.out.println("\nWrite user ID to delete: ");
                                int id2 = scanner.nextInt();
                                user.deleteUser(id2);
                                break;
                            case 5:
                                System.out.println("\nWrite user ID to update: ");
                                int id3 = scanner.nextInt();

                                System.out.println("Write the new username: ");
                                String newNameUser = scanner.next();

                                System.out.println("Write the new user's last name: ");
                                String newLastName = scanner.next();

                                System.out.println("Write the new user's available cash: ");
                                int newAvailableCash = scanner.nextInt();

                                user.updateUser(id3, newNameUser, newLastName, newAvailableCash);
                                break;
                            case 6:
                                System.out.println("\nGoing back to the main menu....");
                                break;
                            default:
                                System.out.println("\nPlease choose the correct option.");
                                break;
                        }

                        if (option3 == 6) {
                            break; // Exit the while loop when option 6 is selected
                        }
                    }
                    break;

                case 4:
                    while (true) {
                        System.out.println("\nPlease choose an option:");
                        System.out.println("1. Buy a product.");
                        System.out.println("2. Go back to the main menu.");

                        int option4 = scanner.nextInt();

                        switch (option4) {
	                        case 1:
	                            // Option to buy products
	                            System.out.println("Choose a product to buy:");
	                            Products.printProductList();
	                            int productCode = scanner.nextInt();
	                            Products selectedProduct = Products.getProductByCode(productCode);
	
	                            if (selectedProduct != null) {
	                                System.out.println("\nEnter the quantity you want to buy:");
	                                int quantity = scanner.nextInt(); // Ask the user for the quantity
	
	                                double[] values = Purchase.calculateValues(selectedProduct, quantity, option0);
	
	                                // Show the total value before selecting the payment method
	                                System.out.println("\nTotal amount to pay: $" + values[3]);
	
	                                System.out.println("\nPlease choose a payment method:");
	                                System.out.println("1. Coins");
	                                System.out.println("2. Card");
	
	                                int paymentMethod = scanner.nextInt();
	
	                                switch (paymentMethod) {
	                                    case 1:
	                                        // Coin payment method
	                                        System.out.println("Insert coins for payment: ");
	                                        int payment = scanner.nextInt();
	
	                                        if (payment >= values[0]) {
	                                            Purchase.purchase(selectedProduct, quantity, 1, null, payment);
	                                        } else {
	                                            System.out.println("Insufficient payment. Please insert more coins.");
	                                        }
	                                        break;
	
	                                    case 2:
	                                        // Card payment method
	                                        System.out.println("\nPlease choose a user:");
	                                        Users.showUserList();
	                                        int selectedUserId = scanner.nextInt();
	
	                                        // Call the getUserById method only when paying with a card
	                                        Users selectedUser = new Users().getUserById(selectedUserId);
	
	                                        if (selectedUser != null) {
	                                            
	                                            double cardPaymentAmount = values[3];
	
	                                            Purchase.purchase(selectedProduct, quantity, 2, selectedUser, cardPaymentAmount);
	                                            
	                                        } else {
	                                            System.out.println("Invalid user ID.");
	                                        }
	                                        break;
	
	                                    default:
	                                        System.out.println("Please choose a valid payment method.");
	                                        break;
	                                }
	                            } else {
	                                System.out.println("Invalid product code.");
	                            }
	                            break;

                            case 2:
                                // Return to the main menu
                                break;

                            default:
                                System.out.println("Please choose a valid option.");
                                break;
                        }

                        // Exit the loop if the option to return to the main menu is chosen
                        if (option4 == 2) {
                            break;
                        }
                    }
                    break;

                case 5:
                    // Option to show transactions
                    Purchase.showTransactionLog();
                    break;

                case 0:
                    // If 0 is selected, exit the loop
                    System.out.println("Exiting... Bye.");
                    break;

                default:
                    System.out.println("Please choose the correct option.");
                    break;
            }

        } while (option0 != 0);
    }
}
