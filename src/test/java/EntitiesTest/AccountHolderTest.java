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
        accountHolderToTest.setMonthlyBudget(2F);
        accountHolderToTest.setMonthlySalary(5F);
        accountHolderToTest.setExistingCarLoan(2F);
        accountHolderToTest.setOtherMonthlySpending(10F);
        accountHolderToTest.setExistingCar("Toyota");
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
    public void testGetMonthlyBudget() {
        assertEquals(2F, accountHolderToTest.getMonthlyBudget());
    }

    @Test
    public void testGetMonthlySalary() {
        assertEquals(5F, accountHolderToTest.getMonthlySalary());
    }

    @Test
    public void testGetExistingCarLoan() {
        assertEquals(2F, accountHolderToTest.getExistingCarLoan());
    }

    @Test
    public void testGetOtherMonthlySpending() {
        assertEquals(10F, accountHolderToTest.getOtherMonthlySpending());
    }

    @Test
    public void testExistingCar() {
        assertEquals("Toyota", accountHolderToTest.getExistingCar());
    }

    @Test
    public void testSetCreditScore() {
        accountHolderToTest.setCreditScore(800);
        assertEquals(800, accountHolderToTest.getCreditScore());
    }

    @Test
    public void testSetSavings() {
        accountHolderToTest.setMonthlyBudget(0F);
        assertEquals(0F, accountHolderToTest.getMonthlyBudget());
    }

    @Test
    public void testSetMonthlyBudget() {
        accountHolderToTest.setMonthlyBudget(0.01F);
        assertEquals(0.01F, accountHolderToTest.getMonthlyBudget());
    }

    @Test
    public void testSetMonthlySalary() {
        accountHolderToTest.setMonthlySalary(0.01F);
        assertEquals(0.01F, accountHolderToTest.getMonthlySalary());
    }

    @Test
    public void testSetExistingCarLoan() {
        accountHolderToTest.setExistingCarLoan(0.01F);
        assertEquals(0.01F, accountHolderToTest.getExistingCarLoan());
    }

    @Test
    public void testSetOtherMonthlySpending() {
        accountHolderToTest.setOtherMonthlySpending(0.01F);
        assertEquals(0.01F, accountHolderToTest.getOtherMonthlySpending());
    }

    @Test
    public void testSetExistingCar() {
        accountHolderToTest.setExistingCar("Honda");
        assertEquals("Honda", accountHolderToTest.getExistingCar());
    }
}
