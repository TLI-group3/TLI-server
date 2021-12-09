package UseCasesTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.Entities.InputData;
import com.caravantage.UseCases.Fetcher;
import com.caravantage.UseCases.Recommender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the recommder use case to see if it returns the expected output
 */

public class RecommenderTest {
    Recommender recommenderToTest;
    InputData knownInput;

    @BeforeEach
    public void setup() {
        recommenderToTest = new Recommender();
        knownInput = new InputData("1402110922112412");
    }

    @Test
    public void testRecommenderUseCase() {
        Fetcher test = new Fetcher();
        recommenderToTest.generateAndInsert(knownInput);
        assertEquals("{\"cars\":[{\"image\":\"https://",
                test.getCars(knownInput.getClientIDs()).substring(0, 27));
    }
}
