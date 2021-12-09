package com.caravantage.CarRecommendations;

import com.caravantage.DataAccess.AccountAccessInterface;
import com.caravantage.DataAccess.CarAccessInterface;
import com.caravantage.Entities.AccountHolder;
import com.caravantage.FetchCars.BankingDataProcessingInterface;
import com.caravantage.FetchCars.CarDataProcessingInterface;

/**
 * The class that is responsible for filling in the instance variables of an AccountHolder.
 */
public class SetUpAccountHolder {
    private AccountHolder account;
    BankingDataProcessingInterface bankProcess;
    AccountAccessInterface accountAccess;
    CarDataProcessingInterface carProcess;
    CarAccessInterface carAccess;
    public SetUpAccountHolder(AccountHolder account,
                              BankingDataProcessingInterface bankProcess, AccountAccessInterface accountAccess,
                              CarDataProcessingInterface carProcess, CarAccessInterface carAccess) {
        this.account = account;
        this.bankProcess = bankProcess;
        this.accountAccess = accountAccess;
        this.carProcess = carProcess;
        this.carAccess = carAccess;
    }

    /**
     * Run the whole chain of responsibility in order to generate a list of recommended car for the given
     * AccountHolder in the following order:
     * 1. Estimate budget.
     * 2. Filter for cars based on budget.
     * 3. Apply the SensoAPI on the cars passing the first filtering,
     *    filter the cars based on returned installment information.
     */
    public void run() {
        Handler estimateBudget = new EstimateBudget(1, account, bankProcess, accountAccess);
        Handler budgetFilter = new BudgetFilter(2, account, carProcess, carAccess);
        Handler interestFilter = new InterestFilter(3, account);

        estimateBudget.add(budgetFilter);
        estimateBudget.add(interestFilter);

        for (int i = 1; i < 4; i++) {
            estimateBudget.execute(i);
        }
    }
}
