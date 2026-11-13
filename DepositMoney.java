
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DepositMoney {

    private static final String URL =
        "jdbc:mysql://localhost:3306/banking_db";

    private static final String USER = "banking_user";

    private static final String PASSWORD = "Banking@2026";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== DEPOSIT MONEY =====");

        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid deposit amount!");
            sc.close();
            return;
        }

        String sql =
            "UPDATE accounts SET balance = balance + ? "
            + "WHERE account_number = ?";

        try (
            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                    "Money deposited successfully!"
                );
                System.out.println("Deposited Amount: Rs. " + amount);
            } else {
                System.out.println("Account not found!");
            }

        } catch (SQLException e) {
            System.out.println("Deposit failed!");
            e.printStackTrace();
        }

        sc.close();
    }
}

