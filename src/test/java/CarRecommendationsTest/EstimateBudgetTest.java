package CarRecommendationsTest;

import com.aviva.CarRecommendations.EstimateBudget;
import com.aviva.Entities.AccountHolder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstimateBudgetTest {
    AccountHolder knownAccount;

    @BeforeEach
    public void setup() {
        knownAccount = new AccountHolder("1402110922112412");
    }

    @Test
    public void testCalculateYearlyBudget() {
        assertEquals(38460.0F, EstimateBudget.calculateYearlyBudget(knownAccount.getAccountNumber()));
    }
}
