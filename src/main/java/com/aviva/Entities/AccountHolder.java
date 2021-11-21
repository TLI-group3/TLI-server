package com.aviva.Entities;

// TODO: When this entity changes, change the budget estimation calculations accordingly

import java.util.ArrayList;

/**
 * This class represents an account holder at the bank
 */

public class AccountHolder {
    private String accountNumber;
    private int creditScore;
    private float savings;
    private float monthlyBudget;
    private ArrayList<RecommendedCar> recommendedCars;

    public AccountHolder(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    public void setSavings(float savings) {this.savings = savings;}

    public void setMonthlyBudget(float monthlyBudget) {this.monthlyBudget = monthlyBudget;}

    public void setRecommendedCars(ArrayList<RecommendedCar> recommended) {this.recommendedCars = recommended;}

    public String getAccountNumber() {return this.accountNumber;}

    public int getCreditScore() {return this.creditScore;}

    public float getSavings() {return this.savings;}

    public float getMonthlyBudget() {return this.monthlyBudget;}

    public ArrayList<RecommendedCar> getRecommendedCars() {return this.recommendedCars;}
}