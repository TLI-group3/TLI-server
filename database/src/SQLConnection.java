/*
 * This is a temporary file to see if we can insert data into the database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {

    public static String url = "jdbc:mysql://cb-database.c0bn4vphjqef.us-east-2.rds.amazonaws.com";
    public static String user = "Alamgir";
    public static String password = "tligroup3";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            System.out.println("Successfully connected");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

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