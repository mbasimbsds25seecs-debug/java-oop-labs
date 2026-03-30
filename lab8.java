

class BankAccount{
   protected String accountNumber;
   protected String holderName;
   protected double balance;

   public BankAccount(String accountNumber, String holderName,double balance){

    this.accountNumber= accountNumber;
    this.holderName= holderName;
    this.balance= balance;

   }
   public void deposit(double amount){
    balance+= amount;
    System.out.println(holderName+ " deposited: " + amount+ "  New Balance: " + balance);
   }
   public void withdraw(double amount){
   if(amount<=balance){
    balance-= amount;
    System.out.println(holderName +" withdrew "+ amount+ "  New Balance: "+ balance);

   }else{
    System.out.println("Insufficient funds for "+ holderName);

   }
}
  public double getBalance(){
    return balance;
  }
  
  public String getHolderName(){
    return holderName;
  }

   }
class SavingsAccount extends BankAccount{
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate){
        super(accountNumber, holderName, balance);
        this.interestRate=interestRate;

    }
   public void calculateinterest(){
    double interest = balance * interestRate;
    balance+= interest;
    System.out.println(holderName+ "'s interest added: "+ interest + "   New balance: "+ balance);

   }
}
class CurrentAccount extends BankAccount{
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit){
        super(accountNumber, holderName, balance);
        this.overdraftLimit=overdraftLimit;

    }
 @Override
public void withdraw (double amount){
    if(amount<=balance +overdraftLimit){
        balance-=amount;
        System.out.println(holderName + " withdrew: " + amount + "  New Balance: " + balance);
    }else{
        System.out.println("Overdraft Limit exceeded for "+ holderName);

    }
}


}

    
public class lab8{
    public static void main(String[]args){

        System.out.println("    TASK1: BANKING SYSTEM    ");
        SavingsAccount s1= new SavingsAccount("S101","Basim",80000,0.07);
         SavingsAccount s2= new SavingsAccount("S105","Asad",60000,0.07);
         CurrentAccount c1= new CurrentAccount( "C101","mahnoor",30000,5000);
s1.deposit(10000);
        s2.withdraw(5000);
        c1.withdraw(24000);

        System.out.println();
        s1.calculateinterest();
        s2.calculateinterest();
    BankAccount[] accounts = { s1, s2, c1 };
        double totalBalance = 0;
        BankAccount highest = accounts[0];
 
        for (BankAccount acc : accounts) {
            totalBalance += acc.getBalance();
            if (acc.getBalance() > highest.getBalance()) {
                highest = acc;
            }
        }
 
        System.out.println("\nHighest Balance: " + highest.getHolderName() + " -> " + highest.getBalance());
        System.out.println("Total Bank Balance: " + totalBalance);
    }
}
 

