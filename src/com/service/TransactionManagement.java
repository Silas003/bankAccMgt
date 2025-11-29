package com.service;
import java.util.ArrayList;
import com.models.Transaction;

/**
 * Management layer for transaction data operations and storage.
 * 
 * <p>This class provides methods for managing the collection of transactions
 * in the system. It maintains an array-based storage structure with a maximum
 * capacity of 200 transactions and provides operations for adding transactions
 * and retrieving transaction history by account.</p>
 * 
 * <p>Key responsibilities:
 * <ul>
 *   <li>Transaction storage and retrieval</li>
 *   <li>Transaction lookup by account number</li>
 *   <li>Transaction history aggregation</li>
 * </ul>
 * </p>
 * 
 * <p>Note: This implementation uses a fixed-size array. In a production environment,
 * consider using a more scalable data structure such as a HashMap indexed by
 * account number or a database for better performance and capacity management.</p>
 * 
 * <p>Note: The methods calculateTotalDeposits and calculateTotalWithdrawals are
 * currently empty placeholders and should be implemented if needed.</p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see Transaction
 */
public class TransactionManagement {    
    /** Fixed-size array to store transactions (maximum 200 transactions) */
    public Transaction transactions[] = new Transaction[200];
    
    /** Current number of transactions in the system */
    public int transactionCount;

    /**
     * Adds a new transaction to the system.
     * 
     * <p>Stores the transaction in the next available position in the array
     * and increments the transaction counter. No validation is performed to
     * check if the array is full.</p>
     * 
     * <p>Warning: This method does not check for array bounds. If transactionCount
     * exceeds the array size, an ArrayIndexOutOfBoundsException will occur.</p>
     * 
     * @param transaction The transaction object to add to the system
     */
    public void addTransaction(Transaction transaction){
        transactions[transactionCount]=transaction;
        transactionCount++;
    }

    /**
     * Retrieves all transactions associated with a specific account.
     * 
     * <p>Searches through all transactions in the system and returns those
     * that match the provided account number. The search is case-sensitive
     * and requires an exact match.</p>
     * 
     * @param accountNumber The account number to search for (e.g., "ACC001")
     * @return ArrayList containing all transactions for the specified account.
     *         Returns an empty list if no transactions are found for the account.
     */
    public ArrayList<Transaction> viewTransactionByAccount(String accountNumber) {
        ArrayList<Transaction> accountTransactions = new ArrayList<>();
        Transaction trnx;
        for (int i = 0 ; i < transactionCount ; i++){
            trnx = transactions[i];
            if (trnx.getAccountNumber().equals(accountNumber)){
               accountTransactions.add(trnx);
            }
        }
        return accountTransactions;
    }

    /**
     * Placeholder method for calculating total deposits for an account.
     * 
     * <p>This method is currently not implemented. If needed, it should iterate
     * through transactions for the specified account and sum all deposit amounts.</p>
     * 
     * @param accountNumber The account number to calculate deposits for
     */
    public void calculateTotalDeposits(String accountNumber) {
    }

    /**
     * Placeholder method for calculating total withdrawals for an account.
     * 
     * <p>This method is currently not implemented. If needed, it should iterate
     * through transactions for the specified account and sum all withdrawal amounts.</p>
     * 
     * @param accountNumber The account number to calculate withdrawals for
     */
    public void calculateTotalWithdrawals(String accountNumber) {
    }

    /**
     * Retrieves the current number of transactions in the system.
     * 
     * @return The total count of transactions that have been added to the system
     */
    public int getTransactionCount(){
        return this.transactionCount;
    }
}