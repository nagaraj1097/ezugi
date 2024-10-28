package providerTestCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoutility.DebitPojo;

public class Retry_For_Debit_3_Test extends BaseApiClass {

	static RequestSpecification header;
	static Response resp;

	String roundID = String.valueOf(javaLib.getRandomNum());

	@Test(invocationCount = 2)
	public void retry_For_Debit_3_debitValidTest() throws Throwable {

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, currentToken,
				playerId, 1, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);
		header = given().contentType(ContentType.JSON).body(dp).header("hash", hash);

		resp = header.when().post(baseUrl + EndPoints.debit);

		ll.printRequestLogInReport(header);
		ll.printResponseLogInReport(resp);

	}

}
