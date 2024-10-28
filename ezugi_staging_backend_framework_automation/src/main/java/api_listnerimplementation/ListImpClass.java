package api_listnerimplementation;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commonobjectutility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_'at'_hh_mm_ss_a");
		String time = sdf.format(dt);
		System.out.println("Report Config");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report" + time + ".html");
		spark.config().setDocumentTitle("API testing");
		spark.config().setReportName("EZUGI");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
	}

	@Override
	public void onFinish(ISuite arg0) {
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		UtilityClassObject.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
		UtilityClassObject.getTest().log(Status.FAIL, "Cause: " + result.getThrowable());

		// Optionally log additional information about the failure
		for (StackTraceElement element : result.getThrowable().getStackTrace()) {
			UtilityClassObject.getTest().log(Status.WARNING, element.toString());
		}

	}

	@Override
	public void onTestStart(ITestResult res) {
		String methodName = res.getMethod().getMethodName();
		test = report.createTest(methodName);
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, res.getMethod().getMethodName() + " >>>>> Started");

	}

	@Override
	public void onTestSuccess(ITestResult res) {
		test.log(Status.PASS, res.getMethod().getMethodName() + " >>>>> Pass");
	}

}
