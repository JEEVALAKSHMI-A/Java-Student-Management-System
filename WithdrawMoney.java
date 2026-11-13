
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.Scanner;

public class WithdrawMoney {

    private static final String URL =
        "jdbc:mysql://localhost:3306/banking_db";

    private static final String USER = "banking_user";

    private static final String PASSWORD = "Banking@2026";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== WITHDRAW MONEY =====");

        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter Withdrawal Amount: ");
        BigDecimal amount = new BigDecimal(sc.nextLine());

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid withdrawal amount!");
            sc.close();
            return;
        }

        String sql = "UPDATE accounts "
                   + "SET balance = balance - ? "
                   + "WHERE account_number = ? "
                   + "AND balance >= ?";

        try (
            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setBigDecimal(1, amount);
            ps.setString(2, accountNumber);
            ps.setBigDecimal(3, amount);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                    "Withdrawal successful!"
                );

                System.out.println(
                    "Withdrawn Amount: Rs. " + amount
                );

            } else {

                String checkSql =
                    "SELECT account_number FROM accounts "
                    + "WHERE account_number = ?";

                try (PreparedStatement check =
                         con.prepareStatement(checkSql)) {

                    check.setString(1, accountNumber);

                    try (ResultSet rs = check.executeQuery()) {

                        if (rs.next()) {
                            System.out.println(
                                "Insufficient balance!"
                            );
                        } else {
                            System.out.println(
                                "Account not found!"
                            );
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {

            System.out.println("Enter a valid amount!");

        } catch (SQLException e) {

            System.out.println("Withdrawal failed!");
            e.printStackTrace();

        }

        sc.close();
    }
}
