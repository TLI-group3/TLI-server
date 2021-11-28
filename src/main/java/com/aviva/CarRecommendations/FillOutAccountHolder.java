package com.aviva.CarRecommendations;

import com.aviva.Entities.AccountHolder;

abstract class Handler {
    protected Handler nextInChain;

    public void add(Handler next) {
        if (nextInChain == null) {
            nextInChain = next;
        } else {
            nextInChain.add(next);
        }
    }

    public abstract void execute();
}

public class FillOutAccountHolder {
    private AccountHolder account;
    public FillOutAccountHolder(AccountHolder account) {
        this.account = account;
    }
    public void run() {
        Handler estimateBudget = new EstimateBudget(account);
        Handler budgetFilter = new BudgetFilter(account);
        Handler interestFilter = new InterestFilter(account);

        estimateBudget.add(budgetFilter);
        estimateBudget.add(interestFilter);

        while (estimateBudget.nextInChain != null) {
            estimateBudget.nextInChain.execute();
        }
    }
}
