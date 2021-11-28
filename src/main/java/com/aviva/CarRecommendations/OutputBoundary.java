package com.aviva.CarRecommendations;

import com.aviva.Entities.Car;
import com.aviva.Entities.Loan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class OutputBoundary {
    public OutputBoundary(){

    }

    public String convert(HashMap<Car, Loan> recommended){
        JSONArray carInfo = new JSONArray();
        for (Car car : recommended.keySet()){
            JSONObject JSONCar = car.toJSON();
            JSONObject JSONLoan = recommended.get(car).toJSON();
            JSONCar.put("loan", JSONLoan);
            carInfo.put(JSONCar);
        }
        JSONObject toReturn = new JSONObject();
        toReturn.put("cars", carInfo);
        return toReturn.toString();
    }
}
