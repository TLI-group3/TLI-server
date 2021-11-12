package com.aviva.initDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class initDB {

    // RDS Credentials
    public static String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public static String user = System.getenv("AVANTAGE_SQLDB_USER");
    public static String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public static void main(String[] args) {
        // Attempt to connect to RDS instance
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected");
            deleteTable(connection, "recommendations");
            deleteDatabase(connection);
            createDatabase(connection);
            createCarTable(connection);
            createBankingTable(connection);
            createCreditTable(connection);
            createClientTable(connection);
            createRecommendationsTable(connection);
            writeCarData(connection, "C:/Users/kalam/Desktop/senso/TLI-server/data/Car_Data.csv");
            writeBankingData(connection, "C:/Users/kalam/Desktop/senso/TLI-server/data/Banking_Data.csv");
            writeCreditData(connection, "C:/Users/kalam/Desktop/senso/TLI-server/data/Credit_Data.csv");
            writeClientData(connection);
            writeRecommendationsData(connection);
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
            String command = "DROP DATABASE aviva" ;
            statement.execute(command);
            System.out.println("Successfully deleted database: aviva");
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
            command = "CREATE TABLE cars(vin VARCHAR(255), price INT(8), brand VARCHAR(24), model VARCHAR(24), modelYear INT(8), image VARCHAR(5000), PRIMARY KEY (vin))";
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
            command = "CREATE TABLE banking(accountNumber VARCHAR(255), transactionDate VARCHAR(24), deposits FLOAT(8), withdrawals FLOAT(8))";
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
            command = "CREATE TABLE credit(accountNumber VARCHAR(255), queryDate DATE, creditScore INT(8))";
            statement.execute(command);
            System.out.println("Successfully created table: credit");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void createClientTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "USE aviva";
            statement.execute(command);
            command = "CREATE TABLE client(accountNumber VARCHAR(255), fullName VARCHAR(24), previousCar VARCHAR(24), PRIMARY KEY(accountNumber))";
            statement.execute(command);
            System.out.println("Successfully created table: client");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void createRecommendationsTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String command = "USE aviva";
            statement.execute(command);
            command = "CREATE TABLE recommendations(accountNumber VARCHAR(255), carOne VARCHAR(24), carTwo VARCHAR(24), carThree VARCHAR(24), carFour VARCHAR(24), carFive VARCHAR(24), PRIMARY KEY(accountNumber))";
            statement.execute(command);
            System.out.println("Successfully created table: recommendations");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

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
            System.out.println("Successfully inserted data into table: cars");

        } catch (SQLException | IOException e) {
            System.out.println(e);
        }
    }

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
            System.out.println(e);
        }
    }

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
            System.out.println(e);
        }
    }

    public static void writeClientData(Connection connection) {
        // Initialize variables
        String command = "INSERT INTO client (accountNumber, fullName, previousCar) VALUES (?, ?, ?)";
        int batchSize = 10;
        int count = 0;

        try {
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            PreparedStatement preparedStatement = connection.prepareStatement(command); // Prepare SQL Command
            String queryCommand = "SELECT DISTINCT(accountNumber) FROM banking;";
            ResultSet rs = statement.executeQuery(queryCommand);

            while (rs.next()) {
                // Prepare data to match the table's data types
                preparedStatement.setString(1, rs.getString("accountNumber"));
                preparedStatement.setString(2, "John Smith");
                preparedStatement.setNull(3, 0);

                count = count + 1;
                // Execute batch of SQL commands
                preparedStatement.addBatch();
                if (count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }

            preparedStatement.executeBatch(); // Execute remaining SQL commands
            System.out.println("Successfully inserted data into table: client");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void writeRecommendationsData(Connection connection) {
        // Initialize variables
        String command = "INSERT INTO recommendations (accountNumber) VALUES (?)";
        int batchSize = 10;
        int count = 0;

        try {
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            PreparedStatement preparedStatement = connection.prepareStatement(command); // Prepare SQL Command
            String queryCommand = "SELECT DISTINCT(accountNumber) FROM banking;";
            ResultSet rs = statement.executeQuery(queryCommand);

            while (rs.next()) {
                // Prepare data to match the table's data types
                preparedStatement.setString(1, rs.getString("accountNumber"));

                count = count + 1;
                // Execute batch of SQL commands
                preparedStatement.addBatch();
                if (count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }

            preparedStatement.executeBatch(); // Execute remaining SQL commands
            System.out.println("Successfully inserted data into table: recommendations");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

}
