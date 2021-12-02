package EntitiesTest;

import com.aviva.Entities.Installment;
import com.aviva.Entities.Loan;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the Loan entity
 */

public class LoanTest {
   Loan loanToTest;


    @BeforeEach
    public void setup() {
        Installment testInstallment = new Installment(1,
                2F, 3F, 4F, 5F, 6F);
        ArrayList<Installment> testList = new ArrayList<>();
        testList.add(testInstallment);
        loanToTest = new Loan(10F, 11F, 12F,
                13F, 14, 15F, testList);
    }

    // Test getters
    @Test
    public void testGetLoanAmount() {
        assertEquals(10F, loanToTest.getLoanAmount());
    }

    @Test
    public void testGetInterestSum() {
        assertEquals(11F, loanToTest.getInterestSum());
    }

    @Test
    public void testGetCapitalSum() {
        assertEquals(12F, loanToTest.getCapitalSum());
    }

    @Test
    public void testGetLoanSum() {
        assertEquals(13F, loanToTest.getLoanSum());
    }

    @Test
    public void testGetLoanTerm() {
        assertEquals(14, loanToTest.getLoanTerm());
    }

    @Test
    public void testGetInterestRate() {
        assertEquals(15F, loanToTest.getInterestRate());
    }

    @Test
    public void testGetInstalments() {
        Installment testInstallment = new Installment(1,
                2F, 3F, 4F, 5F, 6F);
        ArrayList<Installment> testList = new ArrayList<>();
        testList.add(testInstallment);
        assertEquals(testList.size(), loanToTest.getInstallments().size());
    }

    // Test Constructors
    @Test
    public void testNoInstallmentConstructor() {
        Loan newLoan = new Loan(100F, 101F, 102F,
                103F, 104, 105F);
        assertNull(newLoan.getInstallments());
    }

    // Test Converters
    @Test
    public void testLoanJSONConverter() {
        JSONObject jsonEquiv = new JSONObject();
        jsonEquiv.put("loanAmount", 10F);
        jsonEquiv.put("interestSum", 11F);
        jsonEquiv.put("capitalSum", 12F);
        jsonEquiv.put("loanSum", 13F);
        jsonEquiv.put("loanTerm", 14);
        jsonEquiv.put("interestRate", 15F);

        assertEquals(jsonEquiv.length(), loanToTest.toJSON().length());
        assertEquals(jsonEquiv.get("interestRate"), loanToTest.toJSON().get("interestRate"));
    }
}
