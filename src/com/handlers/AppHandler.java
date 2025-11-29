package com.handlers;
import com.service.*;
import java.util.Scanner;

/**
 * Application handler that manages the main menu and user interaction flow.
 * 
 * <p>This class serves as the primary controller for the Bank Account Management System,
 * coordinating between the account and transaction services to provide a unified
 * user interface. It maintains the main application loop and routes user selections
 * to the appropriate service methods.</p>
 * 
 * <p>The handler supports the following operations:
 * <ul>
 *   <li>Account creation</li>
 *   <li>Account viewing</li>
 *   <li>Transaction processing</li>
 *   <li>Transaction history viewing</li>
 * </ul>
 * </p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 */
public class AppHandler{
    /** Service for handling account-related operations */
    private final AccountService accountService;
    
    /** Service for handling transaction-related operations */
    private final TransactionServices transactionService;
    
    /** Management layer for account data operations */
    private final AccountManagement accountManagement = new AccountManagement();
    
    /** Management layer for transaction data operations */
    private final TransactionManagement transactionManagement = new TransactionManagement();
    
    /** Scanner instance for reading user input from console */
    private final Scanner scanner;

    /**
     * Constructs a new AppHandler with the provided Scanner.
     * 
     * <p>Initializes the account and transaction services with their respective
     * management dependencies and the shared scanner for user input.</p>
     * 
     * @param scanner The Scanner instance to use for reading user input
     */
    public AppHandler(Scanner scanner) {
        this.scanner = scanner;
        accountService = new AccountService(accountManagement, scanner);
        transactionService = new TransactionServices(accountManagement, transactionManagement, scanner);
    }

    /**
     * Starts the main application loop and displays the interactive menu.
     * 
     * <p>Continuously displays the main menu options and processes user selections
     * until the user chooses to exit. Each menu option delegates to the appropriate
     * service method for execution.</p>
     * 
     * <p>Menu options:
     * <ol>
     *   <li>Create Account - Opens account creation wizard</li>
     *   <li>View Accounts - Displays all accounts in the system</li>
     *   <li>Process Transaction - Handles deposits and withdrawals</li>
     *   <li>View Transaction History - Shows transaction history for an account</li>
     *   <li>Exit - Terminates the application</li>
     * </ol>
     * </p>
     */
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Create Account \n2. View Accounts \n3. Process Transaction \n4. View Transaction History \n5. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            running = switch (choice) {
                case "1" -> { accountService.createAccount(); yield true; }
                case "2" -> { accountService.viewAllAccounts(); yield true; }
                case "3" -> { transactionService.processTransaction(); yield true; }
                case "4" -> { transactionService.viewTransactionHistory(); yield true; }
                case "5" -> { System.out.println("Goodbye!"); yield false; }
                default -> { System.out.println("Please select a number between [1-5]"); yield true; }
            };
        }
    }

}