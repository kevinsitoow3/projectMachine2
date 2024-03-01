package projectMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Purchase {
    private static List<String> transactionLog = new ArrayList<>();

    public static void giveChange(double change) {
        System.out.println("Change given: " + change + " coins");
        transactionLog.add("Change given: " + change + " coins");
    }
    //payment: lo que se tiene q pagar
    public static void purchase(Products product, int quantity, int paymentMethod, Users user, double payment) {
        // Scanner scanner = new Scanner(System.in); // No es necesario declarar un nuevo scanner

        if (paymentMethod == 1) { // Payment in coins
            // No es necesario pedir el pago aquí ya que se recibe como parámetro

            if (payment >= product.cost * quantity) {
            	double change = (float) (payment - (product.cost * quantity) * 1.19);
                giveChange(change);
                if (dispenseProduct(product, quantity)==true) {
                    // Print receipt only if the purchase is successful
                    printReceipt(product, quantity, 1, payment, change);
                }
            } else {
                System.out.println("Insufficient payment. Please insert more coins.");
            }
        }
        
        else if (paymentMethod == 2) { // Payment with card
            // Check if the user has enough money in availableCash
            if (user.availableCash >= product.cost * quantity) {
                // Deduct money from the user and complete the purchase
                user.availableCash -= product.cost * quantity;
                System.out.println("Payment successful with card. Product dispensed: " + product.nameProduct);
                transactionLog.add("Product dispensed: " + product.nameProduct + " - Quantity: " + quantity + " - Payment: Card");

                if (dispenseProduct(product, quantity)==true) {
                    // Print receipt only if the purchase is successful
                    printReceipt(product, quantity, 2, payment, 0);
                }
            } else {
                System.out.println("Insufficient funds. Payment failed.");
            }
        } else {
            System.out.println("Invalid payment method.");
        }
    }

    public static boolean dispenseProduct(Products product, int quantity) {
        // Check if there is enough inventory
        if (product.availableQuantity >= quantity) {
            // Subtract the purchased quantity from the inventory
            product.availableQuantity -= quantity;
            System.out.println("\nDispensing " + quantity + " " + product.nameProduct + "(s).");
            return true;
        } else {
            System.out.println("\nInsufficient inventory. Cannot dispense " + quantity + " " + product.nameProduct + "(s).");
            return false;
        }
    }

    public static void printReceipt(Products product, int quantity, int paymentMethod, double payment, double change) {
        double[] values = calculateValues(product, quantity, paymentMethod);

        System.out.println("\nReceipt:");
        System.out.println("1. Value per product for " + quantity + " " + product.nameProduct + "(s)-> ($" + values[0] + ") each product to $" + product.cost);
        System.out.println("2. Total discount: $" + values[1]);
        System.out.println("3. Value with IVA: $" + values[2]);
        System.out.println("4. Total purchase value: $" + values[3]);
        System.out.println("5. Payment received: $" + payment);
        System.out.println("6. Change given: $" + change);
    }

    public static double[] calculateValues(Products product, int quantity, int paymentMethod) {
        double valuePerProduct = product.cost * quantity;
        double discount = 0.0;  

        if (paymentMethod == 2) {
            discount = valuePerProduct * 0.1; // 10% discount if paid with a card
        } else {
            discount = 0.0; // no discount if paid with coins
        }

        double valueWithIVA = valuePerProduct * 1.19; // 19% VAT
        double totalPurchaseValue = valueWithIVA - discount;

        return new double[]{valuePerProduct, discount, valueWithIVA, totalPurchaseValue};
    }
    
    public static void showTransactionLog() {
        System.out.println("Transaction Log:");
        for (String transaction : transactionLog) {
            System.out.println(transaction);
        }
    }
}
