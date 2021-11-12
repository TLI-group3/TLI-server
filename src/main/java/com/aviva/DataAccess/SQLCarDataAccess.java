package com.aviva.DataAccess;

import java.sql.*;

public class SQLCarDataAccess implements CarAccessInterface {

    public static String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public static String user = System.getenv("AVANTAGE_SQLDB_USER");
    public static String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public static void main(String[] args) {
        getCar("2018 ford focus");
        getAllCars();
        insertRecommendedCar("1402110922112412", "2018 ford focus");

    }

    public static ResultSet getCar(String carName) {
        ResultSet rs = null;
        String[] carDetails = carName.split(" ");
        String carYear = carDetails[0];
        String carBrand = carDetails[1];
        String carMake = carDetails[2];

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM cars WHERE modelYear = '" + carYear + "' AND brand = '" + carBrand + "' AND model = '" + carMake + "' LIMIT 1";
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

    public static void insertRecommendedCar(String accountNumber, String carName) {
        int count = 0;
        String[] columns = {"carOne", "carTwo", "carThree", "carFour", "carFive"};
        ResultSet rs = null;
        boolean flag = false;
        String nullString = "null";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM recommendations WHERE accountnumber = '" + accountNumber + "'";
            rs = statement.executeQuery(command);
            if (rs.next()) {
                for (int i = 2; i < 7; i++) {
                    String columnValue = rs.getString(i);
                    if (columnValue == null) {
                        flag = true;
                        command = "UPDATE recommendations SET " + columns[i - 2] + " = '" + carName + "' WHERE accountnumber = '" + accountNumber + "'";
                        statement.execute(command);
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Account holder already has 5 recommended cars");
                }
            }
            else {
                System.out.println("Could not find specified account holder");
            }

        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
