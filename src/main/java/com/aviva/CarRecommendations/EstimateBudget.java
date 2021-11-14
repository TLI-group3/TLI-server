package com.aviva.CarRecommendations;

import com.aviva.DataAccess.BankingDataProcess;

import java.util.ArrayList;

public class EstimateBudget {
    public float calculateYearlyBudget(String accountNumber) {
        BankingDataProcess bdpInit = new BankingDataProcess();
        ArrayList<Float> deposits = bdpInit.getDeposits(accountNumber);
        float yearlyIn = 0;
        for (float i : deposits) {
            yearlyIn += i;
        }
        ArrayList<Float> withdrawals = bdpInit.getWithdrawals(accountNumber);
        float yearlyOut = 0;
        for (float i : withdrawals) {
            yearlyOut += i;
        }
        return yearlyIn - yearlyOut;
    }

    public float calculateMonthlyBudget(String accountNumber) {
        return calculateYearlyBudget(accountNumber) / 12;
    }
}
