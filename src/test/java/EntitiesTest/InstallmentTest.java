package EntitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.Entities.Installment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the Installment entity
 */

public class InstallmentTest {
    Installment installmentToTest;

    @BeforeEach
    public void setup() {
        installmentToTest = new Installment(1, 2F,
                3F, 4F, 5F, 6F);
    }

    // Test getters
    @Test
    public void testGetTermNumber() {
        assertEquals(1, installmentToTest.getTermNumber());
    }

    @Test
    public void testGetTermCapital() {
        assertEquals(2F, installmentToTest.getTermCapital());
    }

    @Test
    public void testGetTermInterest() {
        assertEquals(3F, installmentToTest.getTermInterest());
    }

    @Test
    public void testGetTermInstallment() {
        assertEquals(4F, installmentToTest.getTermInstallment());
    }

    @Test
    public void testGetRemainingAmount() {
        assertEquals(5F, installmentToTest.getRemainingAmount());
    }

    @Test
    public void testGetInterestSum() {
        assertEquals(6F, installmentToTest.getInterestSum());
    }
}