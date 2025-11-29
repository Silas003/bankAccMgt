package com.models;

/**
 * Abstract base class representing a bank account in the system.
 * 
 * <p>This class provides the core functionality and structure for all account types
 * (Savings and Checking). It implements the Transactable interface and defines common
 * account properties such as account number, customer association, balance, and status.</p>
 * 
 * <p>Account numbers are automatically generated in the format "ACC00" followed by
 * a sequential counter. Each account must be associated with a Customer and maintains
 * its own balance and status.</p>
 * 
 * <p>Subclasses must implement:
 * <ul>
 *   <li>{@link #displayAccountDetails()} - Format account information for display</li>
 *   <li>{@link #getAccountType()} - Return the account type name</li>
 *   <li>{@link #getAccountSpecificDetails()} - Return account-specific attributes</li>
 *   <li>{@link #withdraw(double)} - Implement withdrawal logic with account-specific rules</li>
 * </ul>
 * </p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see SavingsAccount
 * @see CheckingAccount
 * @see Customer
 */
public abstract class Account implements Transactable{
    /** Unique identifier for the account in format "ACC00" + counter */
    private String accountNumber;
    
    /** The customer who owns this account */
    private Customer customer;
    
    /** Current account balance in dollars */
    private double balance;
    
    /** Account status (e.g., "active", "closed", "suspended") */
    private String status;

    /** Static counter for generating unique account numbers */
    public static int accountCounter;
    
    /**
     * Default constructor that initializes the account and generates a unique account number.
     * 
     * <p>Increments the static account counter to ensure each account receives
     * a unique identifier.</p>
     */
    Account(){
        setAccountNumber();
        accountCounter++;
    }
    
    /**
     * Returns a formatted string containing all account details for display.
     * 
     * <p>This method should provide a comprehensive view of the account including
     * customer information, account type, balance, and account-specific details.</p>
     * 
     * @return Formatted string containing complete account information
     */
    public abstract String displayAccountDetails();
    
    /**
     * Returns the type of account (e.g., "Savings", "Checking").
     * 
     * @return String representation of the account type
     */
    public abstract String getAccountType();
    
    /**
     * Returns account-specific details that vary by account type.
     * 
     * <p>For Savings accounts, this might include interest rate and minimum balance.
     * For Checking accounts, this might include overdraft limit and monthly fees.</p>
     * 
     * @return Formatted string containing account-specific attributes
     */
    public abstract String getAccountSpecificDetails();
    
    /**
     * Generates a unique account number based on the current account counter.
     * 
     * <p>Format: "ACC00" followed by the account counter value.
     * This ensures sequential, unique account numbers.</p>
     */
    private void generateAccountNumber(){
        this.accountNumber = "ACC00" + accountCounter;
    }
    
    /**
     * Retrieves the account number.
     * 
     * @return The account number as a String
     */
    public String getAccountNumber(){
        return this.accountNumber;
    }

    /**
     * Sets the account number by generating a new one.
     * 
     * <p>This method is called during account initialization to assign
     * a unique identifier to the account.</p>
     */
    public void setAccountNumber(){
        generateAccountNumber();
    }
    
    /**
     * Returns a string representation of the customer associated with this account.
     * 
     * @return Customer information as a formatted string
     */
    public String getCustomer(){
        return this.customer.toString();
    }
    
    /**
     * Gets the current account counter value.
     * 
     * @return The total number of accounts created (including this one)
     */
    public int getAccountCounter(){
        return accountCounter;
    }
    
    /**
     * Associates a customer with this account.
     * 
     * @param Customer The customer object to associate with this account
     */
    public void setCustomer(Customer Customer){
        this.customer = Customer;

    }

    /**
     * Retrieves the current account balance.
     * 
     * @return The account balance as a double value
     */
    public double getBalance(){
        return this.balance;
    }
    
    /**
     * Sets the account balance to the specified value.
     * 
     * <p>Note: This method does not validate the balance value. Subclasses
     * should implement appropriate validation in their withdrawal methods.</p>
     * 
     * @param balance The new balance value
     */
    public void setBalance(double balance){
        this.balance = balance;
    }

    /**
     * Retrieves the current account status.
     * 
     * @return The account status as a String
     */
    public String getStatus(){
        return this.status;
    }
    
    /**
     * Sets the account status (e.g., "active", "closed", "suspended").
     * 
     * @param status The new status value
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Processes a deposit transaction.
     * 
     * <p>Validates that the deposit amount is positive before adding it to
     * the account balance. Negative or zero amounts are rejected.</p>
     * 
     * @param amount The amount to deposit. Must be greater than zero.
     * @return {@code true} if the deposit was successful, {@code false} if the amount is invalid
     */
    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        setBalance(getBalance() + amount);
        return true;
    }

    /**
     * Abstract method for processing withdrawal transactions.
     * 
     * <p>Subclasses must implement account-specific withdrawal logic, including
     * validation of minimum balance requirements, overdraft limits, and other
     * account-specific constraints.</p>
     * 
     * @param amount The amount to withdraw. Must be positive.
     */
    public abstract void withdraw(double amount);

    /**
     * Returns a string representation of the account.
     * 
     * <p>Includes customer name, account type, and current balance.</p>
     * 
     * @return Formatted string representation of the account
     */
    @Override
    public String toString(){
        return String.format("Customer: %s\nAccount Type:%s\nCurrent Balance: %.2f",getCustomer(),getAccountType(),getBalance());
    }
  
    /**
     * Processes a transaction based on the specified type.
     * 
     * <p>Routes the transaction to the appropriate method (deposit or withdraw)
     * based on the transaction type. This is the implementation of the Transactable
     * interface contract.</p>
     * 
     * <p>Note: The base implementation calls withdraw() without checking return value.
     * Subclasses may override this method to provide more sophisticated transaction
     * processing with proper validation.</p>
     * 
     * @param amount The transaction amount. Must be positive.
     * @param type The transaction type ("Deposit" or "Withdrawal"), case-insensitive
     * @return {@code true} if the transaction was processed, {@code false} otherwise
     */
    public boolean processTransactions(double amount, String type) {
        if (type.equalsIgnoreCase("Deposit")) {
            return deposit(amount);
        } else if (type.equalsIgnoreCase("Withdrawal")) {
            withdraw(amount);
        }
        return true;
    }
}




