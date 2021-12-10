package UseCasesTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.DataAccess.SQLAccountHolderDataAccess;
import com.caravantage.DataAccess.SQLCarDataAccess;
import com.caravantage.Entities.InputData;
import com.caravantage.FetchCars.BankingDataProcessor;
import com.caravantage.FetchCars.SQLBankingDataProcess;
import com.caravantage.FetchCars.SQLCarDataProcess;
import com.caravantage.UseCases.Fetcher;
import com.caravantage.UseCases.Recommender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the recommder use case to see if it returns the expected output
 */

public class RecommenderTest {
    Recommender recommenderToTest;
    BankingDataProcessor bankProcess;
    InputData knownInput;

    @BeforeEach
    public void setup() {
        SQLCarDataAccess carDataAccess = new SQLCarDataAccess();
        SQLCarDataProcess carProcess = new SQLCarDataProcess(carDataAccess);
        SQLAccountHolderDataAccess accountAccess = new SQLAccountHolderDataAccess();
        bankProcess = new SQLBankingDataProcess(accountAccess, carProcess);
        recommenderToTest = new Recommender(accountAccess, bankProcess, carProcess);
        knownInput = new InputData("1402110922112412");
    }

    @Test
    public void testRecommenderUseCase() {
        Fetcher test = new Fetcher(bankProcess);
        recommenderToTest.generateAndInsert(knownInput);
        assertEquals("{\"cars\":[{\"image\":\"https://",
                test.getCars(knownInput.getClientIDs()).substring(0, 27));
    }
}
