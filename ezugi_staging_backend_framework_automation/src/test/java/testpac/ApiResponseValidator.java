package testpac;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.testng.asserts.SoftAssert;

public class ApiResponseValidator {
    
    /**
     * Generic validation method to validate response parameters.
     */
    public void validateResponse(Map<String, Object> actualResponseData,
            Set<String> expectedMandatoryParams,
            Map<String, Object> expectedValues) {

        // Create an instance of SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Validate mandatory parameters
        expectedMandatoryParams.forEach(param -> 
            validateMandatoryParameter(param, actualResponseData, softAssert));

        // Validate specific expected values
        expectedValues.forEach((param, expectedValue) -> 
            validateExpectedValue(param, expectedValue, actualResponseData, softAssert));

        // Summary log for validation
        System.out.println("API response validation completed successfully.");

        // Assert all to report collected soft assertion errors
        softAssert.assertAll();
    }

    public void validateResponse(Map<String, Object> actualResponseData,
                                 Set<String> expectedMandatoryParams,
                                 Set<String> expectedOptionalParams,
                                 Map<String, Object> expectedValues) {

        // Create an instance of SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Validate mandatory parameters
        expectedMandatoryParams.forEach(param -> 
            validateMandatoryParameter(param, actualResponseData, softAssert));

        // Validate optional parameters if present
        expectedOptionalParams.forEach(param -> 
            validateOptionalParameter(param, actualResponseData, softAssert));

        // Validate specific expected values
        expectedValues.forEach((param, expectedValue) -> 
            validateExpectedValue(param, expectedValue, actualResponseData, softAssert));

        // Summary log for validation
        System.out.println("API response validation completed successfully.");

        // Assert all to report collected soft assertion errors
        softAssert.assertAll();
    }

    /**
     * Validate a mandatory parameter with Soft Assertion.
     */
    private void validateMandatoryParameter(String param, Map<String, Object> responseData, SoftAssert softAssert) {
        softAssert.assertTrue(responseData.containsKey(param), "Missing mandatory parameter: " + param);
        softAssert.assertNotNull(responseData.get(param), "Mandatory parameter " + param + " is null.");
    }

    /**
     * Validate an optional parameter if present with Soft Assertion.
     */
    private void validateOptionalParameter(String param, Map<String, Object> responseData, SoftAssert softAssert) {
        Optional.ofNullable(responseData.get(param)).ifPresent(value ->
            softAssert.assertNotNull(value, "Optional parameter " + param + " is null."));
    }

    /**
     * Validate a parameter against an expected value with Soft Assertion.
     */
    private void validateExpectedValue(String param, Object expectedValue, Map<String, Object> responseData, SoftAssert softAssert) {
        softAssert.assertTrue(responseData.containsKey(param), "Missing expected parameter: " + param);
        softAssert.assertEquals(responseData.get(param), expectedValue, "Mismatch in value for parameter: " + param);
    }

}
