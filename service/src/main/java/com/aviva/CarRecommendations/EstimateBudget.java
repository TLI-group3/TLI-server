package main.java.com.aviva.CarRecommendations;

public class EstimateBudget {
    float totalWithdraws = 0;
    float totalDeposits = 0;

    float annualBudget = totalDeposits - totalWithdraws;

    float monthlyBudget = annualBudget / 12;
}
