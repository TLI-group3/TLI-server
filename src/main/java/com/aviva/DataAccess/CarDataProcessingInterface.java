package com.aviva.DataAccess;

import com.aviva.Entities.Car;
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
     * @param name Full label of car as YEAR MAKE MODEL
     * @return a Car object using the database
     */
    public Car getCarByName(String name);


//    /**
//     * Iterates through our table of cars and returns one at a time.
//     * This method uses a variation on the Iterator pattern.
//     * @return the next Car object
//     */
//    public Car getNextCar();
}