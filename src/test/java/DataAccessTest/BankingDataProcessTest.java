package DataAccessTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.DataAccess.SQLAccountHolderDataAccess;
import com.caravantage.DataAccess.SQLCarDataAccess;
import com.caravantage.FetchCars.SQLBankingDataProcess;
import com.caravantage.Entities.AccountHolder;
import com.caravantage.FetchCars.SQLCarDataProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankingDataProcessTest {
    SQLBankingDataProcess processToTest;
    SQLCarDataAccess carDataAccess;
    SQLAccountHolderDataAccess accountAccess;
    SQLCarDataProcess carDataProcess;
    AccountHolder knownUser;

    @BeforeEach
    public void setup(){
        knownUser = new AccountHolder("1402110922112412");
        processToTest = new SQLBankingDataProcess(accountAccess, carDataProcess, carDataAccess);
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
        assertEquals(800.0F, processToTest.getWithdrawals(knownUser.getAccountNumber()).get(2));
    }

    @Test
    public void testGetCreditScore() {
        assertEquals(719, processToTest.getCreditScore(knownUser.getAccountNumber()));
    }

    @Test
    public void testMakeAccountHolder() {
        assertEquals(719, processToTest.makeAccountHolder(knownUser.getAccountNumber()).getCreditScore());
    }
}
