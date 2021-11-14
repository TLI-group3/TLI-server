package com.aviva.DataAccess;

import com.aviva.Entities.AccountHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankingDataProcess implements BankingDataProcessingInterface{
    /**
     * Returns a list of deposits made by the client from their bank statement assuming ordered by date in database.
     * @param accountNumber the account number of the client
     * @return list of deposit amounts in chronological order.
     */
    public ArrayList<Float> getDeposits(String accountNumber) {

        SQLAccountHolderDataAccess sqlahdaInit = new SQLAccountHolderDataAccess();
        ResultSet accountDetails = sqlahdaInit.getAllFinancialTransactions(accountNumber);

        ArrayList<Float> deposits = new ArrayList<>();
        try {
            while (accountDetails.next()) {
                deposits.add(accountDetails.getFloat("deposits"));
            }
        } catch (SQLException e){
            System.out.println("Could not get deposits");
        }
        return deposits;
    }

    /**
     * Returns a list of withdrawals made by the client from their bank statement.
     * @param accountNumber the account number of the client
     * @return list of withdrawal amounts in chronological order.
     */
    public ArrayList<Float> getWithdrawals(String accountNumber) {
        SQLAccountHolderDataAccess sqlahdaInit = new SQLAccountHolderDataAccess();
        ResultSet accountDetails = sqlahdaInit.getAllFinancialTransactions(accountNumber);
        ArrayList<Float> withdrawals = new ArrayList<>();
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
    public int getCreditScore(String accountNumber){
        SQLAccountHolderDataAccess sqlahdaInit = new SQLAccountHolderDataAccess();
        ResultSet latestCreditResultSet = sqlahdaInit.getLatestCreditScore(accountNumber);
        int creditScore = 0;
        try {
            latestCreditResultSet.next();
            creditScore = latestCreditResultSet.getInt("creditScore");
        } catch (SQLException e) {
            System.out.println("Could not get credit score");
        }
        return creditScore;
    }

    public AccountHolder getAccountHolder(String accountNumber) {
        AccountHolder user = new AccountHolder(accountNumber);
        user.setCreditScore(getCreditScore(user.getAccountNumber()));
        return user;
    }
}
