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
import pojoutility.CreditPojo;
import pojoutility.DebitPojo;

public class CreditAPITest extends BaseApiClass {
	String roundID = String.valueOf(javaLib.getRandomNum());
	String debitTransactionId = javaLib.getUuid();

	@Test
	public void creditValidTest() throws Throwable {

		/* Debit API */

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, currentToken,
				playerId, debitBetTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());
	

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, dp);
		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

		/* Credit API */

		CreditPojo cp = new CreditPojo(gameId, debitTransactionId, isEndRound, creditIndex, gameDataString, platformId,
				serverId, javaLib.getUuid(), currentToken, playerId, returnReason, creditBetTypeId, tableId, seatId, currency,
				creditAmount, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hashc = javaLib.getgenerateHMACSHA256(map.writeValueAsString(cp), secretKey);

		Response respC = given().contentType(ContentType.JSON).body(cp).header("hash", hashc).when()
				.post(baseUrl + EndPoints.credit);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, cp);
		respC.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

	}

	public void doubleCreditAPIWithSame_RId_TIdTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		CreditPojo cp = new CreditPojo(gameId, debitTransactionId, isEndRound, creditIndex, gameDataString, platformId,
				serverId, javaLib.getUuid(), currentToken, playerId, returnReason, creditBetTypeId, tableId, seatId, currency,
				creditAmount, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hashc = javaLib.getgenerateHMACSHA256(map.writeValueAsString(cp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(cp).header("hash", hashc).when()
				.post(baseUrl + EndPoints.credit);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, cp);
		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

	}

	@Test
	public void creditAPIMissingParameterTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, InterruptedException {

		HashMap<String, Object> requestBody = new HashMap<>();

		requestBody.put("gameId", gameId);
		requestBody.put("debitTransactionId", debitTransactionId);
		requestBody.put("isEndRound", isEndRound);
		requestBody.put("creditIndex", creditIndex);
		requestBody.put("gameDataString", gameDataString);
		requestBody.put("platformId", platformId);
		requestBody.put("serverId", serverId);
		requestBody.put("transactionId", javaLib.getUuid());
		requestBody.put("token", currentToken);
		requestBody.put("uid", playerId);
		requestBody.put("returnReason", returnReason);
		requestBody.put("betTypeID", creditBetTypeId);
		requestBody.put("tableId", tableId);
		requestBody.put("seatId", seatId);
		requestBody.put("currency", currency);
		requestBody.put("creditAmount", creditAmount);
		requestBody.put("operatorId", operatorId);
		requestBody.put("roundId", javaLib.getUuid());
		requestBody.put("timestamp", javaLib.getCurrentTimeStamp());

		List<String> keysList = new ArrayList<>(requestBody.keySet());

		for (String key : keysList) {

			HashMap<String, Object> request = new HashMap<String, Object>(requestBody);
			request.remove(key);

			Thread.sleep(2000);

			ll.getLowLevelLogInfo("missing parameter:	" + key);

			String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(request), secretKey);

			Response resp = given().contentType(ContentType.JSON).body(request).header("hash", hash).when()
					.post(baseUrl + EndPoints.credit);
			ll.getLowLevelLogInfo("Request body" + "\n" + request);
			ll.getLowLevelLogInfo("Response body" + resp.prettyPrint());
			ll.getLowLevelLogInfo("responseTime:  " + resp.getTime());
			resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

		}

	}

}
