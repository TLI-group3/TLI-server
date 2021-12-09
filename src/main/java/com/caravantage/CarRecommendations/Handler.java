package com.caravantage.CarRecommendations;

/**
 * The abstract class representing a step in the process of generating a recommended list of cars.
 */
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
     * Perform the action this Handler is responsible for.
     */
    abstract void performTask();
}
