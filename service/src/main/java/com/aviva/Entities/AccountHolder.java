package main.java.com.aviva.Entities;
import org.json.JSONObject;


/**
 * This class represents an account holder at the bank
 */
public class AccountHolder {
    private String accountNumber;
    private int creditScore;
    private float monthlyBudget;

    // A temporarily private instance to store all the financial transactions
    private JSONObject financialTransactions;

    public AccountHolder(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setCreditScore(int creditScore) {this.creditScore = creditScore;}

    public void setMonthlyBudget(float monthlyBudget) {this.monthlyBudget = monthlyBudget;}

    public String getAccountNumber() {return this.accountNumber;}

    public int getCreditScore() {return this.creditScore;}

    public float getMonthlyBudget() {return this.monthlyBudget;}

    // A temporarily setter method to store all the financial transactions
    public void setFinancialTransactions(JSONObject financialTransactions) {
        this.financialTransactions=financialTransactions;}

    // A temporarily getter method to return all the financial transactions
    public JSONObject getFinancialTransactions() {return this.financialTransactions;}
}