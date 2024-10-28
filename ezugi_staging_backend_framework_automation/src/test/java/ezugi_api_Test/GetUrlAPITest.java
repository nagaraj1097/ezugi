package ezugi_api_Test;

import static io.restassured.RestAssured.given;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoutility.GetURLPojo;

public class GetUrlAPITest extends BaseApiClass {

	String operatorId = "betvita";

	/* verify getURL APi with all valid parameters */

	@Test(priority = 1)
	public void getURLValidTest() throws Throwable {
		GetURLPojo gup = new GetURLPojo(playerId, platformId, operatorId, token, currencyCode,
				javaLib.getCurrentTimeStamp());

		RequestSpecification request = given().contentType(ContentType.JSON).body(gup);
		Response resp = request.when().post(baseUrl + EndPoints.getURL);

		ll.printRequestLogInReport(request);
		ll.printResponseLogInReport(resp);

		resp.then().log().all().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

		String launchToken = (String) jsonLib.getValueJsonFromBody(resp, "launchToken");
		eu.setDataIntoExcel("ezugi", 10, 2, launchToken);

	}

	/* verify getURL APi with empty String as token */
	@Test
	public void getURLEmptyStringTokenTest() throws Throwable {

		GetURLPojo gup = new GetURLPojo(playerId, platformId, operatorId, token, currencyCode,
				javaLib.getCurrentTimeStamp());
		/* setting token empty as empty String */
		gup.setToken("");

		Response resp = given().contentType(ContentType.JSON).body(gup).when().post(baseUrl + EndPoints.getURL);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, gup);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	/* verify getURL APi with null as token */

	@Test
	public void getURLNullTokenTest() throws Throwable {

		GetURLPojo gup = new GetURLPojo(playerId, platformId, operatorId, token, currencyCode,
				javaLib.getCurrentTimeStamp());
		/* setting token empty as null */
		gup.setToken(null);

		Response resp = given().contentType(ContentType.JSON).body(gup).when().post(baseUrl + EndPoints.getURL);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, gup);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	/* verify getURL API with IvalidPlayerID */

	@Test
	public void getURLInvalidPlayerIdTest() throws Throwable {

		GetURLPojo gup = new GetURLPojo(playerId, platformId, operatorId, token, currencyCode,
				javaLib.getCurrentTimeStamp());

		/* setPlayerId as empty String */
		gup.setPlayerId(String.valueOf(javaLib.getRandomNum()));

		Response resp = given().contentType(ContentType.JSON).body(gup).when().post(baseUrl + EndPoints.getURL);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, gup);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	/* verify getURL API with null PlayerID */

	@Test
	public void getURLNullPlayerIdTest() throws Throwable {

		GetURLPojo gup = new GetURLPojo(playerId, platformId, operatorId, token, currencyCode,
				javaLib.getCurrentTimeStamp());

		/* setPlayerId as empty String */
		gup.setPlayerId(null);

		Response resp = given().contentType(ContentType.JSON).body(gup).when().post(baseUrl + EndPoints.getURL);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, gup);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	/* verify getURL API with Invalid operatorId */

	@Test
	public void getURLNullOperatorIdTest() throws Throwable {

		GetURLPojo gup = new GetURLPojo(playerId, platformId, operatorId, token, currencyCode,
				javaLib.getCurrentTimeStamp());

		/* setPlayerId operatorId to 0 */
		gup.setOperatorId(null);

		Response resp = given().contentType(ContentType.JSON).body(gup).when().post(baseUrl + EndPoints.getURL);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, gup);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}

	@Test
	public void getURLAPIMissingParametersTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, InterruptedException {

		HashMap<String, Object> requestBody = new HashMap<>();

		requestBody.put("playerId", playerId);
		requestBody.put("operatorId", operatorId);
		requestBody.put("token", token);
		requestBody.put("currencyCode", currencyCode);
		requestBody.put("timestamp", javaLib.getCurrentTimeStamp());

		List<String> keysList = new ArrayList<>(requestBody.keySet());

		for (String key : keysList) {

			HashMap<String, Object> request = new HashMap<String, Object>(requestBody);
			request.remove(key);

			ll.getLowLevelLogInfo("missing parameter:	" + key);

			String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(request), secretKey);

			Response resp = given().contentType(ContentType.JSON).body(request).header("hash", hash).when()
					.post(baseUrl + EndPoints.getURL);
			ll.getLowLevelLogInfo("Request body" + "\n" + request);
			ll.getLowLevelLogInfo("Response body" + resp.prettyPrint());
			ll.getLowLevelLogInfo("responseTime:  " + resp.getTime());
			resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

		}

	}

}
