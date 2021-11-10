package com.aviva.DataAccess;

import com.aviva.Entities.Car;

import java.io.IOException;
import java.util.ArrayList;

public interface CarDataInterface {
    public ArrayList<Car> loadCarData(String file) throws IOException, ClassNotFoundException, InterruptedException;
}
