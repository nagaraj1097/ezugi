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
import pojoutility.RollBackPojo;

public class Rollback_Before_Debit_6_Test extends BaseApiClass{
	
	String roundID = String.valueOf(javaLib.getRandomNum());
	String debitTransactionId = String.valueOf(javaLib.getUuid());

	@Test(dependsOnMethods = "Retry_For_Rollback_5_DebitValidTest")
	public void Rollback_Before_Debit_6_RollbackTest()
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		RollBackPojo rp = new RollBackPojo(operatorId, playerId, debitTransactionId, gameId, authToken,
				rollbackAmount, debitBetTypeId, serverId, roundID, currency, seatId, platformId, tableId,
				javaLib.getCurrentTimeStamp());

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(rp), secretKey);
		RequestSpecification header = given().contentType(ContentType.JSON).body(rp).header("hash", hash);
		Response resp = header.when().post(baseUrl + EndPoints.rollback);

		ll.printRequestLogInReport(header);
		ll.printResponseLogInReport(resp);

	}

}
