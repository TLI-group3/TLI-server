package com.caravantage.entities;

import org.json.JSONObject;

/**
 * This class represents a car
 */

public class Car {

    private String ID;
    private final String make;
    private final String model;
    private final int year;
    private final float price;
    private String image;
    private String vin;

    /**
     * Creates Car Object
     * @param name Car's manufacturer
     * @param model Car's model
     * @param year Car's making year
     * @param price Car's market price
     */
    public Car(String name, String model, int year, float price){
        this.make = name;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    /**
     * Gets the Car's model
     * @return A string representing the Car's model
     */
    public String getModel(){ return this.model;}

    /**
     * Gets the Car's manufacturer
     * @return A string representing the Car's manufacturer
     */
    public String getMake() { return make; }

    /**
     * Gets the Car's making year
     * @return An int representing the Car's making year
     */
    public int getYear() { return year; }

    /**
     * Gets the Car's market price
     * @return A float representing the Car's market price
     */
    public float getPrice() { return price; }

    /**
     * Gets the Car's vin number
     * @return A string representing the Car's vin number
     */
    public String getVin() {return this.vin;}

    /**
     * Gets the Car's ID number
     * @return A string representing the Car's ID number
     */
    public String getID(){ return this.ID; }
    public String getImage() { return image; }


    /**
     * Sets the Car's image
     * @param imageToSet A string representing the Car's image
     */
    public void setImage(String imageToSet) { this.image = imageToSet; }

    /**
     * Sets the Car's ID
     * @param id A string representing the Car's ID number
     */
    public void setID(String id) { this.ID = id; }

    /**
     * Sets the Car's image
     * @param vin A string representing the Car's vin number
     */
    public void setVin(String vin) {this.vin = vin;}


    /**
     * Return the JSONObject representing the Car with its information about manufacturer, model, year, price, and image
     * @return An JSONObject representing the Car
     */
    public JSONObject toJSON() {
        JSONObject carJSON = new JSONObject();
        carJSON.put("make", this.make);
        carJSON.put("model", this.model);
        carJSON.put("year", this.year);
        carJSON.put("price", this.price);
        carJSON.put("image", this.image);
        return carJSON;
    }
}