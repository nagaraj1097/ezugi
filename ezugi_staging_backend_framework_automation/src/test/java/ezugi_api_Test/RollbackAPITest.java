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
import pojoutility.RollBackPojo;

public class RollbackAPITest extends BaseApiClass {

	/* verify rollback API with valid parameters */
	@Test
	public void rollbackValidTest() throws JsonProcessingException, InvalidKeyException, NoSuchAlgorithmException {

		String transactionId = javaLib.getUuid();
		String roundId = String.valueOf(javaLib.getRandomNum());

		RollBackPojo rp = new RollBackPojo(operatorId, playerId, transactionId, gameId, currentToken, rollbackAmount,
				debitBetTypeId, serverId, roundId, currency, seatId, platformId, tableId, javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(rp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(rp).header("hash", hash).when()
				.post(baseUrl + EndPoints.rollback);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, rp);
		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

	}

	@Test
	public void rollbackAPIMissingParametersTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, InterruptedException {

		String transactionId = javaLib.getUuid();
		String roundId = String.valueOf(javaLib.getRandomNum());

		HashMap<String, Object> requestBody = new HashMap<>();

		requestBody.put("operatorId", operatorId);
		requestBody.put("uid", playerId);
		requestBody.put("transactionId", transactionId);
		requestBody.put("gameId", gameId);
		requestBody.put("token", currentToken);
		requestBody.put("rollbackAmount", rollbackAmount);
		requestBody.put("betTypeID", debitBetTypeId);
		requestBody.put("serverId", serverId);
		requestBody.put("roundId", roundId);
		requestBody.put("currency", currency);
		requestBody.put("seatId", seatId);
		requestBody.put("platformId", playerId);
		requestBody.put("tableId", tableId);
		requestBody.put("timestamp", javaLib.getCurrentTimeStamp());

		List<String> keysList = new ArrayList<>(requestBody.keySet());

		for (String key : keysList) {

			HashMap<String, Object> request = new HashMap<String, Object>(requestBody);
			request.remove(key);

			ll.getLowLevelLogInfo("missing parameter:	" + key);

			String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(request), secretKey);

			Response resp = given().contentType(ContentType.JSON).body(request).header("hash", hash).when()
					.post(baseUrl + EndPoints.rollback);
			ll.getLowLevelLogInfo("Request body" + "\n" + request);
			ll.getLowLevelLogInfo("Response body" + resp.prettyPrint());
			ll.getLowLevelLogInfo("responseTime:  " + resp.getTime());
			resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

		}

	}

}
