package com.aviva.CarRecommendations;

import com.aviva.Entities.AccountHolder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.aviva.DataAccess.CarDataProcess;
import java.util.ArrayList;
import com.aviva.Entities.Car;
import java.util.Random;

public class BudgetFilter {

    public ArrayList<Car> getRecommendedCars(AccountHolder user) {
        CarDataProcess cdpInit = new CarDataProcess();
        ArrayList<Car> allCars = cdpInit.getAllCars();

        ArrayList<Car> recommended = new ArrayList<>();
        int index = 0;
        // Random generation of cars for now
        while (recommended.size() < 10) {
            Random rand = new Random();
            index = rand.nextInt(allCars.size());
            Car randomCar = allCars.get(index);
            if (randomCar.getPrice() >= 5000 && randomCar.getPrice() < user.getSavings()){
                recommended.add(randomCar);
            }
        }
        return recommended;
    }

}
