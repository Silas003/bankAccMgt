import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class Main {


    public static void main(String[] args){
        mainMenu();
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
                break;
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
        Scanner scanner = new Scanner(System.in);
        String customerName,customerAddress,customerContact;
        String[] customerType = new String[]{
                "1. Regular Customer (Standard banking services)",
                "2. Premium Customer (Enhanced benefits, min balance $10,000)"
        };

        String[] accountType = new String[]{
                "1. Savings Account (Interest: 3.5%, Min Balance: $500)",
                "2. Checking Account (Overdraft: $1,000 , Monthly Fee: $10)"
        };

        HashMap<String,String> customerTypeMap = new HashMap<>();
        customerTypeMap.put("1","");
        customerTypeMap.put("2","");
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
            for( String type : customerType){
                System.out.println(type);
            }
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
            for( String type : accountType){
                System.out.println(type);
            }


            System.out.print("Select type(1-2): ");
            String accounTypeInput = scanner.nextLine();


            System.out.print("Enter initial deposit amount: ");
            int initialDepositAmount = Integer.parseInt(scanner.nextLine());
//            System.out.println(customerName + customerAge + customerAddress + customerContact );

//            creating account based on accountType selected from the user;
            switch (accounTypeInput){
                case "1":
                    if(customerTypeInput.equals("1")){
                        newSavingsAccount = accounts.new SavingsAccount("ACC001",newRegularCustomer,initialDepositAmount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newSavingsAccount);
                    }else{
                         newSavingsAccount = accounts.new SavingsAccount("ACC001",newPremiumCustomer,initialDepositAmount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newSavingsAccount);
                    }

                    break;

                case "2":
                    if(customerTypeInput.equals("2")){
                         newCheckingAccount = accounts.new CheckingAccount("ACC001",newRegularCustomer,initialDepositAmount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newCheckingAccount);
                    }else{
                         newCheckingAccount = accounts.new CheckingAccount("ACC001",newPremiumCustomer,initialDepositAmount);
                        System.out.println("Account created succesfully!");
                        System.out.println(newCheckingAccount);
                    }

                    break;
                default:
                    System.out.println("Please select a number between of choices of [1-2]");
            }
        }catch(NumberFormatException e){
            System.out.println("Input value must be of type integer");
            System.exit(0);
        }
        scanner.close();
    }
}
