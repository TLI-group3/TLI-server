package com.aviva.Entities;

// TODO: When this entity changes, change the budget estimation calculations accordingly

/**
 * This class represents an account holder at a given bank.
 */

public class AccountHolder {
    // General financial Info
    private String accountNumber;
    private int creditScore;
    private float monthlySalary;

    // Information relating to lifestyle
    private float monthlyBudget;
    private float existingCarLoan;
    private String existingCar;
    private float otherMonthlySpending;

    public AccountHolder(String accountNumber){this.accountNumber = accountNumber;}

    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    public void setMonthlySalary(float monthlySalary) {this.monthlySalary = monthlySalary;}

    public void setMonthlyBudget(float monthlyBudget) {this.monthlyBudget = monthlyBudget;}

    public void setExistingCarLoan(float loan) {this.existingCarLoan = loan;}

    public void setExistingCar(String existingCar) {this.existingCar = existingCar;}

    public void setOtherMonthlySpending(float monthlySpending) {this.otherMonthlySpending = monthlySpending;}

    public String getAccountNumber() {return this.accountNumber;}

    public int getCreditScore() {return this.creditScore;}

    public float getMonthlySalary() {return this.monthlySalary;}

    public float getMonthlyBudget() {return this.monthlyBudget;}

    public float getExistingCarLoan() {return this.existingCarLoan;}

    public String getExistingCar() {return this.existingCar;}

    public float getOtherMonthlySpending() {return this.otherMonthlySpending;}

}
