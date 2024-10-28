package providerTestCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoutility.DebitPojo;

public class Debit_2 extends BaseApiClass{
	
	String roundID = String.valueOf(javaLib.getRandomNum());

	@Test(priority = 1)
	public void debitValidTest() throws Throwable {

		String debitTransactionId = javaLib.getUuid();
		eu.setDataIntoExcel("ezugi", 17, 2, debitTransactionId);

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, currentToken,
				playerId, betTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		System.out.println(map.writeValueAsString(dp));

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, dp);
		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);

	}

}
