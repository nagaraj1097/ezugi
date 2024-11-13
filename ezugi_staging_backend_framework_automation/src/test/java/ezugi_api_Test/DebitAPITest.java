package ezugi_api_Test;

import static io.restassured.RestAssured.given;

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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoutility.DebitPojo;

public class DebitAPITest extends BaseApiClass {
	String roundID = String.valueOf(javaLib.getRandomNum());

	/* data for invalid, null and empty String */

	@DataProvider(name = "testDataforString")
	public Object[][] dataProviderString() {
		return new Object[][] { { "" }, { null }, { javaLib.getUuid() } };
	}

	/* Data for invalid , 0 and negative integer */

	@DataProvider(name = "testDataforNumber")
	public Object[][] dataProviderNumber() {
		return new Object[][] { { 0 }, { -1 }, {javaLib.getRandomNum(2)} };
	}

	/* Verify debit API with all valid parameters */

	@Test(priority = 1)
	public void debitValidTest() throws Throwable {

		String debitTransactionId = javaLib.getUuid();
		eu.setDataIntoExcel("ezugi", 17, 2, debitTransactionId);

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, authToken,
				playerId, debitBetTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());
		

		System.out.println(map.writeValueAsString(dp));
		
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);

		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

	}

	/* Verify debit API by passing same roundId and unique transactionId */

	@Test(priority = 2)
	public void debitAPIwith_sameRoundId_UniqueTransactionID()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, javaLib.getUuid(), authToken,
				playerId, debitBetTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);

		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

	}


	/* verify debit API with invalid , empty String , null as operator value */

	@Test(dataProvider = "testDataforString", priority = 5)
	public void debitAPIWith_Invalid_EmptyString_NullOperatorId(String data)
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, javaLib.getUuid(), authToken,
				playerId, debitBetTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		dp.setUid(data);
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);

		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}
	@Test
	public void debitAPIMissingParameterTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		HashMap<String, Object> requestBody = new HashMap<>();

		requestBody.put("gameId", gameId);
		requestBody.put("debitAmount", debitAmount);
		requestBody.put("platformId", platformId);
		requestBody.put("serverId", serverId);
		requestBody.put("transactionId", javaLib.getUuid());
		requestBody.put("token", authToken);
		requestBody.put("uid", playerId);
		requestBody.put("betTypeID", 1);
		requestBody.put("tableId", 1);
		requestBody.put("seatId", seatId);
		requestBody.put("currency", currency);
		requestBody.put("operatorId", operatorId);
		requestBody.put("roundId", javaLib.getUuid());
		requestBody.put("timestamp", javaLib.getCurrentTimeStamp());

		List<String> keysList = new ArrayList<>(requestBody.keySet());

		for (String key : keysList) {

			HashMap<String, Object> request = new HashMap<String, Object>(requestBody);
			request.remove(key);

			String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(request), secretKey);

			Response resp = given().contentType(ContentType.JSON).body(request).header("hash", hash).when()
					.post(baseUrl + EndPoints.debit);
			ll.getLowLevelLogInfo("Request body" + "\n" + request);
			ll.getLowLevelLogInfo("Response body" + resp.prettyPrint());
			ll.getLowLevelLogInfo("responseTime:  " + resp.getTime());
			resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

		}

	}

}
