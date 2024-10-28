package baseapi;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.fasterxml.jackson.databind.ObjectMapper;

import api_genericutility.ExcelUtility;
import api_genericutility.FileUtility;
import api_genericutility.JavaUtility;
import api_genericutility.JsonUtility;
import api_genericutility.LowLevelLogs;


@Listeners(api_listnerimplementation.ListImpClass.class)
public class BaseApiClass {

	public ObjectMapper map = new ObjectMapper();

	public ExcelUtility eu = new ExcelUtility();
	public FileUtility fLib = new FileUtility();
	public JsonUtility jsonLib = new JsonUtility();
	public LowLevelLogs ll = new LowLevelLogs();
	public JavaUtility javaLib = new JavaUtility();

	public final static String baseUrl = "https://dev-beapi-games-rgs.sportsit-tech.net/rgs/api/ezugi/v2";
	public String secretKey = "5dbb6aed-a244-4e63-b5d9-95e5f118a545";

	public String operatorId;
	public String token;

	public String clientType;
	public String selectGame;
	public int openTable;
	public int tableLimits;
	public int platformId;
	public int serverId;
	public int gameId;
	public int tableId;
	public double debitAmount;
	public double rollbackAmount;
	public double creditAmount;
	public String debitTransactionId;
	public String seatId;
	public int betTypeId;
	public int returnReason;
	public boolean isEndRound;
	public String gameDataString;
	public String creditIndex;
	public String currentToken;
	public String uid;
	public String playerTokenAtLaunch;
	public String currency;
	public String playerId;
	public String currencyCode;

	@BeforeSuite
	public void variables() throws EncryptedDocumentException, IOException {
		/* Initializing data */

		/* getURL */

		playerId = eu.getDataFromExcel("ezugi", 2, 2);
		platformId = Integer.valueOf(eu.getDataFromExcel("ezugi", 5, 2));
		operatorId = eu.getDataFromExcel("ezugi", 9, 2);
		token = eu.getDataFromExcel("ezugi", 4, 2);
		currencyCode = eu.getDataFromExcel("ezugi", 6, 2);

		

		/* Debit API */

		gameId = Integer.valueOf(eu.getDataFromExcel("ezugi", 7, 2));
		debitAmount = Double.valueOf(eu.getDataFromExcel("ezugi", 16, 2));
		currentToken = eu.getDataFromExcel("ezugi", 11, 2);
		serverId = Integer.valueOf(eu.getDataFromExcel("ezugi", 12, 2));
		seatId = eu.getDataFromExcel("ezugi", 13, 2);
		currency = eu.getDataFromExcel("ezugi", 3, 2);

		/* credit API */

		debitTransactionId = eu.getDataFromExcel("ezugi", 17, 2);
		isEndRound = false;
		creditIndex = "1|1";
		gameDataString = "";
		returnReason = 0;
		betTypeId = 101;
		tableId = 1;
		creditAmount = 100.11;

		/* RollBack API */

		rollbackAmount = 100.11;

	}
	
	@BeforeTest
	public void testVariables() throws EncryptedDocumentException, IOException {
		
		/* AuthenticationTest */
		playerTokenAtLaunch = eu.getDataFromExcel("ezugi", 10, 2);
		
	}

}
