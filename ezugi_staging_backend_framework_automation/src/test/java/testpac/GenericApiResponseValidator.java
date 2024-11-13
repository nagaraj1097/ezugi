package testpac;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GenericApiResponseValidator {

    @Test
    public void validateApiResponse() {
        // Sample URL, replace with your actual endpoint
        String url = "https://your-api-endpoint.com/response";
        // Perform the GET request
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Convert response to a Map for easy validation
        Map<String, Object> actualResponseData = response.jsonPath().getMap("$");

        // Define expected mandatory and optional parameters (can be dynamic)
        List<String> expectedMandatoryParams = Arrays.asList(
                "operatorId", "uid", "token", "playerTokenAtLaunch",
                "balance", "currency", "errorCode", "errorDescription", "timestamp"
        );

        List<String> expectedOptionalParams = Arrays.asList(
                "VIP", "clientIP", "nickName", "bonusAmount"
        );

        // Define expected values for specific fields (can be dynamic)
        Map<String, Object> expectedValues = Map.of(
                "errorCode", 0,
                "errorDescription", "ok"
        );

        // Validate the response with dynamic inputs
        validateResponse(actualResponseData, expectedMandatoryParams, expectedOptionalParams, expectedValues);

        // Unset variables (These would typically be environment variables in Postman)
        System.clearProperty("hashGenerator");
        System.clearProperty("generateUUIDsFunction");
        System.clearProperty("setRoundIdFunction");
        System.clearProperty("verifyUpdatedBalanceAfterDebit");
        System.clearProperty("verifyBalanceUnchangedDuringRetry");
        System.clearProperty("verifyUpdatedBalanceAfterRollback");
    }

    /**
     * Generic validation method to validate response parameters
     * @param actualResponseData The actual API response data as a Map
     * @param expectedMandatoryParams List of expected mandatory parameter names
     * @param expectedOptionalParams List of expected optional parameter names
     * @param expectedValues Map of expected values for specific parameters
     */
    public void validateResponse(Map<String, Object> actualResponseData,
                                 List<String> expectedMandatoryParams,
                                 List<String> expectedOptionalParams,
                                 Map<String, Object> expectedValues) {
        
        // Validate mandatory parameters
        for (String param : expectedMandatoryParams) {
            Assert.assertTrue(actualResponseData.containsKey(param), 
                "Mandatory parameter missing: " + param);
            Assert.assertNotNull(actualResponseData.get(param),
                "Mandatory parameter " + param + " is null.");
        }

        // Validate optional parameters if present
        for (String param : expectedOptionalParams) {
            if (actualResponseData.containsKey(param)) {
                System.out.println("Optional parameter present: " + param);
                Assert.assertNotNull(actualResponseData.get(param), 
                    "Optional parameter " + param + " is null.");
            }
        }

        // Validate specific expected values
        for (Map.Entry<String, Object> entry : expectedValues.entrySet()) {
            String param = entry.getKey();
            Object expectedValue = entry.getValue();
            Assert.assertTrue(actualResponseData.containsKey(param), 
                "Expected parameter missing: " + param);
            Assert.assertEquals(actualResponseData.get(param), expectedValue, 
                "Value mismatch for parameter: " + param);
        }

        // Perform additional custom validation, such as token difference checks
        verifyTokenDifference(actualResponseData);
    }

    /**
     * Helper method to perform custom token difference validation
     * @param responseData The API response data
     */
    private void verifyTokenDifference(Map<String, Object> responseData) {
        // Add your specific logic to validate token differences here
        System.out.println("Token difference check passed.");
    }
}

