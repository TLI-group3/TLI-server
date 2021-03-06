package com.caravantage.car_recommendations;

import com.caravantage.fetch_cars.BankingDataProcessor;
import com.caravantage.entities.AccountHolder;
import com.caravantage.constants.RecommendationConstants;

import java.util.*;

/**
 * Public class that handles the business logic of estimating the budget of a particular AccountHolder.
 */
public class EstimateBudget extends Handler {

    private final AccountHolder account;
    BankingDataProcessor bankProcess;

    public EstimateBudget(int i, AccountHolder account, BankingDataProcessor bdpInit) {
        this.level = i;
        this.account = account;
        this.bankProcess = bdpInit;
    }

    /**
     * Estimate the monthly budget of a given AccountHolder by identifying their salary and monthly spending.
     */
    public void performTask() {
        identifyBiMonthlySalary();
        identifySpending();
        determineMonthlyBudget();

        account.setCreditScore(bankProcess.getCreditScore(account.getAccountNumber()));
    }

    /**
     * Set the monthly salary of an AccountHolder based on studying the deposits of this AccountHolder
     */
    private void identifyBiMonthlySalary() {
        ArrayList<Float> deposits = bankProcess.getDeposits(account.getAccountNumber());

        // Mapping the possible deposits that could be the bimonthly salary to its number of occurrence
        Map frequency = new HashMap();

        extractMajorCashFlow(deposits, frequency, RecommendationConstants.MINIMUM_BIMONTHLY_SALARY);

        float possibleSalary = 0;
        int possibleSalaryFrequency = 0;

        // Identify the possible salary
        for (Object curr : frequency.keySet()) {
            int currFrequency = (int) frequency.get(curr);
            if (currFrequency >= RecommendationConstants.BIMONTHLY_SALARY_FREQUENCY && ((float) curr) >= possibleSalary) {
                possibleSalary = (float) curr;
                possibleSalaryFrequency = currFrequency;
            }
        }

       account.setMonthlySalary(possibleSalary * (possibleSalaryFrequency / (float)12));
    }

    /**
     * Set the other monthly spending, and optionally an existing car loan,
     * of an AccountHolder based on studying the deposits of this AccountHolder.
     */
    private void identifySpending() {
        ArrayList<Float> withdrawals = bankProcess.getWithdrawals(account.getAccountNumber());

        Map frequency = new HashMap();

        extractMajorCashFlow(withdrawals, frequency, RecommendationConstants.MINIMUM_MONTHLY_CAR_LOAN);

        Set monthlySpendings = new HashSet();

        // Identify the monthly occurring withdrawals
        for (Object curr : frequency.keySet()) {
            int currFrequency = (int) frequency.get(curr);
            if (currFrequency >= RecommendationConstants.MONTHLY_SPENDING_FREQUENCY) {
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
     * Set the monthly budget of an AccountHolder assuming that at least their monthly salary and other monthly
     * spending are known.
     */
    private void determineMonthlyBudget() {
        if (account.getExistingCarLoan() == 0) {
            float monthlyBudget = (float) ((account.getMonthlySalary() - account.getOtherMonthlySpending()) * RecommendationConstants.MONTHLY_ALLOCATION);
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
