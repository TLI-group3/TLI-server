package com.caravantage.APIController;

import com.caravantage.DataAccess.AccountAccessInterface;
import com.caravantage.DataAccess.CarAccessInterface;
import com.caravantage.DataAccess.SQLAccountHolderDataAccess;
import com.caravantage.DataAccess.SQLCarDataAccess;
import com.caravantage.FetchCars.SQLBankingDataProcess;
import com.caravantage.FetchCars.BankingDataProcessor;
import com.caravantage.FetchCars.SQLCarDataProcess;
import com.caravantage.FetchCars.CarDataProcessor;
import com.caravantage.UseCases.*;
import com.caravantage.Entities.InputData;
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
        this.bankProcess = new SQLBankingDataProcess(accountAccess, carProcess, carAccess);
        this.carProcess = new SQLCarDataProcess(carAccess);
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