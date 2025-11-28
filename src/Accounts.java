
public class Accounts {

    public abstract class Account{
        private String accountNumber;
        private Customers.Customer customer;
        private double balance;
        private String status;

        public static int accountCounter;
        Account(){
            setAccountNumber();
            accountCounter++;
        }
        abstract Account displayAccountDetails();
        abstract String getAccountType();
        abstract String getAccountSpecificDetails();
        private void generateAccountNumber(){
            this.accountNumber = "ACC00" + accountCounter;
        }
        public String getAccountNumber(){
            return this.accountNumber;
        }

        public void setAccountNumber(){
            generateAccountNumber();
        }
        public String getCustomer(){
            return this.customer.toString();
        }
        public int getAccountCounter(){
            return accountCounter;
        }
        public void setCustomer(Customers.Customer Customer){
            this.customer = Customer;

        }

        public double getBalance(){
            return this.balance;
        }
        public void setBalance(double balance){
            this.balance = balance;
        }

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

        @Override
        public String toString(){
            return String.format("Customer: %s\nAccount Type:%s\nCurrent Balance: %.2f",getCustomer(),getAccountType(),getBalance());
        }

    }


    class SavingsAccount extends Account{
        private double interestRate;
        private double minimumBalance;

        SavingsAccount(){
            super();
            this.interestRate = 0.035;
            this.minimumBalance = 500;

        }

        SavingsAccount(Customers.Customer customer,double balance){
            this();
//            setAccountNumber(accountNumber);
            setCustomer(customer);
            setBalance(balance);
            setStatus("active");
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
        private String getInterestRate(){
            return String.format("%.1f%%",interestRate*100);
        }
        private double getMinimumBalance(){
            return minimumBalance;
        }
        @Override
        public String getAccountSpecificDetails(){
            return String.format("Interest Rate: %s Min Balance:$ %.2f",getInterestRate(),getMinimumBalance());
        }
        @Override
        public String toString(){
            return String.format("Account Number: %s\n" +
                    "Customer: %s\n" +
                    "Account Type: %s\n" +
                    "Initial Balance: %.2f\n" +
                    "Interest Rate: %.1f%%\n" +
                    "Minimum Balance: %s\n" +
                    "Status: %s",this.getAccountNumber(),this.getCustomer(),this.getAccountType(),this.getBalance(),(this.interestRate*100),this.minimumBalance,this.getStatus());
        }
    }

    class CheckingAccount extends Account{
        private double overdraftLimit;
        private double monthlyFee;

        CheckingAccount(){
            super();
            this.overdraftLimit = 1000;
            this.monthlyFee = 10;
        }

        CheckingAccount(Customers.Customer customer,double balance){
            this();
//            setAccountNumber(accountNumber);
            setCustomer(customer);
            setBalance(balance);

        }

        @Override
        public String getAccountSpecificDetails(){
            return String.format("Overdraft Limit: $%.2f MonthlyFee: $%.2f",getoverdraftLimit(),getMonthlyFee());
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



        public double getMonthlyFee(){
            return this.monthlyFee;
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

        @Override
        public String toString(){
            return String.format("Account Number: %s\n" +
                    "Customer: %s\n" +
                    "Account Type: %s\n" +
                    "Initial Balance: %.2f\n" +
                    "Overdraft Limit: %s\n" +
                    "Monthly Fee: %s\n" +
                    "Status: %s",this.getAccountNumber(),this.getCustomer(),this.getAccountType(),this.getBalance(),this.getoverdraftLimit(),(this.monthlyFee +" (WAIVED - Premium Customer)"),this.getStatus());
        }
    }
}

