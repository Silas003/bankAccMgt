package com.utilities;
import java.util.Scanner;

/**
 * Utility class providing input validation and user interaction helpers.
 * 
 * <p>This class contains static methods for validating user input with retry logic
 * and user-friendly error messages. All validation methods follow a consistent pattern:
 * <ul>
 *   <li>Allow up to 3 retry attempts (2 for transaction confirmation)</li>
 *   <li>Display clear error messages for invalid input</li>
 *   <li>Return null or -1 on failure to allow calling code to handle gracefully</li>
 *   <li>Return validated values on success</li>
 * </ul>
 * </p>
 * 
 * <p>Validation rules enforced:
 * <ul>
 *   <li>Customer names: Letters only, no numbers or special characters</li>
 *   <li>Customer age: Positive integer</li>
 *   <li>Customer contact: Exactly 10 digits</li>
 *   <li>Account numbers: Format "ACC00" followed by digits (case-insensitive)</li>
 *   <li>Initial deposits: Must meet minimum requirements based on customer/account type</li>
 *   <li>Transaction amounts: Must be positive numbers</li>
 * </ul>
 * </p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 */
public class CustomUtils{
    /** Maximum number of retry attempts allowed for input validation */
    private static final int maxRetries = 3;
    
    /**
     * Validates customer name input from the user.
     * 
     * <p>Validates that the customer name:
     * <ul>
     *   <li>Is not blank or empty</li>
     *   <li>Contains only alphabetic characters (A-Z, a-z)</li>
     *   <li>Does not contain numbers or special characters</li>
     * </ul>
     * </p>
     * 
     * <p>Allows up to 3 attempts. If all attempts fail, returns null and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated customer name, or {@code null} if validation fails after max retries
     */
    public static String validateCustomerNameInput(Scanner scanner){
        String customerName ;

        for(int i=0 ; i < maxRetries ; i++ ) {
            System.out.print("Enter customer name: ");
            customerName = scanner.nextLine();
            if (customerName.isBlank() || !customerName.matches("^[A-Za-z]+$")) {
                System.out.println("Invalid Customer Name provided. NB: Name must not be empty or contain an integer");
            }else return customerName;

        }

        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;

    }

    /**
     * Validates customer age input from the user.
     * 
     * <p>Validates that the customer age:
     * <ul>
     *   <li>Is a valid integer</li>
     *   <li>Is a positive number (greater than 0)</li>
     * </ul>
     * </p>
     * 
     * <p>Handles NumberFormatException for non-numeric input gracefully.
     * Allows up to 3 attempts. If all attempts fail, returns -1 and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated customer age as an integer, or {@code -1} if validation fails after max retries
     */
    public static int validateCustomerAgeInput(Scanner scanner){
        int customerAge ;
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter customer age: ");
            try {
                customerAge = Integer.parseInt(scanner.nextLine());
                if (customerAge > 0) return customerAge;
                System.out.println("Age must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Must be a number.");
            }
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return -1;

    }

