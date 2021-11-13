package com.aviva.CarRecommendations;

import com.aviva.DataProcess.BankingDataProcess;

import java.util.ArrayList;

public class EstimateBudget {
    public static float calculateYearlyBudget(String accountNumber) {
        ArrayList<Float> deposits = BankingDataProcess.getDeposits(accountNumber);
        float yearlyIn = 0;
        for (float i : deposits) {
            yearlyIn += i;
        }
        ArrayList<Float> withdrawals = BankingDataProcess.getWithdrawals(accountNumber);
        float yearlyOut = 0;
        for (float i : withdrawals) {
            yearlyOut += i;
        }
        float yearlyBudget = yearlyIn - yearlyOut;
        return yearlyBudget;
    }

    public static float calculateMonthlyBudget(String accountNumber) {
        return calculateYearlyBudget(accountNumber) / 12;
    }
}
