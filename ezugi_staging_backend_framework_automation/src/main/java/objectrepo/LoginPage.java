package objectrepo;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import api_genericutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	@FindBy(xpath = "//a[text()='Login']")
	private WebElement homeLoginBt;

	@FindBy(xpath = "//input[@placeholder='Enter your mobile number']")
	private WebElement mobileNumField;

	@FindBy(id = "regPassword")
	private WebElement pinTxtField;

	@FindBy(xpath = "//div[@class='SB-btnTxt']")
	private WebElement loginBt;

	@FindBy(xpath = "(//div[@data-dismiss='modal'])[1]")
	private WebElement popUpCloseBt;
	
	@FindBy(xpath = "//div[@class='SB-mainHeader-userBalance']")
	private WebElement userBalance;

	
	/* Business reusable methods*/
	public String getBalanceFromFE() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://neguest:n3Gu35t@fasfas-qa.sportsit-tech.net/");
		getHomeLoginBt().click();
		getMobileNumField().sendKeys("712712712");
		getPinTxtField().sendKeys("1234");
		getLoginBt().click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", getPopUpCloseBt());
		String userBal = getUserBalance().getText();
		driver.quit();
		return userBal;
		
	}
	
	
	/* getters and setters*/

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getHomeLoginBt() {
		return homeLoginBt;
	}

	public void setHomeLoginBt(WebElement homeLoginBt) {
		this.homeLoginBt = homeLoginBt;
	}

	public WebElement getMobileNumField() {
		return mobileNumField;
	}

	public void setMobileNumField(WebElement mobileNumField) {
		this.mobileNumField = mobileNumField;
	}

	public WebElement getPinTxtField() {
		return pinTxtField;
	}

	public void setPinTxtField(WebElement pinTxtField) {
		this.pinTxtField = pinTxtField;
	}

	public WebElement getLoginBt() {
		return loginBt;
	}

	public void setLoginBt(WebElement loginBt) {
		this.loginBt = loginBt;
	}

	public WebElement getPopUpCloseBt() {
		return popUpCloseBt;
	}

	public void setPopUpCloseBt(WebElement popUpCloseBt) {
		this.popUpCloseBt = popUpCloseBt;
	}


	public WebElement getUserBalance() {
		return userBalance;
	}


	public void setUserBalance(WebElement userBalance) {
		this.userBalance = userBalance;
	}
	
	
	
	

}
