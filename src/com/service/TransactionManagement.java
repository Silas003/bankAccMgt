package com.service;
import java.util.ArrayList;
import com.models.Transaction;

public class TransactionManagement {    
    public Transaction transactions[] = new Transaction[200];
    public int transactionCount;

    public void addTransaction(Transaction transaction){
        transactions[transactionCount]=transaction;
        transactionCount++;
    }

    public ArrayList<Transaction> viewTransactionByAccount(String accountNumber) {
        ArrayList<Transaction> accountTransactions = new ArrayList<>();
        Transaction trnx;
        for (int i = 0 ; i < transactionCount ; i++){
            trnx = transactions[i];
            if (trnx.getAccountNumber().equals(accountNumber)){
               accountTransactions.add(trnx);
            }
        }
        return accountTransactions;
    }

    public void calculateTotalDeposits(String accountNumber) {
    }

    public void calculateTotalWithdrawals(String accountNumber) {
    }

    public int getTransactionCount(){
        return this.transactionCount;
    }
}