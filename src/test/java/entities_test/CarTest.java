package entities_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.caravantage.entities.Car;
import org.json.JSONObject;

/**
 * Testing the Car entity
 */

public class CarTest {
    Car carToTest;

    @BeforeEach
    public void setup(){
        carToTest = new Car("Lamborghini", "Aventador", 2021, 400000F);
        carToTest.setImage("https://www.topgear.com/sites/default/files/cars-car/image/2017/01/aventador-s_green_050" +
                ".jpg");
        carToTest.setID("TESTID");
        carToTest.setVin("TESTVIN");
    }

    // Test getters
    @Test
    public void testGetModel() {
        assertEquals("Aventador", carToTest.getModel());
    }

    @Test
    public void testGetMake() {
        assertEquals("Lamborghini", carToTest.getMake());
    }

    @Test
    public void testGetYear() {
        assertEquals(2021, carToTest.getYear());
    }

    @Test
    public void testGetPrice() {
        assertEquals(400000F, carToTest.getPrice());
    }

    @Test
    public void testGetVin() {
        assertEquals("TESTVIN", carToTest.getVin());
    }

    @Test
    public void testGetID() {
        assertEquals("TESTID", carToTest.getID());
    }

    @Test
    public void testGetImage() {
        assertEquals("https://www.topgear.com/sites/default/files/cars-car/" +
                "image/2017/01/aventador-s_green_050.jpg", carToTest.getImage());
    }


    // Test setters
    @Test
    public void testSetImage() {
        carToTest.setImage("newTestImage");
        assertEquals("newTestImage", carToTest.getImage());
    }

    @Test
    public void testSetID() {
        carToTest.setID("newTestID");
        assertEquals("newTestID", carToTest.getID());
    }

    @Test
    public void testSetVin() {
        carToTest.setVin("newTestVin");
        assertEquals("newTestVin", carToTest.getVin());
    }


    // Test converters
    @Test
    public void testToJSON() {
        JSONObject jsonEquiv = new JSONObject();
        jsonEquiv.put("make", "Lamborghini");
        jsonEquiv.put("model", "Aventador");
        jsonEquiv.put("year", 2021);
        jsonEquiv.put("price", 400000F);
        jsonEquiv.put("image", "https://www.topgear.com/sites/default/files/cars-car/image/2017/01/" +
                "aventador-s_green_050.jpg");
        assertEquals(jsonEquiv.length(), carToTest.toJSON().length());
        assertEquals(jsonEquiv.get("image"), carToTest.toJSON().get("image"));
    }
}
