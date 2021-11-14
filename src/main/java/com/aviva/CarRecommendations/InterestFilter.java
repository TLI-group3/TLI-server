package com.aviva.CarRecommendations;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.Entities.Car;
import org.json.JSONObject;

import java.util.ArrayList;

public class InterestFilter {

    public JSONObject getBestFiveCars(String accountNumber) {
        BudgetFilter bfInit = new BudgetFilter();
        ArrayList<Car> cars = bfInit.getRecommendedCars(accountNumber);
        BankingDataProcess bdpInit = new BankingDataProcess();
        int creditScore = bdpInit.getCreditScore(accountNumber);
    }
}
