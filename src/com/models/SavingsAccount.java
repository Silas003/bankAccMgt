package com.models;

/**
 * Represents a savings account with interest accrual and minimum balance requirements.
 * 
 * <p>Savings accounts are designed for customers who want to earn interest on their deposits
 * while maintaining a minimum balance. These accounts have restrictions on withdrawals to
 * ensure the minimum balance requirement is maintained.</p>
 * 
 * <p>Key features:
 * <ul>
 *   <li>Annual interest rate of 3.5% (0.035)</li>
 *   <li>Minimum balance requirement of $500</li>
 *   <li>Withdrawals cannot reduce balance below $0</li>
 *   <li>Withdrawals must maintain the minimum balance requirement</li>
 * </ul>
 * </p>
 * 
 * <p>Withdrawal validation ensures that:
 * <ol>
 *   <li>The balance does not go below $0</li>
 *   <li>The balance after withdrawal meets the minimum balance requirement</li>
 * </ol>
 * </p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see Account
 */
public  class SavingsAccount extends Account{
        /** Annual interest rate as a decimal (0.035 = 3.5%) */
        private double interestRate;
        
        /** Minimum balance that must be maintained in the account */
        private  double minimumBalance;

        /**
         * Default constructor that initializes a savings account with default values.
         * 
         * <p>Sets interest rate to 3.5% (0.035) and minimum balance to $500.
         * Calls the parent Account constructor to generate account number.</p>
         */
        SavingsAccount(){
            super();
            this.interestRate = 0.035;
            this.minimumBalance = 500;

        }

        /**
         * Constructs a savings account with a customer and initial balance.
         * 
         * <p>Initializes the account with default interest rate and minimum balance,
         * then associates the customer and sets the initial balance and status.</p>
         * 
         * @param customer The customer who owns this savings account
         * @param balance The initial deposit amount (must meet minimum balance requirement)
         */
        public SavingsAccount(Customer customer,double balance){
            this();
            setCustomer(customer);
            setBalance(balance);
            setStatus("active");
        }
        
        /**
         * Returns a formatted string containing all account details.
         * 
         * @return Complete account information as a formatted string
         */
        @Override
        public String displayAccountDetails(){
           return this.toString();
        }

        /**
         * Returns the account type identifier.
         * 
         * @return "Savings" as the account type
         */
        @Override
        public String getAccountType() {
            return "Savings";
        }

        /**
         * Processes a withdrawal transaction with balance validation.
         * 
         * <p>Validates that the withdrawal will not result in a negative balance.
         * The minimum balance requirement is enforced in the processTransactions method,
         * not here, to allow for flexibility in different transaction scenarios.</p>
         * 
         * @param amount The amount to withdraw. Must be positive.
         */
        @Override
        public void  withdraw(double amount){
            double balance = getBalance();
            if(balance != 0 && (balance - amount < 0)){
                System.out.println("You cant withdraw below a balance of 0");
                return;
            }else{
                setBalance(balance - amount);
            }
        }

        /**
         * Calculates the interest that would be earned on the current balance.
         * 
         * <p>This is a simple interest calculation based on the current balance
         * and the account's interest rate. The result represents the interest
         * amount, not the new balance with interest applied.</p>
         * 
         * @return The interest amount calculated on the current balance
         */
        public double calculateInterest(){
            double balance = getBalance();

            return balance * this.interestRate;
        }
        
        /**
         * Returns the interest rate as a formatted percentage string.
         * 
         * @return Interest rate formatted as a percentage (e.g., "3.5%")
         */
        private String getInterestRate(){
            return String.format("%.1f%%",interestRate*100);
        }
        
        /**
         * Retrieves the minimum balance requirement for this savings account.
         * 
         * @return The minimum balance amount
         */
        public double getMinimumBalance(){
            return minimumBalance;
        }
        
        /**
         * Returns account-specific details including interest rate and minimum balance.
         * 
         * @return Formatted string containing interest rate and minimum balance information
         */
        @Override
        public String getAccountSpecificDetails(){
            return String.format("Interest Rate: %s Min Balance:$ %.2f",getInterestRate(),getMinimumBalance());
        }
        
        /**
         * Returns a comprehensive string representation of the savings account.
         * 
         * <p>Includes account number, customer information, account type, balance,
         * interest rate, minimum balance, and account status.</p>
         * 
         * @return Formatted string containing all savings account details
         */
        @Override
        public String toString(){
            return String.format("Account Number: %s\n" +
                    "Customer: %s\n" +
                    "Account Type: %s\n" +
                    "Initial Balance: %.2f\n" +
                    "Interest Rate: %.1f%%\n" +
                    "Minimum Balance: %s\n" +
                    "Status: %s",this.getAccountNumber(),this.getCustomer(),this.getAccountType(),this.getBalance(),(this.interestRate*100),this.minimumBalance,this.getStatus());
        }
        
        /**
         * Processes a transaction with minimum balance validation for savings accounts.
         * 
         * <p>Overrides the base implementation to enforce the minimum balance requirement
         * for withdrawal transactions. Deposits are processed normally, while withdrawals
         * are validated to ensure the resulting balance meets the minimum balance requirement.</p>
         * 
         * @param amount The transaction amount. Must be positive.
         * @param type The transaction type ("Deposit" or "Withdrawal"), case-insensitive
         * @return {@code true} if the transaction was successful, {@code false} if validation failed
         */
        public boolean processTransactions(double amount, String type) {
        if ("Deposit".equalsIgnoreCase(type)) {
            return deposit(amount);
        } else if ("Withdrawal".equalsIgnoreCase(type)) {
            if (getBalance() - amount >= getMinimumBalance()) {
                withdraw(amount);
                return true;
            } else return false;
        }
        return false;
    }
    }
