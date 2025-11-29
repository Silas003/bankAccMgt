package com.service;
import com.models.*;
import com.utilities.CustomUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Service layer for transaction-related business operations and user interactions.
 * 
 * <p>This class provides high-level transaction management functionality, coordinating
 * between the user interface (via Scanner) and the data management layers (AccountManagement
 * and TransactionManagement). It handles transaction processing and transaction history viewing.</p>
 * 
 * <p>Key responsibilities:
 * <ul>
 *   <li>Transaction processing (deposits and withdrawals)</li>
 *   <li>Transaction validation and confirmation</li>
 *   <li>Transaction history retrieval and display</li>
 *   <li>Transaction summary generation</li>
 * </ul>
 * </p>
 * 
 * <p>The service validates account existence, transaction amounts, and obtains user
 * confirmation before processing transactions. All transactions are recorded with
 * timestamps for audit purposes.</p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see AccountManagement
 * @see TransactionManagement
 * @see CustomUtils
 */
public class TransactionServices {
    
    /** Data management layer for account operations */
    private final AccountManagement accountManagement;
    
    /** Data management layer for transaction operations */
    private final TransactionManagement transactionManagement;
    
    /** Scanner instance for reading user input */
    private final Scanner scanner;
    
    /** Map of user input choices to transaction type strings */
    private final HashMap<String, String> transactionType = new HashMap<>();

    /**
     * Constructs a new TransactionServices with the provided dependencies.
     * 
     * <p>Initializes the transaction type mapping for user input conversion:
     * <ul>
     *   <li>"1" maps to "Deposit"</li>
     *   <li>"2" maps to "Withdrawal"</li>
     * </ul>
     * </p>
     * 
     * @param accountManagement The account management layer for account operations
     * @param transactionManagement The transaction management layer for transaction operations
     * @param scanner The Scanner instance for reading user input
     */
    public TransactionServices(AccountManagement accountManagement, TransactionManagement transactionManagement, Scanner scanner) {
        this.accountManagement = accountManagement;
        this.transactionManagement = transactionManagement;
        this.scanner = scanner;
        transactionType.put("1", "Deposit");
        transactionType.put("2", "Withdrawal");
    }

    /**
     * Processes a financial transaction (deposit or withdrawal) for an account.
     * 
     * <p>This method orchestrates the complete transaction workflow:
     * <ol>
     *   <li>Validates and retrieves the account number</li>
     *   <li>Verifies the account exists in the system</li>
     *   <li>Prompts for transaction type (Deposit or Withdrawal)</li>
     *   <li>Validates the transaction amount</li>
     *   <li>Calculates and displays the projected new balance</li>
     *   <li>Obtains user confirmation</li>
     *   <li>Processes the transaction through the account's processTransactions method</li>
     *   <li>Records the transaction if successful</li>
     * </ol>
     * </p>
     * 
     * <p>The transaction is validated by the account object based on account-specific
     * rules (minimum balance, overdraft limits, etc.). If validation fails, the
     * transaction is not processed and an error message is displayed.</p>
     * 
     * <p>All successful transactions are recorded with:
     * <ul>
     *   <li>Account number</li>
     *   <li>Transaction type</li>
     *   <li>Transaction amount</li>
     *   <li>Balance after transaction</li>
     *   <li>Timestamp</li>
     * </ul>
     * </p>
     */
    public void processTransaction() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("PROCESS TRANSACTION");
        System.out.println("===================");

        String accountNumber = CustomUtils.validateAccountNumberInput(scanner);
        if (accountNumber == null) return;
        Account userAccount = accountManagement.findAccount(accountNumber.toUpperCase());
        if(userAccount == null){
            System.out.println("Acccont not found.Returning to main menu");
            return;
        }


        String transactionTypeInput = CustomUtils.validateTransactionTypeInput(scanner);
        if (transactionTypeInput == null) return;

        double amount = CustomUtils.validateTransactionAmount(scanner);
        if (amount == -1) return;

        double newBalance = transactionTypeInput.equals("1") ? userAccount.getBalance() + amount : userAccount.getBalance() - amount;
        String dateTime = LocalDateTime.now().format(formatter);

        printTransactionSummary(userAccount, amount, transactionType.get(transactionTypeInput), newBalance, dateTime);

        String confirmation = CustomUtils.validateTransactionConfirmation(scanner);
        if (confirmation == null || confirmation.equalsIgnoreCase("N")) return;

        boolean success = userAccount.processTransactions(amount, transactionType.get(transactionTypeInput));
        if (success) {
            transactionManagement.addTransaction(new Transaction(userAccount.getAccountNumber(),
                    transactionType.get(transactionTypeInput),
                    amount,
                    userAccount.getBalance(),
                    dateTime));
            System.out.println("Transaction successful!");
        } else {
            System.out.println("Transaction failed! Check balance or account rules.");
        }

