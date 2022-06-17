package com.bankguru.user;

import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.data.PaymentJson;
import com.bankguru.data.payment;

import commons.AbstractTest;
import commons.Environment;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.bankGuru.DepositPageObject;
import pageObjects.bankGuru.EditCustomerPageObject;
import pageObjects.bankGuru.FundTransferPageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.MainPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageObjects.bankGuru.RegisterPageObject;
import utilities.DataHelper;


public class Level_25_Database_Testing extends AbstractTest {
	WebDriver driver;
	DataHelper data = DataHelper.getData();
	Environment env;
	
	// windows
	PaymentJson paymentData = PaymentJson.getJsonData(GlobalConstants.ROOT_FOLDER + "/testdata/com/bankguru/data/Payment.json");
	// macOS
	
	
	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl, userID, password, emailAddress;

	
	// Import PageObject vào
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MainPageObject mainPage;
	NewCustomerPageObject newCustomerPage;
	DepositPageObject depositPage;
	EditCustomerPageObject editCustomerPage;
	FundTransferPageObject fundTransferPage;

//	@Parameters({"browser", "url"})
//	@BeforeClass
//	public void beforeClass(String browserName, String appUrl) {
//		System.out.println("Pre-condition - Open browser and Navigate to app url");
//		driver = getBrowserDriver(browserName , appUrl);
	
//	@Parameters("browser")
//	@BeforeClass
//	public void beforeClass(String browserName) {
//		log.info("Pre-condition - step 01: Open" + browserName + "browser and Navigate to app url");
//		driver = getBrowserDriver(browserName);
	
	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("environment", environmentName);
		env = ConfigFactory.create(Environment.class);		
		driver = getBrowserDriver(browserName, env.url());
		
		System.out.println("url = " + env.url());
		System.out.println("DB name = " + env.databaseHostname());
		System.out.println("DB username = " + env.databaseUsername());
		System.out.println("DB pass = " + env.databasePassword());
	
		// được new lên 1 lần
		// Khởi tạo login
		// loginPage = new LoginPageObject(driver);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	
		emailAddress = paymentData.getEmailAddress() + GetrandomNumber() + "@icloud.com";
	
		
		// get login Page Url
		log.info("Pre-condition - step 02: get login Page Url");
		loginPageUrl = loginPage.getLoginPageUrl();

		// click to here link
		log.info("Pre-condition - step 03: click to 'here' link");
		registerPage = loginPage.clickToHereLink();
		
		showBrowserConsoleLogs(driver);
		
		//Assert.assertTrue(false);
	}

	@Test
	public void TC_01_Register_To_System() {
		// Input to Email textbox
		log.info("Register - step 01: Input to 'Email' textbox with value: " + emailAddress);
		registerPage.inputToEmailTextBox(emailAddress);

		// click to Submit button
		log.info("Register - step 02: click to 'Submit' button");
		registerPage.clickToSubmitButton();

		// Get userID/ Password information
		log.info("Register - step 03: Get userID/ Password information");
		userID = registerPage.getUserIDText();
		password = registerPage.getPasswordText();
		
		// kiem tra  so sanh database
		log.info("Register - step 04: Get mobile product number on mobile page UI ");
		int productSizeUI = registerPage.getproductMobileNumberOnUI();
		System.out.println(productSizeUI);
		
		log.info("Register - step 05: Get mobile product numbe in database");
		int productSizeDB =  registerPage.getproductMobileNumberInDB();
		System.out.println(productSizeDB);
		verifyEquals(productSizeUI, productSizeDB);
			
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-condition - Close browser");
		removeBrowserDriver();
	}

	public int randomNumber() {
		Random rand = new Random();
		// bắt đầu từ 0 -> 9999
		int number = rand.nextInt(10000);
		return number;
	}
}
