package com.aviva.CarRecommendations;

import com.aviva.Entities.AccountHolder;
import com.aviva.DataAccess.CarDataProcess;
import java.util.ArrayList;
import com.aviva.Entities.Car;
import java.util.Random;

/**
 * Public class that handles the business logic of generating possible cars to be recommended (first filter)
 * The first filter identifies 10 random cars within the client's budget by checking if the car's price is within
 * the client's yearly savings
 */

public class BudgetFilter {
    // Number of cars to be returned by the budget filter
    public int noOfCars = 10;
    // Minimum budget that the senso api allows/requires
    public int minBudget = 5000;


    /**
     * Returns a ArrayList of 10 random cars recommended for a particular AccountHolder based on client's budget
     *
     * @param user the AccountHolder for which to generate recommendations
     * @return ArrayList of 10 Car Entities.
     */
    public ArrayList<Car> getRecommendedCars(AccountHolder user) {
        // Get list of all cars
        CarDataProcess cdpInit = new CarDataProcess();
        ArrayList<Car> allCars = cdpInit.getAllCars();

        // Variable Initialization
        ArrayList<Car> recommended = new ArrayList<>();
        int index = 0;

        // Loop to get 10 cars
        while (recommended.size() < noOfCars) {
            // Get random car from list
            Random rand = new Random();
            index = rand.nextInt(allCars.size());
            Car randomCar = allCars.get(index);
            // Check if car price is above 5000 (required for API call) and within the AccountHolder's savings
            if (randomCar.getPrice() >= minBudget && randomCar.getPrice() < user.getSavings()){
                recommended.add(randomCar);
            }
        }
        return recommended;
    }

}
