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

public class Repeated_Authentication_1 extends BaseApiClass {

	@Test
	public void getURLValidTest() throws Throwable {
		GetURLPojo gup = new GetURLPojo(playerId, platformId, "betvita", token, currencyCode,
				javaLib.getCurrentTimeStamp());

		RequestSpecification request = given().contentType(ContentType.JSON).body(gup);
		Response resp = request.when().post(baseUrl + EndPoints.getURL);

		ll.printRequestLogInReport(request);
		ll.printResponseLogInReport(resp);

		String launchToken = (String) jsonLib.getValueJsonFromBody(resp, "launchToken");
		eu.setDataIntoExcel("ezugi", 10, 2, launchToken);

	}

	@Test(dependsOnMethods = "getURLValidTest", invocationCount = 2)
	public void authenticationValidTest() throws Throwable {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);

		RequestSpecification request = given().contentType(ContentType.JSON).body(ap).header("hash", hash);

		Response resp = request.when().post(baseUrl + EndPoints.authentication);

		ll.printRequestLogInReport(request);
		ll.printResponseLogInReport(resp);
	}

}
