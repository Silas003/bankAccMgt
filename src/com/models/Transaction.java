package com.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a financial transaction in the bank account management system.
 * 
 * <p>This class encapsulates all information related to a single transaction,
 * including the transaction type (deposit or withdrawal), amount, account involved,
 * resulting balance, and timestamp. Transaction IDs are automatically generated
 * in the format "TNX00" followed by a sequential counter.</p>
 * 
 * <p>Transaction information includes:
 * <ul>
 *   <li>Unique transaction ID</li>
 *   <li>Transaction type (Deposit or Withdrawal)</li>
 *   <li>Account number involved</li>
 *   <li>Transaction amount</li>
 *   <li>Account balance after the transaction</li>
 *   <li>Timestamp of when the transaction occurred</li>
 * </ul>
 * </p>
 * 
 * <p>Transactions are immutable once created and serve as a permanent record
 * of all account activity for auditing and reporting purposes.</p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 */
public class Transaction{
        /** Static counter for generating unique transaction IDs */
        static int transactionCounter;
        
        /** Unique identifier for this transaction in format "TNX00" + counter */
        private String transactionId;
        
        /** Transaction type: "Deposit" or "Withdrawal" */
        private String type;
        
        /** Account number associated with this transaction */
        private String accountNumber;
        
        /** Transaction amount in dollars */
        private double amount;
        
        /** Account balance after this transaction was processed */
        private double balanceAfter;
        
        /** Timestamp when the transaction occurred (formatted as string) */
        private String timeStamp;

        /**
         * Returns a string representation of the transaction.
         * 
         * <p>Currently returns only the account number. This can be extended
         * to provide more detailed transaction information.</p>
         * 
         * @return Account number associated with this transaction
         */
        @Override
        public String toString(){
            return String.format("%s",getAccountNumber());
        }
        
        /**
         * Retrieves the unique transaction identifier.
         * 
         * @return The transaction ID as a String
         */
        public String getTransactionId(){
            return transactionId;
        }
        
        /**
         * Generates a unique transaction ID based on the current transaction counter.
         * 
         * <p>Format: "TNX00" followed by the transaction counter value.
         * This ensures sequential, unique transaction IDs.</p>
         */
        private void generateUniqueId(){
            this.transactionId = "TNX00" + transactionCounter;
        }
        
        /**
         * Retrieves the transaction type.
         * 
         * <p>Note: There is a typo in the method name (TranssactionType instead of TransactionType).
         * Consider renaming in future versions for consistency.</p>
         * 
         * @return The transaction type ("Deposit" or "Withdrawal")
         */
        public String getTranssactionType(){
            return type;
        }

        /**
         * Retrieves the account number associated with this transaction.
         * 
         * @return The account number as a String
         */
        public String getAccountNumber(){
            return this.accountNumber;
        }
        
        /**
         * Retrieves the transaction amount.
         * 
         * @return The transaction amount as a double
         */
        public double getAmount(){
            return  this.amount;
        }

        /**
         * Retrieves the account balance after this transaction was processed.
         * 
         * <p>This represents the account's balance immediately after the transaction
         * was completed, providing a snapshot of the account state at that moment.</p>
         * 
         * @return The balance after transaction as a double
         */
        public double getBalanceAfter(){
            return this.balanceAfter;
        }

        /**
         * Retrieves the transaction type.
         * 
         * @return The transaction type ("Deposit" or "Withdrawal")
         */
        public String getType(){
            return this.type;
        }

        /**
         * Retrieves the timestamp when the transaction occurred.
         * 
         * @return The timestamp as a formatted string
         */
        public String getTimeStamp(){
            return this.timeStamp;
        }
        
        /**
         * Sets the account number associated with this transaction.
         * 
         * @param accountNumber The account number to associate with this transaction
         */
        private void setAccountNumber(String accountNumber){
            this.accountNumber = accountNumber;
        }

        /**
         * Sets the transaction amount.
         * 
         * @param amount The transaction amount. Should be positive.
         */
        private void setAmount(double amount){
            this.amount = amount;
        }

        /**
         * Sets the account balance after the transaction.
         * 
         * @param balanceAfter The account balance after this transaction was processed
         */
        private void setBalanceAfter(double balanceAfter){
            this.balanceAfter = balanceAfter;
        }

        /**
         * Sets the transaction type.
         * 
         * @param type The transaction type ("Deposit" or "Withdrawal")
         */
        private void setType(String type){
            this.type = type;
        }
        
        /**
         * Default constructor that initializes a transaction and generates a unique ID.
         * 
         * <p>Increments the static transaction counter to ensure each transaction
         * receives a unique identifier.</p>
         */
        Transaction(){
            generateUniqueId();
            transactionCounter++;
        }
        
        /**
         * Sets the timestamp for when the transaction occurred.
         * 
         * @param dateTime The timestamp as a formatted string
         */
        private void setTimeStamp(String dateTime){
            this.timeStamp = dateTime;
        }
        
        /**
         * Constructs a transaction with all required information.
         * 
         * <p>Creates a new transaction record with the provided details. The transaction
         * ID is automatically generated, and the transaction counter is incremented.</p>
         * 
         * @param accountNumber The account number involved in the transaction
         * @param type The transaction type ("Deposit" or "Withdrawal")
         * @param amount The transaction amount. Must be positive.
         * @param balanceAfter The account balance after this transaction
         * @param dateTime The timestamp when the transaction occurred (formatted string)
         */
        public Transaction(String accountNumber,String type,double amount,double balanceAfter,String dateTime){
            this();
            setAccountNumber(accountNumber);
            setAmount(amount);
            setType(type);
            setBalanceAfter(balanceAfter);
            setTimeStamp(dateTime);
        }

        /**
         * Returns this transaction object for display purposes.
         * 
         * <p>This method allows the transaction to be used directly in display contexts
         * where the transaction details need to be shown.</p>
         * 
         * @return This transaction instance
         */
        public Transaction displayTransactionDetails(){
            return this;
        }
    }


