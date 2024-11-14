package providerTest;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DebitApiResponseValidator {

    // Validate mandatory and optional response parameters
    public static void validateResponseParameters(List<String> expectedMandatoryParams, List<String> expectedOptionalParams, Map<String, Object> responseJson) {
        // Check for mandatory parameters
        for (String param : expectedMandatoryParams) {
            Assert.assertTrue(responseJson.containsKey(param), "Mandatory parameter missing: " + param);
        }

        // Optional parameters can be present or absent
        for (String param : expectedOptionalParams) {
            if (responseJson.containsKey(param)) {
                System.out.println("Optional parameter found: " + param);
            }
        }
    }

    // Individual parameter validation methods
    public static void operatorID(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("operatorId"), "operatorId is missing or null");
    }

    public static void uid(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("uid"), "uid is missing or null");
    }

    public static void nickName(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("nickName"), "nickName is missing or null");
    }

    public static void token(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("token"), "token is missing or null");
    }

    public static void playerTokenAtLaunch(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("playerTokenAtLaunch"), "playerTokenAtLaunch is missing or null");
    }

    public static void balance(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("balance"), "balance is missing or null");
    }

    public static void currency(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("currency"), "currency is missing or null");
    }

    public static void clientIP(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("clientIP"), "clientIP is missing or null");
    }

    public static void vip(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("VIP"), "VIP is missing or null");
    }

    public static void errorDescription(Map<String, Object> responseJson, List<String> expectedErrorDescription) {
        Assert.assertTrue(expectedErrorDescription.contains(responseJson.get("errorDescription")), "Error Description mismatch");
    }

    public static void errorCode(Map<String, Object> responseJson, int expectedErrorCode) {
        Assert.assertEquals(responseJson.get("errorCode"), expectedErrorCode, "Error Code mismatch");
    }

    public static void timestamp(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("timestamp"), "timestamp is missing or null");
    }

    public static void transactionId(Map<String, Object> responseJson) {
        Assert.assertNotNull(responseJson.get("transactionId"), "transactionId is missing or null");
    }

    public static void main(String[] args) {
        // Example response from API (Simulated for testing)
        Response response = getApiResponse(); // Assume this method fetches the API response
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Parse the JSON response
        JsonPath jsonPath = new JsonPath(responseBody);
        Map<String, Object> responseJson = jsonPath.getMap("$");

        // Log initial balance
        System.out.println("Initial Balance: " + responseJson.get("balance"));

        // Define expected parameters
        List<String> expectedMandatoryParams = Arrays.asList(
            "operatorId", "uid", "token", "balance", "currency", 
            "errorCode", "errorDescription", "transactionId", "timestamp", "roundId"
        );
        List<String> expectedOptionalParams = Arrays.asList(
            "VIP", "clientIP", "nickName", "bonusAmount"
        );

        // Validate response parameters
        validateResponseParameters(expectedMandatoryParams, expectedOptionalParams, responseJson);

        // Invoke specific validation methods
        operatorID(responseJson);
        uid(responseJson);
        nickName(responseJson);
        token(responseJson);
        playerTokenAtLaunch(responseJson);
        balance(responseJson);
        currency(responseJson);
        clientIP(responseJson);
        vip(responseJson);

        // Error Description and Code validation
        List<String> expectedErrorDescription = Arrays.asList("ok");
        errorDescription(responseJson, expectedErrorDescription);

        int expectedErrorCode = 0;
        errorCode(responseJson, expectedErrorCode);

        // Validate timestamp and transactionId
        timestamp(responseJson);
        transactionId(responseJson);

        // Save initial balance (if needed)
        double initialBalance = Double.parseDouble(responseJson.get("balance").toString());
        System.out.println("Initial balance saved: " + initialBalance);

        // Cleanup (Simulating deletion of variables)
        clearVariables();
    }

    // Method to fetch API response (stub)
    private static Response getApiResponse() {
        // Placeholder for actual RestAssured call
        return null; // Implement the actual call here
    }

    // Cleanup method for clearing variables
    private static void clearVariables() {
        System.out.println("Clearing unused variables...");
        // Simulate the unset of variables like in Postman
    }
}

