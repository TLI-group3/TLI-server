package com.aviva.DataAccess;

import java.sql.ResultSet;

/**
 * This is the Data Access Interface for the data of the bank's account holders
 */

public interface AccountAccessInterface {
    static ResultSet getClient(String ID) {
        return null;
    } ;

    static ResultSet getAllClients() {
        return null;
    };

    static ResultSet getLatestCreditScore(String ID) {
        return null;
    };

    static ResultSet getAllFinancialTransactions(String ID) {
        return null;
    };
}
