package com.service;
import java.util.ArrayList;
import java.util.Arrays;
import com.models.Account;

public class AccountManagement {

//    public class AccountManager{
        private static Account[] accounts = new Account[50];
        public static int accountCount;

        public static void addAccount(Account account){

            if (accountCount < accounts.length) {
                accounts[accountCount++] = account;
            } else {
                System.out.println("Account list is full!");
            }

        }

        public static Account findAccount(String accountNumber){

            for(int i = 0; i < accountCount; i++){
                Account account = accounts[i];
                if(account.getAccountNumber().equals(accountNumber)) return account;
            }
            return null;

        }

        public static Account[] viewAllAccounts(){
            return accounts;
        }

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

        public static int getAccountCount(){
            return accountCount;
        }
//    }
}

