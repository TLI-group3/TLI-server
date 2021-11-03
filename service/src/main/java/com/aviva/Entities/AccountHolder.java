package com.aviva.Entities;

/**
 * This class represents an account holder at the bank
 */
public class AccountHolder {
    private String accountNumber;
    private int creditScore;
    private float savings;
    private float monthlyBudget;

    public AccountHolder(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    public void setSavings(float savings) {this.savings = savings;}

    public void setMonthlyBudget(float monthlyBudget) {this.monthlyBudget = monthlyBudget;}

    public String getAccountNumber() {return this.accountNumber;}

    public int getCreditScore() {return this.creditScore;}

    public float getSavings() {return this.savings;}

    public float getMonthlyBudget() {return this.monthlyBudget;}
}