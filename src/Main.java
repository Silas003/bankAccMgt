import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {

    static AccountManagement accManagement = new AccountManagement();
    static  TransactionManagement trnxManagement = new TransactionManagement();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        mainMenu();
        viewAccounts();
        processTransaction();
        viewTransactionHistory();
    }

    private static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("||====================================||");
        System.out.println("BANK ACCOUNT MANAGEMENT - MAIN MENU");
        System.out.println("||====================================||");

        String[] menuItems = new String[]{
                "1. Create Account \n",
                "2. View Accounts \n",
                "3. Process Transaction \n",
                "4.View Transaction History \n",
                "5.Exit"
        };
        for( String item : menuItems){
            System.out.println(item);
        }
        System.out.print("Enter choice: ");
        String userInput = scanner.nextLine();

        switch(userInput){
            case "1":
                createAccount();
                break;
            case "2":
                viewAccounts();
            case "3":
                break;
            case "4":
                break;
            case "5":
                System.out.println("Thank you for using Bank Account Management System! \nGoodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Please select a number between of choices of [1-5]");
                System.exit(0);
        }
    }

    private static void createAccount(){

        String customerName,customerAddress,customerContact;
        int customerAge;
        Customers customers = new Customers();
        Accounts accounts = new Accounts();
        Customers.Customer newRegularCustomer = null;
        Customers.Customer newPremiumCustomer = null ;
        Accounts.Account newSavingsAccount;
        Accounts.Account newCheckingAccount;

        System.out.println("ACCOUNT CREATION");
        System.out.println("====================================");

        try{
            System.out.print("Enter customer name: ");
            customerName = scanner.nextLine();
            System.out.print("Enter customer age: ");
            customerAge = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter customer contact: ");
            customerContact = scanner.nextLine();
            System.out.print("Enter customer address: ");
            customerAddress = scanner.nextLine();

            System.out.println("Customer type:");

            System.out.println("1. Regular Customer (Standard banking services)\n2. Premium Customer (Enhanced benefits, min balance $10,000)");

            System.out.print("Select type(1-2): ");
            String customerTypeInput = scanner.nextLine();

            switch (customerTypeInput){
                case "1":
                    newRegularCustomer = customers.new RegularCustomer(customerName,customerAge, customerContact, customerAddress);

                    break;

                case "2":
                    newPremiumCustomer = customers.new PremiumCustomer(customerName,customerAge,customerContact,customerAddress);

                    break;
                default:
                    System.out.println("Please select a number between of choices of [1-2]");
            }
            System.out.println("Account type:");

            System.out.println("1. Savings Account (Interest: 3.5%, Min Balance: $500)\n2. Checking Account (Overdraft: $1,000 , Monthly Fee: $10)");



            System.out.print("Select type(1-2): ");
            String accounTypeInput = scanner.nextLine();


            System.out.print("Enter initial deposit amount: ");
            int initialDepositAmount = Integer.parseInt(scanner.nextLine());
//            System.out.println(customerName + customerAge + customerAddress + customerContact );

//            creating account based on accountType selected from the user;
            switch (accounTypeInput){
                case "1":
                    if(customerTypeInput.equals("1")){
                        newSavingsAccount = accounts.new SavingsAccount(newRegularCustomer,initialDepositAmount);
                        accManagement.addAccount(newSavingsAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newSavingsAccount);
                    }else{
                         newSavingsAccount = accounts.new SavingsAccount(newPremiumCustomer,initialDepositAmount);
                        accManagement.addAccount(newSavingsAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newSavingsAccount);
                    }

                    break;

                case "2":
                    if(customerTypeInput.equals("2")){
                         newCheckingAccount = accounts.new CheckingAccount(newRegularCustomer,initialDepositAmount);
                        accManagement.addAccount(newCheckingAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newCheckingAccount);
                    }else{
                         newCheckingAccount = accounts.new CheckingAccount(newPremiumCustomer,initialDepositAmount);
                        accManagement.addAccount(newCheckingAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newCheckingAccount);
                    }

                    break;
                default:
                    System.out.println("Please select a number between of choices of [1-2]");
            }
//            scanner.close();
        }catch(NumberFormatException e){
            System.out.println("Input value must be of type integer");
            System.exit(0);
        }

    }

    public static void processTransaction(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        HashMap<String,String> trnType =  new HashMap<>();
        trnType.put("1","Deposit");
        trnType.put("2","Withdrawal");
        System.out.println("PROCESS TRANSACTION");
        System.out.println("===================");

//        String accountNumber = scanner.nextLine();
        Transactions transactions = new Transactions()  ;

        Accounts.Account userAccount = accManagement.findAccount("ACC001");
//        System.out.println(userAccount);
        System.out.println("Transaction type");
        System.out.println("1. Deposit\n2. Withdrawal");
        System.out.print("Select type(1-2): ");
        String transactionType = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        double balanceAfter = transactionType.equals("1") ? userAccount.getBalance() + amount : userAccount.getBalance() - amount;
        var transactionDateTime = LocalDateTime.now().format(formatter);
        System.out.println("TRANSACTION CONFIRMATION");
        System.out.println("========================");
        System.out.println("Transaction ID: TNX002");
        System.out.println("Account: ACC001");
        System.out.printf("Type: %s\n",trnType.get(transactionType));
        System.out.printf("Amount: $%s\n",amount);
        System.out.printf("Previous Balance: $%s\n",userAccount.getBalance());
        System.out.printf("New Balance: $%s\n",balanceAfter);
        System.out.printf("Date/Time: %s\n", transactionDateTime );

        System.out.print("Confirm transaction? (Y/N): ");
        String confirmation = scanner.nextLine();
        Transactions.Transaction trnx;
        switch (confirmation.toUpperCase()) {
            case "Y":
                switch(transactionType){
                    case "1":
                        userAccount.deposit(amount);
                         trnx =  transactions.new Transaction("ACC001","Deposit",amount,
                                balanceAfter,transactionDateTime);
                         trnxManagement.addTransaction(trnx);
                        System.out.println("Transactions created sucessfully!");
                        System.out.println(trnx);
                        break;

                    case "2":
                        userAccount.withdraw(amount);
                         trnx =  transactions.new Transaction("ACC001","Withdrawal",amount,
                                balanceAfter,transactionDateTime);
                        trnxManagement.addTransaction(trnx);
                        break;

                    default:
                        System.out.println("Please select a number between of choices of [1-2]");
                        break;
                }
            case "N":
                break;

            default:
                System.out.println("Please select a number between of choices of [Y/N]");
        }




    }

    public static void viewTransactionHistory(){

        System.out.println("VIEW TRANSACTION HISTORY");
        System.out.println("========================");
        System.out.println("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        Accounts.Account account = accManagement.findAccount(accountNumber.toUpperCase());
        System.out.printf("Account: %s - %s\nAccount Type: %s\nCurrent Balance: %.2f\n\n",account.getAccountNumber(),account.getCustomer(),account.getAccountType(),account.getBalance());
        System.out.println("TRANSACTION HISTORY");
        System.out.println("=====================================================================");
        System.out.println("TXN ID | DATE/TIME          | TYPE    | AMOUNT    | BALANCE");

        double totalDeposits = 0;
        double totalWithdrawals =  0;

        if(true){
            ArrayList<Transactions.Transaction> tnx = trnxManagement.viewTransactionByAccount(accountNumber.toUpperCase());

            for ( Transactions.Transaction tn : tnx){
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
        }else{
        System.out.println("Account not found");
    }
    }
    public static  void viewAccounts(){

        System.out.println("ACCOUNT LISTING");
        System.out.println("====================================================");
        System.out.println("ACC NO | CUSTOMER NAME | TYPE | BALANCE | STATUS");
        System.out.println("====================================================");


        Accounts.Account[] allAccounts = accManagement.viewAllAccounts();
        for (int i = 0; i < accManagement.accountCount; i++) {
            Accounts.Account account = allAccounts[i];
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
    }


}
