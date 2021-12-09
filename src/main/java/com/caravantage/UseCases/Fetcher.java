package com.caravantage.UseCases;

import com.caravantage.FetchCars.BankingDataProcess;
import com.caravantage.Entities.AccountHolder;
import com.caravantage.CarRecommendations.OutputBoundary;

/**
 * Use case class responsible for handling getting a client's recommended cars
 */

public class Fetcher {
    /**
     * Get a client's previously generated and stored recommended cars
     * @param input string sent in from front end containing the client ID
     * @return a JSON formatted string to send to our front end
     */
    public String getCars(String input) {
        AccountHolder user = new AccountHolder(input);
        BankingDataProcess userData = new BankingDataProcess();
        OutputBoundary dataConverter = new OutputBoundary();
        return dataConverter.convert(userData.getRecommendedCars(user.getAccountNumber()));
    }
}
