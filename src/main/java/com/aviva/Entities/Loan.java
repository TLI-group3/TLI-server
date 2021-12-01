package com.aviva.Entities;

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

    public Loan(float loanAmount, float interestSum, float capitalSum, float loanSum, int loanTerm, float interestRate) {
        this.loanAmount = loanAmount;
        this.interestSum = interestSum;
        this.capitalSum = capitalSum;
        this.loanSum = loanSum;
        this.loanTerm = loanTerm;
        this.interestRate = interestRate;
    }

    // Getters
    public float getLoanAmount() {return this.loanAmount;}
    public float getInterestSum() {return this.interestSum;}
    public float getCapitalSum() {return this.capitalSum;}
    public float getLoanSum() {return this.loanSum;}
    public int getLoanTerm() {return this.loanTerm;}
    public float getInterestRate() {return this.interestRate;}
    public ArrayList<Installment> getInstallments() {return this.installments;}

    // Convert loan to JSON type
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

