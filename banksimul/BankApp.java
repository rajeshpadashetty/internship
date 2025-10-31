import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        Account acc = new Account("AC001", name, 1000.0);

        int choice;
        do {
            System.out.println("\n=== Bank Account Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    acc.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    acc.withdraw(sc.nextDouble());
                    break;
                case 3:
                    System.out.println("Current Balance: " + acc.getBalance());
                    break;
                case 4:
                    acc.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the bank app!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
