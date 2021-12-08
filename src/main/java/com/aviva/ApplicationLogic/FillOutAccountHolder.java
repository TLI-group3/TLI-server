package com.aviva.ApplicationLogic;

import com.aviva.Entities.AccountHolder;

abstract class Handler {
    protected Handler nextInChain;
    protected int level;

    /**
     * Add a Handler object to be executed after this current Handler. If there is already an existing Handler next
     * in chain, set the given Handler object to be the next in chain of that already an existing Handler next
     * in chain.
     *
     * @param next a Handler object representing the next action in the chain of responsibility
     */
    public void add(Handler next) {
        if (nextInChain == null) {
            nextInChain = next;
        } else {
            nextInChain.add(next);
        }
    }

    /**
     * Execute the responsibility of this Handler if its level matches the given level.
     *
     * @param i an int representing the order of this Handler in the chain of responsibility.
     */
    public void execute(int i) {
        if (this.level == i) {
            performTask();
        }
        else if (nextInChain != null) {
            nextInChain.execute(i);
        }
    }

    /**
     * Perform the responsibility this Handler is responsible for.
     */
    abstract void performTask();
}

public class FillOutAccountHolder {
    private AccountHolder account;
    public FillOutAccountHolder(AccountHolder account) {
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
