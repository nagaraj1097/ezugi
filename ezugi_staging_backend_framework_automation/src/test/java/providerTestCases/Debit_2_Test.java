package providerTestCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoutility.DebitPojo;

public class Debit_2_Test extends BaseApiClass {

	String roundID = String.valueOf(javaLib.getRandomNum());

	@Test(priority = 1)
	public void debitValidTest() throws Throwable {

		String debitTransactionId = javaLib.getUuid();
		eu.setDataIntoExcel("ezugi", 17, 2, debitTransactionId);

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, currentToken,
				playerId, betTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());


		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		RequestSpecification header = given().contentType(ContentType.JSON).body(dp).header("hash", hash);

		Response resp = header.when().post(baseUrl + EndPoints.debit);

		ll.printRequestLogInReport(header);
		ll.printResponseLogInReport(resp);

	}

}