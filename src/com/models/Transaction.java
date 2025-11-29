package com.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transaction{
        static int transactionCounter;
        private String transactionId;
        private String type;
        private String accountNumber;
        private double amount;
        private double balanceAfter;
        private String timeStamp;

        @Override
        public String toString(){
            return String.format("%s",getAccountNumber());
        }
        public String getTransactionId(){
            return transactionId;
        }
        private void generateUniqueId(){
            this.transactionId = "TNX00" + transactionCounter;
        }
        public String getTranssactionType(){
            return type;
        }

        public String getAccountNumber(){
            return this.accountNumber;
        }
        public double getAmount(){
            return  this.amount;
        }

        public double getBalanceAfter(){
            return this.balanceAfter;
        }

        public String getType(){
            return this.type;
        }

        public String getTimeStamp(){
            return this.timeStamp;
        }
        private void setAccountNumber(String accountNumber){
            this.accountNumber = accountNumber;
        }

        private void setAmount(double amount){
            this.amount = amount;
        }

        private void setBalanceAfter(double balanceAfter){
            this.balanceAfter = balanceAfter;
        }

        private void setType(String type){
            this.type = type;
        }
        Transaction(){
            generateUniqueId();
            transactionCounter++;
        }
        private void setTimeStamp(String dateTime){
            this.timeStamp = dateTime;
        }
        public Transaction(String accountNumber,String type,double amount,double balanceAfter,String dateTime){
            this();
            setAccountNumber(accountNumber);
            setAmount(amount);
            setType(type);
            setBalanceAfter(balanceAfter);
            setTimeStamp(dateTime);
        }

        public Transaction displayTransactionDetails(){
            return this;
        }
    }


