package com.aviva.APIBoundary;

import com.aviva.UseCases.*;
import com.aviva.Entities.InputData;
import org.springframework.web.bind.annotation.*;


/**
 * A controller class that handles input-output data communication between front-end and back-end. It uses springboot to
 * handle HTTP GET/POST/PUT requests.
 */
@RestController
@CrossOrigin(origins="*")
public class CarController {
    /**
     * Endpoint for generating a list of recommended cars for a client and inserting information into database
     * @param input inserted input from the frontend, includes client IDs
     */
    @PostMapping("/generateCars")
    public void generateCarsForClient(@RequestBody InputData input) {
        Recommender useCaseGenerate = new Recommender();
        useCaseGenerate.generateAndInsert(input);
    }


    /**
     * Endpoint for getting recommended cars for a client from our database
     * @param input inserted input from the frontend, includes client IDs
     * @return string representation of recommended car information for front end to display
     */
    @GetMapping("/getCars")
    public String getCars(@RequestBody InputData input){
        Fetcher useCaseGet = new Fetcher();
        return useCaseGet.getCars(input);
    }


    /**
     * Responds to a GET request at the root "/". This method is mainly used for testing purposes. Returns greeting.
     */
    @GetMapping("/")
    public String index() {
        return "Greetings!";
    }
}

//    /**
//     * Runs our program on the selected clients, and inserts their recommended cars
//     * into our database.
//     * This endpoint is meant to be called when a group of client IDs is inserted
//     * from the "Launch Widgets" page in the front-end.
//     * @param inputData inserted input by user, includes client ids
//     * @return boolean representing whether the call was successful
//     */
//    @PutMapping("/input")
//    public boolean runForInputClients(@RequestBody InputData inputData) {
//        String clientIDsString = inputData.getClientIDs();
//        String[] clientIDs = clientIDsString.split(" ");
//        for (String id : clientIDs) {
//            InterestFilter ifInit = new InterestFilter();
//            JSONObject cars = ifInit.getBestFiveCars(id);
//        }
//        // to_do: return false if error or improve the returned message
//        return true;
//    }
//
//    /**
//     * Take in a trade-in car from the user and generate recommended cars.
//     * This endpoint is meant to be called from the widget in the personal banking page.
//     * @param params map including accountNumber and tradeIn car name
//     * @return a list of affordable cars
//     */
//    @PostMapping("/widget")
//    public String getCarsForWidget(@RequestBody Map<String, String> params) {
//        String accountNumber = params.get("accountNumber");
//        String tradeIn = params.get("tradeIn");
//        InterestFilter ifInit = new InterestFilter();
//        JSONObject cars = ifInit.getBestFiveCars(accountNumber);
//        return cars.toString();
//    }