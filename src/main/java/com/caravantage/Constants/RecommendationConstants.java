package com.caravantage.Constants;

/**
 * Public class that stores all the constants used throughout our application
 */

public class RecommendationConstants {

    public static final int BUDGET_FILTER_SIZE = 10; // Number of recommended cars based on BudgetFilter
    public static final int INTEREST_FILTER_SIZE = 5; // Number of recommended cars based on InterestFilter
    public static final double DOWN_PAYMENT_RATIO = 0.1; // % Percentage of car price to pay as down payment
    public static final float VEHICLE_KMS = 15000.0F; // Number of kilometres driven by car
    public static final double UPSELL_RATIO = 0.2; // % of car price to upsell
    public static final double TRADE_DEPRECIATION_RATIO = 0.1; // Car depreciation scaling factor
    public static final int MINIMUM_CAR_PRICE = 5000;
    public static double CAR_EXPENDITURE_RATIO = 0.5; // % of annual salary to spend on car
    public static float MINIMUM_BIMONTHLY_SALARY = 1000;
    public static int BIMONTHLY_SALARY_FREQUENCY = 24;
    public static int MONTHLY_SPENDING_FREQUENCY = 12;
    public static float MINIMUM_MONTHLY_CAR_LOAN = 50;
    public static final double MONTHLY_ALLOCATION = 0.1; // % of monthly budget allocated to spend
}
