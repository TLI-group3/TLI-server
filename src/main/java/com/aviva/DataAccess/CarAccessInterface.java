package com.aviva.DataAccess;

import java.sql.ResultSet;

public interface CarAccessInterface {

    static ResultSet getCar(String vin) {
        return null;
    };

    static ResultSet getAllCars() {
        return null;
    };

    static void insertRecommendedCar(String accountNumber, String carID) {}
}
