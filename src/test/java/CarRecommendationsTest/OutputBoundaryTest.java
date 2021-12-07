package CarRecommendationsTest;

import com.caravantage.CarRecommendations.OutputBoundary;
import com.caravantage.Entities.Car;
import com.caravantage.Entities.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

/**
 * Testing if our output boundary accurately converts our backend objects to front end needed context
 */

public class OutputBoundaryTest {
    OutputBoundary outputBoundaryToTest;
    HashMap<Car, Loan> knownOutput;

    @BeforeEach
    public void setup() {
        outputBoundaryToTest = new OutputBoundary();
        Car testCar = new Car("Ford", "Fiesta", 2019, 20000F);
        Loan testLoan = new Loan(1F, 2F, 3F, 4F, 5, 6F);
        knownOutput = new HashMap<>();
        knownOutput.put(testCar, testLoan);
    }

    @Test
    public void testConvert(){
        assertEquals("{\"cars\":[{\"loan\":{\"interestRate\":6,\"capitalSum\":3,\"loanTerm\":5," +
                "\"loanAmount\":1,\"interestSum\":2,\"loanSum\":4},\"year\":2019,\"price\":20000,\"model\":" +
                "\"Fiesta\",\"make\":\"Ford\"}]}", outputBoundaryToTest.convert(knownOutput));
    }
}
