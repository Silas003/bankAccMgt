import java.util.ArrayList;
public class AccountManagement {
}

class AccountManager{
    private ArrayList<Accounts.Account> accounts = new ArrayList<>(50);
    private int accountCount;

    public void addAccount(Accounts.Account account){
        this.accounts.add(account);
    }

    public Accounts.Account findAccount(String accountNumber){

        for(Accounts.Account account: accounts){
            if(account.getAccountNUmber() == accountNumber){
                return account;
            }
        }
        return null;
    }

    public ArrayList<Accounts.Account> viewAllAccounts(){
        return this.accounts;
    }

    public double getTotalBalance(){
        double totalBalance = 0;

        for ( Accounts.Account account : accounts){
            totalBalance += account.getBalance();
        }

        return  totalBalance;
    }

    public int getAccountCount(){
        return this.accounts.size();
    }
}