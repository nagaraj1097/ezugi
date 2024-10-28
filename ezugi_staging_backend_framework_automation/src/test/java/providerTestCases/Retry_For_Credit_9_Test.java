package providerTestCases;

import static io.restassured.RestAssured.given;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoutility.CreditPojo;
import pojoutility.DebitPojo;

public class Retry_For_Credit_9_Test extends BaseApiClass {

	String roundID = String.valueOf(javaLib.getRandomNum());
	String debitTransactionId = String.valueOf(javaLib.getUuid());

	@Test(priority = 1)
	public void Retry_For_Credit_9_DebitValidTest() throws Throwable {

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, currentToken,
				playerId, 1, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		RequestSpecification header = given().contentType(ContentType.JSON).body(dp).header("hash", hash);

		Response resp = header.when().post(baseUrl + EndPoints.debit);

		ll.printRequestLogInReport(header);
		ll.printResponseLogInReport(resp);
	}

	@Test(invocationCount = 2)
	public void Retry_For_Credit_9_CreditValidTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		CreditPojo cp = new CreditPojo(gameId, debitTransactionId, isEndRound, creditIndex, gameDataString, platformId,
				serverId, javaLib.getUuid(), currentToken, playerId, returnReason, creditBetTypeId, tableId, seatId,
				currency, creditAmount, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hashc = javaLib.getgenerateHMACSHA256(map.writeValueAsString(cp), secretKey);

		RequestSpecification header = given().contentType(ContentType.JSON).body(cp).header("hash", hashc);

		Response resp = header.when().post(baseUrl + EndPoints.credit);
		ll.printRequestLogInReport(header);
		ll.printResponseLogInReport(resp);
	}

}
