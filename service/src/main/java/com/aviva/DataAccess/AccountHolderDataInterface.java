package main.java.com.aviva.DataAccess;

import main.java.com.aviva.Entities.AccountHolder;
import java.util.Collection;

/**
 * This is the Data Access Interface for the data of the bank's account holders
 */
public interface AccountHolderDataInterface {
    public AccountHolder getClientByID(String ID);

    public Collection<AccountHolder> getAllClients();

    // Get the most recent credit score of the bankaccount holder with the given ID.
    public String getClientMostRecentCreditScore(String ID);

    // Get all of financial transaction (deposits and withdrawls)
    // of the bankaccount holder with the given ID.
    public JSONObject getClientFinancialTransaction(String ID);
}