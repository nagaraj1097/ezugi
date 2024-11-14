package providerTest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoutility.DebitPojo;

public class DebitTest extends BaseApiClass{
	
	@Test
	public void debitValidTest() throws Throwable {
		
		/* Fetching Initial balance */
		Double balance = Double.valueOf(getBalance());
		ll.getLowLevelLogInfo("Initial Balance: "+balance);
		
		/* Setting debitTransactionId and  roundID */

		String debitTransactionId = javaLib.getUuid();
		String roundID = javaLib.getUuid();
		
		/* Debit Request */

		DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, authToken,
				playerId, debitBetTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());
		

		System.out.println(map.writeValueAsString(dp));
		
		String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

		Response resp = given().contentType(ContentType.JSON).body(dp).header("hash", hash).when()
				.post(baseUrl + EndPoints.debit);

		resp.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
		
		
		
	}

}
