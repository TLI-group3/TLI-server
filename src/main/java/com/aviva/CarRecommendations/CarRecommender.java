package com.aviva.CarRecommendations;

import com.aviva.DataAccess.AccountHolderDataInterface;
import com.aviva.DataAccess.CSVAccountHolderData;
import com.aviva.DataAccess.CSVCarData;
import com.aviva.DataAccess.CarDataInterface;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CarRecommender {
    public static JSONObject getRecommendedCars(AccountHolder user) throws IOException, InterruptedException, ClassNotFoundException {
        float approvedLoanAmount = SensoRate.getApprovedLoanAmount(user);
        CarDataInterface carData = new CSVCarData();
        ArrayList<Car> availableCars = carData.loadCarData("../../../../../../data/Car_Data.csv");

        JSONObject carsJSON = new JSONObject();
        JSONArray recommendedCars = new JSONArray();
        for (Car currentCar : availableCars) {
            if (currentCar.getPrice() <= approvedLoanAmount) {
                JSONObject carJSON = currentCar.toJSON();
                recommendedCars.put(carJSON);
            }
        }
        carsJSON.put("cars", recommendedCars);
        return carsJSON;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        AccountHolderDataInterface accountData = new CSVAccountHolderData();
        AccountHolder user = accountData.getClientByID("5002357538983918");
        JSONObject cars = CarRecommender.getRecommendedCars(user);

        // System.out.println(cars);
    }
}
