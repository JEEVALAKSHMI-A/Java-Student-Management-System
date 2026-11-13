
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionHistory {

    private static final String URL =
        "jdbc:mysql://localhost:3306/banking_db";

    private static final String USER = "banking_user";

    private static final String PASSWORD = "Banking@2026";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== TRANSACTION HISTORY =====");

        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();

        String sql =
            "SELECT transaction_id, transaction_type, amount, "
            + "transaction_date FROM transactions "
            + "WHERE account_number = ? "
            + "ORDER BY transaction_date DESC";

        try (
            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println("\nTransaction Details");
                System.out.println("-----------------------------------------------");

                System.out.printf(
                    "%-10s %-15s %-15s %-20s%n",
                    "ID", "TYPE", "AMOUNT", "DATE"
                );

                while (rs.next()) {

                    found = true;

                    int id = rs.getInt("transaction_id");

                    String type =
                        rs.getString("transaction_type");

                    String amount =
                        rs.getBigDecimal("amount").toString();

                    String date =
                        rs.getTimestamp("transaction_date").toString();

                    System.out.printf(
                        "%-10d %-15s Rs. %-11s %-20s%n",
                        id, type, amount, date
                    );
                }

                if (!found) {
                    System.out.println(
                        "No transactions found for this account."
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println("Unable to retrieve history!");
            e.printStackTrace();

        }

        sc.close();
    }
}
