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
        Customers customers = new Customers();
        Accounts accounts = new Accounts();
        Customers.Customer newRegularCustomer = null;
        Customers.Customer newPremiumCustomer = null ;
        Accounts.Account newSavingsAccount;
        Accounts.Account newCheckingAccount;


        System.out.println("ACCOUNT CREATION");
        System.out.println("====================================");

//        try{
//            System.out.print("Enter customer name: ");
//            customerName = scanner.nextLine();
////            validation for customer name
//            if(customerName.isBlank() || !customerName.matches("^[A-Za-z]+$")) System.out.println(
//                "Invalid Customer Name provided. NB: Name must not be empty or contain an integer");
//
//            System.out.print("Enter customer age: ");
//            customerAge = Integer.parseInt(scanner.nextLine());
//            System.out.print("Enter customer contact: ");
//            customerContact = scanner.nextLine();
//            if(customerContact.isBlank() || !customerContact.matches("^[0-9]+$")) System.out.println(
//                    "Invalid Customer Contact provided. NB: Contact must not be empty or contain an alphabet or be of negative value");
//            System.out.print("Enter customer address: ");
//            customerAddress = scanner.nextLine();
//            if(customerAddress.isBlank()) System.out.println(
//                    "Invalid Customer Address provided. NB: Address must not be empty");
//            System.out.println("Customer type:");
//
//            System.out.println("1. Regular Customer (Standard banking services)\n2. Premium Customer (Enhanced benefits, min balance $10,000)");
//
//            System.out.print("Select type(1-2): ");
//            String customerTypeInput = scanner.nextLine();

//            while(maxRetries <3){
//                switch (userCreateAccountSession){
//                    case 1:
//                        System.out.print("Enter customer name: ");
//                        customerName = scanner.nextLine();
////            validation for customer name
//                        if(customerName.isBlank() || !customerName.matches("^[A-Za-z]+$")) {
//                            System.out.println("Invalid Customer Name provided. NB: Name must not be empty or contain an integer");
//                            maxRetries++;
//                            userCreateAccountSession = 1;
////
//                        }else{
//                            userCreateAccountSession = 2;
//
//                        }
//                        break;
//                    case 2:
//                        System.out.print("Enter customer contact: ");
//                        customerContact = scanner.nextLine();
//                        if(customerContact.isBlank() || !customerContact.matches("^[0-9]+$")) {
//                            System.out.println(
//                                    "Invalid Customer Contact provided. NB: Contact must not be empty or contain an alphabet or be of negative value");
//                            maxRetries++;
//                            userCreateAccountSession = 2;
////
//                        }else{
//                            userCreateAccountSession = 3;
//
//                        }
//                        break;
//
//                    case 3:
//                        System.out.print("Enter customer address: ");
//                        customerAddress = scanner.nextLine();
//                        if(customerAddress.isBlank()) {
//                            System.out.println(
//                                    "Invalid Customer Address provided. NB: Address must not be empty");
//                            maxRetries++;
//                            userCreateAccountSession = 3;
////
//                        }else{
//                            userCreateAccountSession = 4;
//
//                        }
//                        break;
//
//                    case 4:
//                        System.out.print("Enter customer address: ");
//                        customerAddress = scanner.nextLine();
//                        if(customerAddress.isBlank()) {
//                            System.out.println("Invalid Customer Address provided. NB: Address must not be empty");
//                            maxRetries++;
//                            userCreateAccountSession = 4;
//                        }
//                        break;
//                    default:
//                        userCurrentSession = 1;
//                            }
//            }
//                    System.out.println("1. Regular Customer (Standard banking services)\n2. Premium Customer (Enhanced benefits, min balance $10,000)");

//            System.out.print("Select type(1-2): ");
//            String customerTypeInput = scanner.nextLine();
//
        customerName = validateCustomerNameInput(scanner);
        if(customerName == null) return;
        customerAge = validateCustomerAgeInput(scanner);
        if(customerAge == -1) return;
        customerContact = validateCustomerContactInput(scanner);
        if(customerContact == null) return;
        customerAddress = validateCustomerAddressInput(scanner);
        if(customerAddress == null) return;
        String customerTypeInput = validateCustomerTypeInput(scanner);
        if(customerTypeInput == null) return;
        String  accounTypeInput = validateAccountTypeInput(scanner);
        if(accounTypeInput == null) return;
            switch (customerTypeInput){
                case "1":
                    newRegularCustomer = customers.new RegularCustomer(customerName,customerAge, customerContact, customerAddress);

                    break;

                case "2":
                    newPremiumCustomer = customers.new PremiumCustomer(customerName,customerAge,customerContact,customerAddress);

                    break;


            }

        double initialDepositAmount = validateInitialDepositInput(scanner,customerTypeInput,accounTypeInput);
