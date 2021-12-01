package CarRecommendationsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.CarRecommendations.SensoRate;
import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing if our senso rate class accurately makes calls to the senso api
 */

public class SensoRateTest {
    SensoRate apiToTest;
    Car knownCar;
    AccountHolder knownUser;

    @BeforeEach
    public void setup() {
        apiToTest = new SensoRate();
        knownCar = new Car("Dodge", "Dart", 2020, 15000F);
        knownUser = new AccountHolder("1402110922112412");
        knownUser.setCreditScore(780);
        knownUser.setMonthlySalary(5500F);
        knownUser.setMonthlyBudget(900F);
    }

    @Test
    public void testGetLoan() {
        JSONObject jsonEquiv = new JSONObject();
        jsonEquiv.put("capitalSum", 16500);
        jsonEquiv.put("amount", 16500);
        jsonEquiv.put("term", "36");

        assertEquals(jsonEquiv.get("capitalSum"), apiToTest.getLoan(knownUser, knownCar).get("capitalSum"));
        assertEquals(jsonEquiv.get("amount"), apiToTest.getLoan(knownUser, knownCar).get("amount"));
        assertEquals(jsonEquiv.get("term"), apiToTest.getLoan(knownUser, knownCar).get("term"));
    }
}
