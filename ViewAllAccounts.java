
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAllAccounts {

    private static final String URL =
        "jdbc:mysql://localhost:3306/banking_db";

    private static final String USER = "banking_user";

    private static final String PASSWORD = "Banking@2026";

    public static void main(String[] args) {

        String sql = "SELECT * FROM accounts";

        System.out.println("========== ALL BANK ACCOUNTS ==========");

        try (
            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery()
        ) {

            System.out.printf(
                "%-10s %-20s %-20s %-15s%n",
                "ID", "HOLDER", "ACCOUNT NUMBER", "BALANCE"
            );

            System.out.println(
                "---------------------------------------------------------------"
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                int id = rs.getInt("account_id");
                String name = rs.getString("account_holder");
                String number = rs.getString("account_number");
                String balance = rs.getBigDecimal("balance").toString();

                System.out.printf(
                    "%-10d %-20s %-20s Rs. %-12s%n",
                    id, name, number, balance
                );
            }

            if (!found) {
                System.out.println("No accounts found!");
            }

        } catch (SQLException e) {

            System.out.println("Unable to retrieve accounts!");
            e.printStackTrace();

        }
    }
}
