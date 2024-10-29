package providerTestCases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoutility.DebitPojo;

public class Insufficient_Funds_11_Test extends BaseApiClass {

	String roundID = String.valueOf(javaLib.getRandomNum());
	String debitTransactionId = String.valueOf(javaLib.getUuid());

	@Test()
	public void Insufficient_Funds_11_DebitValidTest() throws Throwable {

//		HashMap<String, Object> hashMap = new HashMap<>();
//
//		hashMap.put("uid", "1000041");
//		hashMap.put("platformId", platformId);
//		hashMap.put("operatorId", operatorId);
//		hashMap.put("timestamp", javaLib.getCurrentTimeStamp());
//
//		Response resbal = given().body(hashMap).when().post(baseUrl + EndPoints.balance);
//
//		double balance = (double) jsonLib.getValueJsonFromBody(resbal, "balance");

		double insuffbalance = 10000000.12;

		DebitPojo dp = new DebitPojo(gameId, insuffbalance, platformId, serverId, debitTransactionId, currentToken,
				playerId, 1, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		RequestSpecification header = given().contentType(ContentType.JSON).body(dp).header("hash", hash);

		Response resp = header.when().post(baseUrl + EndPoints.debit);

		ll.printRequestLogInReport(header);
		ll.printResponseLogInReport(resp);

	}

}
