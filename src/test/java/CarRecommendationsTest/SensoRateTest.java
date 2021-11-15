package CarRecommendationsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.CarRecommendations.SensoRate;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SensoRateTest {
    SensoRate apiToTest;
    Car knownCar;
    AccountHolder knownUser;

    @BeforeEach
    public void setup() {
        apiToTest = new SensoRate();
        knownCar = new Car("BMW", "i8", 2020, 80000F);
        knownUser = new AccountHolder("1402110922112412");
        knownUser.setCreditScore(780);
        knownUser.setSavings(64000F);
        knownUser.setMonthlyBudget((float) (64000 / 12));
    }

    @Test
    public void testGetInterestRate() {
        assertEquals(2.240000009536743, apiToTest.getInterestRate(knownUser, knownCar));
    }
}
