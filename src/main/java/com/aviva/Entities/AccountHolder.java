package com.aviva.Entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an account holder at a given bank.
 */

public class AccountHolder {
    // General financial Info
    private final String accountNumber;
    private int creditScore;
    private float monthlySalary;
    private HashMap<Car,Loan> recommendedCars;

    // Information relating to lifestyle
    private float monthlyBudget;
    private float existingCarLoan;
    private String existingCar;
    private float existingCarValue;
    private float otherMonthlySpending;
    private ArrayList<Car> initialCar;

    public AccountHolder(String accountNumber){this.accountNumber = accountNumber;}

    // Setters
    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    public void setMonthlySalary(float monthlySalary) {this.monthlySalary = monthlySalary;}

    public void setMonthlyBudget(float monthlyBudget) {this.monthlyBudget = monthlyBudget;}

    public void setRecommendedCars(HashMap<Car, Loan> recommended) {this.recommendedCars = recommended;}

    public void setExistingCarLoan(float loan) {this.existingCarLoan = loan;}

    public void setExistingCar(String existingCar) {this.existingCar = existingCar;}

    public void setOtherMonthlySpending(float monthlySpending) {this.otherMonthlySpending = monthlySpending;}

    public void setInitialCar(ArrayList<Car> cars) {this.initialCar = cars;}

    public void setExistingCarValue(float existingCarValue) {
        this.existingCarValue = existingCarValue;
    }


    // Getters
    public String getAccountNumber() {return this.accountNumber;}

    public int getCreditScore() {return this.creditScore;}

    public float getMonthlySalary() {return this.monthlySalary;}

    public float getMonthlyBudget() {return this.monthlyBudget;}

    public HashMap<Car, Loan> getRecommendedCars() {return this.recommendedCars;}

    public ArrayList<Car> getInitialCar() {return this.initialCar;}

    public float getExistingCarLoan() {return this.existingCarLoan;}

    public String getExistingCar() {return this.existingCar;}

    public float getOtherMonthlySpending() {return this.otherMonthlySpending;}

    public float getExistingCarValue() {
        return this.existingCarValue;
    }
}
