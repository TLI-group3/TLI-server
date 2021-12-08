package CarRecommendationsTest;

import com.aviva.ApplicationLogic.EstimateBudget;
import com.aviva.Entities.AccountHolder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstimateBudgetTest {
    EstimateBudget estimatorToTest;
    AccountHolder knownAccount;

    @BeforeEach
    public void setup() {
        knownAccount = new AccountHolder("1402110922112412");
        estimatorToTest = new EstimateBudget(1, knownAccount);
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

