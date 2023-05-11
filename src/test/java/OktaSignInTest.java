import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OktaSignInTest {

    @Test
    public void testOktaSignIn() {
        // Initialize Okta API client
        oktaApiClient oktaApiClient = new OktaApiClient("localhost:3000", "0oa96xwubvWP38hOP5d7");

        // Sign in with valid credentials
        String username = "testuser@email.com";
        String password = "test1234!";
        signInResponse signInResponse = oktaApiClient.signIn(username, password);

        // Verify successful sign-in
        assertNotNull(signInResponse.getSessionToken());
        assertEquals(username, signInResponse.getUserProfile().getEmail());

        // Sign in with invalid credentials
        String invalidUsername = "invalid@example.com";
        String invalidPassword = "invalid_password";
        signInResponse invalidSignInResponse = oktaApiClient.signIn(invalidUsername, invalidPassword);

        // Verify failed sign-in
        assertNull(invalidSignInResponse.getSessionToken());
        assertNull(invalidSignInResponse.getUserProfile());
        assertNotNull(invalidSignInResponse.getErrorCode());
    }
}
