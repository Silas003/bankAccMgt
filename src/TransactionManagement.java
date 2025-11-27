import java.util.ArrayList;

public class TransactionManagement {
//}

//class TransactionManager{
    private Transactions.Transaction transactions[] = new Transactions.Transaction[200];
    private int transactionCount;

    public void addTransaction(Transactions.Transaction transaction){
        transactions[transactionCount]=transaction;
        transactionCount++;
    }

    public ArrayList<Transactions.Transaction> viewTransactionByAccount(String accountNumber) {
        ArrayList<Transactions.Transaction> accountTransactions = new ArrayList<>();
        Transactions.Transaction trnx;
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