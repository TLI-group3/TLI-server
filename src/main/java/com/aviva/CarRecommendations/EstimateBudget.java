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
    static int BIMONTHLY_SALARY_FREQUENCY = 24;
    static int MONTHLY_SPENDING_FREQUENCY = 12;
    static float MINIMUM_MONTHLY_CAR_LOAN = 50;
    BankingDataProcessingInterface bdpInit = new BankingDataProcess();

    /**
     * Set the monthly salary of an AccountHolder based on studying the deposits of this AccountHolder
     *
     * @param account the AccountHolder for which to generate the budget
     */
    void identifyBiMonthlySalary(AccountHolder account) {

        ArrayList<Float> deposits = bdpInit.getDeposits(account.getAccountNumber());

        // Mapping the possible deposits that could be the bimonthly salary to its number of occurrence
        Map frequency = new HashMap();

        extractMajorCashFlow(deposits, frequency, MINIMUM_BIMONTHLY_SALARY);

        float possibleSalary = 0;
        int possibleSalaryFrequency = 0;

        // Identify the possible salary
        for (Object curr : frequency.keySet()) {
            int currFrequency = (int) frequency.get(curr);
            if (currFrequency >= BIMONTHLY_SALARY_FREQUENCY && ((float) curr) == possibleSalary) {
                possibleSalary = (float) curr;
                possibleSalaryFrequency = currFrequency;
            }
        }


       account.setMonthlySalary(possibleSalary * (possibleSalaryFrequency / (float)12));
    }

    /**
     * Set the other monthly spending, and optionally an existing car loan,
     * of an AccountHolder based on studying the deposits of this AccountHolder
     *
     * @param account the AccountHolder for which to generate the budget
     */
    void identifySpending(AccountHolder account) {
        ArrayList<Float> withdrawals = bdpInit.getWithdrawals(account.getAccountNumber());

        Map frequency = new HashMap();

        extractMajorCashFlow(withdrawals, frequency, MINIMUM_MONTHLY_CAR_LOAN);

        Set monthlySpendings = new HashSet();

        // Identify the monthly occurring withdrawals
        for (Object curr : frequency.keySet()) {
            int currFrequency = (int) frequency.get(curr);
            if (currFrequency >= MONTHLY_SPENDING_FREQUENCY) {
               monthlySpendings.add(curr);
            }
        }

        account.setOtherMonthlySpending((float) Collections.max(monthlySpendings));
        monthlySpendings.remove(Collections.max(monthlySpendings));

        // If there is more than 1 monthly withdrawal, assume the next largest spending is for the existing car loan
        if (!monthlySpendings.isEmpty()) {
            account.setExistingCarLoan((float) Collections.max(monthlySpendings));
        }
    }

    /**
     * Set the monthly budget of an AccountHolder assuming that at least their monthly salary and other montly
     * spending are known.
     *
     * @param account the AccountHolder for which to generate the budget
     */
    void determineMonthlyBudget(AccountHolder account) {
        if (account.getExistingCarLoan() == 0) {
            float monthlyBudget = (float) ((account.getMonthlySalary() - account.getOtherMonthlySpending()) * 0.1);
            account.setMonthlyBudget(monthlyBudget);
        } else {
            account.setMonthlyBudget(account.getExistingCarLoan());
        }
    }

    private void extractMajorCashFlow(ArrayList<Float> cashflow, Map frequency, float lowerBound) {
        for (float i : cashflow) {
            if (i >= lowerBound) {
                if (frequency.containsKey(i)) {
                    int curr = (int) frequency.get(i);
                    frequency.put(i, curr + 1);
                } else {
                    frequency.put(i, 1);
                }
            }
        }
    }
}
