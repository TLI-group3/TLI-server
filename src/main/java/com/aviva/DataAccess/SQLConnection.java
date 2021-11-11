/*
 * Establish connection with RDS instance
 */
package com.aviva.DataAccess;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.aviva.Entities.Car;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

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
            writeCarData(connection, "C:/Users/kalam/Desktop/senso/TLI-server/data/Car_Data.csv");
//            testCarsTable(connection);
//            deleteDatabase(connection);
//            createDatabase(connection);
//            createCarTable(connection);
        }
        // Print error statement if connection fails
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void createDatabase(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "CREATE DATABASE aviva";
            System.out.println("Successfully created database: aviva");
            statement.execute(command);
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void deleteDatabase(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "DROP DATABASE aviva";
            statement.execute(command);
            System.out.println("Successfully deleted table: aviva");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void createCarTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "USE aviva";
            statement.execute(command);
            command = "CREATE TABLE cars(vin VARCHAR(24), price INT(8), brand VARCHAR(24), model VARCHAR(24), modelYear INT(8), image VARCHAR(5000), PRIMARY KEY (vin))";
            statement.execute(command);
            System.out.println("Successfully created table: cars");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void createBankingTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "USE aviva";
            statement.execute(command);
            command = "CREATE TABLE banking(accountNumber VARCHAR(24), date VARCHAR(24), deposits INT(8), withdrawals INT(8))";
            statement.execute(command);
            System.out.println("Successfully created table: banking");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void createCreditTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "USE aviva";
            statement.execute(command);
            command = "CREATE TABLE credit(accountNumber VARCHAR(24), date VARCHAR(24), creditScore INT(8))";
            statement.execute(command);
            System.out.println("Successfully created table: credit");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void writeCarData(Connection connection, String file) {
        // Initialize variables
        String command = "INSERT INTO cars (vin, price, brand, model, modelYear, image) VALUES (?, ?, ?, ?, ?, ?)";
        int batchSize = 10;
        int count = 0;
        String lineText = "";

        try {
            // Connect to aviva database
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            PreparedStatement preparedStatement = connection.prepareStatement(command); // Prepare SQL Command
            BufferedReader lineReader = new BufferedReader(new FileReader(file)); // Open CSV file to read
            lineReader.readLine(); // Skip CSV header

            // Loop to read through each line in CSV file
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String vin = data[0];
                String price = data[1];
                String brand = data[2];
                String model = data[3];
                String year = data[4];
                String image = data[5];

                // Prepare data to match the table's data types
                preparedStatement.setString(1, vin);
                preparedStatement.setInt(2, Integer.parseInt(price));
                preparedStatement.setString(3, brand);
                preparedStatement.setString(4, model);
                preparedStatement.setInt(5, Integer.parseInt(year));
                preparedStatement.setString(6, image);

                count = count + 1;
                // Execute batch of SQL commands
                preparedStatement.addBatch();
                if (count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }

            lineReader.close(); // Close CSV file
            preparedStatement.executeBatch(); // Execute remaining SQL commands

        } catch (SQLException | IOException e) {
            System.out.println(e);
        }
    }

    public static void testCarsTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "USE aviva";
            statement.execute(command);
            command = "SELECT * FROM cars WHERE brand = 'nissan'";
            ResultSet rs = statement.executeQuery(command);
            System.out.println(rs.getString(1));
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}