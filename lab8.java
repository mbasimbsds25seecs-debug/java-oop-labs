import java.util.Scanner;

class BankAccount {
    protected String accountNumber;
    protected String holderName;
    protected double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.printf("%s deposited: %.2f | New Balance: %.2f%n", holderName, amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.printf("%s withdrew: %.2f | New Balance: %.2f%n", holderName, amount, balance);
        } else {
            System.out.println("Insufficient funds for " + holderName);
        }
    }

    public double getBalance() { return balance; }
    public String getHolderName() { return holderName; }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.printf("%s's interest added: %.2f | New Balance: %.2f%n", holderName, interest, balance);
    }
}

class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.printf("%s withdrew: %.2f | New Balance: %.2f%n", holderName, amount, balance);
        } else {
            System.out.println("Overdraft limit exceeded for " + holderName);
        }
    }
}

public class lab8 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("       TASK 1: BANKING SYSTEM       \n");

        // --- Savings Accounts ---
        System.out.print("How many Savings Accounts? ");
        int savCount = sc.nextInt();
        sc.nextLine();

        SavingsAccount[] savAccounts = new SavingsAccount[savCount];

        for (int i = 0; i < savCount; i++) {
            System.out.println("\n--- Savings Account " + (i + 1) + " ---");
            System.out.print("Account Number: ");
            String accNum = sc.nextLine();
            System.out.print("Holder Name: ");
            String name = sc.nextLine();
            System.out.print("Initial Balance: ");
            double balance = sc.nextDouble();
            System.out.print("Interest Rate (e.g. 0.05 for 5%): ");
            double rate = sc.nextDouble();
            sc.nextLine();
            savAccounts[i] = new SavingsAccount(accNum, name, balance, rate);
        }

        // --- Current Accounts ---
        System.out.print("\nHow many Current Accounts? ");
        int curCount = sc.nextInt();
        sc.nextLine();

        CurrentAccount[] curAccounts = new CurrentAccount[curCount];

        for (int i = 0; i < curCount; i++) {
            System.out.println("\n--- Current Account " + (i + 1) + " ---");
            System.out.print("Account Number: ");
            String accNum = sc.nextLine();
            System.out.print("Holder Name: ");
            String name = sc.nextLine();
            System.out.print("Initial Balance: ");
            double balance = sc.nextDouble();
            System.out.print("Overdraft Limit: ");
            double overdraft = sc.nextDouble();
            sc.nextLine();
            curAccounts[i] = new CurrentAccount(accNum, name, balance, overdraft);
        }

        // Transactions
        System.out.println("\n========== TRANSACTIONS ==========");

        // Deposits for savings accounts
        for (int i = 0; i < savCount; i++) {
            System.out.print("\nDeposit amount for " + savAccounts[i].getHolderName() + " (0 to skip): ");
            double amt = sc.nextDouble();
            sc.nextLine();
            if (amt > 0) savAccounts[i].deposit(amt);
        }

        // Withdrawals for current accounts
        for (int i = 0; i < curCount; i++) {
            System.out.print("\nWithdraw amount for " + curAccounts[i].getHolderName() + " (0 to skip): ");
            double amt = sc.nextDouble();
            sc.nextLine();
            if (amt > 0) curAccounts[i].withdraw(amt);
        }

        // Apply interest to all savings accounts
        System.out.println("\n========== INTEREST APPLIED ==========");
        for (int i = 0; i < savCount; i++) {
            savAccounts[i].calculateInterest();
        }

        // Summary using base class array 
        int total = savCount + curCount;
        BankAccount[] allAccounts = new BankAccount[total];
        for (int i = 0; i < savCount; i++) allAccounts[i] = savAccounts[i];
        for (int i = 0; i < curCount; i++) allAccounts[savCount + i] = curAccounts[i];

        double totalBalance = 0;
        BankAccount highest = allAccounts[0];

        for (BankAccount acc : allAccounts) {
            totalBalance += acc.getBalance();
            if (acc.getBalance() > highest.getBalance()) {
                highest = acc;
            }
        }

        System.out.println("\n========== SUMMARY ==========");
        System.out.printf("Highest Balance: %s -> %.2f%n", highest.getHolderName(), highest.getBalance());
        System.out.printf("Total Bank Balance: %.2f%n", totalBalance);

        sc.close();
    }
}