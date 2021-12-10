package CarRecommendationsTest;

import com.caravantage.CarRecommendations.EstimateBudget;
import com.caravantage.DataAccess.SQLAccountHolderDataAccess;
import com.caravantage.DataAccess.SQLCarDataAccess;
import com.caravantage.Entities.AccountHolder;

import com.caravantage.FetchCars.SQLBankingDataProcess;
import com.caravantage.FetchCars.SQLCarDataProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstimateBudgetTest {
    EstimateBudget estimatorToTest;
    AccountHolder knownAccount;

    @BeforeEach
    public void setup() {
        SQLCarDataAccess carDataAccess = new SQLCarDataAccess();
        SQLCarDataProcess carProcess = new SQLCarDataProcess(carDataAccess);
        SQLAccountHolderDataAccess accountAccess = new SQLAccountHolderDataAccess();
        SQLBankingDataProcess bankProcess = new SQLBankingDataProcess(accountAccess, carProcess);
        knownAccount = new AccountHolder("1402110922112412");
        estimatorToTest = new EstimateBudget(1, knownAccount, bankProcess);
        estimatorToTest.performTask();
    }

    @Test
    public void testEstimateBudget() {
        assertEquals(5500, knownAccount.getMonthlySalary());
        assertEquals(1995, knownAccount.getOtherMonthlySpending());
        assertEquals(800, knownAccount.getExistingCarLoan());
        assertEquals(800, knownAccount.getMonthlyBudget());
        assertEquals(719, knownAccount.getCreditScore());
    }
}

