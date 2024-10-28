package commonobjectutility;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject 
{
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	
	public static ExtentTest getTest()
	{
		return test.get();
	}
	//setter
	public static void setTest(ExtentTest actTest)
	{
		test.set(actTest);
	}

}
