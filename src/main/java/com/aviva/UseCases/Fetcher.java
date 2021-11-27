package com.aviva.UseCases;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.InputData;
import com.aviva.CarRecommendations.OutputBoundary;

public class Fetcher {

    public String getCars(InputData input) {
        AccountHolder user = new AccountHolder(input.getClientIDs());
        BankingDataProcess userData = new BankingDataProcess();
        OutputBoundary dataConverter = new OutputBoundary();
        return dataConverter.methodToConvert(userData.getRecommendedCars(user));
    }
}
