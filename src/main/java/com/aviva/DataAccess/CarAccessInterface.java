package com.aviva.DataAccess;

import java.sql.ResultSet;

public interface CarAccessInterface {

    public ResultSet getCar(String carName);

    public ResultSet getAllCars();

    public void insertRecommendedCar(String accountNumber, String carName);
}
