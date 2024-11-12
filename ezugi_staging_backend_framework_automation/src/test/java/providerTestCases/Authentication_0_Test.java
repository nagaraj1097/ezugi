package providerTestCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoutility.AuthenticationPojo;
import pojoutility.GetURLPojo;

public class Authentication_0_Test extends BaseApiClass {

	@Test
	public void auth0_GetURLValidTest() throws Throwable {
		GetURLPojo gup = new GetURLPojo(playerId, platformId, "betvita", playerToken, currencyCode,
				javaLib.getCurrentTimeStamp());

		RequestSpecification request = given().contentType(ContentType.JSON).body(gup);
		Response resp = request.when().post(baseUrl + EndPoints.getURL);

		ll.printRequestLogInReport(request);
		ll.printResponseLogInReport(resp);

		String launchToken = (String) jsonLib.getValueJsonFromBody(resp, "launchToken");
		eu.setDataIntoExcel("ezugi", 10, 2, launchToken);
		
		

	}

	@Test(dependsOnMethods = "auth0_GetURLValidTest")
	public void auth0_AuthenticationValidTest() throws Throwable {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);

		RequestSpecification request = given().contentType(ContentType.JSON).body(ap).header("hash", hash);

		Response resp = request.when().post(baseUrl + EndPoints.authentication);

		ll.printRequestLogInReport(request);
		ll.printResponseLogInReport(resp);

		String token = (String) jsonLib.getValueJsonFromBody(resp, "token");
		eu.setDataIntoExcel("ezugi", 11, 2, token);
		
		jsonLib.getVerifyDataOnJsonPath(resp, ".operatorId","operatorId", "10909001");
	}

}
