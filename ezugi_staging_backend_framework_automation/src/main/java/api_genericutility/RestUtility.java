package api_genericutility;

import static io.restassured.RestAssured.given;

import commonobjectutility.UtilityClassObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtility extends LowLevelLogs {

	public void performPost(String baseUrl, String endpoint, Object body) {
		RequestSpecification request = given().contentType(ContentType.JSON).body(body);
		Response resp = request.when().post(baseUrl + endpoint);
		UtilityClassObject.setRequest(request);
		UtilityClassObject.setResponse(resp);
		printRequestLogInReport(request);
		printResponseLogInReport(resp);
	}

	public void performPostWithHeader(String baseUrl, String endpoint, Object body, String key, String value) {
		RequestSpecification request = given().contentType(ContentType.JSON).body(body).header(key, value);
		Response resp = request.when().post(baseUrl + endpoint);
		UtilityClassObject.setRequest(request);
		UtilityClassObject.setResponse(resp);
		printRequestLogInReport(request);
		printResponseLogInReport(resp);
//		System.out.println(body.toString().substring(11, 17) +" response time"+ "	:"+resp.getTime() );
		
	}

}
