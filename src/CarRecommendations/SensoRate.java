package CarRecommendations;

import Entities.AccountHolder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SensoRate {
    public static String getInterestRateJSON(AccountHolder user) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // Input parameters for the Senso API's rate endpoint
        // TODO: use actual JSON
        String inputjson = "{\n" +
                "  \"loanAmount\": "+ String.valueOf(user.getSavings()) +",\n" +
                "  \"creditScore\": "+ String.valueOf(user.getCreditScore()) +",\n" +
                "  \"pytBudget\": "+ String.valueOf(user.getMonthlyBudget()) +",\n" +
                "  \"vehicleMake\": \"Honda\",\n" +
                "  \"vehicleModel\": \"Civic\",\n" +
                "  \"vehicleYear\": 2021,\n" +
                "  \"vehicleKms\": 1000\n" +
                "}";

        // Creates a POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://auto-loan-api.senso.ai/rate"))
                .header("Content-Type", "application/json")
                .header("x-api-key", "AIzaSyCD_-qCdXqrvWGHN1tpe2PH6Rf8zpnTdXs")
                .POST(HttpRequest.BodyPublishers.ofString(inputjson))
                .build();

        // use the client to send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
