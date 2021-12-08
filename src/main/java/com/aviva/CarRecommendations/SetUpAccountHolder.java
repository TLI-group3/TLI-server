package com.aviva.CarRecommendations;

import com.aviva.Entities.AccountHolder;

/**
 * The class that is responsible for filling in the instance variables of an AccountHolder.
 */
public class SetUpAccountHolder {
    private AccountHolder account;
    public SetUpAccountHolder(AccountHolder account) {
        this.account = account;
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
        Handler estimateBudget = new EstimateBudget(1, account);
        Handler budgetFilter = new BudgetFilter(2, account);
        Handler interestFilter = new InterestFilter(3, account);

        estimateBudget.add(budgetFilter);
        estimateBudget.add(interestFilter);

        for (int i = 1; i < 4; i++) {
            estimateBudget.execute(i);
        }
    }
}
