import java.util.Scanner;

public class Lab11_Task1 {

    static double balance = 0.00;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.println("===== Welcome to the OOP ATM =====");

        while (true) {
            System.out.print("Enter your starting account balance: ");
            try {
                balance = Double.parseDouble(sc.nextLine().trim());
                if (balance < 0) {
                    System.out.println("Balance cannot be negative. Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                continue;
            }

            if (choice == 4) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            }

            switch (choice) {
                case 1:
                    checkBalance();
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    try {
                        double amount = Double.parseDouble(sc.nextLine().trim());
                        deposit(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid numeric amount.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    try {
                        double amount = Double.parseDouble(sc.nextLine().trim());
                        withdraw(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid numeric amount.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        }

        sc.close();
    }

    static void checkBalance() {
        System.out.printf("Your current balance is: PKR %.2f%n", balance);
    }

    static void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.printf("PKR %.2f deposited successfully. New balance: PKR %.2f%n", amount, balance);
    }

    static void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new ArithmeticException("Insufficient funds. Available balance: PKR " + balance);
        }
        balance -= amount;
        System.out.printf("PKR %.2f withdrawn successfully. Remaining balance: PKR %.2f%n", amount, balance);
    }
}
