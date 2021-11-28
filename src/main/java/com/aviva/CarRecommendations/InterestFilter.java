package com.aviva.CarRecommendations;

import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import com.aviva.Entities.Installment;
import com.aviva.Entities.Loan;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Public class that handles the business logic of generating the best cars for an AccountHolder (second filter)
 * Filters the initial 10 cars down to 5 based on the lowest interest rates returned by the Senso API
 */

public class InterestFilter extends Handler{
    private final AccountHolder account;

    public InterestFilter(int i, AccountHolder account) {
        this.level = i;
        this.account = account;
    }

    /**
     * Filter a list of cars by their loan interest rate given by the senso api.
     */
    public void performTask() {
        generateRecommendedCars();
    }



    private void generateRecommendedCars(){
        ArrayList<Car> initialCars = this.account.getInitialCar();
        // Variable Initialization
        SensoRate srInit = new SensoRate();
        HashMap<Car, Loan> bestFive = new HashMap<Car, Loan>();
        ArrayList<Loan> loans = new ArrayList<Loan>();
        float[] rates = new float[10];

        // Loop to store interest rate of each car
        for (int i = 0; i < initialCars.size(); i++) {
            JSONObject JSONLoan = srInit.getLoan(account, initialCars.get(i));
            Loan loan = loanConverter(JSONLoan);
            loans.add(loan);
            rates[i] = loan.getInterestRate();
        }

        // Create a copy of the array of interest rates and sort them
        float[] sortedRates = Arrays.copyOf(rates, 10);
        Arrays.sort(sortedRates);

        // Loop to get five best cars
        for (int i = 0; i < 5; i++) {
            float sortedRate = sortedRates[i];
            int carIndex = getFirstIndex(rates, sortedRate);
            Car carAtIndex = initialCars.get(carIndex);
            Loan loanAtIndex = loans.get(carIndex);
            bestFive.put(carAtIndex, loanAtIndex);
            rates[carIndex] = (float) -1.0;
        }
        account.setRecommendedCars(bestFive);
    }

    /**
     * Returns the lowest index value of an element in an array that matches a given value
     *
     * @param rates an array of floats representing interest rates
     * @param value a float representing the interest rate to find within the given array
     * @return index value of given element in array as integer
     */
    private int getFirstIndex(float[] rates, float value) {
        for (int i = 0; i < rates.length; i++) {
            if (rates[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private Loan loanConverter(JSONObject JSONLoan) {
        float amount = JSONLoan.getFloat("amount");
        float interestSum = JSONLoan.getFloat("interestSum");
        float capitalSum = JSONLoan.getFloat("capitalSum");
        float sum = JSONLoan.getFloat("sum");
        int term = JSONLoan.getInt("term");
        float interestRate = JSONLoan.getFloat("interestRate");
        JSONArray JSONInstallments = JSONLoan.getJSONArray("installments");
        ArrayList<Installment> installments = installmentConvertor(JSONInstallments);

        Loan loan = new Loan(amount, interestSum, capitalSum, sum, term, interestRate, installments);

        return loan;
    }

    private ArrayList<Installment> installmentConvertor(JSONArray JSONInstallments) {
        ArrayList<Installment> installments = new ArrayList<Installment>();

        for (int i = 0; i < JSONInstallments.length(); i++) {
            JSONObject JSONInstallment = JSONInstallments.getJSONObject(i);

            float capital = JSONInstallment.getFloat("capital");
            float interest = JSONInstallment.getFloat("interest");
            float installment = JSONInstallment.getFloat("installment");
            float remain = JSONInstallment.getFloat("remain");
            float interestSum = JSONInstallment.getFloat("interestSum");

            Installment installmentToAdd = new Installment(i + 1, capital, interest, installment,
                    remain, interestSum);
            installments.add(installmentToAdd);
        }

        return installments;
    }
}
