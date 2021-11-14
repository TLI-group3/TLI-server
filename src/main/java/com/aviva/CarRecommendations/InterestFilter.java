package com.aviva.CarRecommendations;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Public class that handles the business logic of generating the best cars for an AccountHolder (second filter)
 */

public class InterestFilter {

    /**
     * Returns the best five cars given an AccountHolder's accountNumber
     *
     * @param accountNumber the account number of an AccountHolder
     * @return JSON that maps cars to several Car entities converted to JSON
     */
    public JSONObject getBestFiveCars(String accountNumber) {
        // Instantiate AccountHolder given the accountNumber
        BankingDataProcess bdpInit = new BankingDataProcess();
        AccountHolder user = bdpInit.getAccountHolder(accountNumber);

        // Get and set the AccountHolder's budget
        EstimateBudget ebInit = new EstimateBudget();
        float yearlyBudget = ebInit.calculateYearlyBudget(user);
        user.setSavings(yearlyBudget);
        float monthlyBudget = ebInit.calculateMonthlyBudget(user);
        user.setMonthlyBudget(monthlyBudget);

        // Get the list of possible cars for the AccountHolder
        BudgetFilter bfInit = new BudgetFilter();
        ArrayList<Car> cars = bfInit.getRecommendedCars(user);

        // Variable Initialization
        SensoRate srInit = new SensoRate();
        ArrayList<Car> bestFive = new ArrayList<>();
        float[] rates = new float[10];

        // Loop to store interest rate of each car
        for (int i = 0; i < cars.size(); i++) {
            float interestRate = srInit.getInterestRate(user, cars.get(i));
            rates[i] = interestRate;
        }

        // Create a copy of the array of interest rates and sort them
        float[] sortedRates = Arrays.copyOf(rates, 10);
        Arrays.sort(sortedRates);

        // Loop to get five best cars
        for (int i = 0; i < 5; i++) {
            float sortedRate = sortedRates[i];
            int carIndex = getFirstIndex(rates, sortedRate);
            bestFive.add(cars.get(carIndex));
            rates[carIndex] = (float) -1.0;
        }

        // Converting to JSON
        JSONObject carsJSON = new JSONObject();
        JSONArray recommendedCarsJSON = new JSONArray();
        for (Car car : bestFive) {
            JSONObject carJSON = car.toJSON();
            recommendedCarsJSON.put(carJSON);
        }
        carsJSON.put("cars", recommendedCarsJSON);

        return carsJSON;
    }

    /**
     * Returns the lowest index value of an element in an array that matches a given value
     *
     * @param rates an array of floats representing interest rates
     * @param value a float representing the interest rate to find within the given array
     * @return index value of given element in array as integer
     */
    public int getFirstIndex(float[] rates, float value) {
        for (int i = 0; i < rates.length; i++) {
            if (rates[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
