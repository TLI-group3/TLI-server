package com.aviva.UseCases;

import com.aviva.CarRecommendations.FillOutAccountHolder;
import com.aviva.DataAccess.SQLAccountHolderDataAccess;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.InputData;

public class Recommender {
    public Recommender(){

    }
    public void generateAndInsert(InputData input) {

        SQLAccountHolderDataAccess sqlinit = new SQLAccountHolderDataAccess();

        String clientIDsString = input.getClientIDs();
        String[] clientIDs = clientIDsString.split(" ");
        for (String id : clientIDs) {
            AccountHolder user = new AccountHolder(id);
            FillOutAccountHolder program = new FillOutAccountHolder(user);
            program.run();
            sqlinit.insertRecommendedCars(user);
        }
    }
}
