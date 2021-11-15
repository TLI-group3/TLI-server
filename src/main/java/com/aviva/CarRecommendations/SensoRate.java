package com.aviva.CarRecommendations;

import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Public class that handles the making a call to the Senso API
 */

public class SensoRate {

    /**
     * Returns the interest rate for a particular AccountHolder and Car
     *
     * @param user the AccountHolder for which to calculate the rate
     * @param car the Car for which to calculate the rate
     * @return the interest rate as a float
     */
    public float getInterestRate(AccountHolder user, Car car) {
        HttpClient client = HttpClient.newHttpClient();

        // Initialize variables required by the Senso API
        float carPrice = car.getPrice();
        float downPayment = (float) (0.1 * carPrice);
        float vehicleKMS = 15000.0F; // Assuming average kilometres driven is 15k
        String carMake = car.getMake();
        String carModel = car.getModel();
        int carYear = car.getYear();
        float loanAmount = (float) (carPrice - downPayment + (0.2 * carPrice)); // Adding 20% of car value as upselling

        // Input parameters for the Senso API's rate endpoint
        String inputJson = String.format("{\"loanAmount\": %f, \"creditScore\": %d, \"pytBudget\": %d," +
                " \"vehicleMake\": \"%s\", \"vehicleModel\": \"%s\", \"vehicleYear\": %d, \"vehicleKms\": %f," +
                " \"listPrice\": %f, \"downpayment\": %f}", loanAmount, user.getCreditScore(), (int) user.getMonthlyBudget(),
                carMake, carModel, carYear, vehicleKMS, carPrice, downPayment);

         // Creates a POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("SENSO_API_URL") + "/rate"))
                .header("Content-Type", "application/json")
                .header("x-api-key", System.getenv("SENSO_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();

        try {
            // Use the client to send the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject responseJSON = new JSONObject(response.body());
            return responseJSON.getFloat("interestRate");
        }
        catch (InterruptedException | IOException e) {
            System.out.println("Could not HTTP Request");
        }

        return (float) -1.0;
    }

    /**
     * Returns the installments for a particular AccountHolder and Car
     *
     * @param user the AccountHolder for which to calculate the rate
     * @param car the Car for which to calculate the rate
     * @return the installment plan for a given Car and Accountholder as a JSONArray
     */
    public JSONArray getInstallments(AccountHolder user, Car car) {
        HttpClient client = HttpClient.newHttpClient();
        float carPrice = car.getPrice();
        float downPayment = (float) (0.1 * carPrice);
        float vehicleKMS = 15000.0F; // Assuming average kilometres driven is 15k
        String carMake = car.getMake();
        String carModel = car.getModel();
        int carYear = car.getYear();
        float loanAmount = (float) (carPrice - downPayment + (0.2 * carPrice)); // Adding 20% of car value as upselling

        // Input parameters for the Senso API's rate endpoint
        String inputJson = String.format("{\"loanAmount\": %f, \"creditScore\": %d, \"pytBudget\": %d," +
                        " \"vehicleMake\": \"%s\", \"vehicleModel\": \"%s\", \"vehicleYear\": %d, \"vehicleKms\": %f," +
                        " \"listPrice\": %f, \"downpayment\": %f}", loanAmount, user.getCreditScore(), (int) user.getMonthlyBudget(),
                carMake, carModel, carYear, vehicleKMS, carPrice, downPayment);

        // Creates a POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("SENSO_API_URL") + "/rate"))
                .header("Content-Type", "application/json")
                .header("x-api-key", System.getenv("SENSO_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();

        try {
            // use the client to send the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject responseJSON = new JSONObject(response.body());
            return responseJSON.getJSONArray("installments");
        } catch (InterruptedException | IOException e) {
            System.out.println("Could not HTTP Request");
            return new JSONArray();
        }
    }
    
}
