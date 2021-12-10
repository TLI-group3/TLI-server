package use_case_managers_test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.data_access.SQLAccountHolderDataAccess;
import com.caravantage.data_access.SQLCarDataAccess;
import com.caravantage.entities.AccountHolder;
import com.caravantage.fetch_cars.SQLBankingDataProcess;
import com.caravantage.fetch_cars.SQLCarDataProcess;
import com.caravantage.use_case_managers.Fetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the fetcher use case to see if it returns the expected output
 */

public class FetcherTest {
    Fetcher fetcherToTest;
    AccountHolder knownUser;

    @BeforeEach
    public void setup() {
        SQLCarDataAccess carDataAccess = new SQLCarDataAccess();
        SQLCarDataProcess carProcess = new SQLCarDataProcess(carDataAccess);
        SQLAccountHolderDataAccess accountAccess = new SQLAccountHolderDataAccess();
        SQLBankingDataProcess bankProcess = new SQLBankingDataProcess(accountAccess, carProcess);
        fetcherToTest = new Fetcher(bankProcess);
        knownUser = new AccountHolder("1402110922112412");
    }

    @Test
    public void testFetcherUseCase() {
        assertEquals("{\"cars\":[{\"image\":\"https://",
                fetcherToTest.getCars(knownUser.getAccountNumber()).substring(0, 27));
    }
}
