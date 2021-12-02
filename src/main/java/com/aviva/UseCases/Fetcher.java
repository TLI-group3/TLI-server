package com.aviva.UseCases;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.InputData;
import com.aviva.CarRecommendations.OutputBoundary;

public class Fetcher {

    public Fetcher(){

    }

    public String getCars(String input) {
        AccountHolder user = new AccountHolder(input);
        BankingDataProcess userData = new BankingDataProcess();
        OutputBoundary dataConverter = new OutputBoundary();
        return dataConverter.convert(userData.getRecommendedCars(user.getAccountNumber()));
    }
}
