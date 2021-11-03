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

    public void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            insertInstallments(connection, "{\"cars\":[{price: 100000, make: \"Aventador\", model: \"S\",year: \"2020\"},{price: 50000,make: \"SUV\",year: \"2021\",model:\"Explorer\"}]}");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertInstallments(Connection connection, String intallments) {
        try {
            Statement statement = connection.createStatement();

            String sqlCommand = "USE senso";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "INSERT INTO customers (clientID, cars) VALUES('1007212080', " + intallments + ")";
            statement.executeUpdate(sqlCommand);
        }
        catch (SQLException e) {
            System.out.println(e);
        }

    }