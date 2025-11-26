import java.time.LocalDateTime;
public class Transactions {
}

interface Transactable{
    boolean processTransactions(double amount, String type);
}

class Transaction{
    static int transactionCounter;
    private String transactionId;
    private String type;
    private String accountNumber;
    private double amount;
    private double balanceAfter;
    private String timeStamp;

    Transaction(String accountNumber,String type,double amount,double balanceAfter){

    }

    public Transaction displayTransactionDetails(){
        return this;
    }
}