package com.aviva.Entities;

/**
 * A data class representing the data input by the user.
 */

public class InputData {
    private final String clientIDs; // integers separated by spaces
    private String tradeInCar; // the make model year of their current car

    public InputData(String clientIDs) {
        this.clientIDs = clientIDs;
    }

    public InputData(String clientIDs, String tradeInCar) {
        this.clientIDs = clientIDs;
        this.tradeInCar = tradeInCar;
    }

    // Getters
    public String getClientIDs() { return this.clientIDs; }
    public String getTradeInCar() { return this.tradeInCar; }
}
