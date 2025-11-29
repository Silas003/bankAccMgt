package com.models;

    public abstract class Account implements Transactable{
        private String accountNumber;
        private Customer customer;
        private double balance;
        private String status;

        public static int accountCounter;
        Account(){
            setAccountNumber();
            accountCounter++;
        }
        public abstract String displayAccountDetails();
        public abstract String getAccountType();
        public abstract String getAccountSpecificDetails();
        private void generateAccountNumber(){
            this.accountNumber = "ACC00" + accountCounter;
        }
        public String getAccountNumber(){
            return this.accountNumber;
        }

        public void setAccountNumber(){
            generateAccountNumber();
        }
        public String getCustomer(){
            return this.customer.toString();
        }
        public int getAccountCounter(){
            return accountCounter;
        }
        public void setCustomer(Customer Customer){
            this.customer = Customer;

        }

        public double getBalance(){
            return this.balance;
        }
        public void setBalance(double balance){
            this.balance = balance;
        }

        public String getStatus(){
            return this.status;
        }
        public void setStatus(String status){
            this.status = status;
        }



        public abstract void deposit(double amount);

        public abstract void withdraw(double amount);

        @Override
        public String toString(){
            return String.format("Customer: %s\nAccount Type:%s\nCurrent Balance: %.2f",getCustomer(),getAccountType(),getBalance());
        }
  
  
    public boolean processTransaction(double amount, String type) {
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




