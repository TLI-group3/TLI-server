package UseCasesTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.DataAccess.SQLAccountHolderDataAccess;
import com.caravantage.DataAccess.SQLCarDataAccess;
import com.caravantage.Entities.AccountHolder;
import com.caravantage.FetchCars.SQLBankingDataProcess;
import com.caravantage.FetchCars.SQLCarDataProcess;
import com.caravantage.UseCases.Fetcher;
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
        SQLBankingDataProcess bankProcess = new SQLBankingDataProcess(accountAccess, carProcess, carDataAccess);
        fetcherToTest = new Fetcher(bankProcess);
        knownUser = new AccountHolder("1402110922112412");
    }

    @Test
    public void testFetcherUseCase() {
        assertEquals("{\"cars\":[{\"image\":\"https://",
                fetcherToTest.getCars(knownUser.getAccountNumber()).substring(0, 27));
    }
}