package com.aviva.DataAccess;

import java.sql.ResultSet;

/**
 * This is the Data Access Interface for the data of the bank's account holders
 */

public interface AccountAccessInterface {
    static ResultSet getClientByID(String ID) {
        return null;
    } ;

    static ResultSet getAllClients() {
        return null;
    };
}
