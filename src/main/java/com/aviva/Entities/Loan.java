package com.aviva.Entities;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class represents a recommended car with complete loan information
 */

public class Loan {
    private float loanAmount;
    private float interestSum;
    private float capitalSum;
    private float loanSum;
    private int loanTerm;
    private float interestRate;
    private ArrayList<Installment> installments;

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

    public void setLoanAmount(float loanAmount) {this.loanAmount = loanAmount;}
    public void setInterestSum(float interestSum) {this.interestSum = interestSum;}
    public void setCapitalSum(float capitalSum) {this.capitalSum = capitalSum;}
    public void setLoanSum(float loanSum) {this.loanSum = loanSum;}
    public void setLoanTerm(int loanTerm) {this.loanTerm = loanTerm;}
    public void setInterestRate(float interestRate) {this.interestRate = interestRate;}
    public void setInstallments(ArrayList<Installment> installments) {this.installments = installments;}

    public float getLoanAmount() {return this.loanAmount;}
    public float getInterestSum() {return this.interestSum;}
    public float getCapitalSum() {return this.capitalSum;}
    public float getLoanSum() {return this.loanSum;}
    public int getLoanTerm() {return this.loanTerm;}
    public float getInterestRate() {return this.interestRate;}
    public ArrayList<Installment> getInstallments() {return this.installments;}

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

