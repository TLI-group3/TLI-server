package com.aviva.APIBoundary;

import com.aviva.CarRecommendations.CarRecommender;
import com.aviva.DataAccess.AccountHolderDataInterface;
import com.aviva.DataAccess.CSVAccountHolderData;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.InputData;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

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
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    // TODO: document the format of the returned JSON
    @PostMapping("/cars")
    public String getCars(@RequestBody String clientID) throws IOException, InterruptedException, ClassNotFoundException {
        AccountHolderDataInterface accountData = new CSVAccountHolderData();
        AccountHolder user = accountData.getClientByID(clientID);
        JSONObject cars = CarRecommender.getRecommendedCars(user);
        System.out.println(cars);
        return cars.toString();
    }

    /**
     * Runs our program on the selected clients, and inserts their recommended cars
     * into our database.
     * @param inputData inserted input by user, includes client ids
     * @return boolean representing whether the call was successful
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    @PutMapping("/input")
    public boolean insertClient(@RequestBody InputData inputData) throws IOException, InterruptedException, ClassNotFoundException {
        String clientIDsString = inputData.getClientIDs();
        String[] clientIDs = clientIDsString.split(" ");
        for (String id : clientIDs) {
            AccountHolderDataInterface accountData = new CSVAccountHolderData();
            AccountHolder user = accountData.getClientByID(id);
            JSONObject cars = CarRecommender.getRecommendedCars(user);
        }
        // TODO: rewrite to reflect changes to business rules
        // TODO: return false if error or improve the returned message
        return true;
    }
}