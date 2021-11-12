package com.aviva.DataAccess;

import java.sql.*;

public class SQLAccountHolderData implements AccountAccessInterface {

    public static String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public static String user = System.getenv("AVANTAGE_SQLDB_USER");
    public static String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public static void main(String[] args) {
//        getClientByID("1402110922112412");
//        getAllClients();
    }

    public static ResultSet getClientByID(String ID) {
        ResultSet rs = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM client WHERE accountNumber = '" + ID + "' LIMIT 1";
            rs = statement.executeQuery(command);
            return rs;
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return rs;
    }

    public static ResultSet getAllClients() {
        ResultSet rs = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM client";
            rs = statement.executeQuery(command);
            return rs;
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return rs;
    }
}
