package com.aviva.DataProcess;

import com.aviva.Entities.Car;

public class CarDataProcess implements CarDataProcessingInterface{
    public Car getNextCar(){
        /**
         * Iterates through our table of cars and returns one at a time.
         * This method uses a variation on the Iterator pattern.
         * @return the next Car object
         */
        Car nextCar = new Car("Honda", "Civic", 2019, 10000);
        return nextCar;
    }

    public Car getCarByName(String name){
        /**
         * @param name Full label of car as YEAR MAKE MODEL
         * @return a Car object using the database
         */
        Car carToReturn = new Car("Honda", "Civic", 2019, 10000);
        return carToReturn;
    }
}
