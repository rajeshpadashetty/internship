import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactions;

    // Constructor
    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        addTransaction("Account created with initial balance: " + initialBalance);
    }

    // Deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: " + amount);
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrawn: " + amount);
            System.out.println("Successfully withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Get Balance
    public double getBalance() {
        return balance;
    }

    // Show Transaction History
    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History for " + accountHolder + " ---");
        for (String record : transactions) {
            System.out.println(record);
        }
    }

    // Add timestamped transaction record
    private void addTransaction(String details) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        transactions.add(timeStamp + " - " + details);
    }
}
