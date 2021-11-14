package com.aviva.CarRecommendations;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.Entities.AccountHolder;

import java.util.ArrayList;

public class EstimateBudget {
    public float calculateYearlyBudget(AccountHolder user) {
        BankingDataProcess bdpInit = new BankingDataProcess();
        ArrayList<Float> deposits = bdpInit.getDeposits(user.getAccountNumber());
        float yearlyIn = 0;
        for (float i : deposits) {
            yearlyIn += i;
        }
        ArrayList<Float> withdrawals = bdpInit.getWithdrawals(user.getAccountNumber());
        float yearlyOut = 0;
        for (float i : withdrawals) {
            yearlyOut += i;
        }
        return yearlyIn - yearlyOut;
    }

    public float calculateMonthlyBudget(AccountHolder user) {
        return calculateYearlyBudget(user) / 12;
    }
}
