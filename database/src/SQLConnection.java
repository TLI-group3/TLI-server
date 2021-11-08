/*
 * Establish connection with RDS instance
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    
    // RDS Credentials
    public static String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public static String user = System.getenv("AVANTAGE_SQLDB_USER");
    public static String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public static void main(String[] args) {
        // Attempt to connect to RDS instance
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected");
        }
        // Print error statement if connection fails
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
    * Inserts a string into a dummy table in the database
    */
    public static void insertInstallments(Connection connection, String intallments) {
        try {
            Statement statement = connection.createStatement();

            String sqlCommand = "USE senso";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "INSERT INTO customers (clientID, cars) VALUES('1007212080', " + intallments + ")";
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
