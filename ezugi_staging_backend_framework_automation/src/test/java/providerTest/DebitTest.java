package providerTest;

import api_endpoints.EndPoints;
import baseapi.BaseApiClass;
import commonobjectutility.UtilityClassObject;
import org.testng.annotations.Test;
import pojoutility.DebitPojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class DebitTest extends BaseApiClass {

    @Test
    public void debitValidTest() throws Throwable {

        /* Fetching Initial balance */
        String balance = getBalance();
        ll.getLowLevelLogInfo("Initial Balance: " + balance);
        BigDecimal initialBalance = new BigDecimal(balance);

        /* Setting debitTransactionId and  roundID */
        String debitTransactionId = javaLib.getUuid();
        String roundID = String.valueOf(javaLib.getRandomNum(8));

        /* Debit Request */
        DebitPojo dp = new DebitPojo(gameId, debitAmount, platformId, serverId, debitTransactionId, authToken, playerId, debitBetTypeId, tableId, seatId, currency, operatorId, roundID, javaLib.getCurrentTimeStamp());

        /* hash generation */
        String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(dp), secretKey);

        /* posting a request */
        rLib.performPostWithHeader(baseUrl, EndPoints.debit, dp, "hash", hash);

        /* post validations */
        double debitBalance = Double.parseDouble((String) jsonLib.getValueJsonFromBody(UtilityClassObject.getResponse(), "balance"));
        BigDecimal expectedBalance = initialBalance.subtract(BigDecimal.valueOf(debitAmount));

        System.out.println(expectedBalance);

        /* Convert response to a Map for validation*/
        Map<String, Object> actualResponseData = UtilityClassObject.getResponse().jsonPath().getMap("$");

        /* Define expected mandatory parameters */
        Set<String> expectedMandatoryParams = Set.of("operatorId", "roundId", "uid", "token", "balance", "transactionId", "currency", "bonusAmount", "errorCode", "errorDescription", "timestamp");

        /* Define expected values for specific fields */
        Map<String, Object> expectedValues = Map.of("errorCode", 0, "errorDescription", "OK", "balance", expectedBalance);

        arv.validateResponse(actualResponseData, expectedMandatoryParams, expectedValues);
    }

}
