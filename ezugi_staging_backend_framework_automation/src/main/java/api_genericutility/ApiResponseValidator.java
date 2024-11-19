package api_genericutility;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.testng.asserts.SoftAssert;

public class ApiResponseValidator {

	// Object for LowLevelLogs to log in HTML report

	LowLevelLogs ll = new LowLevelLogs();

	/**
	 * Generic validation method to validate response parameters with logging in
	 * Extent report.
	 */
	public void validateResponse(Map<String, Object> actualResponseData, Set<String> expectedMandatoryParams,
			Map<String, Object> expectedValues) {

		// Create an instance of SoftAssert
		SoftAssert softAssert = new SoftAssert();

		// Validate mandatory parameters
		expectedMandatoryParams.forEach(param -> {
			validateMandatoryParameter(param, actualResponseData, softAssert);
			if (actualResponseData.containsKey(param) && actualResponseData.get(param) != null) {
				ll.getLowLevelLogPass("Mandatory parameter '" + param + "' is present.");
			} else {
				ll.getLowLevelLogFail("Mandatory parameter '" + param + "' is missing or null.");
			}
		});

		// Validate specific expected values
		expectedValues.forEach((param, expectedValue) -> {
			validateExpectedValue(param, expectedValue, actualResponseData, softAssert);
			if (actualResponseData.containsKey(param) && actualResponseData.get(param).equals(expectedValue)) {
				ll.getLowLevelLogPass("Expected value for parameter '" + param + "' is correct.");
			} else {
				ll.getLowLevelLogFail("Expected value for parameter '" + param + "' is incorrect.");
			}
		});

		// Summary log for validation
		System.out.println("API response validation completed successfully.");

		// Assert all to report collected soft assertion errors
		softAssert.assertAll();
	}

	public void validateResponse(Map<String, Object> actualResponseData, Set<String> expectedMandatoryParams,
			Set<String> expectedOptionalParams, Map<String, Object> expectedValues) {

		// Create an instance of SoftAssert
		SoftAssert softAssert = new SoftAssert();

		// Validate mandatory parameters
		expectedMandatoryParams.forEach(param -> {
			validateMandatoryParameter(param, actualResponseData, softAssert);
			if (actualResponseData.containsKey(param) && actualResponseData.get(param) != null) {
				ll.getLowLevelLogPass("Mandatory parameter '" + param + "' is present.");
			} else {
				ll.getLowLevelLogFail("Mandatory parameter '" + param + "' is missing or null.");
			}
		});

		// Validate optional parameters if present
		expectedOptionalParams.forEach(param -> {
			validateOptionalParameter(param, actualResponseData, softAssert);
			if (actualResponseData.containsKey(param) && actualResponseData.get(param) != null) {
				ll.getLowLevelLogPass("Optional parameter '" + param + "' is present.");
			} else {
				ll.getLowLevelLogInfo("Optional parameter '" + param + "' is missing.");
			}
		});

		// Validate specific expected values
		expectedValues.forEach((param, expectedValue) -> {
			validateExpectedValue(param, expectedValue, actualResponseData, softAssert);
			if (actualResponseData.containsKey(param) && actualResponseData.get(param).equals(expectedValue)) {
				ll.getLowLevelLogPass("Expected value for parameter '" + param + "' is correct.");
			} else {
				ll.getLowLevelLogFail("Expected value for parameter '" + param + "' is incorrect.");
			}
		});

		// Custom validation
//		verifyTokenDifference(actualResponseData, String.valueOf(expectedValues.get("token")));

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
		Optional.ofNullable(responseData.get(param))
				.ifPresent(value -> softAssert.assertNotNull(value, "Optional parameter " + param + " is null."));
	}

	/**
	 * Validate a parameter against an expected value with Soft Assertion.
	 */
	private void validateExpectedValue(String param, Object expectedValue, Map<String, Object> responseData,
			SoftAssert softAssert) {
		softAssert.assertTrue(responseData.containsKey(param), "Missing expected parameter: " + param);
		softAssert.assertEquals(responseData.get(param), expectedValue, "Mismatch in value for parameter: " + param);
	}

	/*
	 * Custom method to perform token difference validation. Verifies if the request
	 * token matches the response token.
	 */


	/*
	private void verifyTokenDifference(Map<String, Object> responseData, String requestToken) {
		// Extract the token from the response data
		String responseToken = (String) responseData.get("token"); // Change the key if needed

		// Check if both tokens are present and compare them
		if (requestToken != null && responseToken != null) {
			if (requestToken.equals(responseToken)) {
				// Log success message
				ll.getLowLevelLogPass("Token validation passed: Request token matches Response token.");
			} else {
				// Log failure message
				ll.getLowLevelLogFail("Token validation failed: Request token does not match Response token.");
				ll.getLowLevelLogFail("Request Token: " + requestToken);
				ll.getLowLevelLogFail("Response Token: " + responseToken);
			}
		} else {
			// Log information if tokens are missing
			if (requestToken == null) {
				ll.getLowLevelLogFail("Request token is missing.");
			}
			if (responseToken == null) {
				ll.getLowLevelLogFail("Response token is missing.");
			}
		}
		System.out.println("Token difference check completed.");
	}

	 */

}
