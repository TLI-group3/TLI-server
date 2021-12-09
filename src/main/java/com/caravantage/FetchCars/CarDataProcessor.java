package com.caravantage.FetchCars;

import com.caravantage.DataAccess.CarAccessInterface;
import com.caravantage.Entities.Car;
import java.util.ArrayList;

/**
 * This interface defines methods that process data from our database into
 * a format that our business rules can use.
 *
 * Implementations of this interface should NOT talk directly to the SQL db
 */

public abstract class CarDataProcessor {
    CarAccessInterface carAccess;

    public CarDataProcessor(CarAccessInterface carAccess) {
        this.carAccess = carAccess;
    }

    /**
     * Iterates through our table of cars and returns all of them
     * @return a list of car objects from our database sorted by ascending price
     */
    public abstract ArrayList<Car> getAllCars();

    /**
     * @param vin the vin number of the car to query
     * @return a Car object using the database
     */
    public abstract Car getCarByVin(String vin);
}