    /**
     * Validates customer type selection input from the user.
     * 
     * <p>Displays customer type options and validates the selection:
     * <ul>
     *   <li>"1" for Regular Customer</li>
     *   <li>"2" for Premium Customer</li>
     * </ul>
     * </p>
     * 
     * <p>Allows up to 3 attempts. If all attempts fail, returns null and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated selection ("1" or "2"), or {@code null} if validation fails after max retries
     */
    public static String validateCustomerTypeInput(Scanner scanner) {
        System.out.println("1. Regular Customer\n2. Premium Customer");
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Select type (1-2): ");
            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2")) return input;
            System.out.println("Invalid selection. Choose 1 or 2.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    /**
     * Validates account type selection input from the user.
     * 
     * <p>Displays account type options and validates the selection:
     * <ul>
     *   <li>"1" for Savings Account</li>
     *   <li>"2" for Checking Account</li>
     * </ul>
     * </p>
     * 
     * <p>Allows up to 3 attempts. If all attempts fail, returns null and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated selection ("1" or "2"), or {@code null} if validation fails after max retries
     */
    public static String validateAccountTypeInput(Scanner scanner) {
        System.out.println("1. Savings Account\n2. Checking Account");
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Select type (1-2): ");
            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2")) return input;
            System.out.println("Invalid selection. Choose 1 or 2.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    /**
     * Validates transaction type selection input from the user.
     * 
     * <p>Displays transaction type options and validates the selection:
     * <ul>
     *   <li>"1" for Deposit</li>
     *   <li>"2" for Withdrawal</li>
     * </ul>
     * </p>
     * 
     * <p>Allows up to 3 attempts. If all attempts fail, returns null and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated selection ("1" or "2"), or {@code null} if validation fails after max retries
     */
    public static String validateTransactionTypeInput(Scanner scanner) {
        System.out.println("1. Deposit\n2. Withdrawal");
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Select type (1-2): ");
            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2")) return input;
            System.out.println("Invalid selection. Choose 1 or 2.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    /**
     * Validates initial deposit amount input with account and customer type requirements.
     * 
     * <p>Validates that the deposit amount:
     * <ul>
     *   <li>Is a valid number (double)</li>
     *   <li>Is not negative</li>
     *   <li>Meets minimum requirements based on customer type:
     *       <ul>
     *         <li>Premium customers (customerType "2"): Minimum $10,000</li>
     *       </ul>
     *   </li>
     *   <li>Meets minimum requirements based on account type:
     *       <ul>
     *         <li>Savings accounts (accountType "1"): Minimum $500</li>
     *       </ul>
     *   </li>
     * </ul>
     * </p>
     * 
     * <p>Handles NumberFormatException for non-numeric input gracefully.
     * Allows up to 3 attempts. If all attempts fail, returns -1 and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @param customerType The customer type selection ("1" for Regular, "2" for Premium)
     * @param accountType The account type selection ("1" for Savings, "2" for Checking)
     * @return The validated deposit amount as a double, or {@code -1} if validation fails after max retries
     */
    public static double validateInitialDepositInput(Scanner scanner, String customerType, String accountType) {
        final int premiumDeposit = 10000 ;
        final int savingsDepost = 500;
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter initial deposit amount: ");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Deposit cannot be negative.");
                } else if (customerType.equals("2") && amount < premiumDeposit) {
                    System.out.println("Premium customers require a minimum deposit of $10,000.");
                } else if (accountType.equals("1") && amount < savingsDepost) {
                    System.out.println("Savings account requires a minimum deposit of $500.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Must be a number.");
            }
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return -1;
    }

    /**
     * Validates transaction amount input from the user.
     * 
     * <p>Validates that the transaction amount:
     * <ul>
     *   <li>Is a valid number (double)</li>
     *   <li>Is not negative</li>
     * </ul>
     * </p>
     * 
     * <p>Note: The error message says "Deposit cannot be negative" but this method
     * is used for both deposits and withdrawals. The validation logic is correct
     * (rejects negative amounts), but the message could be more generic.</p>
     * 
     * <p>Handles NumberFormatException for non-numeric input gracefully.
     * Allows up to 3 attempts. If all attempts fail, returns -1 and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated transaction amount as a double, or {@code -1} if validation fails after max retries
     */
    public static  double validateTransactionAmount(Scanner scanner){
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter amount: ");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Deposit cannot be negative.");
               } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Must be a number.");
            }
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return -1;
    }
    
    /**
     * Validates customer contact (phone number) input from the user.
     * 
     * <p>Validates that the contact:
     * <ul>
     *   <li>Is not blank or empty</li>
     *   <li>Contains only numeric digits (0-9)</li>
     *   <li>Is exactly 10 digits long</li>
     * </ul>
     * </p>
     * 
     * <p>Allows up to 3 attempts. If all attempts fail, returns null and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated contact number (10 digits), or {@code null} if validation fails after max retries
     */
    public static String validateCustomerContactInput(Scanner scanner) {
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter customer contact: ");
            String contact = scanner.nextLine();
            if (!contact.isBlank() && contact.matches("^[0-9]+$") && contact.length() == 10) return contact;
            System.out.println("Invalid contact. Must contain only digits and be 10 digits long.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    /**
     * Validates customer address input from the user.
     * 
     * <p>Validates that the address:
     * <ul>
     *   <li>Is not blank or empty</li>
     * </ul>
     * </p>
     * 
     * <p>This is a minimal validation - it only checks that the address is not empty.
     * No format validation is performed on the address content.</p>
     * 
     * <p>Allows up to 3 attempts. If all attempts fail, returns null and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated address string, or {@code null} if validation fails after max retries
     */
    public static String validateCustomerAddressInput(Scanner scanner) {
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter customer address: ");
            String address = scanner.nextLine();
            if (!address.isBlank()) return address;
            System.out.println("Invalid address. Cannot be empty.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    /**
     * Validates transaction confirmation input from the user.
     * 
     * <p>Prompts the user to confirm a transaction and validates the response:
     * <ul>
     *   <li>"Y" or "y" for Yes (confirmation)</li>
     *   <li>"N" or "n" for No (cancellation)</li>
     * </ul>
     * </p>
     * 
     * <p>Note: This method allows only 2 retry attempts instead of the standard 3,
     * as confirmation is a simpler binary choice.</p>
     * 
     * <p>If all attempts fail, returns null and the calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated confirmation ("Y", "y", "N", or "n"), or {@code null} if validation fails after max retries
     */
    public static String validateTransactionConfirmation(Scanner scanner){
        
        for (int i = 0; i < 2; i++) {
            System.out.print("Confirm transaction? (Y/N): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) return input;
            System.out.println("Invalid selection. Choose Y or N.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    /**
     * Validates account number input from the user.
     * 
     * <p>Validates that the account number:
     * <ul>
     *   <li>Is not blank or empty</li>
     *   <li>Matches the format "ACC00" followed by one or more digits</li>
     *   <li>Case-insensitive matching (e.g., "acc001" is valid)</li>
     * </ul>
     * </p>
     * 
     * <p>Example valid account numbers: "ACC001", "ACC0023", "acc001"</p>
     * 
     * <p>Allows up to 3 attempts. If all attempts fail, returns null and the
     * calling code should abort the operation.</p>
     * 
     * @param scanner The Scanner instance to read input from
     * @return The validated account number, or {@code null} if validation fails after max retries
     */
    public static String validateAccountNumberInput(Scanner scanner){
        String accountNumber;
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter Account Number: ");
            accountNumber = scanner.nextLine();
            if(accountNumber.isBlank() || !accountNumber.matches("(?i)^ACC00\\d{1,}$")) {
                System.out.println("Invalid Account Number provided. eg: ACC004");
            }else return accountNumber;
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    /**
     * Prompts the user to press Enter to continue.
     * 
     * <p>This utility method provides a consistent way to pause execution
     * and wait for user acknowledgment before proceeding. Useful for allowing
     * users to read displayed information before returning to menus.</p>
     * 
     * <p>The method reads the next line from the scanner, effectively waiting
     * for the user to press Enter. The actual input is discarded.</p>
     * 
     * @param scanner The Scanner instance to read input from
     */
    public static void promptEnterKey(Scanner scanner){
        System.out.print("Press Enter to continue....");
        scanner.nextLine();
    }

}