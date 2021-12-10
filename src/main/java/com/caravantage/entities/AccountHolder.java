package com.caravantage.entities;

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

    /**
     * Creates AccountHolder object
     * @param accountNumber A String representing this AccountHolder's account number.
     */
    public AccountHolder(String accountNumber){this.accountNumber = accountNumber;}

    /**
     * Sets the AccountHolder's credit score.
     * @param creditScore An int representing this AccountHolder's credit score.
     */
    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    /**
     * Sets the AccountHolder's estimated monthly salary.
     * @param monthlySalary A float representing this AccountHolder's estimated monthly salary.
     */
    public void setMonthlySalary(float monthlySalary) {this.monthlySalary = monthlySalary;}

    /**
     * Sets the AccountHolder's estimated monthly budget.
     * @param monthlyBudget A float representing this AccountHolder's estimated monthly budget.
     */
    public void setMonthlyBudget(float monthlyBudget) {this.monthlyBudget = monthlyBudget;}

    /**
     * Sets the AccountHolder's final list of recommended cars.
     * @param recommended A hashmap mapping a recommended car to its loan information.
     */
    public void setRecommendedCars(HashMap<Car, Loan> recommended) {this.recommendedCars = recommended;}

    /**
     * Sets the AccountHolder's estimated existing car loan.
     * @param loan A float representing this AccountHolder's estimated monthly car loan.
     */
    public void setExistingCarLoan(float loan) {this.existingCarLoan = loan;}

    /**
     * Sets the AccountHolder's existing car name.
     * @param existingCar A String representing this AccountHolder's existing car name.
     */
    public void setExistingCar(String existingCar) {this.existingCar = existingCar;}

    /**
     * Sets the AccountHolder's estimated monthly spending, not including the estimated existing car loan.
     * @param monthlySpending A float representing this AccountHolder's estimated monthly spending,
     *        not including the estimated existing car loan.
     */
    public void setOtherMonthlySpending(float monthlySpending) {this.otherMonthlySpending = monthlySpending;}

    /**
     * Sets the AccountHolder's list of suitable budget-wise cars.
     * @param cars An ArrayList storing the cars that are within the AccountHolder's budget.
     */
    public void setInitialCar(ArrayList<Car> cars) {this.initialCar = cars;}

    /**
     * Sets the AccountHolder's existing car's value.
     * @param existingCarValue A float representing the value of the AccountHolder's existing car.
     */
    public void setExistingCarValue(float existingCarValue) {
        this.existingCarValue = existingCarValue;
    }


    /**
     * Gets the AccountHolder's bank account number.
     * @return A String representing this AccountHolder's bank account number.
     */
    public String getAccountNumber() {return this.accountNumber;}

    /**
     * Gets the AccountHolder's credit score.
     * @return An int representing this AccountHolder's credit score.
     */
    public int getCreditScore() {return this.creditScore;}

    /**
     * Gets the AccountHolder's estimated monthly salary.
     * @return A float representing this AccountHolder's estimated monthly salary.
     */
    public float getMonthlySalary() {return this.monthlySalary;}

    /**
     * Gets the AccountHolder's estimated monthly budget.
     * @return A float representing this AccountHolder's estimated budget salary.
     */
    public float getMonthlyBudget() {return this.monthlyBudget;}

    /**
     * Gets the AccountHolder's final list of recommended cars.
     * @return A HashMap mapping a recommended car to its loan information.
     */
    public HashMap<Car, Loan> getRecommendedCars() {return this.recommendedCars;}

    /**
     * Gets the AccountHolder's list of suitable budget-wise cars.
     * @return An ArrayList storing the cars that are within the AccountHolder's budget.
     */
    public ArrayList<Car> getInitialCar() {return this.initialCar;}

    /**
     * Gets the AccountHolder's estimated existing car loan.
     * @return A float representing this AccountHolder's estimated monthly car loan.
     */
    public float getExistingCarLoan() {return this.existingCarLoan;}

    /**
     * Gets the AccountHolder's existing car name.
     * @return A String representing this AccountHolder's existing car name.
     */
    public String getExistingCar() {return this.existingCar;}

    /**
     * Gets the AccountHolder's estimated monthly spending, not including the estimated existing car loan.
     * @return A float representing this AccountHolder's estimated monthly spending,
     *        not including the estimated existing car loan.
     */
    public float getOtherMonthlySpending() {return this.otherMonthlySpending;}

    /**
     * Gets the AccountHolder's existing car's value.
     * @return A float representing the value of the AccountHolder's existing car.
     */
    public float getExistingCarValue() {
        return this.existingCarValue;
    }
}
