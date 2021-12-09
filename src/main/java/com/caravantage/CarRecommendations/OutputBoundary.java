package com.caravantage.CarRecommendations;

import com.caravantage.Entities.Car;
import com.caravantage.Entities.Loan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * An output boundary responsible for converting our returned objects for logic to required output format for front end
 */

public class OutputBoundary {
    /**
     * Converst a hasmap of car, loan to a json formatted string
     * @param recommended the filtered recommended cars to convert
     * @return a JSON formatted string containing all information of cars and their loans e.g.:
     * {"cars":[{"image":"https://",
     *           "loan":{"interestRate":2.69,"capitalSum":6600,"loanTerm":36,"loanAmount":6600,"interestSum":277.29,"loanSum":6877.29},
     *           "year":2018,"price":6000,"model":"malibu","make":"chevrolet"}
     */
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
