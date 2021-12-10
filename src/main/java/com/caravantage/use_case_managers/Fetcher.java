package com.caravantage.use_case_managers;

import com.caravantage.entities.AccountHolder;
import com.caravantage.car_recommendations.OutputBoundary;
import com.caravantage.fetch_cars.BankingDataProcessor;

/**
 * Use case class responsible for handling getting a client's recommended cars
 */

public class Fetcher {
    BankingDataProcessor bankProcess;

     public Fetcher(BankingDataProcessor bankProcess) {
         this.bankProcess = bankProcess;
     }

    /**
     * Get a client's previously generated and stored recommended cars
     * @param input string sent in from front end containing the client ID
     * @return a JSON formatted string to send to our front end
     */
    public String getCars(String input) {
        AccountHolder user = new AccountHolder(input);
        OutputBoundary dataConverter = new OutputBoundary();
        return dataConverter.convert(bankProcess.getRecommendedCars(user.getAccountNumber()));
    }
}
