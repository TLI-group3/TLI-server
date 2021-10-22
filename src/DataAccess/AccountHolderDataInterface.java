package DataAccess;

import Entities.AccountHolder;
import java.util.Collection;

/**
 * This is the Data Access Interface for the data of the bank's account holders
 */
public interface AccountHolderDataInterface {
    public AccountHolder getClientByID(String ID);

    public Collection<AccountHolder> getAllClients();
}