package com.aviva.CarRecommendations;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.DataAccess.SQLCarDataAccess;
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

    /**
     * Returns the best five cars given an AccountHolder's accountNumber based on the lowest interest rates
     *
     * @param account an AccountHolder object
     * @return JSON that maps cars to five Car entities with the lowest interest rate
     */
    void generateBestFiveCars(AccountHolder account, ArrayList<Car> cars) {
        // Variable Initialization
        SensoRate srInit = new SensoRate();
        ArrayList<Car> bestFive = new ArrayList<>();
        float[] rates = new float[10];

        // Loop to store interest rate of each car
        for (int i = 0; i < cars.size(); i++) {
            float interestRate = srInit.getInterestRate(account, cars.get(i));
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

        // to save in db
        SQLCarDataAccess carDataAccess = new SQLCarDataAccess();

        for (Car car : bestFive) {
            JSONObject carJSON = car.toJSON();
            recommendedCarsJSON.put(carJSON);
            carDataAccess.insertRecommendedCar(account.getAccountNumber(),
                    car.getYear() + " " + car.getMake() + " " + car.getModel());
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
