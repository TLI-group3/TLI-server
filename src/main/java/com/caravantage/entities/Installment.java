package com.caravantage.entities;

/**
 * This class represents an installment from a loan
 */

public class Installment {
    private final int termNumber;
    private final float termCapital;
    private final float termInterest;
    private final float termInstallment;
    private final float remainingAmount;
    private final float interestSum;

    /**
     * Creates Installment Object
     * @param termNumber An int representing the number of installment
     * @param termCapital A float representing the capital amount
     * @param termInterest A float representing the interest rate
     * @param termInstallment A float representing the installment amount
     * @param remainingAmount A float representing the remaining amount to be paid
     * @param interestSum A float representing the total interest
     */
    public Installment(int termNumber, float termCapital, float termInterest, float termInstallment, float remainingAmount, float interestSum) {
        this.termNumber = termNumber;
        this.termCapital = termCapital;
        this.termInterest = termInterest;
        this.termInstallment = termInstallment;
        this.remainingAmount = remainingAmount;
        this.interestSum = interestSum;
    }

    /**
     * Gets the number of terms of this loan
     * @return An int representing the number of terms
     */
    public int getTermNumber() {return this.termNumber;}

    /**
     * Gets the amount of the capital of one term for this loan
     * @return A float representing amount of the capital
     */
    public float getTermCapital() {return this.termCapital;}

    /**
     * Gets the monthly interest rate of this loan
     * @return A float representing the monthly interest rate
     */
    public float getTermInterest() {return this.termInterest;}

    /**
     * Gets the monthly installment of this loan
     * @return A float representing the monthly installment
     */
    public float getTermInstallment() {return this.termInstallment;}

    /**
     * Gets the remaining amount to be repaid for this loan
     * @return A float representing the remaining amount to be repaid
     */
    public float getRemainingAmount() {return this.remainingAmount;}

    /**
     * Gets the total amount of interest in this loan
     * @return A float representing the total interest
     */
    public float getInterestSum() {return this.interestSum;}
}
