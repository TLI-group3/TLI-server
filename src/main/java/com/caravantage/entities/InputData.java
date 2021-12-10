package com.caravantage.entities;

/**
 * A data class representing the data input by the user.
 */

public class InputData {
    private final String clientIDs; // integers separated by spaces
    private String tradeInCar; // the make model year of their current car

    /**
     * Creates InputData object
     * @param clientIDs A string representing the InputData's client ID
     */
    public InputData(String clientIDs) {
        this.clientIDs = clientIDs;
    }

    /**
     * Creates InputData object
     * @param clientIDs A String representing the InputData's client ID
     * @param tradeInCar A String representing the vin number of the trade-in car
     */
    public InputData(String clientIDs, String tradeInCar) {
        this.clientIDs = clientIDs;
        this.tradeInCar = tradeInCar;
    }

    /**
     * Gets this InputData's client ID
     * @return A string representing the InputData's client ID
     */
    public String getClientIDs() { return this.clientIDs; }

    /**
     * Gets this InputData's trade-in car
     * @return A string representing the InputData's trade-in car
     */
    public String getTradeInCar() { return this.tradeInCar; }
}
