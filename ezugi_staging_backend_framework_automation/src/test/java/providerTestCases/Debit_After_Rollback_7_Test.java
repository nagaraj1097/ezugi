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
import pojoutility.DebitPojo;
import pojoutility.RollBackPojo;

public class Debit_After_Rollback_7_Test extends BaseApiClass {

	String roundID = String.valueOf(javaLib.getRandomNum());
	String debitTransactionId = String.valueOf(javaLib.getUuid());

	@Test()
	public void Debit_After_Rollback_7_RollbackTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		RollBackPojo rp = new RollBackPojo(operatorId, playerId, debitTransactionId, gameId, currentToken,
				rollbackAmount, debitBetTypeId, serverId, roundID, currency, seatId, platformId, tableId,
				javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(rp), secretKey);
		RequestSpecification header = given().contentType(ContentType.JSON).body(rp).header("hash", hash);
		Response resp = header.when().post(baseUrl + EndPoints.rollback);

		ll.printRequestLogInReport(header);
		ll.printResponseLogInReport(resp);

	}

	@Test(dependsOnMethods = "Debit_After_Rollback_7_RollbackTest")
	public void Debit_After_Rollback_7_DebitValidTest() throws Throwable {

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, currentToken,
				playerId, 1, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		RequestSpecification header = given().contentType(ContentType.JSON).body(dp).header("hash", hash);

		Response resp = header.when().post(baseUrl + EndPoints.debit);

		ll.printRequestLogInReport(header);
		ll.printResponseLogInReport(resp);

	}

}
