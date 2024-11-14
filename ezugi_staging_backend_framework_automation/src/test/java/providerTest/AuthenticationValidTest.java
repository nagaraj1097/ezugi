package providerTest;

import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import api_genericutility.ApiResponseValidator;
import baseapi.BaseApiClass;
import commonobjectutility.UtilityClassObject;
import pojoutility.AuthenticationPojo;

public class AuthenticationValidTest extends BaseApiClass {

	String headerHash = "hash";

	/* Verify Authentication API with all valid parameters */

	@Test(dependsOnMethods = "ezugi_api_Test.GetUrlAPITest.getURLValidTest")
	public void authenticationValidTest() throws Throwable {
		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);
		rLib.performPostWithHeader(baseUrl, EndPoints.authentication, ap, headerHash, hash);
		String token = (String) jsonLib.getValueJsonFromBody(UtilityClassObject.getResponse(), "token");
		ll.getLowLevelLogInfo("Authentication token:	"+token);
		eu.setDataIntoExcel("ez", 6, 1, token);
		

		// Convert response to a Map for easy validation
		Map<String, Object> actualResponseData = UtilityClassObject.getResponse().jsonPath().getMap("$");

		// Define expected mandatory and optional parameters
		Set<String> expectedMandatoryParams = Set.of("operatorId", "uid", "nickName", "token", "playerTokenAtLaunch",
				"balance", "currency", "errorCode", "errorDescription", "timestamp");

		// Define expected values for specific fields
		Map<String, Object> expectedValues = Map.of("errorCode", 0, "errorDescription", "ok" );

		arv.validateResponse(actualResponseData, expectedMandatoryParams, expectedValues);

	}

}
