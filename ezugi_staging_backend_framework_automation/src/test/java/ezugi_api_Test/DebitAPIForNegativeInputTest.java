package ezugi_api_Test;

import static io.restassured.RestAssured.given;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoutility.DebitPojo;

public class DebitAPIForNegativeInputTest extends BaseApiClass {
	
	String roundID = String.valueOf(javaLib.getRandomNum());

	/* data for invalid, null and empty String */

	@DataProvider(name = "testDataforString")
	public Object[][] dataProviderString() {
		return new Object[][] { { "" }, { null }, { javaLib.getUuid() } };
	}

	/* Data for invalid , 0 and negative integer */

	@DataProvider(name = "testDataforNumber")
	public Object[][] dataProviderNumber() {
		return new Object[][] { { 0 }, { -1 }, { javaLib.getRandomNum(2) } };
	}
	
	
	/* Verify debit API with invalid , empty String , null as token value */

	@Test(dataProvider = "testDataforString")
	public void debitAPIWithInvalid_EmptyString_nullTokenTest(String data)
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, javaLib.getUuid(), currentToken,
				playerId, betTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());
		dp.setToken(data);
		ll.getLowLevelLogInfo("TokenTest for : "+data);

		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, dp);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}
	
	/* Verify debit API with invalid , empty String , null as PlayerId value */
	
	@Test(dataProvider = "testDataforString")
	public void debitAPIWith_Invalid_EmptyString_NullPlayerId(String data)
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, javaLib.getUuid(), currentToken,
				playerId, betTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		dp.setUid(data);
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, dp);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}
	
	/* Verify debit API with invalid , 0  and negative as PlayerId value */
	
	@Test(dataProvider = "testDataforNumber")
	public void debitAPIWith_Invalid_EmptyString_NullGameId(int data)
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, javaLib.getUuid(), currentToken,
				playerId, betTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

		dp.setGameId(data);
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);
		ll.getLowLevelReportOfReq_Res_ResTime(resp, dp);
		resp.then().assertThat().statusCode(400).assertThat().contentType(ContentType.JSON);

	}


}
