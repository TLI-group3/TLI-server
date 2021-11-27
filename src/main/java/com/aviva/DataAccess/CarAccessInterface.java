package com.aviva.DataAccess;

import com.aviva.Entities.Car;
import com.aviva.Entities.Loan;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

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
     * @param recommendations a map of Car Entities to their respective Loan entity
     */
    public void insertRecommendedCars(String accountNumber, HashMap<Car, Loan> recommendations);
}
