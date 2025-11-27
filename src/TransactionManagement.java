import java.util.ArrayList;

public class TransactionManagement {
//}

//class TransactionManager{
    private ArrayList<Transactions.Transaction> transactions = new ArrayList<Transactions.Transaction>(200);
    private int transactionCount;

    public void addTransaction(Transactions.Transaction transaction){
        transactions.add(transaction);
    }

    public ArrayList<Transactions.Transaction> viewTransactionByAccount(String accountNumber) {
        ArrayList<Transactions.Transaction> accountTransactions = new ArrayList<>();
        for (Transactions.Transaction trnx : transactions){
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
        return this.transactions.size();
    }
}