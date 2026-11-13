
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateAccount {

    private static final String URL =
        "jdbc:mysql://localhost:3306/banking_db";

    private static final String USER = "banking_user";

    private static final String PASSWORD = "Banking@2026";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== BANK ACCOUNT CREATION =====");

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();

        String sql = "INSERT INTO accounts "
                   + "(account_holder, account_number, balance) "
                   + "VALUES (?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, name);
            ps.setString(2, accountNumber);
            ps.setDouble(3, 0.00);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                    "Account created successfully!"
                );
                System.out.println("Account Holder: " + name);
                System.out.println(
                    "Account Number: " + accountNumber
                );
                System.out.println("Initial Balance: Rs. 0.00");
            }

        } catch (SQLException e) {
            System.out.println("Account creation failed!");
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
