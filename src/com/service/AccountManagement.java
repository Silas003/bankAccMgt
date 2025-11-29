package com.service;
import java.util.ArrayList;
import java.util.Arrays;
import com.models.Account;

/**
 * Management layer for account data operations and storage.
 * 
 * <p>This class provides static methods for managing the collection of accounts
 * in the system. It maintains an array-based storage structure with a maximum
 * capacity of 50 accounts and provides operations for adding, finding, and
 * retrieving accounts.</p>
 * 
 * <p>Key responsibilities:
 * <ul>
 *   <li>Account storage and retrieval</li>
 *   <li>Account lookup by account number</li>
 *   <li>Account listing and aggregation</li>
 *   <li>Balance calculations across all accounts</li>
 * </ul>
 * </p>
 * 
 * <p>Note: This implementation uses a fixed-size array. In a production environment,
 * consider using a more scalable data structure such as a HashMap or database
 * for better performance and capacity management.</p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see Account
 */
public class AccountManagement {

        /** Fixed-size array to store accounts (maximum 50 accounts) */
        private static Account[] accounts = new Account[50];
        
        /** Current number of accounts in the system */
        public static int accountCount;

        /**
         * Adds a new account to the system.
         * 
         * <p>Validates that there is available capacity before adding the account.
         * If the account array is full, displays an error message and does not add
         * the account.</p>
         * 
         * @param account The account object to add to the system
         */
        public static void addAccount(Account account){

            if (accountCount < accounts.length) {
                accounts[accountCount++] = account;
            } else {
                System.out.println("Account list is full!");
            }

        }

        /**
         * Finds an account by its account number.
         * 
         * <p>Searches through all accounts in the system to find a match.
         * The search is case-sensitive and requires an exact match.</p>
         * 
         * @param accountNumber The account number to search for (e.g., "ACC001")
         * @return The Account object if found, {@code null} if no matching account exists
         */
        public static Account findAccount(String accountNumber){

            for(int i = 0; i < accountCount; i++){
                Account account = accounts[i];
                if(account.getAccountNumber().equals(accountNumber)) return account;
            }
            return null;

        }

        /**
         * Retrieves all accounts in the system.
         * 
         * <p>Returns the entire accounts array. Note that this includes null
         * entries beyond the accountCount index. Callers should use accountCount
         * to determine the valid range of accounts.</p>
         * 
         * @return Array containing all accounts (may include null entries)
         */
        public static Account[] viewAllAccounts(){
            return accounts;
        }

        /**
         * Calculates the total balance across all accounts in the system.
         * 
         * <p>Iterates through all active accounts and sums their balances to
         * provide a system-wide total balance figure.</p>
         * 
         * <p>Note: This method includes a debug print statement that should be
         * removed in production code.</p>
         * 
         * @return The sum of all account balances in the system
         */
        public double getTotalBalance(){
            double totalBalance = 0;
            Account account ;
            System.out.println(accountCount);
            for ( int i = 0 ; i < accountCount; i++){
                account = accounts[i];
                totalBalance += account.getBalance();
            }

            return  totalBalance;
        }

        /**
         * Retrieves the current number of accounts in the system.
         * 
         * @return The total count of accounts that have been added to the system
         */
        public static int getAccountCount(){
            return accountCount;
        }
}

