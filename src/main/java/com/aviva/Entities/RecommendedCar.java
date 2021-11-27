package com.aviva.Entities;

import java.util.ArrayList;

/**
 * This class represents a recommended car with complete loan information
 */

public class RecommendedCar extends Car {
    private float loanAmount;
    private float interestSum;
    private float capitalSum;
    private float loanSum;
    private int loanTerm;
    private float interestRate;
    private ArrayList<Installment> installments;

    public RecommendedCar(String name, String model, int year, float price) {
        super(name, model, year, price);
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

}

