/*
 * Establish connection with RDS instance
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    
    // RDS Credentials
    public static String url = "jdbc:mysql://cb-database.c0bn4vphjqef.us-east-2.rds.amazonaws.com";
    public static String user = "Alamgir";
    public static String password = "tligroup3";

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
