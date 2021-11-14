package com.aviva.DataAccess;

import java.sql.ResultSet;

/**
 * This is the Data Access Interface for the data of the bank's account holders
 */

public interface AccountAccessInterface {
    public ResultSet getClient(String accountNumber);

    public ResultSet getAllClients();

    public ResultSet getLatestCreditScore(String accountNumber);

    public ResultSet getAllFinancialTransactions(String accountNumber);
}
