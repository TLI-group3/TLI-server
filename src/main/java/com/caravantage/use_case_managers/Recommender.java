package com.caravantage.use_case_managers;

import com.caravantage.car_recommendations.SetUpAccountHolder;
import com.caravantage.constants.RecommendationConstants;
import com.caravantage.data_access.AccountAccessInterface;
import com.caravantage.fetch_cars.BankingDataProcessor;
import com.caravantage.entities.AccountHolder;
import com.caravantage.entities.InputData;
import com.caravantage.fetch_cars.CarDataProcessor;

/**
 * A use case class responsible for handling the generating a client's recommended cars and inserting
 * the cars into our database
 */

public class Recommender {
    AccountAccessInterface accountAccess;
    BankingDataProcessor bankProcess;
    CarDataProcessor carProcess;

    public Recommender(AccountAccessInterface accountAccess,
                       BankingDataProcessor bankProcess, CarDataProcessor carProcess) {
        this.accountAccess = accountAccess;
        this.bankProcess = bankProcess;
        this.carProcess = carProcess;
    }

    /**
     * Follows our backend logic to generate a list of the best possible cars based on loans and saved to that client
     * @param input the input sent in from our front end including the clients' IDs
     */
    public void generateAndInsert(InputData input) {
        // Generate and insert for every client ID given by front end
        String clientIDsString = input.getClientIDs();
        String[] clientIDs = clientIDsString.split(" ");
        for (String id : clientIDs) {
            AccountHolder user = new AccountHolder(id);
            // Check to see if they have a current car they would like to trade in
            if (input.getTradeInCar() != null){
                user.setExistingCar(input.getTradeInCar());
                user.setExistingCarValue((float) (carProcess.getCarByVin(user.getExistingCar()).getPrice() * RecommendationConstants.TRADE_DEPRECIATION_RATIO));
            }
            SetUpAccountHolder program = new SetUpAccountHolder(user, bankProcess, carProcess);
            program.run();
            accountAccess.insertRecommendedCars(user);
        }
    }
}
