import java.util.ArrayList;
import java.util.Arrays;

public class AccountManagement {

//    public class AccountManager{
        private static Accounts.Account[] accounts = new Accounts.Account[50];
        public static int accountCount;

        public static void addAccount(Accounts.Account account){

            if (accountCount < accounts.length) {
                accounts[accountCount++] = account;
            } else {
                System.out.println("Account list is full!");
            }

        }

        public static Accounts.Account findAccount(String accountNumber){

            for(Accounts.Account account: accounts){
                if(account.getAccountNumber().equals(accountNumber)){
                    return account;
                }
            }
            return null;
        }

        public static Accounts.Account[] viewAllAccounts(){
            return accounts;
        }

        public double getTotalBalance(){
            double totalBalance = 0;
            Accounts.Account account ;
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

