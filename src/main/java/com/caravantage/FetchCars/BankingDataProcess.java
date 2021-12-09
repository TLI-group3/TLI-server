package com.caravantage.FetchCars;

import com.caravantage.DataAccess.AccountAccessInterface;
import com.caravantage.DataAccess.CarAccessInterface;
import com.caravantage.DataAccess.SQLAccountHolderDataAccess;
import com.caravantage.Entities.AccountHolder;
import com.caravantage.Entities.Car;
import com.caravantage.Entities.Loan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Public class that handles processing data from the database and converting it into proper types/entities for clients
 */

public class BankingDataProcess implements BankingDataProcessingInterface {
    /**
     * Returns a list of deposits made by the client from their bank statement assuming ordered by date in database.
     * @param accountNumber the account number of the client
     * @return list of deposit amounts in chronological order.
     */
    public ArrayList<Float> getDeposits(String accountNumber, AccountAccessInterface sqlahdaInit) {
        // Query all transactions for the given account number
        ResultSet accountDetails = sqlahdaInit.getAllFinancialTransactions(accountNumber);
        ArrayList<Float> deposits = new ArrayList<>();

        // Add any deposit to a list
        try {
            while (accountDetails.next()) {
                deposits.add(accountDetails.getFloat("deposits"));
            }
        }
        catch (SQLException e){
            System.out.println("Could not get deposits");
        }
        return deposits;
    }

    /**
     * Returns a list of withdrawals made by the client from their bank statement.
     * @param accountNumber the account number of the client
     * @return list of withdrawal amounts in chronological order.
     */
    public ArrayList<Float> getWithdrawals(String accountNumber, AccountAccessInterface sqlahdaInit) {
        // Query all transactions for the given account number
        ResultSet accountDetails = sqlahdaInit.getAllFinancialTransactions(accountNumber);
        ArrayList<Float> withdrawals = new ArrayList<>();

        // Add any withdrawals to a list
        try {
            while (accountDetails.next()) {
                withdrawals.add(accountDetails.getFloat("withdrawals"));
            }
        } catch (SQLException e){
            System.out.println("Could not get withdrawals");
        }
        return withdrawals;
    }

    /**
     * Returns the client's credit score or 0 if not found
     * @param accountNumber the account number of the client
     * @return credit score
     */
    public int getCreditScore(String accountNumber, AccountAccessInterface sqlahdaInit){
        // Query the latest credit score for the given account number
        ResultSet latestCreditResultSet = sqlahdaInit.getLatestCreditScore(accountNumber);

        int creditScore = 0;

        // Return the credit score if found
        try {
            latestCreditResultSet.next();
            creditScore = latestCreditResultSet.getInt("creditScore");
            return creditScore;
        }
        catch (SQLException e) {
            System.out.println("Could not get credit score");
            return creditScore;
        }
    }

    /**
     * Returns an AccountHolder entity with an appropriate credit score given an accountNumber
     * @param accountNumber the account number of the client
     * @return AccountHolder entity for the given accountNumber
     */
    public AccountHolder makeAccountHolder(String accountNumber, AccountAccessInterface accountAccess) {
        AccountHolder user = new AccountHolder(accountNumber);
        user.setCreditScore(getCreditScore(accountNumber, accountAccess));
        return user;
    }

    /**
     * Returns a mapping of a client's recommended Car Objects to their respective Loan Objects
     * @param accountNumber the id number of the client
     * @return HashMap of Car Objects to their respective Loan Objects
     */
    public HashMap<Car, Loan> getRecommendedCars(String accountNumber,
                                                 CarDataProcessingInterface carProcess,
                                                 AccountAccessInterface accountAccess, CarAccessInterface carAccess) {
        HashMap<Car, Loan> recommendations = new HashMap<>();

        try {
            // Query the latest credit score for the given account number
            ResultSet allRecommendations = accountAccess.getAllRecommendations(accountNumber);

            while (allRecommendations.next()) {
                String vin = allRecommendations.getString(3);

                Car carFromDataset = carProcess.getCarByVin(vin, carAccess);
                Loan loanfromQuery = helperCreateLoanObject(allRecommendations);
                recommendations.put(carFromDataset, loanfromQuery);
            }

            return recommendations;
        }
        catch (SQLException e) {
            System.out.println("Failed to get recommended cars for client");
            return null;
        }
    }

    /**
     * Returns a Loan object given a ResultSet from the database
     * @return a Loan object with respective data
     */
    private Loan helperCreateLoanObject(ResultSet loanInfo) throws SQLException {
        float loanAmount = loanInfo.getFloat("loanAmount");
        float interestSum = loanInfo.getFloat("interestSum");
        float CapitalSum = loanInfo.getFloat("CapitalSum");
        float loanSum = loanInfo.getFloat("loanSum");
        int loanTerm = loanInfo.getInt("loanTerm");
        float interestRate = loanInfo.getFloat("interestRate");
        Loan loan = new Loan(loanAmount, interestSum, CapitalSum, loanSum, loanTerm, interestRate);
        return loan;
    }
}
