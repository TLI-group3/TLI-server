package EntitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import com.aviva.Entities.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Testing the AccountHolder entity
 */

public class AccountHolderTest {
    AccountHolder accountHolderToTest;
    Car testCar = new Car("Lamborghini", "Aventador", 2022, 0F);
    Loan testLoan = new Loan(1F, 1F, 1F, 1F, 1, 1F);
    HashMap<Car, Loan> testMap = new HashMap<>();
    ArrayList<Car> testCars = new ArrayList<>();


    @BeforeEach
    public void setup(){
        accountHolderToTest = new AccountHolder("6699");
        accountHolderToTest.setCreditScore(420);
        accountHolderToTest.setMonthlySalary(420F);
        accountHolderToTest.setMonthlyBudget(2F);
        accountHolderToTest.setInitialCar(testCars);
        accountHolderToTest.setRecommendedCars(testMap);
        accountHolderToTest.setExistingCarLoan(2F);
        accountHolderToTest.setExistingCar("2040 Ferrari 458");
        accountHolderToTest.setOtherMonthlySpending(18F);
        accountHolderToTest.setExistingCarValue(0F);

        testMap.put(testCar, testLoan);
        testCars.add(testCar);
    }

    // Test getters
    @Test
    public void testGetAccountNumber() {
        assertEquals("6699", accountHolderToTest.getAccountNumber());
    }

    @Test
    public void testGetCreditScore() {
        assertEquals(420, accountHolderToTest.getCreditScore());
    }

    @Test
    public void testGetMonthlySalary() {
        assertEquals(420F, accountHolderToTest.getMonthlySalary());
    }

    @Test
    public void testGetMonthlyBudget() {
        assertEquals(2F, accountHolderToTest.getMonthlyBudget());
    }

    @Test
    public void testGetRecommendedCars() {
        assertEquals(testMap, accountHolderToTest.getRecommendedCars());
    }

    @Test
    public void testGetInitialCar() {
        assertEquals(testCars, accountHolderToTest.getInitialCar());
    }

    @Test
    public void testGetExistingCarLoan() {
        assertEquals(2F, accountHolderToTest.getExistingCarLoan());
    }

    @Test
    public void testGetExistingCar() {
        assertEquals("2040 Ferrari 458", accountHolderToTest.getExistingCar());
    }


    @Test
    public void testGetOtherMonthlySpending() {
        assertEquals(18F, accountHolderToTest.getOtherMonthlySpending());
    }

    @Test
    public void testGetExistingCarValue() {
        assertEquals(0F, accountHolderToTest.getExistingCarValue());
    }

    // Test setters
    @Test
    public void testConstructor() {
        AccountHolder conTest = new AccountHolder("0202");
        assertEquals("0202", conTest.getAccountNumber());
    }

    @Test
    public void testSetCreditScore() {
        accountHolderToTest.setCreditScore(800);
        assertEquals(800, accountHolderToTest.getCreditScore());
    }

    @Test
    public void testSetMonthlySalary() {
        accountHolderToTest.setMonthlySalary(0F);
        assertEquals(0F, accountHolderToTest.getMonthlySalary());
    }

    @Test
    public void testSetMonthlyBudget() {
        accountHolderToTest.setMonthlyBudget(2000F);
        assertEquals(2000F, accountHolderToTest.getMonthlyBudget());
    }

    @Test
    public void testSetRecommendedCars() {
        Car newCar = new Car("Ford ", "Fiesta", 1999, 100000F);
        Loan newLoan = new Loan(2F, 2F, 2F, 2F, 2, 2F);
        HashMap<Car, Loan> testingMap = new HashMap<>();
        testingMap.put(newCar, newLoan);
        accountHolderToTest.setRecommendedCars(testingMap);
        assertEquals(testingMap, accountHolderToTest.getRecommendedCars());
    }

    @Test
    public void testSetInitialCar() {
        Car newCar = new Car("Yare", "Yare", 2022, 0F);
        ArrayList<Car> testingCars = new ArrayList<>();
        testingCars.add(newCar);
        accountHolderToTest.setInitialCar(testingCars);
        assertEquals(testingCars, accountHolderToTest.getInitialCar());
    }

    @Test
    public void testSetExistingCarLoan() {
        accountHolderToTest.setExistingCarLoan(6666F);
        assertEquals(6666F, accountHolderToTest.getExistingCarLoan());
    }

    @Test
    public void testSetExistingCar() {
        accountHolderToTest.setExistingCar("1980 Vauxhall Astra");
        assertEquals("1980 Vauxhall Astra", accountHolderToTest.getExistingCar());
    }


    @Test
    public void testSetOtherMonthlySpending() {
        accountHolderToTest.setOtherMonthlySpending(88F);
        assertEquals(88F, accountHolderToTest.getOtherMonthlySpending());
    }

    @Test
    public void testSetExistingCarValue() {
        accountHolderToTest.setExistingCarValue(11111F);
        assertEquals(11111F, accountHolderToTest.getExistingCarValue());
    }
}
