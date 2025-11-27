import java.util.ArrayList;
import java.util.Arrays;

public class AccountManagement {

//    public class AccountManager{
        private static ArrayList<Accounts.Account> accounts = new ArrayList<>(50);
        private static int accountCount;

        public static void addAccount(Accounts.Account account){
            accounts.add(account);
        }

        public static Accounts.Account findAccount(String accountNumber){

            for(Accounts.Account account: accounts){
                if(account.getAccountNUmber().equals(accountNumber)){
                    return account;
                }
            }
            return null;
        }

        public static ArrayList<Accounts.Account> viewAllAccounts(){
            return accounts;
        }

        public double getTotalBalance(){
            double totalBalance = 0;

            for ( Accounts.Account account : accounts){
                totalBalance += account.getBalance();
            }

            return  totalBalance;
        }

        public static int getAccountCount(){
            return accounts.size();
        }
//    }
}

