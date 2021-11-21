package com.aviva.DataAccess;

import com.aviva.Entities.Installment;
import com.aviva.Entities.RecommendedCar;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

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
     * @param car the RecommendedCar entity to insert against the client
     */
    public void insertRecommendedCar(String accountNumber, RecommendedCar car) {
        try {
            // Establish connection with aviva database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query for existing recommendations for a client with the given account number
            String command = "SELECT * FROM client WHERE accountnumber = '" + accountNumber + "'";
            ResultSet rs = statement.executeQuery(command);

            // Delete previous recommendations if 5 cars already recommended
            deletePreviousRecommendations(connection, accountNumber);

            if (rs.next()) { // Check to client already has recommendations
                String uniqueID = UUID.randomUUID().toString(); // Generate unique carID
                insertIntoRecommendations(connection, accountNumber, car, uniqueID);
                insertIntoInstallments(connection, car, uniqueID);
            }
            else {
                System.out.println("Could not find specified account holder");
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to insert recommendations into database");
        }
    }

    /**
     * Helper method to insert data into recommendations table
     */
    public void insertIntoRecommendations(Connection connection, String accountNumber, RecommendedCar car, String uniqueID) throws SQLException {
        String command = "INSERT INTO recommendations (carID, accountNumber, carYear, carBrand, carMake, loanAmount, interestSum, capitalSum, loanSum, loanTerm, interestRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(command); // Prepare SQL Command

        // Match variables against SQL data types
        preparedStatement.setString(1, uniqueID);
        preparedStatement.setString(2, accountNumber);
        preparedStatement.setInt(3, car.getYear());
        preparedStatement.setString(4, car.getMake());
        preparedStatement.setString(5, car.getModel());
        preparedStatement.setFloat(6, car.getLoanAmount());
        preparedStatement.setFloat(7, car.getInterestSum());
        preparedStatement.setFloat(8, car.getCapitalSum());
        preparedStatement.setFloat(9, car.getLoanSum());
        preparedStatement.setInt(10, car.getLoanTerm());
        preparedStatement.setFloat(11, car.getInterestRate());

        // Execute SQL commands
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }

    /**
     * Helper method to insert data into installments table
     */
    public void insertIntoInstallments(Connection connection, RecommendedCar car, String uniqueID) throws SQLException {
        String command = "INSERT INTO recommendations (carID, termNumber, termCapital, termInterest, termInstallment, remainingAmount, interestSum) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(command); // Prepare SQL Command

        // Loop through each installment of the RecommendedCar
        for (Installment installment: car.getInstallments()) {
            // Match variables against SQL data types
            preparedStatement.setString(1, uniqueID);
            preparedStatement.setInt(2, installment.getTermNumber());
            preparedStatement.setFloat(3, installment.getTermCapital());
            preparedStatement.setFloat(4, installment.getTermInterest());
            preparedStatement.setFloat(5, installment.getTermInstallment());
            preparedStatement.setFloat(6, installment.getRemainingAmount());
            preparedStatement.setFloat(7, installment.getInterestSum());

            preparedStatement.addBatch(); // Add to batch of statements to execute
        }
        preparedStatement.executeBatch(); // Execute SQL commands
    }

    /**
     * Helper method to delete data from recommendations and installments tables if an account number already has 5 recommended cars
     */
    public void deletePreviousRecommendations (Connection connection, String accountNumber) {
        ArrayList<String> carIDs = new ArrayList<>();

        try {
            // Query how many existing recommendations this account number has
            Statement statement = connection.createStatement();
            String command = "SELECT * FROM recommendations WHERE accountnumber = '" + accountNumber + "'";
            ResultSet rs = statement.executeQuery(command);

            // Add IDs of existing recommendations to a list
            while (rs.next()) {
                carIDs.add(rs.getString(1));
                }

            // Delete all records of recommendations and installments if there are already 5 recommendations
            if (carIDs.size() >= 5) {
                // Delete all records from recommendations table
                command = "DELETE FROM recommendations WHERE accountnumber = '" + accountNumber + "'";
                statement.execute(command);

                // Delete all records from installments table
                for (String ID : carIDs) {
                    command = "DELETE FROM installments WHERE carID = '" + ID + "'";
                    statement.execute(command);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("This account number has less than 5 recommended cars");
        }

    }
}
