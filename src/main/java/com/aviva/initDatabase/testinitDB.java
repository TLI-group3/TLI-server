package com.aviva.initDatabase;

import java.sql.*;

public class testinitDB {

    // RDS Credentials
    public static String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public static String user = System.getenv("AVANTAGE_SQLDB_USER");
    public static String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public static void main(String[] args) {
        // Attempt to connect to RDS instance
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected");
            testCarsTable(connection);
        }
        // Print error statement if connection fails
        catch (SQLException e) {
            System.out.println("Failed to connect to database");
        }
    }

    public static void testCarsTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "USE aviva";
            statement.execute(command);
            command = "SELECT * FROM cars WHERE brand = 'nissan'";
            ResultSet rs = statement.executeQuery(command);
            if (rs.next()) {
                System.out.println(rs.getString("vin"));
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to get test car data");
        }
    }
}
