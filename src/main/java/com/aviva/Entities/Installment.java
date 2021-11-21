package com.aviva.Entities;

public class Installment {
    private int termNumber;
    private float termCapital;
    private float termInterest;
    private float termInstallment;
    private float remainingAmount;
    private float interestSum;

    public Installment(int termNumber, float termCapital, float termInterest, float termInstallment, float remainingAmount, float interestSum) {
        this.termNumber = termNumber;
        this.termCapital = termCapital;
        this.termInterest = termInterest;
        this.termInstallment = termInstallment;
        this.remainingAmount = remainingAmount;
        this.interestSum = interestSum;
    }

    public int getTermNumber() {return this.termNumber;}
    public float getTermCapital() {return this.termCapital;}
    public float getTermInterest() {return this.termInterest;}
    public float getTermInstallment() {return this.termInstallment;}
    public float getRemainingAmount() {return this.remainingAmount;}
    public float getInterestSum() {return this.interestSum;}
}
