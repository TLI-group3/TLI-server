package DataAccessTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aviva.DataAccess.BankingDataProcess;
import com.aviva.Entities.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankingDataProcessTest {
    BankingDataProcess processToTest;
    AccountHolder knownUser;

    @BeforeEach
    public void setup(){
        knownUser = new AccountHolder("1402110922112412");
        processToTest = new BankingDataProcess();
    }

    @Test
    public void testGetDeposits() {
        assertEquals(0.0F, processToTest.getDeposits(knownUser.getAccountNumber()).get(0));
        assertEquals(2750.0F, processToTest.getDeposits(knownUser.getAccountNumber()).get(1));
    }

    @Test
    public void testGetWithdrawals() {
        assertEquals(1995.0F, processToTest.getWithdrawals(knownUser.getAccountNumber()).get(0));
        assertEquals(0.0F, processToTest.getWithdrawals(knownUser.getAccountNumber()).get(1));
        assertEquals(300.0F, processToTest.getWithdrawals(knownUser.getAccountNumber()).get(2));
    }

    @Test
    public void testGetCreditScore() {
        assertEquals(719, processToTest.getCreditScore(knownUser.getAccountNumber()));
    }

    @Test
    public void testGetAccountHolder() {
        assertEquals(719, processToTest.getAccountHolder(knownUser.getAccountNumber()).getCreditScore());
    }
}
