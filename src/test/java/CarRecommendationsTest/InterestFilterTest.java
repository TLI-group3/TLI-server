package CarRecommendationsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.CarRecommendations.BudgetFilter;
import com.caravantage.DataAccess.SQLAccountHolderDataAccess;
import com.caravantage.DataAccess.SQLCarDataAccess;
import com.caravantage.Entities.AccountHolder;
import com.caravantage.CarRecommendations.InterestFilter;
import com.caravantage.FetchCars.SQLBankingDataProcess;
import com.caravantage.FetchCars.SQLCarDataProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing if our interest filter successfully returns an expected list of cars
 */

public class InterestFilterTest {
    InterestFilter filterToTest;
    AccountHolder knownUser;
    SQLCarDataProcess carProcess;

    @BeforeEach
    public void setup() {
        SQLCarDataAccess carDataAccess = new SQLCarDataAccess();
        carProcess = new SQLCarDataProcess(carDataAccess);
        knownUser = new AccountHolder("1402110922112412");
        knownUser.setMonthlySalary(5500F);
        knownUser.setMonthlyBudget(900F);
        knownUser.setCreditScore(719);
        filterToTest = new InterestFilter(2, knownUser);
    }

    @Test
    public void testGenerateRecommendedCars(){
        BudgetFilter testInitCars = new BudgetFilter(1, knownUser, carProcess);
        testInitCars.performTask();
        filterToTest.performTask();
        assertEquals(5, knownUser.getRecommendedCars().size());
    }
}
