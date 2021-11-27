package com.aviva.Entities;

import org.json.JSONObject;
import org.yaml.snakeyaml.events.Event;

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