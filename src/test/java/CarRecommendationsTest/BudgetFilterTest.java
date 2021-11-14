package CarRecommendationsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.aviva.CarRecommendations.BudgetFilter;
import com.aviva.Entities.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BudgetFilterTest {
    AccountHolder knownUser;
    BudgetFilter filterToTest;

    @BeforeEach
    public void setup() {
        knownUser = new AccountHolder("1402110922112412");
        filterToTest = new BudgetFilter();
        knownUser.setSavings(100000F);
    }

    @Test
    public void testGetRecommendedCarsOutputSize() {
        assertEquals(10, filterToTest.getRecommendedCars(knownUser).size());
    }

    @Test
    public void testGetRecommendedCarsNotEmpty() {
        assertFalse(filterToTest.getRecommendedCars(knownUser).isEmpty());
    }
}
