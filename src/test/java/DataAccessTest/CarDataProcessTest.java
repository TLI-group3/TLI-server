package DataAccessTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.DatabaseUseCases.CarDataProcess;
import com.aviva.Entities.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarDataProcessTest {
    CarDataProcess processToTest;

    @BeforeEach
    public void setup() {
        processToTest = new CarDataProcess();
    }

    @Test
    public void testGetAllCarsSize() {
        assertEquals(109, processToTest.getAllCars().size());
    }

    @Test
    public void testGetCarByVin() {
        Car gotCar = processToTest.getCarByVin("1fm5k7bh1ggc33135");
        assertEquals("ford", gotCar.getMake());
        assertEquals("explorer", gotCar.getModel());
        assertEquals(2016, gotCar.getYear());
        assertEquals(12000F, gotCar.getPrice());
    }
}
