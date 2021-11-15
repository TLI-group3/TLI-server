package com.aviva.DataAccess;

import java.util.ArrayList;

/**
 * This interface defines methods that process data from our database into
 * a format that our business rules can use.
 *
 * Implementations of this interface should NOT talk directly to the SQL db
 */
public interface BankingDataProcessingInterface {
    /**
     * Returns a list of deposits made by the client from their bank statement.
     *
     * @param accountNumber the id number of the client
     * @return list of deposit amounts in chronological order.
     */
    public ArrayList<Float> getDeposits(String accountNumber);

    /**
     * Returns a list of withdrawals made by the client from their bank statement.
     *
     * @param accountNumber the id number of the client
     * @return list of withdrawal amounts in chronological order.
     */
    public ArrayList<Float> getWithdrawals(String accountNumber);

    /**
     * Returns the client's credit score
     *
     * @param accountNumber the id number of the client
     * @return credit score
     */
    public int getCreditScore(String accountNumber);
}