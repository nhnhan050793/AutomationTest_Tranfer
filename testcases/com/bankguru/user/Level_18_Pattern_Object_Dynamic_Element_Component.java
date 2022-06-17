package com.bankguru.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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


public class Level_18_Pattern_Object_Dynamic_Element_Component extends AbstractTest {
	WebDriver driver;

	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl, emailAddress, userID, password;
	String customerName, genderMale, dateOfBirth, address, city, state, pin, phone;
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
		log.info("Pre-condition - step 01: Open" + browserName + "browser and Navigate to app url");
		driver = getBrowserDriver(browserName);
		// được new lên 1 lần
		// Khởi tạo login
		// loginPage = new LoginPageObject(driver);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Data test new customer
		emailAddress = "autofc" + GetrandomNumber() + "@hotmail.com";
		customerName = "John Nhan";
		genderMale = "male";
		dateOfBirth = "1960-07-05";
		address = "226 PO address";
		city = "Califonia";
		state = "NewYork";
		pin = "567889";
		phone = "09874589874";

		
		// get login Page Url
		log.info("Pre-condition - step 02: get login Page Url");
		loginPageUrl = loginPage.getLoginPageUrl();

		// click to here link
		log.info("Pre-condition - step 03: click to 'here' link");
		registerPage = loginPage.clickToHereLink();
		
		//Assert.assertTrue(false);
	}

	@Test
	public void TC_01_Register_To_System() {
		// Input to Email textbox
		log.info("Register - step 01: Input to 'Email' textbox with value: " + emailAddress);
		registerPage.inputToTextboxByNameAttribute(driver, "emailid", emailAddress);

		// click to Submit button
		log.info("Register - step 02: click to 'Submit' button");
		registerPage.clickToButtonByValue(driver, "Submit");
		
		// Get userID/ Password information
		log.info("Register - step 03: Get userID/ Password information");
		userID = registerPage.getUserIDText();
		password = registerPage.getPasswordText();
		
		
	}
	
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
		loginPage.inputToTextboxByNameAttribute(driver, "uid", userID);
		
		log.info("Login - step 04: input password textbox with value: " + password);
		loginPage.inputToTextboxByNameAttribute(driver, "password", password);

		// click to login button
		log.info("Login - step 05: click to login button");
		//mainPage = loginPage.clickToLoginButton();
		// viet lai
		loginPage.clickToButtonByValue(driver, "LOGIN");
		mainPage = PageGeneratorManager.getMainPage(driver);
		
		// Second pass 
		// verify login success
		log.info("Login - step 06: Verify welcome message displayed");
		// cách 1: lưu 
		// Assert.assertEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
		// truyền sai message 
		verifyEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
		// cách 2: truyền cứng data test cho MainPageObject và MainPageUI
		//Assert.assertTrue(mainPage.isWelcomeMessageDisplayed());
		
		// Thrid  passed 
		log.info("Login - step 07: Verify login form undisplayed");
		//Assert.assertTrue(mainPage.isLoginFormUndisplayed());
		verifyTrue(mainPage.isLoginFormUndisplayed());
	}
	
	@Test
	public void TC_03_New_Customer() {
		log.info("New Customer - step 01: Open New Customer page");
		mainPage.openMenuPagesByName(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		
		log.info("New Customer - step 02: Verify New Customer page displayed");
		verifyTrue(newCustomerPage.isPageHeaderDisplayedByText(driver, "Add New Customer"));
		
		log.info("New Customer - step 03: Input to Customer Name textbox");
		newCustomerPage.inputToTextboxByNameAttribute(driver, "name", customerName );
		
		log.info("New Customer - step 04: Click to Gender with male value");
		newCustomerPage.clickToRadioButtonByValue(driver, "m");
		
		log.info("New Customer - step 05: Input to Date of Birth textbox");
		newCustomerPage.inputToTextboxByNameAttribute(driver, "dob", dateOfBirth );
		
		log.info("New Customer - step 06: Input to Address textarea");
		newCustomerPage.inputToTextAreaByNameAttribute(driver, "addr", address);
		
		log.info("New Customer - step 07: Input to City textbox");
		newCustomerPage.inputToTextboxByNameAttribute(driver, "city", city );
		
		log.info("New Customer - step 08: Input to State textbox");
		newCustomerPage.inputToTextboxByNameAttribute(driver, "state", state );
		
		log.info("New Customer - step 09: Input to PIN textbox");
		newCustomerPage.inputToTextboxByNameAttribute(driver, "pinno", pin );
		
		log.info("New Customer - step 10: Input to Mobile Number textbox");
		newCustomerPage.inputToTextboxByNameAttribute(driver, "telephoneno", phone );
		
		log.info("New Customer - step 11: Input to Email textbox");
		newCustomerPage.inputToTextboxByNameAttribute(driver, "emailid", emailAddress );
		
		log.info("New Customer - step 12: Input to Password textbox");
		newCustomerPage.inputToTextboxByNameAttribute(driver, "password", password );
		
		log.info("New Customer - step 13: click to 'Submit' button");
		newCustomerPage.clickToButtonByValue(driver, "Submit");
		
		log.info("New Customer - step 14: Verify New Customer registered success");
		verifyTrue(newCustomerPage.isPageHeaderDisplayedByText(driver, "Customer Registered Successfully!!!"));
		
		log.info("New Customer - step 15: Verify Customer Name displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Customer Name"), customerName);

		log.info("New Customer - step 16: Verify Gender displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Gender"), genderMale);
		
		log.info("New Customer - step 17: Verify Birthdate displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Birthdate"), dateOfBirth);
		
		log.info("New Customer - step 18: Verify Address displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Address"), address);
		
		log.info("New Customer - step 19: Verify City displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "City"), city);
		
		log.info("New Customer - step 20: Verify State displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "State"), state);
		
		log.info("New Customer - step 21: Verify Pin displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Pin"), pin);
		
		log.info("New Customer - step 22: Verify Mobile No. displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Mobile No."), phone);
		
		log.info("New Customer - step 23: Verify Email displayed correct");
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Email"), emailAddress);
				
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-condition - Close browser");
		removeBrowserDriver();
	}
}
