package data_access_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.data_access.SQLCarDataAccess;
import com.caravantage.fetch_cars.SQLCarDataProcess;
import com.caravantage.entities.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarDataProcessTest {
    SQLCarDataProcess processToTest;
    SQLCarDataAccess accessor;

    @BeforeEach
    public void setup() {
        accessor = new SQLCarDataAccess();
        processToTest = new SQLCarDataProcess(accessor);
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
