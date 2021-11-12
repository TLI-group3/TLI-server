package main.java.com.aviva.Entities;
import org.json.JSONObject;


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

    public AccountHolder(String accountNumber){
        this.accountNumber = accountNumber;
    }

    /**
     * Returns the account number of this account holder.
     * @return string representing account number.
     */
    public String getAccountNumber() {return this.accountNumber;}

    /**
     * Sets the most recent credit score of this account holder.
     * @param credit score
     */
    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    /**
     * Sets the monthly salary of this account holder.
     * @param monthly salary
     */
    public void setMonthlySalary(int monthlySalary) {this.monthlySalary = monthlySalary;}

    /**
     * Set the calculated estimated budget of this account holder.
     * @param estimated budget of this account holder
     */
    public void setMonthlyBudget(float monthlyBudget) {this.monthlyBudget = monthlyBudget;}

    /**
     * Sets the existing car loan of this account holder.
     * @param car loan
     */
    public void setExistingCarLoan(float loan) {this.existingCarLoan = loan;}

    /**
     * Sets the existing vehicle of this account holder.
     * @param car name
     */
    public void setExistingCar(String ownedCar) {this.existingCar = ownedCar;}

    /**
     * Sets the total of the monthly spending of this account holder.
     * @param monthly spending
     */
    public void setOtherMonthlySpending(float monthlySpending) {this.otherMonthlySpending = monthlySpending;}

    /**
     * Returns the most recent credit score this account holder.
     * @return credit score.
     */
    public int getCreditScore() {return this.creditScore;}


    /**
     * Returns the monthly salary of this account holder.
     * @return monthly salary
     */
    public void getMonthlySalary() {return this.monthlySalary;}

    /**
     * Returns calculated estimated budget of this account holder.
     * @return credit score.
     */

    public float getMonthlyBudget() {return this.monthlyBudget;}

    /**
     * Returns the existing car loan of this account holder.
     * @return car loan
     */
    public void getExistingCarLoan() {return this.existingCarLoan;}

    /**
     * Returns the existing vehicle of this account holder.
     * @return car name
     */
    public void getExistingCar() {return this.existingCar;}

    /**
     * Returns the total of the monthly spending of this account holder.
     * @return monthly spending
     */
    public void setOtherMonthlySpending() {return this.otherMonthlySpending;}

}