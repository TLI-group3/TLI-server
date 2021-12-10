package car_recommendations_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.car_recommendations.BudgetFilter;
import com.caravantage.data_access.SQLCarDataAccess;
import com.caravantage.entities.AccountHolder;
import com.caravantage.fetch_cars.SQLCarDataProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing if our budget filter returns an expected output of list of cars
 */

public class BudgetFilterTest {
    AccountHolder knownUser;
    BudgetFilter filterToTest;

    @BeforeEach
    public void setup() {
        SQLCarDataAccess carAccess = new SQLCarDataAccess();
        SQLCarDataProcess carProcess = new SQLCarDataProcess(carAccess);
        knownUser = new AccountHolder("1402110922112412");
        knownUser.setMonthlySalary(5500F);
        filterToTest = new BudgetFilter(1, knownUser, carProcess);
    }

    @Test
    public void testGetInitialCars() {;
        filterToTest.performTask();
        assertEquals(10, knownUser.getInitialCar().size());
    }
}
