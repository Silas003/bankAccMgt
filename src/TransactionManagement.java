import java.util.ArrayList;

public class TransactionManagement {
}

class TransactionManager{
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>(200);
    private int transactionCount;

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public void viewTransactionByAccount(String accountNumber) {
    }

    public void calculateTotalDeposits(String accountNumber) {
    }

    public void calculateTotalWithdrawals(String accountNumber) {
    }

    public int getTransactionCount(){
        return this.transactions.size();
    }
}