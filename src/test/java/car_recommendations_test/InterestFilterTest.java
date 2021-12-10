package car_recommendations_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.car_recommendations.BudgetFilter;
import com.caravantage.data_access.SQLCarDataAccess;
import com.caravantage.entities.AccountHolder;
import com.caravantage.car_recommendations.InterestFilter;
import com.caravantage.fetch_cars.SQLCarDataProcess;
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
