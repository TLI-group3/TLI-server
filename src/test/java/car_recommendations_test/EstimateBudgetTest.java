package car_recommendations_test;

import com.caravantage.car_recommendations.EstimateBudget;
import com.caravantage.data_access.SQLAccountHolderDataAccess;
import com.caravantage.data_access.SQLCarDataAccess;
import com.caravantage.entities.AccountHolder;

import com.caravantage.fetch_cars.SQLBankingDataProcess;
import com.caravantage.fetch_cars.SQLCarDataProcess;
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

