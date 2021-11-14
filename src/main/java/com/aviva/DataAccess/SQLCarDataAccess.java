package com.aviva.DataAccess;

import java.sql.*;

public class SQLCarDataAccess implements CarAccessInterface {

    public String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public String user = System.getenv("AVANTAGE_SQLDB_USER");
    public String password = System.getenv("AVANTAGE_SQLDB_PWD");

    public ResultSet getCar(String carName) {
        String[] carDetails = carName.split(" ");
        String carYear = carDetails[0];
        String carBrand = carDetails[1];
        String carMake = carDetails[2];

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM cars WHERE modelYear = '" + carYear + "' AND brand = '" + carBrand + "' AND model = '" + carMake + "' LIMIT 1";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get car");
        }

        return null;
    }

    public ResultSet getAllCars() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM cars";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get all cars");
        }

        return null;
    }

    public void insertRecommendedCar(String accountNumber, String carName) {
        String[] columns = {"carOne", "carTwo", "carThree", "carFour", "carFive"};
        boolean flag = false;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");
            String command = "SELECT * FROM recommendations WHERE accountnumber = '" + accountNumber + "'";
            ResultSet rs = statement.executeQuery(command);
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
            System.out.println("Failed to insert recommendations into database");
        }
    }
}
