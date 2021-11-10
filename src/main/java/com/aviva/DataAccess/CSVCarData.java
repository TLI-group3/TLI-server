package com.aviva.DataAccess;

import com.aviva.Entities.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVCarData implements CarDataInterface {

    @Override
    public ArrayList<Car> loadCarData(String file) throws IOException, ClassNotFoundException, InterruptedException {
        return parseCSV(file);
    }

    private ArrayList<Car> parseCSV(String file) throws IOException {
        // TODO Polish the body of this method.
        ArrayList<Car> cars = new ArrayList<Car>();
        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        String row = csvReader.readLine();

        while ((row = csvReader.readLine()) != null) {
            String[] carData = row.split(",");
            cars.add(new Car(carData[0], carData[1], Integer.parseInt(carData[2]), Float.parseFloat(carData[3])));
        }
        csvReader.close();
        return cars;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        CSVCarData testCarData = new CSVCarData();
        ArrayList<Car> cars = testCarData.loadCarData("../../../../../../data/Car_Data.csv");
        for (Car car : cars) {
            System.out.println(car.getMake() +" "+ car.getModel());
        }
    }
}
