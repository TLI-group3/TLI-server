package com.aviva.CarRecommendations;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.Entities.AccountHolder;
import java.util.ArrayList;

/**
 * Public class that handles the business logic of estimating the budget of a particular AccountHolder
 */

public class EstimateBudget {

    /**
     * Return the yearly budget of an AccountHolder as a float
     *
     * @param accountNumber the AccountHolder for which to generate the budget
     * @return float representing the AccountHolder's yearly budget.
     */
    public static float calculateYearlyBudget(String accountNumber) {
        // Get AccountHolder's deposits
        BankingDataProcess bdpInit = new BankingDataProcess();
        ArrayList<Float> deposits = bdpInit.getDeposits(accountNumber);

        // Sum all deposits
        float yearlyIn = 0;
        for (float i : deposits) {
            yearlyIn += i;
        }

        // Get all withdrawals and sum them
        ArrayList<Float> withdrawals = bdpInit.getWithdrawals(accountNumber);
        float yearlyOut = 0;
        for (float i : withdrawals) {
            yearlyOut += i;
        }

        return yearlyIn - yearlyOut;
    }
}
