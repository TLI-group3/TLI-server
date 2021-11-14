package com.aviva.DataAccess;

import java.sql.*;

public class SQLAccountHolderDataAccess implements AccountAccessInterface {

    public String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public String user = System.getenv("AVANTAGE_SQLDB_USER");
    public String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public ResultSet getClient(String accountNumber) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM client WHERE accountNumber = '" + accountNumber + "' LIMIT 1";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get client");
        }

        return null;
    }

    public ResultSet getAllClients() {

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM client";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get all clients");
        }
        return null;
    }

    public ResultSet getLatestCreditScore(String accountNumber) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM credit WHERE accountNumber = " + "'" + accountNumber + "' ORDER BY queryDate DESC LIMIT 1";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get latest credit score");
        }

        return null;
    }

    public ResultSet getAllFinancialTransactions(String accountNumber) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM banking WHERE accountNumber = '" + accountNumber + "'";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get all financial transactions");
        }

        return null;
    }
}
