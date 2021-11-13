package com.aviva.DataProcess;
import com.aviva.DataAccess.SQLAccountHolderDataAccess;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountHolderDataProcess implements BankingDataProcessingInterface{

    public ArrayList<Integer> getDeposits(String accountNumber) {
        /**
         * Returns a list of deposits made by the client from their bank statement.
         * @param accountNumber the account number of the client
         * @return list of deposit amounts in chronological order.
         */
        ResultSet accountDetails = SQLAccountHolderDataAccess.getAllFinancialTransactions(accountNumber);
        ArrayList<Integer> deposits = new ArrayList<Integer>();
        return deposits;
    }


    public ArrayList<Integer> getWithdrawals(String accountNumber) {
        /**
         * Returns a list of withdrawals made by the client from their bank statement.
         * @param accountNumber the account number of the client
         * @return list of withdrawal amounts in chronological order.
         */
        ResultSet accountDetails = SQLAccountHolderDataAccess.getAllFinancialTransactions(accountNumber);
        ArrayList<Integer> withdrawals = new ArrayList<Integer>();
        return withdrawals;
    }

    public int getCreditScore(String accountNumber){
        /**
         * Returns the client's credit score
         * @param accountNumber the account number of the client
         * @return credit score
         */
        ResultSet accountDetails = SQLAccountHolderDataAccess.getAllFinancialTransactions(accountNumber);
        int creditScore = 10;
        return creditScore;
    }

}
