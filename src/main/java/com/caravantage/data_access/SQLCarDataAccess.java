package com.caravantage.data_access;

import java.sql.*;

/**
 * Public class that handles querying data from the database for cars
 */

public class SQLCarDataAccess implements CarAccessInterface {

    // RDS Credentials
    public String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public String user = System.getenv("AVANTAGE_SQLDB_USER");
    public String password = System.getenv("AVANTAGE_SQLDB_PWD");

    /**
     * Returns the ResultSet of the query to get a car by its vin number
     * @param vin the vin number of a car
     * @return ResultSet of SQL query to get the car.
     */
    public ResultSet getCarByVin(String vin) {
        try {
            // Establish connection with aviva database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query for a car that matches the given year, brand and model
            String command = "SELECT * FROM cars WHERE vin = '" + vin + "' LIMIT 1";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get car");
        }

        return null;
    }

    /**
     * Returns the ResultSet of the query to get all cars
     * @return ResultSet of SQL query to get all cars.
     */
    public ResultSet getAllCars() {
        try {
            // Establish connection with aviva database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query for all cars
            String command = "SELECT * FROM cars";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get all cars");
        }

        return null;
    }

}
