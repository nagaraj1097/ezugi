package testpac;

import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import pojoutility.CreditPojo;
import pojoutility.DebitPojo;

public class EzugiTest2 extends BaseApiClass {

	@Test
	public void creditValidTest() throws Throwable {

		for (int i = 1; i <= 5; i++) {

			ll.getLowLevelLogInfo("Iteration count : " + String.valueOf(i));

			String roundID = String.valueOf(javaLib.getRandomNum());
			String debitTransactionId = javaLib.getUuid();
			/* Debit API */

			DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, authToken,
					playerId, debitBetTypeId, tableId, seatId, currency, operatorId, roundID,
					javaLib.getCurrentTimeStamp());

			String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

			rLib.performPostWithHeader(baseUrl, EndPoints.debit, dp, "hash", hash);

			/* Credit API */

			CreditPojo cp = new CreditPojo(gameId, debitTransactionId, isEndRound, creditIndex, gameDataString,
					platformId, serverId, javaLib.getUuid(), authToken, playerId, returnReason, creditBetTypeId,
					tableId, seatId, currency, creditAmount, operatorId, roundID, javaLib.getCurrentTimeStamp());

			String hashc = javaLib.getgenerateHMACSHA256(map.writeValueAsString(cp), secretKey);

			rLib.performPostWithHeader(baseUrl, EndPoints.credit, cp, "hash", hashc);

			Thread.sleep(3000);

		}

	}

}
