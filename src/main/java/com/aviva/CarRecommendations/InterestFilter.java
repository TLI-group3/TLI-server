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
 * Filters the initial 10 cars down to 5 based on the lowest interest rates returned by the Senso API
 */

public class InterestFilter {
    // Number of cars to return by interest filter
    private int lowestNoCars = 5;
    /**
     * Returns the best five cars given an AccountHolder's accountNumber based on the lowest interest rates
     *
     * @param accountNumber the account number of an AccountHolder
     * @return JSON that maps cars to five Car entities with the lowest interest rate
     */
    public JSONObject getBestFiveCars(String accountNumber) {
        // Instantiate AccountHolder given the accountNumber
        BankingDataProcess bdpInit = new BankingDataProcess();
        AccountHolder user = bdpInit.makeAccountHolder(accountNumber);

        // Get and set the AccountHolder's budget
        EstimateBudget ebInit = new EstimateBudget();
        float yearlyBudget = ebInit.calculateYearlyBudget(user);
        user.setSavings(yearlyBudget);
        float monthlyBudget = ebInit.calculateMonthlyBudget(user);
        user.setMonthlyBudget(monthlyBudget);

        // Get the list of possible cars for the AccountHolder
        BudgetFilter bfInit = new BudgetFilter();
        ArrayList<Car> cars = bfInit.getRecommendedCars(user);

        ArrayList<Car> bestFive = filterLowestFive(user, cars);

        // Convert to JSON
        return convertToJSON(bestFive);
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

    /**
     * Convert an array list of cars into JSON equivalent
     *
     * @param bestFive an array list of car objects
     * @return return JSONObject of cars
     */
    public JSONObject convertToJSON(ArrayList<Car> bestFive){
        // Initialisation
        JSONObject carsJSON = new JSONObject();
        JSONArray recommendedCarsJSON = new JSONArray();

        // Convert each car object to JSON and add to JSON array
        for (Car car : bestFive) {
            JSONObject carJSON = car.toJSON();
            recommendedCarsJSON.put(carJSON);
        }
        carsJSON.put("cars", recommendedCarsJSON);

        return carsJSON;
    }

    /**
     * Filter a list of cars by their loan interest rate given by the senso api
     *
     * @param user an account holder providing financial details
     * @param initalCars the initial list of cars to filter against
     * @return an array list of lowest interest cars
     */
    public ArrayList<Car> filterLowestFive(AccountHolder user, ArrayList<Car> initalCars){
        // Variable Initialization
        SensoRate srInit = new SensoRate();
        ArrayList<Car> bestFive = new ArrayList<>();
        float[] rates = new float[initalCars.size()];

        // Loop to store interest rate of each car
        for (int i = 0; i < initalCars.size(); i++) {
            float interestRate = srInit.getInterestRate(user, initalCars.get(i));
            rates[i] = interestRate;
        }

        // Create a copy of the array of interest rates and sort them
        float[] sortedRates = Arrays.copyOf(rates, initalCars.size());
        Arrays.sort(sortedRates);

        // Loop to get five best cars
        for (int i = 0; i < lowestNoCars; i++) {
            float sortedRate = sortedRates[i];
            int carIndex = getFirstIndex(rates, sortedRate);
            bestFive.add(initalCars.get(carIndex));
            rates[carIndex] = (float) -1.0;
        }
        return bestFive;
    }
}