        CustomUtils.promptEnterKey(scanner);
    }

    /**
     * Displays the complete transaction history for a specific account.
     * 
     * <p>This method provides a comprehensive view of all transactions for an account:
     * <ol>
     *   <li>Validates and retrieves the account number</li>
     *   <li>Verifies the account exists in the system</li>
     *   <li>Retrieves all transactions for the account</li>
     *   <li>Displays account summary information</li>
     *   <li>Lists all transactions in a formatted table</li>
     *   <li>Calculates and displays transaction statistics</li>
     * </ol>
     * </p>
     * 
     * <p>The transaction history includes:
     * <ul>
     *   <li>Transaction ID</li>
     *   <li>Date and time of transaction</li>
     *   <li>Transaction type (Deposit or Withdrawal)</li>
     *   <li>Transaction amount (with + or - prefix)</li>
     *   <li>Account balance after the transaction</li>
     * </ul>
     * </p>
     * 
     * <p>Summary statistics displayed:
     * <ul>
     *   <li>Total number of transactions</li>
     *   <li>Total deposits amount</li>
     *   <li>Total withdrawals amount</li>
     *   <li>Net change (deposits - withdrawals)</li>
     * </ul>
     * </p>
     */
    public void viewTransactionHistory() {
        System.out.println("VIEW TRANSACTION HISTORY");
        System.out.println("========================");
        String accountNumber = CustomUtils.validateAccountNumberInput(scanner);
        if (accountNumber == null) return;
        if(accountManagement.findAccount(accountNumber.toUpperCase()) == null){
            System.out.println("Account not found.Returning to main menu");
            return;
        }
        Account account = accountManagement.findAccount(accountNumber.toUpperCase());
        

        ArrayList<Transaction> tnx = transactionManagement.viewTransactionByAccount(account.getAccountNumber());
        double totalDeposits = 0;
        double totalWithdrawals = 0;

        System.out.printf("Account: %s - %s\nAccount Type: %s\nCurrent Balance: %.2f\n\n",
                account.getAccountNumber(), account.getCustomer(), account.getAccountType(), account.getBalance());
        System.out.println("TRANSACTION HISTORY");
        System.out.println("=====================================================================");
        System.out.println("TXN ID | DATE/TIME          | TYPE    | AMOUNT    | BALANCE");

        for (Transaction tr : tnx) {
            if (tr.getType().equals("Deposit")) totalDeposits += tr.getAmount();
            else if (tr.getType().equals("Withdrawal")) totalWithdrawals += tr.getAmount();

            System.out.printf("%s |%s |%s  |%s$%.2f  |$%.2f\n",
                    tr.getTransactionId(), tr.getTimeStamp(), tr.getType(),
                    tr.getType().equals("Deposit") ? "+" : "-", tr.getAmount(), tr.getBalanceAfter());
        }

        System.out.println("=====================================================================\n");
        System.out.println("Total Transactions: " + tnx.size());
        System.out.println("Total Deposits: " + totalDeposits);
        System.out.println("Total Withdrawals: " + totalWithdrawals);
        System.out.println("Net Change: " + (totalDeposits - totalWithdrawals));

        CustomUtils.promptEnterKey(scanner);
    }

    /**
     * Displays a summary of the transaction before confirmation.
     * 
     * <p>Shows the user all relevant transaction details so they can review
     * and confirm before the transaction is processed. This helps prevent
     * accidental transactions and provides transparency.</p>
     * 
     * <p>Displays:
     * <ul>
     *   <li>Transaction ID (next available ID)</li>
     *   <li>Account number</li>
     *   <li>Transaction type</li>
     *   <li>Transaction amount</li>
     *   <li>Previous balance</li>
     *   <li>Projected new balance</li>
     *   <li>Date and time</li>
     * </ul>
     * </p>
     * 
     * @param account The account involved in the transaction
     * @param amount The transaction amount
     * @param type The transaction type ("Deposit" or "Withdrawal")
     * @param newBalance The projected balance after the transaction
     * @param dateTime The timestamp for the transaction
     */
    public void printTransactionSummary(Account account, double amount, String type, double newBalance, String dateTime) {
        System.out.println("TRANSACTION CONFIRMATION");
        System.out.println("========================");
        System.out.printf("Transaction ID: TNX00%d\n", transactionManagement.getTransactionCount());
        System.out.printf("Account: %s\n", account.getAccountNumber());
        System.out.printf("Type: %s\n", type);
        System.out.printf("Amount: $%.2f\n", amount);
        System.out.printf("Previous Balance: $%.2f\n", account.getBalance());
        System.out.printf("New Balance: $%.2f\n", newBalance);
        System.out.printf("Date/Time: %s\n", dateTime);
    }
}