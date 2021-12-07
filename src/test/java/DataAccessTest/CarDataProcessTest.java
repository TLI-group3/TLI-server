package DataAccessTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.DataAccess.CarDataProcess;
import com.aviva.Entities.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarDataProcessTest {
    CarDataProcess processToTest;
    Car knownCar;

    @BeforeEach
    public void setup() {
        processToTest = new CarDataProcess();
        knownCar = new Car("ford", "explorer", 2016, 12000F);
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
