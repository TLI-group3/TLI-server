package main.java.com.aviva.Entities;

import org.json.JSONObject;

public class Car {

    private final String make;
    private final String model;
    private final int year;
    private final float price;

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

    public JSONObject toJSON() {
        JSONObject carJSON = new JSONObject();
        carJSON.put("make", this.make);
        carJSON.put("model", this.model);
        carJSON.put("year", this.year);
        carJSON.put("price", this.price);
        return carJSON;
    }
}