package providerTest;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import commonobjectutility.UtilityClassObject;
import org.testng.annotations.Test;
import pojoutility.AuthenticationPojo;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;

public class RetryAuthenticationTest extends BaseApiClass {

	@Test(dependsOnMethods = "ezugi_api_Test.GetUrlAPITest.getURLValidTest")
	public void retryAuthTest() throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {
		
		for(int i=1; i<=2; i++) {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);
		rLib.performPostWithHeader(baseUrl, EndPoints.authentication, ap, "hash", hash);
		String token = (String) jsonLib.getValueJsonFromBody(UtilityClassObject.getResponse(), "token");
		ll.getLowLevelLogInfo("Authentication token:	" + token);
		}
		
		Map<String, Object> actualResponseData = UtilityClassObject.getResponse().jsonPath().getMap("$");
		Set<String> expectedMandatoryParams = Set.of("operatorId","errorCode", "errorDescription", "timestamp" );
		Map<String, Object> expectedValues = Map.of("operatorId", "10909001","errorCode" , 6, "errorDescription","Token not found");
		arv.validateResponse(actualResponseData, expectedMandatoryParams, expectedValues);
	}
}
