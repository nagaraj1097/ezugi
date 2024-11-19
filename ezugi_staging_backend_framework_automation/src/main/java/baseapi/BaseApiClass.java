package baseapi;

import api_endpoints.EndPoints;
import api_genericutility.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonobjectutility.UtilityClassObject;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import pojoutility.BalancePojo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Listeners(api_listnerimplementation.ListImpClass.class)
public class BaseApiClass {

    public ObjectMapper map = new ObjectMapper();

    public ExcelUtility eu = new ExcelUtility();
//    public FileUtility fLib = new FileUtility();
    public JsonUtility jsonLib = new JsonUtility();
    public LowLevelLogs ll = new LowLevelLogs();
    public JavaUtility javaLib = new JavaUtility();
    public RestUtility rLib = new RestUtility();
    public ApiResponseValidator arv = new ApiResponseValidator();

    public final static String baseUrl = "https://dev-beapi-games-rgs.sportsit-tech.net/rgs/api/ezugi/v2";
    public String secretKey = "5dbb6aed-a244-4e63-b5d9-95e5f118a545";

    public String operatorId;
    public String playerToken;


    public int platformId;
    public int serverId;
    public int gameId;
    public int tableId;
    public double debitAmount;
    public double rollbackAmount;
    public double creditAmount;
    public String debitTransactionId;
    public String seatId;
    public int debitBetTypeId;
    public int creditBetTypeId;
    public int returnReason;
    public boolean isEndRound;
    public String gameDataString;
    public String creditIndex;
    public String authToken;
    public String uid;
    public String playerTokenAtLaunch;
    public String currency;
    public String playerId;
    public String currencyCode;

    @BeforeMethod
    public void variables() throws EncryptedDocumentException, IOException {
        /* Initializing data */

        /* getURL */

        playerId = eu.getDataFromExcel("ez", 2, 0);
        platformId = Integer.parseInt(eu.getDataFromExcel("ez", 2, 1));
        operatorId = eu.getDataFromExcel("ez", 2, 2);
        playerToken = eu.getDataFromExcel("ez", 2, 3);
        currencyCode = eu.getDataFromExcel("ez", 2, 4);

        /* AuthenticationTest */
        playerTokenAtLaunch = eu.getDataFromExcel("ez", 6, 0);

        /* Debit API */

        gameId = Integer.parseInt(eu.getDataFromExcel("ez", 10, 0));
//		debitAmount = Double.valueOf(eu.getDataFromExcel("ezugi", 16, 2));
        debitAmount = 10.11;
        authToken = eu.getDataFromExcel("ez", 6, 1);
        serverId = Integer.parseInt(eu.getDataFromExcel("ez", 10, 2));
        seatId = eu.getDataFromExcel("ez", 10, 7);
        currency = eu.getDataFromExcel("ez", 2, 4);
        debitBetTypeId = 1;

        /* credit API */

        debitTransactionId = eu.getDataFromExcel("ezugi", 17, 2);
        isEndRound = false;
        creditIndex = "1|1";
        gameDataString = "";
        returnReason = 0;
        creditBetTypeId = 101;
        tableId = 1;
        creditAmount = 11.11;

        /* RollBack API */

        rollbackAmount = 100.11;
    }


    /* data for invalid, null and empty String */

    @DataProvider(name = "testDataforString")
    public Object[][] dataProviderString() {
        return new Object[][]{{""}, {null}, {javaLib.getUuid()}};
    }


    public String getBalance() throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        BalancePojo bp = new BalancePojo(playerId, platformId, operatorId, javaLib.getCurrentTimeStamp());

        /* hash generation */
        String hash = javaLib.getgenerateHMACSHA256(map.writeValueAsString(bp), secretKey);
        rLib.performPostWithHeader(baseUrl, EndPoints.balance, bp, "hash", hash);
        return String.valueOf(jsonLib.getValueJsonFromBody(UtilityClassObject.getResponse(), "balance"));
    }
}
