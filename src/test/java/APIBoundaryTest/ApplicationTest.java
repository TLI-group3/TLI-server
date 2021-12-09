package APIBoundaryTest;

import com.caravantage.APIController.Application;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

/**
 * Testing if our own spring boot api is starting up and running as intended, both locally and on the EC2
 */

public class ApplicationTest {

    @Test
    public void testApiSetup() throws IOException, InterruptedException {;
        String[] args = new String[1];
        args[0] = "SpringApplication.run(Application.class, args)";
        Application.main(args);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseJSON = response.body();
        assertEquals("Greetings!", responseJSON);
    }
}
