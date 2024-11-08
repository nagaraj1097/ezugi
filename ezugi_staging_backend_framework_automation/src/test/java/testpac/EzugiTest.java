package testpac;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoutility.CreditPojo;
import pojoutility.DebitPojo;

public class EzugiTest extends BaseApiClass {

	@Test
	public void creditValidTest() throws Throwable {

		for (int i = 1; i <= 5; i++) {

		ll.getLowLevelLogInfo("Iteration count : "+String.valueOf(i));

		String roundID = String.valueOf(javaLib.getRandomNum());
		String debitTransactionId = javaLib.getUuid();

		/* Debit API */

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, currentToken,
				playerId, debitBetTypeId, tableId, seatId, currency, operatorId, roundID,
				javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		RequestSpecification req = given().contentType(ContentType.JSON).body(dp).header("hash", hash);

		Response resp = req.when().post(baseUrl + EndPoints.debit);

		ll.printRequestLogInReport(req);
		ll.printResponseLogInReport(resp);

//			resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
		System.out.println("Debit response Time" + resp.getTime());

		/* Credit API */

		CreditPojo cp = new CreditPojo(gameId, debitTransactionId, isEndRound, creditIndex, gameDataString, platformId,
				serverId, javaLib.getUuid(), currentToken, playerId, returnReason, creditBetTypeId, tableId, seatId,
				currency, creditAmount, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hashc = javaLib.getgenerateHMACSHA256(map.writeValueAsString(cp), secretKey);

		RequestSpecification req2 = given().contentType(ContentType.JSON).body(cp).header("hash", hashc);

		Response resp2 = req2.when().post(baseUrl + EndPoints.credit);

		ll.printRequestLogInReport(req2);
		ll.printResponseLogInReport(resp2);

//			respC.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);s
		System.out.println("Credit response Time" + resp2.getTime());
		Thread.sleep(3000);

		}

	}

}
