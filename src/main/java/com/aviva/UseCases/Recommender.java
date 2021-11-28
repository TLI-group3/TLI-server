package com.aviva.UseCases;

import com.aviva.CarRecommendations.FillOutAccountHolder;
import com.aviva.DataAccess.CarDataProcess;
import com.aviva.DataAccess.SQLAccountHolderDataAccess;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.InputData;


public class Recommender {
    public Recommender(){

    }
    public void generateAndInsert(InputData input) {

        SQLAccountHolderDataAccess sqlinit = new SQLAccountHolderDataAccess();
        CarDataProcess cdpInit = new CarDataProcess();

        String clientIDsString = input.getClientIDs();
        String[] clientIDs = clientIDsString.split(" ");
        for (String id : clientIDs) {
            AccountHolder user = new AccountHolder(id);
            if (input.getTradeInCar() != null){
                user.setExistingCar(input.getTradeInCar());
                // Assuming car trade in worth 10% of MSRP price
                user.setExistingCarValue((float) (cdpInit.getCarByName(user.getExistingCar()).getPrice() * 0.1));
            }
            FillOutAccountHolder program = new FillOutAccountHolder(user);
            program.run();
            sqlinit.insertRecommendedCars(user);
        }
    }
}
