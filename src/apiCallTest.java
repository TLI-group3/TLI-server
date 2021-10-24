import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
 
class Main
{
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://reqres.in/api/users?page=1");
        URLConnection connection = url.openConnection();
 
        // `HttpURLConnection` connection = (HttpURLConnection) url.openConnection();
        // connection.setRequestMethod("GET");
 
        try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())))
        {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}