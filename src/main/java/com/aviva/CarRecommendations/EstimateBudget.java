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
     * @param user the AccountHolder for which to generate the budget
     * @return float representing the AccountHolder's yearly budget.
     */
    public float calculateYearlyBudget(AccountHolder user) {
        // Get AccountHolder's deposits
        BankingDataProcess bdpInit = new BankingDataProcess();
        ArrayList<Float> deposits = bdpInit.getDeposits(user.getAccountNumber());

        // Sum all deposits
        float yearlyIn = 0;
        for (float i : deposits) {
            yearlyIn += i;
        }

        // Get all withdrawals and sum them
        ArrayList<Float> withdrawals = bdpInit.getWithdrawals(user.getAccountNumber());
        float yearlyOut = 0;
        for (float i : withdrawals) {
            yearlyOut += i;
        }

        return yearlyIn - yearlyOut; // difference of deposits and withdrawals
    }

    /**
     * Return the monthly budget of an AccountHolder as a float
     *
     * @param user the AccountHolder for which to generate the budget
     * @return float representing the AccountHolder's monthly budget.
     */
    public float calculateMonthlyBudget(AccountHolder user) {
        return calculateYearlyBudget(user) / 12;
    }

}
