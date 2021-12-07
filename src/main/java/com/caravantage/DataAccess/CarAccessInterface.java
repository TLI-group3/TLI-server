package com.caravantage.DataAccess;

import java.sql.ResultSet;

/**
 * This is the Data Access Interface for the data of the cars
 */

public interface CarAccessInterface {

    /**
     * Returns the ResultSet of the query to get a car by its name
     * @param carName the name of a car in the format: YEAR MAKE MODEL
     * @return ResultSet of SQL query to get the car.
     */
    public ResultSet getCarByName(String carName);

    /**
     * Returns the ResultSet of the query to get a car by its vin number
     * @param vin the vin number of a car
     * @return ResultSet of SQL query to get the car.
     */
    public ResultSet getCarByVin(String vin);

    /**
     * Returns the ResultSet of the query to get all cars
     * @return ResultSet of SQL query to get all cars.
     */
    public ResultSet getAllCars();

}
