package entities_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.caravantage.entities.InputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the InputData entity
 */

public class InputDataTest {
    InputData inputDataToTest;

    @BeforeEach
    public void setup() {
        inputDataToTest = new InputData("02022001", "2001 Volkswagen Golf");
    }

    // Test getters
    @Test
    public void testGetClientIDs() {
        assertEquals("02022001", inputDataToTest.getClientIDs());
    }

    @Test
    public void testGetTradeInCar() {
        assertEquals("2001 Volkswagen Golf", inputDataToTest.getTradeInCar());
    }

    @Test
    public void testConstructor() {
        InputData withTrade = new InputData("TEST1", "TEST2");
        assertEquals("TEST1TEST2", withTrade.getClientIDs() + withTrade.getTradeInCar());
    }


    @Test
    public void testNoTradeInConstructor() {
        InputData withoutTrade = new InputData("NOTRADEIN");
        assertNull(withoutTrade.getTradeInCar());
    }
}
