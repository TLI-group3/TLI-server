import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class apiCallTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        executePost();
    }

    public static void executePost() throws IOException, InterruptedException {
        // create a client
        HttpClient client = HttpClient.newHttpClient();

 
        // create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://18.222.134.223/demo"))
                .header("Content-Type", "application/json")
                .GET(HttpRequest.BodyPublishers.ofString())
                .build();

        // use the client to send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // the response:
        System.out.println(response.body());
    }
}
