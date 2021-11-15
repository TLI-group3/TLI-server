package CarRecommendationsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.Entities.AccountHolder;
import com.aviva.CarRecommendations.InterestFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InterestFilterTest {
    InterestFilter filterToTest;
    AccountHolder knownUser;

    @BeforeEach
    public void setup() {
        filterToTest = new InterestFilter();
        knownUser = new AccountHolder("1402110922112412");
    }

    @Test
    public void testGetFirstIndexHelper(){
        float[] testArr = {0F, 1F, 2F, 3F, 4F, 5F, 6F};
        assertEquals(6, filterToTest.getFirstIndex(testArr, 6F));
    }

    @Test
    public void testBestFiveCarsRecommendedSize(){
        int lengthOfList = filterToTest.getBestFiveCars(knownUser.getAccountNumber()).getJSONArray("cars").length();
        assertEquals(5, lengthOfList);
    }

}
