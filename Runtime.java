import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Runtime {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // JDBC driver name and database URL
            // Simulate a hardcoded user/password
            String jdbcUrl = "jdbc:mysql://localhost:3306/idp";
            String username = "idpUser";
            String password = "123456";

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();

            // Simulate a vulnerable query using SQL concatenation (DO NOT use this in production)
            int userId = 1; // Replace with the actual user ID you want to retrieve
            String sql = "SELECT * FROM users_table WHERE user_id = " + userId; // SQL concatenation

            // Execute the vulnerable query
            resultSet = statement.executeQuery(sql);

            // Process the result
            if (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String usernameValue = resultSet.getString("username");
                String email = resultSet.getString("email");

                System.out.println("User Retrieved:");
                System.out.println("ID: " + id);
                System.out.println("Username: " + usernameValue);
                System.out.println("Email: " + email);
            } else {
                System.out.println("User not found!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block to ensure they're closed properly
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//test