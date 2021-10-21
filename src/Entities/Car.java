package Entities;

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
}