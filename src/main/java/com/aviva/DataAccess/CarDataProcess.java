package com.aviva.DataAccess;

import com.aviva.Entities.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDataProcess implements CarDataProcessingInterface{
    /**
     * Iterates through our table of cars and returns all of them
     * @return a list of car objects from our database sorted by ascending price
     */
    public ArrayList<Car> getAllCars(){
        SQLCarDataAccess sqlcdaInit = new SQLCarDataAccess();
        ResultSet carsInDB = sqlcdaInit.getAllCars();
        ArrayList<Car> cars = new ArrayList<>();
        try {
            while (carsInDB.next()) {
                String carName = carsInDB.getString("brand");
                String carModel = carsInDB.getString("model");
                int carYear = carsInDB.getInt("modelYear");
                int carPrice = carsInDB.getInt("price");
                Car carToAdd = new Car(carName, carModel, carYear, carPrice);
                cars.add(carToAdd);
            }
        } catch (SQLException e){
            System.out.println("Could not get all cars");
        }
        return cars;
    }

    /**
     * @param name Full label of car as YEAR MAKE MODEL
     * @return a Car object using the database
     */
    public Car getCarByName(String name){
        SQLCarDataAccess sqlcdaInit = new SQLCarDataAccess();
        ResultSet carInDB = sqlcdaInit.getCar(name);
        Car noCar = new Car("noCar", "noCar", 0, 0);
        try {
            carInDB.next();
            String carName = carInDB.getString("brand");
            String carModel = carInDB.getString("model");
            int carYear = carInDB.getInt("modelYear");
            int carPrice = carInDB.getInt("price");
            return new Car(carName, carModel, carYear, carPrice);
        } catch (SQLException e) {
            System.out.println("Could not get car by name");
        }
        return noCar;
    }
}
