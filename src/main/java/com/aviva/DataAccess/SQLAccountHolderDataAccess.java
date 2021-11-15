package com.aviva.DataAccess;

import java.sql.*;

/**
 * Public class that handles querying data from the database for clients
 */

public class SQLAccountHolderDataAccess implements AccountAccessInterface {

    // RDS Credentials
    public String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public String user = System.getenv("AVANTAGE_SQLDB_USER");
    public String password = System.getenv("AVANTAGE_SQLDB_PWD");


    /**
     * Returns the ResultSet of the query to get a client's information from the database by their accountNumber
     * @param accountNumber the account number of the client
     * @return ResultSet of SQL query to get the client's information.
     */
    public ResultSet getClient(String accountNumber) {
        try {
            // Establish connection with database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Connect to aviva database
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query a client given the account number
            String command = "SELECT * FROM client WHERE accountNumber = '" + accountNumber + "' LIMIT 1";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get client");
        }

        return null;
    }

    /**
     * Returns the ResultSet of the query to get all clients' information from the database
     * @return ResultSet of SQL query to get all clients' information.
     */
    public ResultSet getAllClients() {
        try {
            // Establish connection with database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Connect to aviva database
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query all clients
            String command = "SELECT * FROM client";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get all clients");
        }
        return null;
    }

    /**
     * Returns the ResultSet of the query to get a particular client's latest credit score
     * @param accountNumber the account number of the client
     * @return ResultSet of SQL query to get the client's latest credit score.
     */
    public ResultSet getLatestCreditScore(String accountNumber) {
        try {
            // Establish connection with database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Connect to aviva database
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query a client's latest credit score by sorting their credit check dates in order
            String command = "SELECT * FROM credit WHERE accountNumber = " + "'" + accountNumber + "' ORDER BY queryDate DESC LIMIT 1";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get latest credit score");
        }

        return null;
    }

    /**
     * Returns the ResultSet of the query to get all financial transactions for a particular client
     * @param accountNumber the account number of the client
     * @return ResultSet of SQL query to get all financial transactions stored for the client.
     */
    public ResultSet getAllFinancialTransactions(String accountNumber) {
        try {
            // Establish connection with database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Connect to aviva database
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query all transactions of a client given their account number
            String command = "SELECT * FROM banking WHERE accountNumber = '" + accountNumber + "'";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get all financial transactions");
        }

        return null;
    }
}
