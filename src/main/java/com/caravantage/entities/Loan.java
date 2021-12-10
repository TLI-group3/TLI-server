package com.caravantage.entities;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class represents complete loan information for a recommended car
 */

public class Loan {
    private final float loanAmount;
    private final float interestSum;
    private final float capitalSum;
    private final float loanSum;
    private final int loanTerm;
    private final float interestRate;
    private ArrayList<Installment> installments;

    /**
     * Creates Loan Object
     * @param loanAmount A float representing amount of the loan
     * @param interestSum A float representing the total amount of interest
     * @param capitalSum A float representing the total amount of capital
     * @param loanSum A float representing the total amount of the loan, the sum of the interestSum and capitalSum
     * @param loanTerm An int representing the number of terms
     * @param interestRate A float representing the monthly interest rate
     * @param installments An Arraylist of Installment objects containing the monthly installment of the loan
     */
    public Loan(float loanAmount, float interestSum, float capitalSum, float loanSum, int loanTerm, float interestRate,
                ArrayList<Installment> installments) {
        this.loanAmount = loanAmount;
        this.interestSum = interestSum;
        this.capitalSum = capitalSum;
        this.loanSum = loanSum;
        this.loanTerm = loanTerm;
        this.interestRate = interestRate;
        this.installments = installments;
    }

    /**
     * Creates Loan Object
     * @param loanAmount A float representing amount of the loan
     * @param interestSum A float representing the total amount of interest
     * @param capitalSum A float representing the total amount of capital
     * @param loanSum A float representing the total amount of the loan, the sum of the interestSum and capitalSum
     * @param loanTerm An int representing the number of terms
     * @param interestRate A float representing the monthly interest rate
     */
    public Loan(float loanAmount, float interestSum, float capitalSum, float loanSum, int loanTerm, float interestRate) {
        this.loanAmount = loanAmount;
        this.interestSum = interestSum;
        this.capitalSum = capitalSum;
        this.loanSum = loanSum;
        this.loanTerm = loanTerm;
        this.interestRate = interestRate;
    }

    /**
     * Gets the amount of the loan this loan
     * @return A float representing amount of the loan
     */
    public float getLoanAmount() {return this.loanAmount;}

    /**
     * Gets the total amount of interest
     * @return A float representing the total amount of interest
     */
    public float getInterestSum() {return this.interestSum;}

    /**
     * Gets the total amount of capital
     * @return A float representing the total amount of capital
     */
    public float getCapitalSum() {return this.capitalSum;}

    /**
     * Gets the total amount of the loan, the sum of the interestSum and capitalSum
     * @return A float representing the loan sum
     */
    public float getLoanSum() {return this.loanSum;}

    /**
     * Gets the number of terms of this loan
     * @return An int representing the loan's number of terms
     */
    public int getLoanTerm() {return this.loanTerm;}

    /**
     * Gets the monthly interest rate of this loan
     * @return A float representing the interest rate
     */
    public float getInterestRate() {return this.interestRate;}

    /**
     * Gets the Arraylist of Installment objects containing the monthly installment of the loan
     * @return An Arraylist of Installment objects containing the monthly installment
     */
    public ArrayList<Installment> getInstallments() {return this.installments;}

    /**
     * Return the JSONObject representing the Loan with its information about manufacturer, model, year, price, and image
     * @return An JSONObject representing the Loan
     */
    public JSONObject toJSON() {
        JSONObject loanJSON = new JSONObject();
        loanJSON.put("loanAmount", this.loanAmount);
        loanJSON.put("interestSum", this.interestSum);
        loanJSON.put("capitalSum", this.capitalSum);
        loanJSON.put("loanSum", this.loanSum);
        loanJSON.put("loanTerm", this.loanTerm);
        loanJSON.put("interestRate", this.interestRate);
        return loanJSON;
    }
}

