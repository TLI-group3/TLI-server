package com.aviva.DataAccess;

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
     * Returns the ResultSet of the query to get a car by its name
     * @param carName the name of a car
     * @return ResultSet of SQL query to get the car.
     */
    public ResultSet getCar(String carName) {
        // Split the car name string into its year, brand and make
        String[] carDetails = carName.split(" ");
        String carYear = carDetails[0];
        String carBrand = carDetails[1];
        String carMake = carDetails[2];

        try {
            // Establish connection with aviva database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query for a car that matches the given year, brand and model
            String command = "SELECT * FROM cars WHERE modelYear = '" + carYear + "' AND brand = '" + carBrand + "' AND model = '" + carMake + "' LIMIT 1";
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

    /**
     * Inserts a car name against an account number into a table
     * @param accountNumber the account number of the client
     * @param carName the name of the car to insert
     */
    public void insertRecommendedCar(String accountNumber, String carName) {
        String[] columns = {"carOne", "carTwo", "carThree", "carFour", "carFive"};
        boolean flag = false;

        try {
            // Establish connection with aviva database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query for existing recommendations for a client with the given account number
            String command = "SELECT * FROM recommendations WHERE accountnumber = '" + accountNumber + "'";
            ResultSet rs = statement.executeQuery(command);

            if (rs.next()) { // Check to client already has recommendations
                // Loop through each possible recommendation
                for (int i = 2; i < 7; i++) {
                    // Check if the client does not have an ith recommendation
                    String columnValue = rs.getString(i);
                    if (columnValue == null) {
                        // Add the car to the recommendations table
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
