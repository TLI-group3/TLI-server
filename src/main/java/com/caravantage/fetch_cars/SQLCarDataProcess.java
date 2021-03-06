package com.caravantage.fetch_cars;

import com.caravantage.data_access.CarAccessInterface;
import com.caravantage.entities.Car;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public class that handles processing data from the database and converting it into proper types/entities for cars
 */

public class SQLCarDataProcess extends CarDataProcessor {

    public SQLCarDataProcess(CarAccessInterface carAccess) {
        super(carAccess);
    }

    /**
     * Iterates through our table of cars and returns all of them
     * @return a list of car objects from our database sorted by ascending price
     */
    @Override
    public ArrayList<Car> getAllCars(){
        // Query all cars
        ResultSet carsInDB = carAccess.getAllCars();

        ArrayList<Car> cars = new ArrayList<>();

        // Convert the query data into a Car entity and add it to a list
        try {
            while (carsInDB.next()) {
                cars.add(helperCreateCarObject(carsInDB));
            }
        }
        catch (SQLException e){
            System.out.println("Could not get all cars");
        }
        return cars;
    }

    /**
     * @param vin vin number of the car
     * @return a Car object using the database
     */
    @Override
    public Car getCarByVin(String vin){
        // Query a car by its vin number
        ResultSet carInDB = carAccess.getCarByVin(vin);

        Car noCar = new Car("noCar", "noCar", 0, 0);

        // Convert the queried car into a Car entity
        try {
            carInDB.next();
            return helperCreateCarObject(carInDB);
        }
        catch (SQLException e) {
            System.out.println("Could not get car by name");
        }
        return noCar;
    }

    /**
     * Returns a Car object given a ResultSet from the database
     * @return a Car object with respective data
     */
    public Car helperCreateCarObject(ResultSet carsInDB) throws SQLException {
        String carName = carsInDB.getString("brand");
        String carModel = carsInDB.getString("model");
        int carYear = carsInDB.getInt("modelYear");
        int carPrice = carsInDB.getInt("price");
        String carImage = carsInDB.getString("image");
        String vin = carsInDB.getString("vin");
        Car carToAdd = new Car(carName, carModel, carYear, carPrice);
        carToAdd.setImage(carImage);
        carToAdd.setVin(vin);
        return carToAdd;
    }
}
