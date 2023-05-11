import org.junit.jupiter.api.Test;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

public class UrlTest {

    @Test
    public void testUrlIsWorking() {
        String url = "http:localhost:3000"; // Replace with the URL to test
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            assertEquals(200, responseCode); // Verify that the response code is 200 OK
        } catch (Exception e) {
            fail("The URL is not working: " + e.getMessage()); // Fail the test if there is an exception
        }
    }
}
