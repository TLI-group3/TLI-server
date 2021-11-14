package com.aviva.CarRecommendations;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class InterestFilter {

    public JSONObject getBestFiveCars(String accountNumber) {
        BankingDataProcess bdpInit = new BankingDataProcess();
        AccountHolder user = bdpInit.getAccountHolder(accountNumber);

        EstimateBudget ebInit = new EstimateBudget();
        float yearlyBudget = ebInit.calculateYearlyBudget(user);
        user.setSavings(yearlyBudget);
        float monthlyBudget = ebInit.calculateMonthlyBudget(user);
        user.setMonthlyBudget(monthlyBudget);

        BudgetFilter bfInit = new BudgetFilter();
        ArrayList<Car> cars = bfInit.getRecommendedCars(user);

        SensoRate srInit = new SensoRate();
        ArrayList<Car> bestFive = new ArrayList<>();
        float[] rates = new float[10];

        for (int i = 0; i < cars.size(); i++) {
            float interestRate = srInit.getInterestRate(user, cars.get(i));
            rates[i] = interestRate;
        }

        float[] sortedRates = Arrays.copyOf(rates, 10);
        Arrays.sort(sortedRates);

        for (int i = 0; i < 5; i++) {
            float sortedRate = sortedRates[i];
            int carIndex = Arrays.asList(rates).indexOf(sortedRate);
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
}
