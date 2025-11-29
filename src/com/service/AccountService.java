package com.service;
import com.utilities.CustomUtils;
import java.util.Scanner;
import com.models.*;

/**
 * Service layer for account-related business operations and user interactions.
 * 
 * <p>This class provides high-level account management functionality, coordinating
 * between the user interface (via Scanner) and the data management layer (AccountManagement).
 * It handles the complete account creation workflow and account listing operations.</p>
 * 
 * <p>Key responsibilities:
 * <ul>
 *   <li>Account creation with customer information collection</li>
 *   <li>Account listing and display</li>
 *   <li>User input validation coordination</li>
 *   <li>Customer and account type selection</li>
 * </ul>
 * </p>
 * 
 * <p>The service validates all user inputs through utility methods before proceeding
 * with account creation, ensuring data integrity and providing user-friendly error messages.</p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see AccountManagement
 * @see CustomUtils
 */
public class AccountService {

    /** Data management layer for account operations */
    private final AccountManagement accountManagement;
    
    /** Scanner instance for reading user input */
    private final Scanner scanner;

    /**
     * Constructs a new AccountService with the provided dependencies.
     * 
     * @param accountManagement The account management layer for data operations
     * @param scanner The Scanner instance for reading user input
     */
    public AccountService(AccountManagement accountManagement, Scanner scanner) {
        this.accountManagement = accountManagement;
        this.scanner = scanner;
    }

    /**
     * Guides the user through the account creation process.
     * 
     * <p>This method orchestrates the complete account creation workflow:
     * <ol>
     *   <li>Collects and validates customer information (name, age, contact, address)</li>
     *   <li>Prompts for customer type selection (Regular or Premium)</li>
     *   <li>Prompts for account type selection (Savings or Checking)</li>
     *   <li>Validates initial deposit amount based on customer and account type requirements</li>
     *   <li>Creates the appropriate Customer and Account objects</li>
     *   <li>Adds the account to the system and displays confirmation</li>
     * </ol>
     * </p>
     * 
     * <p>If any validation step fails, the process is aborted and the user is returned
     * to the main menu. All validations are performed through CustomUtils methods
     * with retry logic and user-friendly error messages.</p>
     * 
     * <p>Minimum deposit requirements:
     * <ul>
     *   <li>Premium customers: $10,000</li>
     *   <li>Savings accounts: $500</li>
     * </ul>
     * </p>
     */
    public void createAccount(){

        String customerName,customerAddress,customerContact;
        int customerAge;

        System.out.println("ACCOUNT CREATION");
        System.out.println("====================================");
        customerName = CustomUtils.validateCustomerNameInput(scanner);
        if(customerName == null) return;
        customerAge = CustomUtils.validateCustomerAgeInput(scanner);
        if(customerAge == -1) return;
        customerContact = CustomUtils.validateCustomerContactInput(scanner);
        if(customerContact == null) return;
        customerAddress = CustomUtils.validateCustomerAddressInput(scanner);
        if(customerAddress == null) return;
        String customerTypeInput = CustomUtils.validateCustomerTypeInput(scanner);
        if(customerTypeInput == null) return;
        String  accounTypeInput = CustomUtils.validateAccountTypeInput(scanner);
        if(accounTypeInput == null) return;
 
        double initialDepositAmount = CustomUtils.validateInitialDepositInput(scanner,customerTypeInput,accounTypeInput);
        // Determine customer type
        Customer customer;
        switch (customerTypeInput) {
        case "1":
            customer = new RegularCustomer(customerName, customerAge, customerContact, customerAddress);
            break;
        case "2":
            customer = new PremiumCustomer(customerName, customerAge, customerContact, customerAddress);
            break;
        default:
            System.out.println("Invalid customer type. Account creation aborted.");
            return;
        }

        // Determine account type
        Account newAccount;
        switch (accounTypeInput) {
            case "1":
                newAccount = new SavingsAccount(customer, initialDepositAmount);
                break;
            case "2":
                newAccount = new CheckingAccount(customer, initialDepositAmount);
                break;
            default:
                System.out.println("Invalid account type. Please select 1 or 2.");
                return;
        }

    // Add account and display confirmation
    accountManagement.addAccount(newAccount);
    System.out.println("Account created successfully!");
    System.out.println(newAccount);


        CustomUtils.promptEnterKey(scanner);
    }
    
    /**
     * Displays a formatted listing of all accounts in the system.
     * 
     * <p>Retrieves all accounts from the management layer and displays them in a
     * tabular format. The display includes:
     * <ul>
     *   <li>Account number</li>
     *   <li>Customer name</li>
     *   <li>Account type (Savings or Checking)</li>
     *   <li>Current balance</li>
     *   <li>Account status</li>
     *   <li>Account-specific details (overdraft limit, interest rate, etc.)</li>
     * </ul>
     * </p>
     * 
     * <p>Also displays summary information:
     * <ul>
     *   <li>Total number of accounts in the system</li>
     *   <li>Total balance across all accounts</li>
     * </ul>
     * </p>
     * 
     * <p>After displaying the information, waits for user to press Enter before
     * returning to the main menu.</p>
     */
    public void viewAllAccounts() {
        System.out.println("ACCOUNT LISTING");
        System.out.println("====================================================");
        System.out.println("ACC NO | CUSTOMER NAME | TYPE | BALANCE | STATUS");
        System.out.println("====================================================");

        Account[] allAccounts = accountManagement.viewAllAccounts();
        for (int i = 0; i < accountManagement.accountCount; i++) {
            Account account = allAccounts[i];
            System.out.printf("%s | %s | %s | $%.2f | %s | %s\n",
                    account.getAccountNumber(),
                    account.getCustomer(),
                    account.getAccountType(),
                    account.getBalance(),
                    account.getStatus(),
                    account.getAccountSpecificDetails());
        }

        System.out.printf("Total Accounts: %d\nTotal Bank Balance: $%.2f\n",
accountManagement.getAccountCount(), accountManagement.getTotalBalance());
        CustomUtils.promptEnterKey(scanner);
    }

}