//package CarRecommendationsTest;
//
//import com.aviva.CarRecommendations.EstimateBudget;
//import com.aviva.Entities.AccountHolder;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class EstimateBudgetTest {
//    EstimateBudget classToTest;
//    AccountHolder knownAccount;
//
//    @BeforeEach
//    public void setup() {
//        classToTest = new EstimateBudget();
//        knownAccount = new AccountHolder("1402110922112412");
//    }
//
//    @Test
//    public void testCalculateYearlyBudget() {
//        assertEquals(38460.0F, classToTest.calculateYearlyBudget(knownAccount));
//    }
//
//    @Test
//    public void testCalculateMonthlyBudget() {
//        assertEquals(3205.0F, classToTest.calculateMonthlyBudget(knownAccount));
//    }
//}
