import com.service.*;
import com.models.*;
import com.utilities.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {

    static AccountManagement accManagement = new AccountManagement();
    static  TransactionManagement trnxManagement = new TransactionManagement();
    static Scanner scanner = new Scanner(System.in);
    static int userCurrentSession = 1;
    static final int maxRetries = 3;

    public static void main(String[] args){
        System.out.println("||====================================||");
        System.out.println("  BANK ACCOUNT MANAGEMENT - MAIN MENU");
        System.out.println("||====================================||");
        while (userCurrentSession != 0 ){
            switch (userCurrentSession){
                case 1:
                    mainMenu();
                    break;
                case 2:
                    viewAccounts();
                    break;
                case 3:
                    processTransaction();
                    break;
                case 4:
                    viewTransactionHistory();
                default:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void mainMenu(){

        System.out.println( "1. Create Account \n2. View Accounts \n3. Process Transaction \n4.View Transaction History \n5.Exit");

        System.out.print("Enter choice: ");
        String userInput = scanner.nextLine();

            switch(userInput){
                case "1":
                    createAccount();
                    break;
                case "2":
                    viewAccounts();
                    break;
                case "3":
                    processTransaction();
                    break;
                case "4":
                    viewTransactionHistory();
                    break;
                case "5":
                    System.out.println("Thank you for using Bank Account Management System! \nGoodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a number between of choices of [1-5]");
                    userCurrentSession = 0;
            }


    }

    private static void createAccount(){
        int userCreateAccountSession = 1;
        int maxRetries = 0;
        String customerName = "",customerAddress = "",customerContact = "";
        int customerAge = 0;
        Customer newRegularCustomer = null;
        Customer newPremiumCustomer = null ;
        SavingsAccount newSavingsAccount;
        CheckingAccount newCheckingAccount;


        System.out.println("ACCOUNT CREATION");
        System.out.println("====================================");
        customerName = CustomUtils.validateCustomerNameInput(scanner);
        if(customerName == null) return;
        customerAge = CustomUtils.validateCustomerAgeInput(scanner);
        if(customerAge == -1) return;
        customerContact = CustomUtils.validateCustomerContactInput(scanner);
        if(customerContact == null) return;
        customerAddress = CustomUtils.validateCustomerAddressInput(scanner);
        if(customerAddress == null) return;
        String customerTypeInput = CustomUtils.validateCustomerTypeInput(scanner);
        if(customerTypeInput == null) return;
        String  accounTypeInput = CustomUtils.validateAccountTypeInput(scanner);
        if(accounTypeInput == null) return;
            switch (customerTypeInput){
                case "1":
                    newRegularCustomer = new RegularCustomer(customerName,customerAge, customerContact, customerAddress);

                    break;

                case "2":
                    newPremiumCustomer = new PremiumCustomer(customerName,customerAge,customerContact,customerAddress);

                    break;


            }

        double initialDepositAmount = CustomUtils.validateInitialDepositInput(scanner,customerTypeInput,accounTypeInput);

            switch (accounTypeInput){
                case "1":
                    if(customerTypeInput.equals("1")){
                        newSavingsAccount = new SavingsAccount(newRegularCustomer,initialDepositAmount);
                        accManagement.addAccount(newSavingsAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newSavingsAccount);
                    }else if(customerTypeInput.equals("2")){
                         newSavingsAccount = new SavingsAccount(newPremiumCustomer,initialDepositAmount);
                        accManagement.addAccount(newSavingsAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newSavingsAccount);
                    }

                    break;

                case "2":
                    if(customerTypeInput.equals("1")){
                         newCheckingAccount = new CheckingAccount(newRegularCustomer,initialDepositAmount);
                        accManagement.addAccount(newCheckingAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newCheckingAccount);
                    }else if(customerTypeInput.equals("2")){
                         newCheckingAccount = new CheckingAccount(newPremiumCustomer,initialDepositAmount);
                        accManagement.addAccount(newCheckingAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newCheckingAccount);
                    }

                    break;
                default:
                    System.out.println("Please select a number between of choices of [1-2]");
            }

        System.out.print("Press Enter to continue....");
        scanner.nextLine();
        userCurrentSession = 1;
    }

    public static void processTransaction(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        HashMap<String,String> trnType =  new HashMap<>();
        trnType.put("1","Deposit");
        trnType.put("2","Withdrawal");
        System.out.println("PROCESS TRANSACTION");
        System.out.println("===================");

        String accountNumber = CustomUtils.validateAccountNumberInput(scanner);
        if (accountNumber == null) return;
        Account userAccount = accManagement.findAccount(accountNumber.toUpperCase());
        if(userAccount == null){
            System.out.println("Acccont not found.Returning to main menu");
            return;
        }

        String transactionType = CustomUtils.validateTransactionTypeInput(scanner);
        if(transactionType == null) return;
        double amount = CustomUtils.validateTransactionAmount(scanner);
        if (amount == -1 )return;
        double balanceAfter = transactionType.equals("1") ? userAccount.getBalance() + amount : userAccount.getBalance() - amount;
        var transactionDateTime = LocalDateTime.now().format(formatter);
        System.out.println("TRANSACTION CONFIRMATION");
        System.out.println("========================");
        System.out.printf("Transaction ID: TNX00%d\n",trnxManagement.getTransactionCount());
        System.out.printf("Account: %s\n",userAccount.getAccountNumber());
        System.out.printf("Type: %s\n",trnType.get(transactionType));
        System.out.printf("Amount: $%s\n",amount);
        System.out.printf("Previous Balance: $%.2f\n",userAccount.getBalance());
        System.out.printf("New Balance: $%.2f\n",balanceAfter);
        System.out.printf("Date/Time: %s\n", transactionDateTime );

        String confirmation = CustomUtils.validateTransactionConfirmation(scanner);
        if(confirmation == null) return;
        Transaction trnx;
        boolean transactionSuccess ;
        switch (confirmation.toUpperCase()) {
            case "Y":
                switch(transactionType){
                    case "1":
                        transactionSuccess = userAccount.processTransaction(amount, trnType.get(transactionType));
                        if(transactionSuccess){
                            trnx =  new Transaction(userAccount.getAccountNumber(), "Deposit",amount,
                                    balanceAfter,transactionDateTime);
                            trnxManagement.addTransaction(trnx);
                            System.out.println("Transactions created sucessfully!");
                            System.out.println(trnx);
                        }else{
                            System.out.println("Transaction failed!");
                        }
                        break;

                    case "2":
                        double minimumBalance = 500;
                        if(!(balanceAfter < minimumBalance && userAccount.getAccountType().equalsIgnoreCase("Savings"))  ){
                           transactionSuccess = userAccount.processTransaction(amount, trnType.get(transactionType));
                           if(transactionSuccess){
                            trnx =  new Transaction(userAccount.getAccountNumber(),"Withdrawal",amount,balanceAfter,transactionDateTime) ; 
                            trnxManagement.addTransaction(trnx);
                            System.out.println("Transactions created sucessfully!");
                            System.out.println(trnx);
                        }else{
                            System.out.println("Transaction failed!");
                        }
                        }else{
                            System.out.println("You cannot withdraw amount at this moment.You would go before minimum balance for a savings account");
 
                            return ;
                        }
                        
                        break;

                    default:
                        break;
                }
            case "N":
                break;

            default:
                System.out.println("Please select a number between of choices of [Y/N]");
        }


        System.out.print("Press Enter to continue....");
        scanner.nextLine();
        userCurrentSession = 1;

    }

    public static void viewTransactionHistory(){

        System.out.println("VIEW TRANSACTION HISTORY");
        System.out.println("========================");
        String accountNumber = CustomUtils.validateAccountNumberInput(scanner);
        if (accountNumber == null) return;
        Account account = accManagement.findAccount(accountNumber.toUpperCase());
        

        double totalDeposits = 0;
        double totalWithdrawals =  0;

        if(account != null){
            ArrayList<Transaction> tnx = trnxManagement.viewTransactionByAccount(accountNumber.toUpperCase());
            System.out.printf("Account: %s - %s\nAccount Type: %s\nCurrent Balance: %.2f\n\n",account.getAccountNumber(),account.getCustomer(),account.getAccountType(),account.getBalance());
            System.out.println("TRANSACTION HISTORY");
            System.out.println("=====================================================================");
            System.out.println("TXN ID | DATE/TIME          | TYPE    | AMOUNT    | BALANCE");

            for ( Transaction tn : tnx){
                if(tn.getType().equals("Deposit")){
                    totalDeposits += tn.getAmount();
                }else if(tn.getType().equals("Withdrawal")){totalWithdrawals += tn.getAmount();
                }

                System.out.printf("%s |%s |%s  |%s$%.2f  |$%.2f\n",tn.getTransactionId(),tn.getTimeStamp(),tn.getType(),tn.getType().equals("Deposit") ? "+" : "-",tn.getAmount(),tn.getBalanceAfter());
            }
        System.out.println("=====================================================================\n");
        System.out.println("Total Transactions: "+tnx.size());
        System.out.println("Total Deposits: "+totalDeposits);
        System.out.println("Total Withdrawals: "+totalWithdrawals);
        System.out.println("Net Change: "+(totalDeposits - totalWithdrawals));
        }else {
            System.out.println("==========================================");
            System.out.println("No transactions recorded for this account.");
            System.out.println("==========================================");
    }

        System.out.print("Press Enter to continue....");
        scanner.nextLine();
        userCurrentSession = 1;
    }
    public static  void viewAccounts(){

        System.out.println("ACCOUNT LISTING");
        System.out.println("====================================================");
        System.out.println("ACC NO | CUSTOMER NAME | TYPE | BALANCE | STATUS");
        System.out.println("====================================================");


        Account[] allAccounts = accManagement.viewAllAccounts();
        for (int i = 0; i < accManagement.accountCount; i++) {
            Account account = allAccounts[i];
            System.out.printf("%s | %s |  %s |  $%.2f | %s |  %s\n",
                    account.getAccountNumber(),
                    account.getCustomer(),
                    account.getAccountType(),
                    account.getBalance(),
                    account.getStatus(),
                    account.getAccountSpecificDetails()
            );
        }


        System.out.printf("Total Accounts: %d\nTotal Bank Balance: $%.2f\n",accManagement.getAccountCount(),accManagement.getTotalBalance());

        System.out.print("Press Enter to continue....");
        scanner.nextLine();
        userCurrentSession = 1;
    }



}
