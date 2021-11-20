package EntitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.Entities.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountHolderTest {
    AccountHolder accountHolderToTest;

    @BeforeEach
    public void setup(){
        accountHolderToTest = new AccountHolder("6699");
        accountHolderToTest.setCreditScore(420);
        accountHolderToTest.setSavings(10000000F);
        accountHolderToTest.setMonthlyBudget(2F);
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals("6699", accountHolderToTest.getAccountNumber());
    }

    @Test
    public void testGetCreditScore() {
        assertEquals(420, accountHolderToTest.getCreditScore());
    }

    @Test
    public void testGetSavings() {
        assertEquals(10000000F, accountHolderToTest.getSavings());
    }

    @Test
    public void testGetMonthlyBudget() {
        assertEquals(2F, accountHolderToTest.getMonthlyBudget());
    }

    @Test
    public void testSetCreditScore() {
        accountHolderToTest.setCreditScore(800);
        assertEquals(800, accountHolderToTest.getCreditScore());
    }

    @Test
    public void testSetSavings() {
        accountHolderToTest.setSavings(0F);
        assertEquals(0F, accountHolderToTest.getSavings());
    }

    @Test
    public void testSetMonthlyBudget() {
        accountHolderToTest.setMonthlyBudget(0.01F);
        assertEquals(0.01F, accountHolderToTest.getMonthlyBudget());
    }
}
