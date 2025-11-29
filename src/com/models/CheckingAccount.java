package com.models;
 public class CheckingAccount extends Account{
        private double overdraftLimit;
        private double monthlyFee;

        CheckingAccount(){
            super();
            this.overdraftLimit = 1000;
            this.monthlyFee = 10;
        }

        public CheckingAccount(Customer customer,double balance){
            this();
            setCustomer(customer);
            setBalance(balance);
            setStatus("active");

        }

        @Override
        public String getAccountSpecificDetails(){
            return String.format("Overdraft Limit: $%.2f MonthlyFee: $%.2f",getoverdraftLimit(),getMonthlyFee());
        }
        public double getoverdraftLimit(){
            return this.overdraftLimit;
        }
        @Override
        public String displayAccountDetails(){
            return this.toString();
        }
        @Override
        public String getAccountType() {
            return "Checking";
        }



        public double getMonthlyFee(){
            return this.monthlyFee;
        }
        public void applyMonthlyFee(){
            this.monthlyFee = -10 ;
        }

        @Override
        public void deposit(double amount){
            double balance = getBalance();
            setBalance(balance + amount);
        }

        public boolean hasOverdraftlimitExceeded(double amount){
            double overdraftLimit = this.getoverdraftLimit();
            double balance = getBalance();

            if ((Math.abs(balance - amount ))> overdraftLimit){
                System.out.println("You cant withdraw more than your overdraft limit");
                return true;
            }
            return false;
        }

       
        public void withdraw(double amount){
            double balance = getBalance();
            if( hasOverdraftlimitExceeded(amount)){
                 return ;
                }
                this.setBalance(balance - amount);
                
        }

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
        @Override
        public boolean processTransactions(double amount, String type) {
            if (type.equalsIgnoreCase("Deposit")) {
                deposit(amount);
                return true;
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