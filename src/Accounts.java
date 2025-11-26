
public class Accounts {
    enum AccountType{
        Savings,
        Checking
    }

    public abstract class Account{
        private String accountNumber;
        private Customers.Customer customer;
        private double balance;
        private String status;

        static int accountCounter;

        abstract Account displayAccountDetails();
        abstract String getAccountType();

        public String getAccountNUmber(){
            return this.accountNumber;
        }

        public void setAccountNumber(String AccountNumber){
            this.accountNumber = AccountNumber;
        }
        public Customers.Customer getCustomer(){
            return this.customer;
        }

        public void setCustomer(Customers.Customer Customer){
            this.customer = Customer;
        }

        public double getBalance(){
            return this.balance;
        }
        public void setBalance(double balance){}

        public String getStatus(){
            return this.status;
        }
        public void setStatus(String status){
            this.status = status;
        }



        public void deposit(double amount){
            this.balance += amount;
        }

        public void withdraw(double amount){
            this.balance -= amount;
        }

    }


    class SavingsAccount extends Account{
        private double interestRate;
        private double minimumBalance;

        SavingsAccount(){
            this.interestRate = 0.035;
            this.minimumBalance = 500;

        }

        SavingsAccount(String accountNumber,Customers.Customer customer,double balance){
            this();
            setAccountNumber(accountNumber);
            setCustomer(customer);
            setBalance(balance);
        }
        @Override
        Account displayAccountDetails(){
            System.out.println("Savings Account");
            return this;
        }

        @Override
        String getAccountType() {
            return "Savings";
        }

        @Override
        public void  withdraw(double amount){
            System.out.println(this.minimumBalance);
        }

        public double calculateInterest(){
            double balance = getBalance();

            return balance * this.interestRate;
        }
    }

    class CheckingAccount extends Account{
        private double overdraftLimit;
        private double monthlyFee;

        CheckingAccount(){
            this.overdraftLimit = 1000;
            this.monthlyFee = 10;
        }

        CheckingAccount(String accountNumber,Customers.Customer customer,double balance){
            this();
            setAccountNumber(accountNumber);
            setCustomer(customer);
            setBalance(balance);
        }

        public double getoverdraftLimit(){
            return this.overdraftLimit;
        }
        @Override
        Account displayAccountDetails(){
            System.out.println("Checking Account");
            return this;
        }
        @Override
        String getAccountType() {
            return "Checking";
        }

        public void applyMonthlyFee(){
            this.setBalance(-10);
        }

        @Override
        public void withdraw(double amount){
            double overdraftLimit = this.getoverdraftLimit();

            if (amount > overdraftLimit){
                System.out.println("You cant withdraw more than your overdraft limit");
            }else{
                this.setBalance(-amount);
            }
        }
    }
}

