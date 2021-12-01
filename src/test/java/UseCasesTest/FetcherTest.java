package UseCasesTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.Entities.InputData;
import com.aviva.UseCases.Fetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the fetcher use case to see if it returns the expected output
 */

public class FetcherTest {
    Fetcher fetcherToTest;
    InputData knownInput;

    @BeforeEach
    public void setup() {
        fetcherToTest = new Fetcher();
        knownInput = new InputData("1402110922112412");
    }

    @Test
    public void testFetcherUseCase() {
        assertEquals("{\"cars\":[{\"image\":\"https://",
                fetcherToTest.getCars(knownInput).substring(0, 27));
    }
}