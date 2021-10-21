/*
 * This is a temporary file to see if we can insert data into the database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {

    public static String url = "jdbc:mysql://localhost";
    public static String user = "Alamgir";
    public static String password = "tlig3p1";

    public static void main(String[] args) {
        try {
            System.out.println("1");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            String sqlCommand = "USE senso";
            System.out.println("2");
            statement.executeUpdate(sqlCommand);
            sqlCommand = "INSERT INTO customers (firstName, lastName, creditScore, savings) VALUES('Alamgir', 'Khan', 740, 10000)";
            System.out.println("3");
            statement.executeUpdate(sqlCommand);

        }
        catch (SQLException e) {
            System.out.println(e);
        }

    }
}
