package ezugi_api_Test;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoutility.AuthenticationPojo;

public class AuthenticationTest extends BaseApiClass {
	/* Verify Authentication API with all valid parameters */

	@Test(priority = 0)
	public void authenticationValidTest() throws Throwable {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, "a82b52bd-4229-4d28-8af7-4d87f6089f42",
				javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(ap).header("hash", hash).when()
				.post(baseUrl + EndPoints.authentication);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, ap);
		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

		String token = (String) jsonLib.getValueJsonFromBody(resp, "token");
		eu.setDataIntoExcel("ezugi", 11, 2, token);

	}

	/* Verify Authentication API with invalid launch token */

	@Test
	public void authenticationInvalidLaunchTokenTest() throws Throwable {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		ap.setToken(javaLib.getUuid());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(ap).header("hash", hash).when()
				.post(baseUrl + EndPoints.authentication);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, ap);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	/* Verify Authentication API with empty launch token */

	@Test
	public void authenticationEmptyLaunchTokenTest() throws Throwable {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		ap.setToken("");

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(ap).header("hash", hash).when()
				.post(baseUrl + EndPoints.authentication);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, ap);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	/* Verify Authentication API with null launch token */

	@Test
	public void authenticationNullLaunchTokenTest() throws Throwable {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		ap.setToken(null);

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(ap).header("hash", hash).when()
				.post(baseUrl + EndPoints.authentication);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, ap);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	/* Verify Authentication API with missing parameters */

	@Test
	public void authenticationMissingLaunchTokenTest() throws Throwable {

		HashMap<String, Object> requestBody = new HashMap<>();

		requestBody.put("platformId", platformId);
		requestBody.put("operatorId", operatorId);
		requestBody.put("token", playerTokenAtLaunch);
		requestBody.put("timestamp", javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(String.valueOf(requestBody), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(requestBody).header("hash", hash).when()
				.post(baseUrl + EndPoints.authentication);
		
		ll.getLowLevelLogInfo("Request body" + "\n" + String.valueOf(requestBody));
		ll.getLowLevelLogInfo("Response body" + resp.prettyPrint());
		ll.getLowLevelLogInfo("responseTime:  " + resp.getTime());
		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

	}

	/* verify authentication API with missing parameters */



}
