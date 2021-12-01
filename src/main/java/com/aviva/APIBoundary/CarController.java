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
    @PutMapping("/generateCars")
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