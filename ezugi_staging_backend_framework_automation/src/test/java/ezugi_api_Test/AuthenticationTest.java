package ezugi_api_Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import commonobjectutility.UtilityClassObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojoutility.AuthenticationPojo;

import static io.restassured.RestAssured.given;

public class AuthenticationTest extends BaseApiClass {
	
	String headerHash ="hash";
	
	/* Verify Authentication API with all valid parameters */

	@Test()
	public void authenticationValidTest() throws Throwable {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);
		rLib.performPostWithHeader(baseUrl, EndPoints.authentication, ap, headerHash, hash);
		String token = (String) jsonLib.getValueJsonFromBody(UtilityClassObject.getResponse(), "token");
		ll.getLowLevelLogInfo(token);
		authToken=token;
	}

	/* Verify Authentication API with invalid launch token */

	@Test(dataProvider = "testDataforString")
	public void authenticationInvalidLaunchTokenTest(String data) throws Throwable {

		AuthenticationPojo ap = new AuthenticationPojo(platformId, operatorId, playerTokenAtLaunch,
				javaLib.getCurrentTimeStamp());
		ap.setToken(data);

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(ap), secretKey);
		rLib.performPostWithHeader(baseUrl, EndPoints.authentication, ap, headerHash, hash);
		UtilityClassObject.getResponse().then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

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

		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	/* Verify Authentication API with missing parameters */

//	@Test
//	public void authenticationMissingLaunchTokenTest() throws Throwable {
//
//		HashMap<String, Object> requestBody = new HashMap<>();
//
//		requestBody.put("platformId", platformId);
//		requestBody.put("operatorId", operatorId);
//		requestBody.put("token", playerTokenAtLaunch);
//		requestBody.put("timestamp", javaLib.getCurrentTimeStamp());
//
//		String hash = javaLib.getgenerateHMACSHA256(String.valueOf(requestBody), secretKey);
//
//		Response resp = given().contentType(ContentType.JSON).body(requestBody).header("hash", hash).when()
//				.post(baseUrl + EndPoints.authentication);
//
//		ll.getLowLevelLogInfo("Request body" + "\n" + String.valueOf(requestBody));
//		ll.getLowLevelLogInfo("Response body" + resp.prettyPrint());
//		ll.getLowLevelLogInfo("responseTime:  " + resp.getTime());
//		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
//
//	}

	/* verify authentication API with missing parameters */

}
