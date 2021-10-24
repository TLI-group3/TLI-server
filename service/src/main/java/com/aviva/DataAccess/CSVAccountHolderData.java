package main.java.com.aviva.DataAccess;

import main.java.com.aviva.Entities.AccountHolder;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class CSVAccountHolderData implements AccountHolderDataInterface {
    private HashMap<String, AccountHolder> accountHolders;

    public CSVAccountHolderData() throws IOException {
        this.accountHolders = new HashMap<String, AccountHolder>();
        this.bankingReader();
    }

    @Override
    public AccountHolder getClientByID(String ID) {
        if (accountHolders.containsKey(ID)){
            return accountHolders.get(ID);
        }
        return null;
    }

    @Override
    public Collection<AccountHolder> getAllClients() {
        return accountHolders.values();
    }

    public void bankingReader() throws IOException {
        // TODO Polish the body of this method.
        FileReader fr = null;
        fr = new FileReader
                ("data/100_BT_Records.csv");

        BufferedReader csvReader = new BufferedReader(fr);

        String row = null;
        row = csvReader.readLine();
        row = csvReader.readLine();

        String[] data = row.split(",");

        csvReader.close();

        fr.close();

        //Retrieve the data from the fake dataset
        String accountNumber = data[0];
        int creditScore = Integer.parseInt(data[6]);
        float savings = Float.parseFloat(data[5]);
        float monthlyBudget = Float.parseFloat(data[3]);

        //Temporarily assign the info extracted from the dataset to the AccountHolder object
        AccountHolder account = new AccountHolder(accountNumber);
        account.setCreditScore(creditScore);
        account.setSavings(savings);
        account.setMonthlyBudget(monthlyBudget);

        this.accountHolders.put(accountNumber, account);
    }

    public static void main(String[] args) throws IOException {
        CSVAccountHolderData tester = new CSVAccountHolderData();
        AccountHolder acc = tester.getClientByID("5002357538983918");
        System.out.println("Account Number: " + acc.getAccountNumber());
        System.out.println("Credit Score: " + acc.getCreditScore());
        System.out.println("Monthly Budget: " + acc.getMonthlyBudget());
        System.out.println("Savings: "+acc.getSavings());
    }
}
