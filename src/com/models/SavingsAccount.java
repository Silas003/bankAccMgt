package com.models;
public  class SavingsAccount extends Account{
        private double interestRate;
        private  double minimumBalance;

        SavingsAccount(){
            super();
            this.interestRate = 0.035;
            this.minimumBalance = 500;

        }

        public SavingsAccount(Customer customer,double balance){
            this();
            setCustomer(customer);
            setBalance(balance);
            setStatus("active");
        }
        @Override
        public String displayAccountDetails(){
           return this.toString();
        }

        @Override
        public String getAccountType() {
            return "Savings";
        }

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

        @Override
        public void deposit(double amount){
            double balance = getBalance();
            setBalance(balance + amount);
        }

        public double calculateInterest(){
            double balance = getBalance();

            return balance * this.interestRate;
        }
        private String getInterestRate(){
            return String.format("%.1f%%",interestRate*100);
        }
        public double getMinimumBalance(){
            return minimumBalance;
        }
        @Override
        public String getAccountSpecificDetails(){
            return String.format("Interest Rate: %s Min Balance:$ %.2f",getInterestRate(),getMinimumBalance());
        }
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
        @Override
        public boolean processTransactions(double amount, String type) {
            if (type.equalsIgnoreCase("Deposit")) {
                deposit(amount);
                return true;
            } else if (type.equalsIgnoreCase("Withdrawal")) {
                withdraw(amount);
                return true;
            }
            return false;
        }
    }
