package api_genericutility;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonobjectutility.UtilityClassObject;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.List;

public class LowLevelLogs {
	public ObjectMapper map = new ObjectMapper();

	public void getLowLevelLogInfo(String infoMessage) {
		UtilityClassObject.getTest().info(MarkupHelper.createLabel(infoMessage, ExtentColor.AMBER));
	}

	public void getLowLevelLogPass(String passMessage) {
		UtilityClassObject.getTest().pass(MarkupHelper.createLabel(passMessage, ExtentColor.GREEN));
	}

	public void getLowLevelLogFail(String failMessage) {
		UtilityClassObject.getTest().fail(MarkupHelper.createLabel(failMessage, ExtentColor.RED));
	}

//	public void getLowLevelReportOfReq_Res_ResTime(Response resp, Object o) throws JsonProcessingException {
//		String requestBody = map.writeValueAsString(o);
//		String responseBody = resp.getBody().prettyPrint();
//
//		System.out.println(requestBody);
//		System.out.println(responseBody);
//
//		logJson("Request body" + requestBody);
//		logJson("Response body" + responseBody);
//		getLowLevelLogInfo("responseTime:  " + resp.getTime());
//	}

	/* log methods */

	public static void logPassDetails(String log) {
		UtilityClassObject.getTest().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}

	public static void logFailureDetails(String log) {
		UtilityClassObject.getTest().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
	}

	public static void logExceptionDetails(String log) {
		UtilityClassObject.getTest().fail(log);
	}

	public static void logInfoDetails(String log) {
		UtilityClassObject.getTest().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
	}

	public static void logWarningDetails(String log) {
		UtilityClassObject.getTest().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
	}

	public static void logJson(String json) {
		UtilityClassObject.getTest().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
	}

	public static void logHeaders(List<Header> headersList) {

		String[][] arrayHeaders = headersList.stream()
				.map(header -> new String[] { header.getName(), header.getValue() }).toArray(String[][]::new);
		UtilityClassObject.getTest().info(MarkupHelper.createTable(arrayHeaders));
	}

	public void printRequestLogInReport(RequestSpecification requestSpecification) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
		LowLevelLogs.logInfoDetails("URI is :	" + queryableRequestSpecification.getURI());
		LowLevelLogs.logInfoDetails("Method is :	" + queryableRequestSpecification.getMethod());
		LowLevelLogs.logInfoDetails("Request Headers are :	");
		LowLevelLogs.logHeaders(queryableRequestSpecification.getHeaders().asList());
		LowLevelLogs.logInfoDetails("Request body is :");
		LowLevelLogs.logJson(queryableRequestSpecification.getBody());
	}

	public void printResponseLogInReport(Response response) {
		LowLevelLogs.logInfoDetails("Response status is :	" + response.getStatusCode());
		LowLevelLogs.logInfoDetails("Response time is :	"+ response.getTime());
		LowLevelLogs.logInfoDetails("Response Headers are :	");
		LowLevelLogs.logHeaders(response.getHeaders().asList());
		LowLevelLogs.logInfoDetails("Response body is :");
		LowLevelLogs.logJson(response.getBody().prettyPrint());
	}

}
