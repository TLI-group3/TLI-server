package com.aviva.CarRecommendations;

import com.aviva.Entities.AccountHolder;

abstract class Handler {
    protected Handler nextInChain;
    protected int level;

    public void add(Handler next) {
        if (nextInChain == null) {
            nextInChain = next;
        } else {
            nextInChain.add(next);
        }
    }

    public void execute(int i) {
        if (this.level == i) {
            performTask();
        }
        else if (nextInChain != null) {
            nextInChain.execute(i);
        }
    }

    abstract void performTask();
}

public class FillOutAccountHolder {
    private AccountHolder account;
    public FillOutAccountHolder(AccountHolder account) {
        this.account = account;
    }
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
