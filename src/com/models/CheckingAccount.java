package com.models;

/**
 * Represents a checking account with overdraft protection and monthly fees.
 * 
 * <p>Checking accounts allow customers to withdraw funds beyond their current balance
 * up to a specified overdraft limit. These accounts typically have monthly maintenance
 * fees, though fees may be waived for premium customers.</p>
 * 
 * <p>Key features:
 * <ul>
 *   <li>Overdraft limit of $1,000 (default)</li>
 *   <li>Monthly fee of $10 (may be waived for premium customers)</li>
 *   <li>Withdrawals can result in negative balances up to the overdraft limit</li>
 * </ul>
 * </p>
 * 
 * <p>Withdrawal validation ensures that the account balance after withdrawal does not
 * exceed the overdraft limit. The calculation checks if the absolute value of the
 * resulting balance exceeds the overdraft limit.</p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see Account
 * @see PremiumCustomer
 */
 public class CheckingAccount extends Account{
        /** Maximum amount the account can go into negative balance (overdraft) */
        private double overdraftLimit;
        
        /** Monthly maintenance fee charged to the account */
        private double monthlyFee;

        /**
         * Default constructor that initializes a checking account with default values.
         * 
         * <p>Sets overdraft limit to $1,000 and monthly fee to $10.
         * Calls the parent Account constructor to generate account number.</p>
         */
        CheckingAccount(){
            super();
            this.overdraftLimit = 1000;
            this.monthlyFee = 10;
        }

        /**
         * Constructs a checking account with a customer and initial balance.
         * 
         * <p>Initializes the account with default overdraft limit and monthly fee,
         * then associates the customer and sets the initial balance and status.</p>
         * 
         * @param customer The customer who owns this checking account
         * @param balance The initial deposit amount
         */
        public CheckingAccount(Customer customer,double balance){
            this();
            setCustomer(customer);
            setBalance(balance);
            setStatus("active");

        }

        /**
         * Returns account-specific details including overdraft limit and monthly fee.
         * 
         * @return Formatted string containing overdraft limit and monthly fee information
         */
        @Override
        public String getAccountSpecificDetails(){
            return String.format("Overdraft Limit: $%.2f MonthlyFee: $%.2f",getoverdraftLimit(),getMonthlyFee());
        }
        
        /**
         * Retrieves the overdraft limit for this checking account.
         * 
         * @return The overdraft limit amount
         */
        public double getoverdraftLimit(){
            return this.overdraftLimit;
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
         * @return "Checking" as the account type
         */
        @Override
        public String getAccountType() {
            return "Checking";
        }

        /**
         * Retrieves the monthly maintenance fee for this account.
         * 
         * @return The monthly fee amount
         */
        public double getMonthlyFee(){
            return this.monthlyFee;
        }
        
        /**
         * Applies the monthly fee to the account.
         * 
         * <p>Note: The current implementation sets the fee to -10, which appears
         * to be a placeholder. In production, this should deduct the fee from
         * the account balance.</p>
         */
        public void applyMonthlyFee(){
            this.monthlyFee = -10 ;
        }

        /**
         * Validates whether a withdrawal would exceed the overdraft limit.
         * 
         * <p>Checks if the absolute value of the balance after withdrawal would
         * exceed the overdraft limit. The overdraft limit represents the maximum
         * negative balance allowed.</p>
         * 
         * @param amount The withdrawal amount to validate
         * @return {@code true} if the withdrawal would exceed the overdraft limit, {@code false} otherwise
         */
        public boolean hasOverdraftlimitExceeded(double amount){
            double overdraftLimit = this.getoverdraftLimit();
            double balance = getBalance();

            if (Math.abs(balance - amount) > overdraftLimit){
                System.out.println("You cant withdraw more than your overdraft limit");
                return true;
            }
            return false;
        }

        /**
         * Processes a withdrawal transaction with overdraft protection.
         * 
         * <p>Validates that the withdrawal does not exceed the overdraft limit
         * before processing. If the limit would be exceeded, the withdrawal is
         * rejected and the balance remains unchanged.</p>
         * 
         * @param amount The amount to withdraw. Must be positive.
         */
        public void withdraw(double amount){
            double balance = getBalance();
            if( hasOverdraftlimitExceeded(amount)){
                 return ;
                }
                this.setBalance(balance - amount);
                
        }

        /**
         * Returns a comprehensive string representation of the checking account.
         * 
         * <p>Includes account number, customer information, account type, balance,
         * overdraft limit, monthly fee status, and account status.</p>
         * 
         * @return Formatted string containing all checking account details
         */
        @Override
        public String toString(){
            return String.format("Account Number: %s\n" +
                    "Customer: %s\n" +
                    "Account Type: %s\n" +
                    "Initial Balance: %.2f\n" +
                    "Overdraft Limit: %s\n" +
                    "Monthly Fee: %s\n" +
                    "Status: %s",this.getAccountNumber(),this.getCustomer(),this.getAccountType(),this.getBalance(),this.getoverdraftLimit(),(this.monthlyFee +" (WAIVED - Premium Customer)"),this.getStatus());
        }
       
        /**
         * Processes a transaction with enhanced validation for checking accounts.
         * 
         * <p>Overrides the base implementation to add overdraft limit checking
         * for withdrawal transactions. Deposits are processed normally, while
         * withdrawals are validated against the overdraft limit before execution.</p>
         * 
         * @param amount The transaction amount. Must be positive.
         * @param type The transaction type ("Deposit" or "Withdrawal"), case-insensitive
         * @return {@code true} if the transaction was successful, {@code false} if validation failed
         */
        public boolean processTransactions(double amount, String type) {
            if (type.equalsIgnoreCase("Deposit")) {
                return deposit(amount);
            } else if (type.equalsIgnoreCase("Withdrawal")) {
                if(hasOverdraftlimitExceeded(amount)){
                    return false;
                }
                withdraw(amount);
                return true;
            }
            return false;
        }
 }