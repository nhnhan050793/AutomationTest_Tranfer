package com.bankguru.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
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

@Epic("Bank Guru User")
@Feature("Register and Login")
public class Level_15_Log_Report_HTML extends AbstractTest {
	WebDriver driver;

	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl, emailAddress, userID, password;
	String name, dateOfBirth, address, city, state, pin, phone, email;
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
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-condition - Open" + browserName + "browser and Navigate to app url");
		driver = getBrowserDriver(browserName);
		// được new lên 1 lần
		// Khởi tạo login
		// loginPage = new LoginPageObject(driver);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Data test new customer
		emailAddress = "autofc" + randomNumber() + "@hotmail.com";

		// Data test new customer
		name = "John Nhan";
		dateOfBirth = "05-07-1960";
		address = "226 PO address";
		city = "Califonia";
		state = "NewYork";
		pin = "567889";
		phone = "09874589874";
		email = "johnKen" + randomNumber() + "@hotmail.com";
	}

	@Story("User Story 01 - Register to System")
	@Description("Register to system and verify register success")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC_01_Register_To_System() {
		// get login Page Url
		log.info("Register - step 01: get login Page Url");
		loginPageUrl = loginPage.getLoginPageUrl();

		// click to here link
		log.info("Register - step 02: click to 'here' link");
		registerPage = loginPage.clickToHereLink();

		// Input to Email textbox
		log.info("Register - step 03: Input to 'Email' textbox with value: " + emailAddress);
		registerPage.inputToEmailTextBox(emailAddress);

		// click to Submit button
		log.info("Register - step 04: click to 'Submit' button");
		registerPage.clickToSubmitButton();

		// Get userID/ Password information
		log.info("Register - step 05: Get userID/ Password information");
		userID = registerPage.getUserIDText();
		password = registerPage.getPasswordText();
	}


	@Story("User Story 02 - Login to System")
	@Description("Login to system and verify Logged success")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC_02_Login_To_System() {
		// open login page
		log.info("Login - step 01: get login Page");
		loginPage = registerPage.openLoginPage(loginPageUrl);

		// First pass (step này sẽ bị pass )
		log.info("Login - step 02: Verify Login form displayed");
		verifyTrue(loginPage.isLoginFormDisplayed());
		
		// input username/password textbox
		log.info("Login - step 03: input username textbox with value: " + userID);
		loginPage.inputToUserIDTextbox(userID);
		
		log.info("Login - step 04: input password textbox with value: " + password);
		loginPage.inputToPasswordTextbox(password);

		// click to login button
		log.info("Login - step 05: click to login button");
		mainPage = loginPage.clickToLoginButton();

		// Second pass 
		// verify login success
		log.info("Login - step 06: Verify welcome message displayed");
		// cách 1: lưu 
		// Assert.assertEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
		// truyền sai message 
		verifyEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank.");
		// cách 2: truyền cứng data test cho MainPageObject và MainPageUI
		//Assert.assertTrue(mainPage.isWelcomeMessageDisplayed());
		
		// Thrid  passed 
		log.info("Login - step 07: Verify login form undisplayed");
		//Assert.assertTrue(mainPage.isLoginFormUndisplayed());
		verifyTrue(mainPage.isLoginFormUndisplayed());
	}

	@AfterClass
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
