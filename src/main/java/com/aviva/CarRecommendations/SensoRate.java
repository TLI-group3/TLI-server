package com.aviva.CarRecommendations;

import com.aviva.Entities.AccountHolder;

import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SensoRate {
    public static float getApprovedLoanAmount(AccountHolder user) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // Input parameters for the Senso API's rate endpoint
        String inputjson = "{\n" +
                "  \"loanAmount\": "+ user.getSavings() +",\n" +
                "  \"creditScore\": "+ user.getCreditScore() +",\n" +
                "  \"pytBudget\": "+ user.getMonthlyBudget() +",\n" +
                "  \"vehicleMake\": \"Honda\",\n" +
                "  \"vehicleModel\": \"Civic\",\n" +
                "  \"vehicleYear\": 2021,\n" +
                "  \"vehicleKms\": 1000\n" +
                "}";

        // Creates a POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("SENSO_API_URL")))
                .header("Content-Type", "application/json")
                .header("x-api-key", System.getenv("SENSO_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(inputjson))
                .build();

        // use the client to send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //parse the returned JSON file
        return getLoanAmountFromJSON(response.body());
    }

    private static float getLoanAmountFromJSON(String response) {
        JSONObject obj = new JSONObject(response);
        return obj.getFloat("amount");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AccountHolder user;
        AccountHolderDataInterface accountData = new CSVAccountHolderData();
        user = accountData.getClientByID("5002357538983918");
        System.out.println(getApprovedLoanAmount(user));
    }
}
