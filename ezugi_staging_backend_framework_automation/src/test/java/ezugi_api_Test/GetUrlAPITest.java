package ezugi_api_Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import commonobjectutility.UtilityClassObject;
import io.restassured.http.ContentType;
import pojoutility.GetURLPojo;

public class GetUrlAPITest extends BaseApiClass {

	String getURLOperatorId = "betvita";

	/* verify getURL APi with all valid parameters */

	@Test(priority = 1)
	public void getURLValidTest() throws Throwable {
		GetURLPojo gup = new GetURLPojo(playerId, platformId, getURLOperatorId, playerToken, currencyCode,
				javaLib.getCurrentTimeStamp());
		rLib.performPost(baseUrl, EndPoints.getURL, gup);
		UtilityClassObject.getResponse().then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
		String launchToken = String.valueOf(jsonLib.getValueJsonFromBody(UtilityClassObject.getResponse(), "launchToken"));
		operatorId = String.valueOf(jsonLib.getValueJsonFromBody(UtilityClassObject.getResponse(), "operatorId")) ;
		ll.getLowLevelLogInfo("launch Token:  " + launchToken);
		ll.getLowLevelLogInfo("operatorId:  " + operatorId);
		eu.setDataIntoExcel("ez", 2, 2, operatorId);
		eu.setDataIntoExcel("ez", 6, 0, launchToken);
	}

	/* verify getURL APi with invalid token */
	@Test(dataProvider = "testDataforString")
	public void getURLInvalidTokenTest(String data) throws Throwable {

		GetURLPojo gup = new GetURLPojo(playerId, platformId, getURLOperatorId, playerToken, currencyCode,
				javaLib.getCurrentTimeStamp());
		/* passing data to setter method */
		gup.setToken(data);
		ll.getLowLevelLogInfo("Testing the token value using: " + data);
		rLib.performPost(baseUrl, EndPoints.getURL, gup);
		UtilityClassObject.getResponse().then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
	}

	/* verify getURL API with IvalidPlayerID */

	@Test(dataProvider = "testDataforString")
	public void getURLInvalidPlayerIdTest(String data) throws Throwable {

		GetURLPojo gup = new GetURLPojo(playerId, platformId, getURLOperatorId, playerToken, currencyCode,
				javaLib.getCurrentTimeStamp());

		ll.getLowLevelLogInfo("Testing the playerId value using :" + data);
		gup.setPlayerId(data);
		rLib.performPost(baseUrl, EndPoints.getURL, gup);
		UtilityClassObject.getResponse().then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
	}

	/* verify getURL API with Invalid operatorId */

	@Test(dataProvider = "testDataforString")
	public void getURLInvalidOperatorIdTest(String data) throws Throwable {

		GetURLPojo gup = new GetURLPojo(playerId, platformId, getURLOperatorId, playerToken, currencyCode,
				javaLib.getCurrentTimeStamp());
		gup.setOperatorId(data);
		ll.getLowLevelLogInfo("Testing the token value using: " + data);
		rLib.performPost(baseUrl, EndPoints.getURL, gup);
		UtilityClassObject.getResponse().then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
	}

	@Test
	public void getURLAPIMissingParametersTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, InterruptedException {

		HashMap<String, Object> requestBody = new HashMap<>();

		requestBody.put("playerId", playerId);
		requestBody.put("operatorId", getURLOperatorId);
		requestBody.put("token", playerToken);
		requestBody.put("currencyCode", currencyCode);
		requestBody.put("timestamp", javaLib.getCurrentTimeStamp());

		List<String> keysList = new ArrayList<>(requestBody.keySet());

		for (String key : keysList) {

			HashMap<String, Object> request = new HashMap<String, Object>(requestBody);
			request.remove(key);

			ll.getLowLevelLogInfo("missing parameter:	" + key);

//			String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(request), secretKey);
			
			rLib.performPost(baseUrl, EndPoints.getURL, requestBody);
			UtilityClassObject.getResponse().then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
		}

	}

}
