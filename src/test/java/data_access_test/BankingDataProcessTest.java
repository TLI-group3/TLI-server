package data_access_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.caravantage.data_access.SQLAccountHolderDataAccess;
import com.caravantage.data_access.SQLCarDataAccess;
import com.caravantage.fetch_cars.SQLBankingDataProcess;
import com.caravantage.entities.AccountHolder;
import com.caravantage.fetch_cars.SQLCarDataProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankingDataProcessTest {
    SQLBankingDataProcess processToTest;
    AccountHolder knownUser;

    @BeforeEach
    public void setup(){
        SQLCarDataAccess carDataAccess = new SQLCarDataAccess();
        SQLCarDataProcess carProcess = new SQLCarDataProcess(carDataAccess);
        SQLAccountHolderDataAccess accountAccess = new SQLAccountHolderDataAccess();
        knownUser = new AccountHolder("1402110922112412");
        processToTest = new SQLBankingDataProcess(accountAccess, carProcess);
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
