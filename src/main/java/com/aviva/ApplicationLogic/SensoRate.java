package com.aviva.ApplicationLogic;

import com.aviva.Entities.AccountHolder;
import com.aviva.Entities.Car;
import com.aviva.Constants.RecommendationConstants;
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
    public JSONObject getLoan(AccountHolder user, Car car) {
        // Set up HTTP Client and POST Request to Senso API
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = sensoAPICallHelper(user, car);

        try {
            // Use the client to send the request
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (InterruptedException | IOException e) {
            System.out.println("Could not make API call");
            return null;
        }
    }

    /**
     * Returns the HTTP Request to be made to the Senso API Rate method for a given Car and AccountHolder
     *
     * @param user the AccountHolder for which to calculate the rate
     * @param car the Car for which to calculate the rate
     * @return HTTP POST Request to the Senso API Rate method
     */
    private HttpRequest sensoAPICallHelper (AccountHolder user, Car car){
        // Initialize variables required by the Senso API
        float carPrice = car.getPrice();
        float downPayment = (float) ((RecommendationConstants.DOWN_PAYMENT_RATIO * carPrice) + user.getExistingCarValue());
        float vehicleKMS = RecommendationConstants.VEHICLE_KMS;
        String carMake = car.getMake();
        String carModel = car.getModel();
        int carYear = car.getYear();
        float loanAmount = (float) (carPrice - downPayment + (RecommendationConstants.UPSELL_RATIO * carPrice));

        // Input parameters for the Senso API's rate endpoint
        String inputJson = String.format("{\"loanAmount\": %f, \"creditScore\": %d, \"pytBudget\": %d," +
                        " \"vehicleMake\": \"%s\", \"vehicleModel\": \"%s\", \"vehicleYear\": %d, \"vehicleKms\": %f," +
                        " \"listPrice\": %f, \"downpayment\": %f}", loanAmount, user.getCreditScore(), (int) user.getMonthlyBudget(),
                carMake, carModel, carYear, vehicleKMS, carPrice, downPayment);

        // Return the POST Request
        return HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("SENSO_API_URL") + "/rate"))
                .header("Content-Type", "application/json")
                .header("x-api-key", System.getenv("SENSO_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
    }
}
