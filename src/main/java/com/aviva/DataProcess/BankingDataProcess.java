package com.aviva.DataProcess;
import com.aviva.DataAccess.SQLAccountHolderDataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankingDataProcess implements BankingDataProcessingInterface{

    public static ArrayList<Float> getDeposits(String accountNumber) {
        /**
         * Returns a list of deposits made by the client from their bank statement assuming ordered by date in database.
         * @param accountNumber the account number of the client
         * @return list of deposit amounts in chronological order.
         */
        ResultSet accountDetails = SQLAccountHolderDataAccess.getAllFinancialTransactions(accountNumber);
        ArrayList<Float> deposits = new ArrayList<Float>();
        try {
            while (accountDetails.next()) {
                deposits.add(accountDetails.getFloat("deposits"));
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return deposits;
    }


    public static ArrayList<Float> getWithdrawals(String accountNumber) {
        /**
         * Returns a list of withdrawals made by the client from their bank statement.
         * @param accountNumber the account number of the client
         * @return list of withdrawal amounts in chronological order.
         */
        ResultSet accountDetails = SQLAccountHolderDataAccess.getAllFinancialTransactions(accountNumber);
        ArrayList<Float> withdrawals = new ArrayList<Float>();
        try {
            while (accountDetails.next()) {
                withdrawals.add(accountDetails.getFloat("withdrawals"));
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return withdrawals;
    }

    public static int getCreditScore(String accountNumber){
        /**
         * Returns the client's credit score or 0 if not found
         * @param accountNumber the account number of the client
         * @return credit score
         */
        ResultSet latestCreditResultSet = SQLAccountHolderDataAccess.getLatestCreditScore(accountNumber);
        int creditScore = 0;
        try {
            latestCreditResultSet.next();
            creditScore = latestCreditResultSet.getInt("creditScore");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return creditScore;
    }

    public static void main(String args[]) {
        System.out.println(getDeposits("1402110922112412"));
        System.out.println(getWithdrawals("1402110922112412"));
        System.out.println(getCreditScore("1402110922112412"));
    }
}