//            System.out.println("Account type:");
//
//            System.out.println("1. Savings Account (Interest: 3.5%, Min Balance: $500)\n2. Checking Account (Overdraft: $1,000 , Monthly Fee: $10)");
//            System.out.print("Select type(1-2): ");
//            String accounTypeInput = scanner.nextLine();
//            System.out.print("Enter initial deposit amount: ");
//            double initialDepositAmount = Double.parseDouble(scanner.nextLine());
//            if (initialDepositAmount < 0) {
//                throw new NumberFormatException();
//            }else if (initialDepositAmount <10000 && customerTypeInput.equals("2")) {
//                System.out.println("A premium customer cannot have an initial deposit less than $10,000");
//            }else if (initialDepositAmount < 500 && accounTypeInput.equals("1")){
//                System.out.println("A savings account cannot have an initial deposit less than $500");
//            }

//            creating account based on accountType selected from the user;
            switch (accounTypeInput){
                case "1":
                    if(customerTypeInput.equals("1")){
                        newSavingsAccount = accounts.new SavingsAccount(newRegularCustomer,initialDepositAmount);
                        accManagement.addAccount(newSavingsAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newSavingsAccount);
                    }else if(customerTypeInput.equals("2")){
                         newSavingsAccount = accounts.new SavingsAccount(newPremiumCustomer,initialDepositAmount);
                        accManagement.addAccount(newSavingsAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newSavingsAccount);
                    }

                    break;

                case "2":
                    if(customerTypeInput.equals("1")){
                         newCheckingAccount = accounts.new CheckingAccount(newRegularCustomer,initialDepositAmount);
                        accManagement.addAccount(newCheckingAccount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newCheckingAccount);
                    }else if(customerTypeInput.equals("2")){
                         newCheckingAccount = accounts.new CheckingAccount(newPremiumCustomer,initialDepositAmount);
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

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        if(accountNumber.isBlank() || !accountNumber.matches("(?i)^ACC00\\d{1,}$")) {
            System.out.println("Invalid Account Number provided. eg: ACC004");
            userCurrentSession = 3;
            return;

        }
        Transactions transactions = new Transactions()  ;

        Accounts.Account userAccount = accManagement.findAccount(accountNumber.toUpperCase());


        String transactionType = validateTransactionTypeInput(scanner);
        if(transactionType == null) return;
        System.out.print("Enter amount: ");
        double amount = validateTransactionAmount(scanner);
        if (amount == -1 )return;
        double balanceAfter = transactionType.equals("1") ? userAccount.getBalance() + amount : userAccount.getBalance() - amount;
        var transactionDateTime = LocalDateTime.now().format(formatter);
        System.out.println("TRANSACTION CONFIRMATION");
        System.out.println("========================");
        System.out.printf("Transaction ID: TNX00%d\n",trnxManagement.getTransactionCount());
        System.out.printf("Account: %s\n",userAccount.getAccountNumber());
        System.out.printf("Type: %s\n",trnType.get(transactionType));
        System.out.printf("Amount: $%s\n",amount);
        System.out.printf("Previous Balance: $%s\n",userAccount.getBalance());
        System.out.printf("New Balance: $%s\n",balanceAfter);
        System.out.printf("Date/Time: %s\n", transactionDateTime );

        String confirmation = validateTransactionConfirmation(scanner);
        if(confirmation == null) return;
        Transactions.Transaction trnx;
        switch (confirmation.toUpperCase()) {
            case "Y":
                switch(transactionType){
                    case "1":
                        userAccount.deposit(amount);
                         trnx =  transactions.new Transaction(userAccount.getAccountNumber(), "Deposit",amount,
                                balanceAfter,transactionDateTime);
                         trnxManagement.addTransaction(trnx);
                        System.out.println("Transactions created sucessfully!");
                        System.out.println(trnx);
                        break;

                    case "2":
                        userAccount.withdraw(amount);
                         trnx =  transactions.new Transaction(userAccount.getAccountNumber(),"Withdrawal",amount,
                                balanceAfter,transactionDateTime);
                        trnxManagement.addTransaction(trnx);
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
        System.out.println("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        if(accountNumber.isBlank() || !accountNumber.matches("(?i)^ACC00\\d{1,}$")) {
            System.out.println("Invalid Account Number provided. eg: ACC004");
            userCurrentSession = 4;
            return;

        }
        Accounts.Account account = accManagement.findAccount(accountNumber.toUpperCase());
        System.out.printf("Account: %s - %s\nAccount Type: %s\nCurrent Balance: %.2f\n\n",account.getAccountNumber(),account.getCustomer(),account.getAccountType(),account.getBalance());

        double totalDeposits = 0;
        double totalWithdrawals =  0;

        if(account.getAccountNumber().equalsIgnoreCase(accountNumber)){
            ArrayList<Transactions.Transaction> tnx = trnxManagement.viewTransactionByAccount(accountNumber.toUpperCase());
            System.out.println("TRANSACTION HISTORY");
            System.out.println("=====================================================================");
            System.out.println("TXN ID | DATE/TIME          | TYPE    | AMOUNT    | BALANCE");

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

        System.out.print("Press Enter to continue....");
        scanner.nextLine();
        userCurrentSession = 1;
    }

    public static String validateCustomerNameInput(Scanner scanner){
        String customerName ;

        for(int i=0 ; i < maxRetries ; i++ ) {
            System.out.print("Enter customer name: ");
            customerName = scanner.nextLine();
            if (customerName.isBlank() || !customerName.matches("^[A-Za-z]+$")) {
                System.out.println("Invalid Customer Name provided. NB: Name must not be empty or contain an integer");
            }else return customerName;

        }

        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;

    }

    public static int validateCustomerAgeInput(Scanner scanner){
        int customerAge ;
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter customer age: ");
            try {
                customerAge = Integer.parseInt(scanner.nextLine());
                if (customerAge > 0) return customerAge;
                System.out.println("Age must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Must be a number.");
            }
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return -1;

    }


    private static String validateCustomerTypeInput(Scanner scanner) {
        System.out.println("1. Regular Customer\n2. Premium Customer");
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Select type (1-2): ");
            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2")) return input;
            System.out.println("Invalid selection. Choose 1 or 2.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    private static String validateAccountTypeInput(Scanner scanner) {
        System.out.println("1. Savings Account\n2. Checking Account");
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Select type (1-2): ");
            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2")) return input;
            System.out.println("Invalid selection. Choose 1 or 2.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    private static String validateTransactionTypeInput(Scanner scanner) {
        System.out.println("1. Savings Account\n2. Checking Account");
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Select type (1-2): ");
            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2")) return input;
            System.out.println("Invalid selection. Choose 1 or 2.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    private static double validateInitialDepositInput(Scanner scanner, String customerType, String accountType) {
        final int premiumDeposit = 10000 ;
        final int savingsDepost = 500;
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter initial deposit amount: ");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Deposit cannot be negative.");
                } else if (customerType.equals("2") && amount < premiumDeposit) {
                    System.out.println("Premium customers require a minimum deposit of $10,000.");
                } else if (accountType.equals("1") && amount < savingsDepost) {
                    System.out.println("Savings account requires a minimum deposit of $500.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Must be a number.");
            }
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return -1;
    }

    private static  double validateTransactionAmount(Scanner scanner){
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter initial deposit amount: ");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Deposit cannot be negative.");
               } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Must be a number.");
            }
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return -1;
    }
    private static String validateCustomerContactInput(Scanner scanner) {
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter customer contact: ");
            String contact = scanner.nextLine();
            if (!contact.isBlank() && contact.matches("^[0-9]+$")) return contact;
            System.out.println("Invalid contact. Must contain only digits.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    private static String validateCustomerAddressInput(Scanner scanner) {
        for (int i = 0; i < maxRetries; i++) {
            System.out.print("Enter customer address: ");
            String address = scanner.nextLine();
            if (!address.isBlank()) return address;
            System.out.println("Invalid address. Cannot be empty.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }

    private static String validateTransactionConfirmation(Scanner scanner){
        System.out.print("Confirm transaction? (Y/N): ");
        for (int i = 0; i < 2; i++) {
            System.out.print("Select type (Y/N): ");
            String input = scanner.nextLine();
            if (input.equals("Y") || input.equals("N")) return input;
            System.out.println("Invalid selection. Choose Y or N.");
        }
        System.out.println("Too many invalid attempts. Returning to main menu.");
        return null;
    }



}
