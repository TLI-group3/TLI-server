package CarRecommendations;

import DataAccess.AccountHolderDataInterface;
import DataAccess.CSVAccountHolderData;
import DataAccess.CSVCarData;
import DataAccess.CarDataInterface;
import Entities.AccountHolder;
import Entities.Car;

import java.io.IOException;
import java.util.ArrayList;

public class CarRecommender {
    public static ArrayList<Car> getRecommendedCars(AccountHolder user) throws IOException, InterruptedException, ClassNotFoundException {
        float approvedLoanAmount = SensoRate.getApprovedLoanAmount(user);
        CarDataInterface carData = new CSVCarData();
        ArrayList<Car> availableCars = carData.loadCarData("data/Car_Data.csv");

        ArrayList<Car> recommendedCars = new ArrayList<Car>();
        for (Car currentCar : availableCars) {
            if (currentCar.getPrice() <= approvedLoanAmount) {
                recommendedCars.add(currentCar);
            }
        }

        return recommendedCars;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        AccountHolderDataInterface accountData = new CSVAccountHolderData();
        AccountHolder user = accountData.getClientByID("5002357538983918");
        ArrayList<Car> cars = CarRecommender.getRecommendedCars(user);
        for (Car car : cars) {
            System.out.println(car.getMake() +" "+ car.getModel());
        }
    }
}
