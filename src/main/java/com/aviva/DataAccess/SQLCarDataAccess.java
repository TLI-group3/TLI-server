package com.aviva.DataAccess;

import java.sql.*;

public class SQLCarDataAccess implements CarAccessInterface {

    public static String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public static String user = System.getenv("AVANTAGE_SQLDB_USER");
    public static String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public static void main(String[] args) {
        getCar("19xfb2f81fe252000");
        getAllCars();

    }

    public static ResultSet getCar(String vin) {
        ResultSet rs = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM cars WHERE vin = '" + vin + "' LIMIT 1";
            rs = statement.executeQuery(command);
            return rs;
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return rs;
    }

    public static ResultSet getAllCars() {
        ResultSet rs = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM cars";
            rs = statement.executeQuery(command);
            return rs;
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return rs;
    }

    public static void insertRecommendedCar(String accountNumber, String carID) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM cars";
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
