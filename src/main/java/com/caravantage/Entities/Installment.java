package com.caravantage.Entities;

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

    public Installment(int termNumber, float termCapital, float termInterest, float termInstallment, float remainingAmount, float interestSum) {
        this.termNumber = termNumber;
        this.termCapital = termCapital;
        this.termInterest = termInterest;
        this.termInstallment = termInstallment;
        this.remainingAmount = remainingAmount;
        this.interestSum = interestSum;
    }


    // Getters
    public int getTermNumber() {return this.termNumber;}
    public float getTermCapital() {return this.termCapital;}
    public float getTermInterest() {return this.termInterest;}
    public float getTermInstallment() {return this.termInstallment;}
    public float getRemainingAmount() {return this.remainingAmount;}
    public float getInterestSum() {return this.interestSum;}
}
