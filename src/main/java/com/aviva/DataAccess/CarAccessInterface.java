package com.aviva.DataAccess;

import java.sql.ResultSet;

/**
 * This is the Data Access Interface for the data of the cars
 */

public interface CarAccessInterface {

    /**
     * Returns the ResultSet of the query to get a car by its name
     * @param carName the name of a car
     * @return ResultSet of SQL query to get the car.
     */
    public ResultSet getCar(String carName);

    /**
     * Returns the ResultSet of the query to get all cars
     * @return ResultSet of SQL query to get all cars.
     */
    public ResultSet getAllCars();

    /**
     * Inserts a car name against an account number into a table
     * @param accountNumber the account number of the client
     * @param carName the name of the car to insert
     */
    public void insertRecommendedCar(String accountNumber, String carName);
}
