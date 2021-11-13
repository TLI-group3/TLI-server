package com.aviva.DataProcess;

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
     * @param accountNumber the account number of the client
     * @return list of deposit amounts in chronological order.
     */
    static ArrayList<Float> getDeposits(String accountNumber){
        return null;
    }

    /**
     * Returns a list of withdrawals made by the client from their bank statement.
     * @param accountNumber the account number of the client
     * @return list of withdrawal amounts in chronological order.
     */
    static ArrayList<Float> getWithdrawals(String accountNumber){
        return null;
    }

    /**
     * Returns the client's credit score
     * @param accountNumber the account number of the client
     * @return credit score
     */
    static int getCreditScore(String accountNumber){
        return 0;
    }
}