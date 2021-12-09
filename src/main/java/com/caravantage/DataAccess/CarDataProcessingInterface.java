package com.caravantage.DataAccess;

import com.caravantage.Entities.Car;
import java.util.ArrayList;

/**
 * This interface defines methods that process data from our database into
 * a format that our business rules can use.
 *
 * Implementations of this interface should NOT talk directly to the SQL db
 */

public interface CarDataProcessingInterface {
    /**
     * Iterates through our table of cars and returns all of them
     * @return a list of car objects from our database sorted by ascending price
     */
    public ArrayList<Car> getAllCars();

    /**
     * @param vin the vin number of the car to query
     * @return a Car object using the database
     */
    public Car getCarByVin(String vin);
}