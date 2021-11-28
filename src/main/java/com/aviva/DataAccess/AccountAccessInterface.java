package com.aviva.DataAccess;

import com.aviva.Entities.AccountHolder;

import java.sql.ResultSet;

/**
 * This is the Data Access Interface for the data of the bank's account holders
 */

public interface AccountAccessInterface {

    /**
     * Returns the ResultSet of the query to get a particular client's latest credit score
     * @param accountNumber the account number of the client
     * @return ResultSet of SQL query to get the client's latest credit score.
     */
    public ResultSet getLatestCreditScore(String accountNumber);

    /**
     * Returns the ResultSet of the query to get all financial transactions for a particular client
     * @param accountNumber the account number of the client
     * @return ResultSet of SQL query to get all financial transactions stored for the client.
     */
    public ResultSet getAllFinancialTransactions(String accountNumber);

    /**
     * Inserts a car name against an account number into a table
     * @param accountHolder the AccountHolder entity for which to insert the recommendations
     */
    public void insertRecommendedCars(AccountHolder accountHolder);
}
