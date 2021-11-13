package com.aviva.APIBoundary;

import com.aviva.CarRecommendations.CarRecommender;
import com.aviva.DataAccess.AccountHolderDataInterface;
import com.aviva.DataAccess.CSVAccountHolderData;
import com.aviva.Entities.AccountHolder;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * A controller class that handles input-output data communication between front-end and back-end. It uses springboot to
 * handle HTTP GET/POST/PUT requests.
 */
@RestController
@CrossOrigin(origins="*")
public class CarController {
    /**
     * Responds to a GET request at the root "/". This method is mainly used for testing purposes. Returns greeting.
     */
    @GetMapping("/")
    public String index() {
        return "Greetings!";
    }

    /**
     * Returns a list of recommended cars for a certain client.
     * @param clientID the client's ID number
     * @return list of recommended cars, formatted as a JSON string
//     * @throws IOException
//     * @throws InterruptedException
//     * @throws ClassNotFoundException
     */
    // TODO: document the format of the returned JSON
    @PostMapping("/cars")
//    public String getCars(@RequestBody String clientID) throws IOException, InterruptedException, ClassNotFoundException {
//        AccountHolderDataInterface accountData = new CSVAccountHolderData();
//        AccountHolder user = accountData.getClientByID(clientID);
//        JSONObject cars = CarRecommender.getRecommendedCars(user);
//        System.out.println(cars);
//        return cars.toString();
//    }
    public String getCars(@RequestBody String accountNumber){
        JSONObject cars = CarRecommender.getRecommendedCars(accountNumber);
        return cars.toString();
    }
}