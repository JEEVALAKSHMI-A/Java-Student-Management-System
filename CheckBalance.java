
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckBalance {

    private static final String URL =
        "jdbc:mysql://localhost:3306/banking_db";

    private static final String USER = "banking_user";

    private static final String PASSWORD = "Banking@2026";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== CHECK ACCOUNT BALANCE =====");

        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();

        String sql = "SELECT account_holder, account_number, balance "
                   + "FROM accounts WHERE account_number = ?";

        try (
            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String name = rs.getString("account_holder");
                    String number = rs.getString("account_number");
                    double balance = rs.getDouble("balance");

                    System.out.println("\nAccount Details");
                    System.out.println("----------------------------");
                    System.out.println("Account Holder: " + name);
                    System.out.println("Account Number: " + number);
                    System.out.printf(
                        "Available Balance: Rs. %.2f%n", balance
                    );

                } else {

                    System.out.println("Account not found!");

                }
            }

        } catch (SQLException e) {

            System.out.println("Database error!");
            e.printStackTrace();

        }

        sc.close();
    }
}
