package com.aviva.Entities;

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

    public Car(String name, String model, int year, float price){
        this.make = name;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getModel(){ return this.model;}
    public String getMake() { return make;  }
    public int getYear() { return year; }
    public float getPrice() { return price; }
    public void setImage(String imageToSet) { this.image = imageToSet; }
    public String getImage() { return image; }
    public void setID(String id) { this.ID = id; }
    public String getID(){ return this.ID; }
    public void setVin(String vin) {this.vin = vin;}
    public String getVin() {return this.vin;}


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