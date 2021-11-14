package com.aviva.CarRecommendations;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aviva.DataAccess.CarDataProcess;
import java.util.ArrayList;
import com.aviva.Entities.Car;
import java.util.Random;

public class CarRecommender {

    public JSONObject getRecommendedCars(String accountNumber) {
        CarDataProcess cdpInit = new CarDataProcess();
        ArrayList<Car> allCars = cdpInit.getAllCars();

        EstimateBudget ebInit = new EstimateBudget();
        float budget = ebInit.calculateYearlyBudget(accountNumber);

        ArrayList<Car> recommended = new ArrayList<>();
        int index = 0;
        // Random generation of cars for now
        while (recommended.size() < 5) {
            Random rand = new Random();
            index = rand.nextInt(allCars.size());
            Car randomCar = allCars.get(index);
            if (randomCar.getPrice() < budget){
                recommended.add(randomCar);
            }
        }
        // Converting to JSON
        JSONObject carsJSON = new JSONObject();
        JSONArray recommendedCarsJSON = new JSONArray();
        for (Car car : recommended) {
            JSONObject carJSON = car.toJSON();
            recommendedCarsJSON.put(carJSON);
        }
        carsJSON.put("cars", recommendedCarsJSON);
        return carsJSON;
    }
}
