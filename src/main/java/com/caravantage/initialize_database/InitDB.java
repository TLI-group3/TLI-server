package com.caravantage.initialize_database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * This class is responsible for instantiating the database structure and inserting data into it
 */

public class InitDB {

    // RDS Credentials
    public static String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public static String user = System.getenv("AVANTAGE_SQLDB_USER");
    public static String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public static void main(String[] args) {

        try {
            // Establish connection
            Connection connection = DriverManager.getConnection(url, user, password);

            // Calls to every method to re-setup the database
            deleteTable(connection, "cars");
            deleteDatabase(connection);
            createDatabase(connection);
            createTable(connection, "CREATE TABLE cars(vin VARCHAR(255), price INT(8), brand VARCHAR(24), " +
                    "model VARCHAR(24), modelYear INT(8), image VARCHAR(5000), PRIMARY KEY (vin))");
            createTable(connection, "CREATE TABLE banking(accountNumber VARCHAR(255), " +
                    "transactionDate VARCHAR(24), deposits FLOAT(8), withdrawals FLOAT(8))");
            createTable(connection, "CREATE TABLE credit(accountNumber VARCHAR(255), " +
                    "queryDate DATE, creditScore INT(8))");
            createTable(connection, "CREATE TABLE recommendations(carID VARCHAR(255), " +
                    "accountNumber VARCHAR(255), vin VARCHAR (255), loanAmount FLOAT(32), interestSum FLOAT(32), " +
                    "capitalSum FLOAT(32), loanSum FLOAT(32), loanTerm INT(8), interestRate FLOAT(32), PRIMARY KEY(carID))");
            createTable(connection, "CREATE TABLE installments(carID VARCHAR(255), termNumber INT(8), " +
                    "termCapital FLOAT(32), termInterest FLOAT(32), termInstallment FLOAT(32), " +
                    "remainingAmount FLOAT(32), interestSum FLOAT(32))");
            writeCarData(connection, "data/Car_Data.csv");
            writeBankingData(connection, "data/Banking_Data.csv");
            writeCreditData(connection, "data/Credit_Data.csv");
        }
        // Print error statement if connection fails
        catch (SQLException e) {
            System.out.println("Failed to connect to the database");
        }
    }

    /**
     * Creates a database named aviva on RDS
     * @param connection communication link with RDS
     */
    public static void createDatabase(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "CREATE DATABASE aviva";
            statement.execute(command);
            System.out.println("Successfully created database: aviva");
        }
        catch (SQLException e) {
            System.out.println("Failed to create database");
        }
    }

    /**
     * Deletes a database named aviva on RDS
     * @param connection communication link with RDS
     */
    public static void deleteDatabase(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "DROP DATABASE aviva" ;
            statement.execute(command);
            System.out.println("Successfully deleted database: aviva");
        }
        catch (SQLException e) {
            System.out.println("Failed to delete database");
        }
    }

    /**
     * Creates a table specified by a command on the aviva database
     * @param connection communication link with RDS
     * @param command string representing the SQL command to create a table in the database
     */
    public static void createTable(Connection connection, String command) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            statement.execute(command);
            System.out.println("Successfully created table with the following command: " + command);
        }
        catch (SQLException e) {
            System.out.println("Failed to create table with the following command: " + command);
        }
    }

    /**
     * Deletes a table from the aviva database given its name
     * @param connection communication link with RDS
     * @param table name of the table to delete
    */
    public static void deleteTable(Connection connection, String table) {
        try {
            Statement statement = connection.createStatement();
            String command = "USE aviva";
            statement.execute(command);
            command = "DROP TABLE " + table;
            statement.execute(command);
            System.out.println("Successfully deleted table: " + table);
        }
        catch (SQLException e) {
            System.out.println("Failed to delete table");
        }
    }

    /**
     * Writes car data into the cars table on the aviva database given a file path the car data csv
     * @param connection communication link with RDS
     * @param file csv filepath to cars data
     */
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

            // Prepare SQL statement
            PreparedStatement preparedStatement = connection.prepareStatement(command); // Prepare SQL Command
            BufferedReader lineReader = new BufferedReader(new FileReader(file)); // Open CSV file to read
            lineReader.readLine(); // Skip CSV header

            // Loop to read through each line in CSV file
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String vin = data[0].trim();
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
            System.out.println("Successfully inserted data into table: cars");

        } catch (SQLException | IOException e) {
            System.out.println("Failed to write car data");
        }
    }

    /**
     * Writes banking data into the banking table on the aviva database given a file path the banking data csv
     * @param connection communication link with RDS
     * @param file csv filepath to banking data
     */
    public static void writeBankingData(Connection connection, String file) {
        // Initialize variables
        String command = "INSERT INTO banking (accountNumber, transactionDate, deposits, withdrawals) VALUES (?, ?, ?, ?)";
        int batchSize = 10;
        int count = 0;
        String lineText = "";
        String withdrawals = "";

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
                String accountNumber = data[0];
                String transactionDate = data[1];
                String deposits = data[2];
                // Special case when withdrawals field is empty in CSV
                if (data.length > 3) {
                    withdrawals = data[3];
                }
                else {
                    withdrawals = "";
                }

                // Prepare data to match the table's data types
                preparedStatement.setString(1, accountNumber);
                preparedStatement.setString(2, transactionDate);
                if (!deposits.isEmpty()) {
                    preparedStatement.setFloat(3, Float.parseFloat(deposits));
                }
                else {
                    preparedStatement.setNull(3, 0);
                }
                if (!withdrawals.isEmpty()) {
                    preparedStatement.setFloat(4, Float.parseFloat(withdrawals));
                }
                else {
                    preparedStatement.setNull(4, 0);
                }

                count = count + 1;
                // Execute batch of SQL commands
                preparedStatement.addBatch();
                if (count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }

            lineReader.close(); // Close CSV file
            preparedStatement.executeBatch(); // Execute remaining SQL commands
            System.out.println("Successfully inserted data into table: banking");

        } catch (SQLException | IOException e) {
            System.out.println("Failed to write banking data");
        }
    }

    /**
     * Writes credit data into the credit table on the aviva database given a file path the credit data csv
     * @param connection communication link with RDS
     * @param file csv filepath to credit data
     */
    public static void writeCreditData(Connection connection, String file) {
        // Initialize variables
        String command = "INSERT INTO credit (accountNumber, queryDate, creditScore) VALUES (?, ?, ?)";
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
                String accountNumber = data[0];
                String date = data[1];
                String creditScore = data[2];


                // Prepare data to match the table's data types
                preparedStatement.setString(1, accountNumber);
                Date queryDate = Date.valueOf(date);
                preparedStatement.setDate(2, queryDate);
                preparedStatement.setInt(3, Integer.parseInt(creditScore));


                count = count + 1;
                // Execute batch of SQL commands
                preparedStatement.addBatch();
                if (count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }

            lineReader.close(); // Close CSV file
            preparedStatement.executeBatch(); // Execute remaining SQL commands
            System.out.println("Successfully inserted data into table: credit");

        } catch (SQLException | IOException e) {
            System.out.println("Failed to write credit data");
        }
    }
}
