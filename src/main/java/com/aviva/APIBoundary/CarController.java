package com.aviva.APIBoundary;

import com.aviva.Entities.InputData;
import com.aviva.CarRecommendations.InterestFilter;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

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
     * This endpoint is meant to be used when viewing the recommended cars for a particular
     * client on the "Client Information" page.
     * @param accountNumber the client's ID number
     * @return list of recommended cars, formatted as a JSON string
     */
    @PostMapping("/cars")
    public String getCarsForClient(@RequestBody String accountNumber) {
        InterestFilter ifInit = new InterestFilter();
        JSONObject cars = ifInit.getBestFiveCars(accountNumber);
        return cars.toString();
    }

    /**
     * Runs our program on the selected clients, and inserts their recommended cars
     * into our database.
     * This endpoint is meant to be called when a group of client IDs is inserted
     * from the "Launch Widgets" page in the front-end.
     * @param inputData inserted input by user, includes client ids
     * @return boolean representing whether the call was successful
     */
    @PutMapping("/input")
    public boolean runForInputClients(@RequestBody InputData inputData) {
        String clientIDsString = inputData.getClientIDs();
        String[] clientIDs = clientIDsString.split(" ");
        for (String id : clientIDs) {
            // TODO: generate recommended cars for this client
            // TODO: store these recommendations in the db
            System.out.println(id);
        }
        // TODO: return false if error or improve the returned message
        return true;
    }

    /**
     * Take in a trade-in car from the user and generate recommended cars.
     * This endpoint is meant to be called from the widget in the personal banking page.
     * @param tradeInName the name of the trade-in, formatted as YEAR MAKE MODEL
     * @return a list of affordable cars
     */
    @PostMapping("/widget")
    public String getCarsForWidget(@RequestBody String tradeInName) {
        // TODO: fetch recommended cars from output db
        return tradeInName;
    }
}