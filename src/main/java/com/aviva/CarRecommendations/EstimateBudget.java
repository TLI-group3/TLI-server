package com.aviva.CarRecommendations;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.DataAccess.BankingDataProcessingInterface;
import com.aviva.Entities.AccountHolder;

import java.util.*;

/**
 * Public class that handles the business logic of estimating the budget of a particular AccountHolder
 */

public class EstimateBudget {
    static float MINIMUM_BIMONTHLY_SALARY = 1000;
    static int SALARY_FREQUENCY = 20;
    BankingDataProcessingInterface bdpInit = new BankingDataProcess();

    /**
     * Return the yearly budget of an AccountHolder as a float
     *
     * @param user the AccountHolder for which to generate the budget
     * @return float representing the AccountHolder's yearly budget.
     */
    float identifyMonthlySalary(AccountHolder account) {

        ArrayList<Float> deposits = bdpInit.getDeposits(account.getAccountNumber());

        // Mapping the possible deposits that could be the bimonthly salary to its number of occurrence
        Map frequency = new HashMap();

        // Identify the possible deposits that could be the bimonthly salary
        for (float i : deposits) {
            if (i >= MINIMUM_BIMONTHLY_SALARY) {
                if (frequency.containsKey(i)) {
                    int curr = (int) frequency.get(i);
                    frequency.put(i, curr + 1);
                } else {
                    frequency.put(i, 1);
                }
            }
        }

        float possibleSalary = 0;

        // Identify the possible salary
        for (Object curr : frequency.keySet()) {
            int currValue = (int) frequency.get(curr);
            if (currValue >= SALARY_FREQUENCY && ((float) curr) == possibleSalary) {
                possibleSalary = (float) curr;
            }
        }
        return possibleSalary;
    }
}
