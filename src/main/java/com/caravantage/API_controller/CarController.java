package com.caravantage.API_controller;

import com.caravantage.data_access.AccountAccessInterface;
import com.caravantage.data_access.CarAccessInterface;
import com.caravantage.data_access.SQLAccountHolderDataAccess;
import com.caravantage.data_access.SQLCarDataAccess;
import com.caravantage.fetch_cars.SQLBankingDataProcess;
import com.caravantage.fetch_cars.BankingDataProcessor;
import com.caravantage.fetch_cars.SQLCarDataProcess;
import com.caravantage.fetch_cars.CarDataProcessor;
import com.caravantage.use_case_managers.*;
import com.caravantage.entities.InputData;
import org.springframework.web.bind.annotation.*;


/**
 * A controller class that handles input-output data communication between front-end and back-end. It uses springboot to
 * handle HTTP GET/POST/PUT requests.
 */
@RestController
@CrossOrigin(origins="*")
public class CarController {
    AccountAccessInterface accountAccess;
    CarAccessInterface carAccess;
    BankingDataProcessor bankProcess;
    CarDataProcessor carProcess;

    public CarController() {
        this.accountAccess = new SQLAccountHolderDataAccess();
        this.carAccess = new SQLCarDataAccess();
        this.carProcess = new SQLCarDataProcess(carAccess);
        this.bankProcess = new SQLBankingDataProcess(accountAccess, carProcess);
    }

    /**
     * Endpoint for generating a list of recommended cars for a client and inserting information into database
     * @param input inserted input from the frontend, includes client IDs
     */
    @PutMapping("/generateCars")
    public void generateCarsForClient(@RequestBody InputData input) {
        Recommender useCaseGenerate = new Recommender(accountAccess, bankProcess, carProcess);
        useCaseGenerate.generateAndInsert(input);
    }


    /**
     * Endpoint for getting recommended cars for a client from our database
     * @param input inserted input from the frontend, includes client IDs
     * @return string representation of recommended car information for front end to display
     */
    @GetMapping("/getCars")
    public String getCars(@RequestParam String input){
        Fetcher useCaseGet = new Fetcher(bankProcess);
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
