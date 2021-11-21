package com.aviva.Entities;

// TODO: When this entity changes, change the budget estimation calculations accordingly

import com.aviva.CarRecommendations.EstimateBudget;

/**
 * This class represents an account holder at the bank
 */

public class AccountHolder {
    private final String accountNumber;
    private int creditScore;
    private float savings;
    private float monthlyBudget;

    public AccountHolder(String accountNumber){
        this.accountNumber = accountNumber;
        this.savings = EstimateBudget.calculateYearlyBudget(this.accountNumber);
        this.monthlyBudget = this.savings / 12;
    }

    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    public void setSavings(float savings) {this.savings = savings;}

    public void setMonthlyBudget(float monthlyBudget) {this.monthlyBudget = monthlyBudget;}

    public String getAccountNumber() {return this.accountNumber;}

    public int getCreditScore() {return this.creditScore;}

    public float getSavings() {return this.savings;}

    public float getMonthlyBudget() {return this.monthlyBudget;}
}