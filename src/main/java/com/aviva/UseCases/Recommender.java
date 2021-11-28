package com.aviva.UseCases;

import com.aviva.CarRecommendations.FillOutAccountHolder;
import com.aviva.DataAccess.SQLCarDataAccess;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.InputData;

public class Recommender {
    public Recommender(){

    }
    public void generateAndInsert(InputData input) {

        SQLCarDataAccess sqlinit = new SQLCarDataAccess();

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
