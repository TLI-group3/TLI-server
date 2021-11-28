package com.aviva.DataAccess;

import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import com.aviva.Entities.Installment;
import com.aviva.Entities.Loan;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Public class that handles querying data from the database for clients
 */

public class SQLAccountHolderDataAccess implements AccountAccessInterface {

    // RDS Credentials
    public String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
    public String user = System.getenv("AVANTAGE_SQLDB_USER");
    public String password = System.getenv("AVANTAGE_SQLDB_PWD");

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

    /**
     * Inserts a car name against an account number into a table
     * @param accountHolder the AccountHolder entity for which to insert the recommendations
     */
    public void insertRecommendedCars(AccountHolder accountHolder) {
        try {
            // Establish connection with aviva database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query for existing recommendations for a client with the given account number
            String accountNumber = accountHolder.getAccountNumber();
            String command = "SELECT * FROM client WHERE accountnumber = '" + accountNumber + "'";
            ResultSet rs = statement.executeQuery(command);

            // Delete previous recommendations if 5 cars already recommended
            deletePreviousRecommendations(connection, accountNumber);

            if (rs.next()) {
                // Loop through entry in the HashMap
                for (Map.Entry<Car, Loan> entry : accountHolder.getRecommendedCars().entrySet()) {
                    Car car = entry.getKey();
                    Loan loan = entry.getValue();

                    // Generate and set unique carID
                    String uniqueID = UUID.randomUUID().toString();
                    car.setID(uniqueID);

                    insertIntoRecommendations(connection, accountNumber, car, loan);
                    insertIntoInstallments(connection, loan, car.getID());
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

    /**
     * Helper method to insert data into recommendations table
     */
    public void insertIntoRecommendations(Connection connection, String accountNumber, Car car, Loan loan) throws SQLException {
        String command = "INSERT INTO recommendations (carID, accountNumber, carYear, carBrand, carMake, loanAmount, interestSum, capitalSum, loanSum, loanTerm, interestRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(command); // Prepare SQL Command

        // Match variables against SQL data types
        preparedStatement.setString(1, car.getID());
        preparedStatement.setString(2, accountNumber);
        preparedStatement.setInt(3, car.getYear());
        preparedStatement.setString(4, car.getMake());
        preparedStatement.setString(5, car.getModel());
        preparedStatement.setFloat(6, loan.getLoanAmount());
        preparedStatement.setFloat(7, loan.getInterestSum());
        preparedStatement.setFloat(8, loan.getCapitalSum());
        preparedStatement.setFloat(9, loan.getLoanSum());
        preparedStatement.setInt(10, loan.getLoanTerm());
        preparedStatement.setFloat(11, loan.getInterestRate());

        // Execute SQL commands
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }

    /**
     * Helper method to insert data into installments table
     */
    public void insertIntoInstallments(Connection connection, Loan loan, String uniqueID) throws SQLException {
        String command = "INSERT INTO installments (carID, termNumber, termCapital, termInterest, termInstallment, remainingAmount, interestSum) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(command); // Prepare SQL Command

        // Loop through each installment of the RecommendedCar
        for (Installment installment: loan.getInstallments()) {
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

    /**
     * Returns the ResultSet of the query to get all recommended cars IDS for a particular client
     * @param accountNumber the account number of the client
     * @return ResultSet of SQL query to get all recommended cars IDS stored for the client.
     */
    public ResultSet getAllRecommendations(String accountNumber) {
        try {
            // Establish connection with database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Connect to aviva database
            Statement statement = connection.createStatement();
            statement.execute("USE aviva");

            // Query all transactions of a client given their account number
            String command = "SELECT * FROM recommendations WHERE accountNumber = '" + accountNumber + "'";
            return statement.executeQuery(command);
        }
        catch (SQLException e) {
            System.out.println("Failed to get recommended car IDS");
        }

        return null;
    }
}
