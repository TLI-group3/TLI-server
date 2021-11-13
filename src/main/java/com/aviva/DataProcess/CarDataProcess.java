package com.aviva.DataProcess;

import com.aviva.DataAccess.SQLCarDataAccess;
import com.aviva.Entities.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDataProcess implements CarDataProcessingInterface{
    public static ArrayList<Car> getAllCars(){
        /**
         * Iterates through our table of cars and returns all of them
         * @return a list of car objects from our database sorted by ascending price
         */
        ResultSet carsInDB = SQLCarDataAccess.getAllCars();
        ArrayList<Car> cars = new ArrayList<Car>();
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
            System.out.println(e);
        }
        return cars;
    }

    public static Car getCarByName(String name){
        /**
         * @param name Full label of car as YEAR MAKE MODEL
         * @return a Car object using the database
         */
        ResultSet carInDB = SQLCarDataAccess.getCar(name);
        Car noCar = new Car("noCar", "noCar", 0, 0);
        try {
            carInDB.next();
            String carName = carInDB.getString("brand");
            String carModel = carInDB.getString("model");
            int carYear = carInDB.getInt("modelYear");
            int carPrice = carInDB.getInt("price");
            Car carToReturn = new Car(carName, carModel, carYear, carPrice);
            return carToReturn;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return noCar;
    }

    public static void main(String args[]){
        System.out.println(getAllCars());
        System.out.println(getCarByName("2015 honda door"));
    }
}
