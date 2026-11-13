
import java.util.*;

// Interface
interface Transaction {
    void deposit(double amount);
    void withdraw(double amount);
}

// Abstract parent class
abstract class BankAccount implements Transaction {

    private int accountNumber;
    private String accountHolder;
    private double balance;

    private ArrayList<String> transactionHistory =
            new ArrayList<>();

    // Constructor
    public BankAccount(int accountNumber,
                       String accountHolder,
                       double balance) {

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;

        transactionHistory.add(
            "Account opened with balance: " + balance
        );
    }

    // Getters - Encapsulation
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // Abstract methods
    public abstract String getAccountType();

    protected abstract double getWithdrawalFee();

    // Deposit
    @Override
    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid deposit amount!");
            return;
        }

        balance += amount;

        transactionHistory.add(
            "Deposited: " + amount +
            " | Balance: " + balance
        );

        System.out.println("Deposit successful!");
    }

    // Withdraw
    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
            return;
        }

        double total = amount + getWithdrawalFee();

        if (total > balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        balance -= total;

        transactionHistory.add(
            "Withdrawn: " + amount +
            " | Fee: " + getWithdrawalFee() +
            " | Balance: " + balance
        );

        System.out.println("Withdrawal successful!");
    }

    // Balance enquiry
    public void checkBalance() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + getAccountType());
        System.out.println("Available Balance: " + balance);
    }

    // Transaction history
    public void showTransactionHistory() {

        System.out.println("\n--- Transaction History ---");

        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

// Inheritance
class SavingsAccount extends BankAccount {

    public SavingsAccount(int number, String holder,
                          double balance) {
        super(number, holder, balance);
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    protected double getWithdrawalFee() {
        return 0;
    }
}

// Inheritance
class CurrentAccount extends BankAccount {

    public CurrentAccount(int number, String holder,
                          double balance) {
        super(number, holder, balance);
    }

    @Override
    public String getAccountType() {
        return "Current Account";
    }

    @Override
    protected double getWithdrawalFee() {
        return 10;
    }
}

// Main class
public class BankingManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static HashMap<Integer, BankAccount> accounts =
            new HashMap<>();

    static int nextAccountNumber = 1001;

    // Create account
    static void createAccount() {

        try {
            System.out.println("\n1. Savings Account");
            System.out.println("2. Current Account");

            System.out.print("Choose account type: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid account type!");
                return;
            }

            System.out.print("Enter account holder name: ");
            String name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty!");
                return;
            }

            System.out.print("Enter opening balance: ");
            double balance = Double.parseDouble(sc.nextLine());

            if (balance < 0 ||
                Double.isNaN(balance) ||
                Double.isInfinite(balance)) {
                System.out.println("Invalid opening balance!");
                return;
            }

            int number = nextAccountNumber++;

            // Runtime polymorphism
            BankAccount account;

            if (choice == 1) {
                account = new SavingsAccount(
                    number, name, balance
                );
            } else {
                account = new CurrentAccount(
                    number, name, balance
                );
            }

            accounts.put(number, account);

            System.out.println(
                "Account created successfully!"
            );

            System.out.println(
                "Your Account Number: " + number
            );

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers!");
        }
    }

    // Find account
    static BankAccount findAccount(int number) {

        BankAccount account = accounts.get(number);

        if (account == null) {
            System.out.println("Account not found!");
        }

        return account;
    }

    // Deposit
    static void depositMoney() {

        try {
            System.out.print("Enter account number: ");
            int number = Integer.parseInt(sc.nextLine());

            BankAccount account = findAccount(number);

            if (account != null) {
                System.out.print("Enter deposit amount: ");
                double amount = Double.parseDouble(sc.nextLine());

                account.deposit(amount);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    // Withdraw
    static void withdrawMoney() {

        try {
            System.out.print("Enter account number: ");
            int number = Integer.parseInt(sc.nextLine());

            BankAccount account = findAccount(number);

            if (account != null) {
                System.out.print("Enter withdrawal amount: ");
                double amount = Double.parseDouble(sc.nextLine());

                account.withdraw(amount);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    // Check balance
    static void balanceEnquiry() {

        try {
            System.out.print("Enter account number: ");
            int number = Integer.parseInt(sc.nextLine());

            BankAccount account = findAccount(number);

            if (account != null) {
                account.checkBalance();
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid account number!");
        }
    }

    // Transaction history
    static void transactionHistory() {

        try {
            System.out.print("Enter account number: ");
            int number = Integer.parseInt(sc.nextLine());

            BankAccount account = findAccount(number);

            if (account != null) {
                account.showTransactionHistory();
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid account number!");
        }
    }

    // Display all accounts
    static void displayAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available!");
            return;
        }

        for (BankAccount account : accounts.values()) {
            account.checkBalance();
            System.out.println("----------------------");
        }
    }

    // Main method
    public static void main(String[] args) {

        while (true) {

            System.out.println(
                "\n===== BANKING MANAGEMENT SYSTEM ====="
            );

            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        createAccount();
                        break;

                    case 2:
                        depositMoney();
                        break;

                    case 3:
                        withdrawMoney();
                        break;

                    case 4:
                        balanceEnquiry();
                        break;

                    case 5:
                        transactionHistory();
                        break;

                    case 6:
                        displayAccounts();
                        break;

                    case 7:
                        System.out.println(
                            "Thank you for using our banking system!"
                        );
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid menu number!");
            }
        }
    }
}

