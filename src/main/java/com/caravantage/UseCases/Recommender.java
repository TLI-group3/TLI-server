package com.caravantage.UseCases;

import com.caravantage.CarRecommendations.SetUpAccountHolder;
import com.caravantage.Constants.RecommendationConstants;
import com.caravantage.FetchCars.CarDataProcess;
import com.caravantage.DataAccess.SQLAccountHolderDataAccess;
import com.caravantage.Entities.AccountHolder;
import com.caravantage.Entities.InputData;

/**
 * A use case class responsible for handling the generating a client's recommended cars and inserting
 * the cars into our database
 */

public class Recommender {
    /**
     * Follows our backend logic to generate a list of the best possible cars based on loans and saved to that client
     * @param input the input sent in from our front end including the clients' IDs
     */
    public void generateAndInsert(InputData input) {

        SQLAccountHolderDataAccess sqlinit = new SQLAccountHolderDataAccess();
        CarDataProcess cdpInit = new CarDataProcess();

        // Generate and insert for every client ID given by front end
        String clientIDsString = input.getClientIDs();
        String[] clientIDs = clientIDsString.split(" ");
        for (String id : clientIDs) {
            AccountHolder user = new AccountHolder(id);
            // Check to see if they have a current car they would like to trade in
            if (input.getTradeInCar() != null){
                user.setExistingCar(input.getTradeInCar());
                user.setExistingCarValue((float) (cdpInit.getCarByVin(user.getExistingCar()).getPrice() * RecommendationConstants.TRADE_DEPRECIATION_RATIO));
            }
            SetUpAccountHolder program = new SetUpAccountHolder(user);
            program.run();
            sqlinit.insertRecommendedCars(user);
        }
    }
}