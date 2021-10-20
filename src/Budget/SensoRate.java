import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SensoRate {
    public static void main(String[] args) throws IOException, InterruptedException {
        executePost("https://auto-loan-api.senso.ai/rate");
    }

    public static void executePost(String targetURL) throws IOException, InterruptedException {
        // create a client
        HttpClient client = HttpClient.newHttpClient();

        String inputjson = "{\n" +
                "  \"loanAmount\": 10000,\n" +
                "  \"creditScore\": 780,\n" +
                "  \"pytBudget\": 800,\n" +
                "  \"vehicleMake\": \"Honda\",\n" +
                "  \"vehicleModel\": \"Civic\",\n" +
                "  \"vehicleYear\": 2021,\n" +
                "  \"vehicleKms\": 1000\n" +
                "}";

        // create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://auto-loan-api.senso.ai/rate"))
                .header("Content-Type", "application/json")
                .header("x-api-key", "AIzaSyCD_-qCdXqrvWGHN1tpe2PH6Rf8zpnTdXs")
                .POST(HttpRequest.BodyPublishers.ofString(inputjson))
                .build();

        // use the client to send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // the response:
        System.out.println(response.body());
    }
}