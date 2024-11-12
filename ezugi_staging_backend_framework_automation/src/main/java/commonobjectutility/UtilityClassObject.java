package commonobjectutility;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilityClassObject {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<RequestSpecification> request = new ThreadLocal<RequestSpecification>();
	public static ThreadLocal<Response> response = new ThreadLocal<Response>();

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
	}


	public static RequestSpecification getRequest() {
		return request.get();
	}

	public static void setRequest(RequestSpecification actRequest) {
		request.set(actRequest);
	}
	
	
	
	public static Response getResponse() {
		return response.get();
	}

	public static void setResponse(Response actResponse) {
		response.set(actResponse);
	}

}
