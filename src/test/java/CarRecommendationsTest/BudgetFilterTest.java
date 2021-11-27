package CarRecommendationsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.aviva.CarRecommendations.BudgetFilter;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BudgetFilterTest {
    AccountHolder knownUser;
    BudgetFilter filterToTest;

    @BeforeEach
    public void setup() {
        knownUser = new AccountHolder("1402110922112412");
        knownUser.setSavings(50000F);
        filterToTest = new BudgetFilter();
        knownUser.setSavings(100000F);
    }

    @Test
    public void testGetRecommendedCarsOutputSize() {
        assertEquals(10, filterToTest.getInitialCars(knownUser).size());
    }

    @Test
    // Each car has to be at least $5000 for the SensoAPI to work
    public void testCarPricesMinimum() {
        ArrayList<Car> listToTest = filterToTest.getInitialCars(knownUser);
        for (Car car : listToTest){
            assertTrue(car.getPrice() >= 5000F);
        }
    }

    @Test
    // Each car has to be less than the account holder's budget
    public void testCarPricesMaximum() {
        ArrayList<Car> listToTest = filterToTest.getInitialCars(knownUser);
        for (Car car : listToTest){
            assertTrue(car.getPrice() < knownUser.getSavings());
        }
    }
}
