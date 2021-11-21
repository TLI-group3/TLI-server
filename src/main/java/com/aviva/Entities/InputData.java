package com.aviva.Entities;

/**
 * A data class representing the data input by the user.
 */

public class InputData {
    private final String clientIDs; // integers separated by spaces
    private final boolean generateEmail;
    private final boolean generateWidget;

    public InputData(String clientIDs, boolean generateEmail, boolean generateWidget) {
        this.clientIDs = clientIDs;
        this.generateEmail = generateEmail;
        this.generateWidget = generateWidget;
    }

    // Getters
    public String getClientIDs() { return clientIDs; }

    public boolean isGenerateEmail() { return generateEmail; }

    public boolean isGenerateWidget() { return generateWidget; }
}
