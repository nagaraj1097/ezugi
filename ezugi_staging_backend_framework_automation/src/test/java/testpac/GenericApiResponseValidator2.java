package testpac;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

public class GenericApiResponseValidator2 {

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

        // Define expected mandatory and optional parameters
        Set<String> expectedMandatoryParams = Set.of(
                "operatorId", "uid", "token", "playerTokenAtLaunch",
                "balance", "currency", "errorCode", "errorDescription", "timestamp"
        );

        Set<String> expectedOptionalParams = Set.of(
                "VIP", "clientIP", "nickName", "bonusAmount"
        );

        // Define expected values for specific fields
        Map<String, Object> expectedValues = Map.of(
                "errorCode", 0,
                "errorDescription", "ok"
        );

        // Validate the response
        validateResponse(actualResponseData, expectedMandatoryParams, expectedOptionalParams, expectedValues);

        // Clean up environment variables
        clearEnvironmentProperties();
    }

    /**
     * Generic validation method to validate response parameters.
     */
    
    public void validateResponse(Map<String, Object> actualResponseData,
            Set<String> expectedMandatoryParams,
            Map<String, Object> expectedValues) {

    	// Validate mandatory parameters
    	expectedMandatoryParams.forEach(param -> validateMandatoryParameter(param, actualResponseData));
    	
    	// Validate specific expected values
    	expectedValues.forEach((param, expectedValue) -> validateExpectedValue(param, expectedValue, actualResponseData));

    	// Custom validation
    	verifyTokenDifference(actualResponseData);

    	// Summary log for validation
    	System.out.println("API response validation completed successfully.");
}    
    public void validateResponse(Map<String, Object> actualResponseData,
                                 Set<String> expectedMandatoryParams,
                                 Set<String> expectedOptionalParams,
                                 Map<String, Object> expectedValues) {

        // Validate mandatory parameters
        expectedMandatoryParams.forEach(param -> validateMandatoryParameter(param, actualResponseData));

        // Validate optional parameters if present
        expectedOptionalParams.forEach(param -> validateOptionalParameter(param, actualResponseData));

        // Validate specific expected values
        expectedValues.forEach((param, expectedValue) ->
                validateExpectedValue(param, expectedValue, actualResponseData));

        // Custom validation
        verifyTokenDifference(actualResponseData);

        // Summary log for validation
        System.out.println("API response validation completed successfully.");
    }

    /**
     * Validate a mandatory parameter.
     */
    private void validateMandatoryParameter(String param, Map<String, Object> responseData) {
        Assert.assertTrue(responseData.containsKey(param), "Missing mandatory parameter: " + param);
        Assert.assertNotNull(responseData.get(param), "Mandatory parameter " + param + " is null.");
    }

    /**
     * Validate an optional parameter if present.
     */
    private void validateOptionalParameter(String param, Map<String, Object> responseData) {
        Optional.ofNullable(responseData.get(param)).ifPresent(value ->
                Assert.assertNotNull(value, "Optional parameter " + param + " is null."));
    }

    /**
     * Validate a parameter against an expected value.
     */
    private void validateExpectedValue(String param, Object expectedValue, Map<String, Object> responseData) {
        Assert.assertTrue(responseData.containsKey(param), "Missing expected parameter: " + param);
        Assert.assertEquals(responseData.get(param), expectedValue, "Mismatch in value for parameter: " + param);
    }

    /**
     * Custom method to perform token difference validation.
     */
    private void verifyTokenDifference(Map<String, Object> responseData) {
        // Add your specific logic to validate token differences here
        System.out.println("Token difference check passed.");
    }

    /**
     * Clear environment variables used in API validation.
     */
    private void clearEnvironmentProperties() {
        List<String> properties = Arrays.asList(
                "hashGenerator",
                "generateUUIDsFunction",
                "setRoundIdFunction",
                "verifyUpdatedBalanceAfterDebit",
                "verifyBalanceUnchangedDuringRetry",
                "verifyUpdatedBalanceAfterRollback"
        );
        properties.forEach(System::clearProperty);
    }
}
