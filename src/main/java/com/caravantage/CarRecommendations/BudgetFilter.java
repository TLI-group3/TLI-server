package com.caravantage.CarRecommendations;

import com.caravantage.DataAccess.CarAccessInterface;
import com.caravantage.Entities.AccountHolder;
import com.caravantage.FetchCars.CarDataProcess;
import com.caravantage.Constants.RecommendationConstants;
import java.util.ArrayList;
import com.caravantage.Entities.Car;
import com.caravantage.FetchCars.CarDataProcessingInterface;

import java.util.Random;

/**
 * Public class that handles the business logic of generating possible cars to be recommended (first filter)
 * The first filter identifies #BUDGET_FILTER_SIZE random cars within the client's budget by checking if the car's price is within
 * the client's yearly savings
 */
public class BudgetFilter extends Handler {

    private final AccountHolder account;
    CarDataProcessingInterface carProcess;
    CarAccessInterface carAccess;

    public BudgetFilter(int i, AccountHolder account, CarDataProcessingInterface carProcess, CarAccessInterface carAccess) {
        this.level = i;
        this.account = account;
        this.carProcess = carProcess;
        this.carAccess = carAccess;
    }

    /**
     * Generate #BUDGET_FILTER_SIZE number of random cars recommended for a particular AccountHolder based on client's budget and set it
     * to the AccountHolder.
     */
    public void performTask() {
        getInitialCars(carProcess, carAccess);
    }

    private void getInitialCars(CarDataProcessingInterface carProcess, CarAccessInterface carAccess) {
        // Get list of all cars
        ArrayList<Car> allCars = carProcess.getAllCars(carAccess);
        int numCars = allCars.size();

        // Variable Initialization
        ArrayList<Car> recommended = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        int index;

        // Loop to #BUDGET_FILTER_SIZE number of cars
        while (recommended.size() < RecommendationConstants.BUDGET_FILTER_SIZE) {
            // Get random car from list
            Random rand = new Random();
            index = rand.nextInt(numCars);

            if (!indexes.contains(index)) {
                Car randomCar = allCars.get(index);

                // Check if car price is above MINIMUM_CAR_PRICE (required for API call) and within the AccountHolder's savings
                if (randomCar.getPrice() >= RecommendationConstants.MINIMUM_CAR_PRICE && randomCar.getPrice() <
                        account.getMonthlySalary() * 12 * RecommendationConstants.CAR_EXPENDITURE_RATIO) {
                    recommended.add(randomCar);
                }

                indexes.add(index);
            }
        }
        account.setInitialCar(recommended);
    }

}
