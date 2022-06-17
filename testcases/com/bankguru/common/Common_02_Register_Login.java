package com.bankguru.common;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
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

public class Common_02_Register_Login extends AbstractTest {
	WebDriver driver;

	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl, emailAddress;
	public static String userID, password;
	
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
	@BeforeTest
	public void beforeTest(String browserName) {
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
		

		// click to here link
		log.info("Pre-condition - Step 01: Click to here link ");
		loginPageUrl = loginPage.getLoginPageUrl();
		registerPage = loginPage.clickToHereLink();

		// Input to Email textbox
		log.info("Pre-condition - Step 02: Input to 'Email' textbox with value: " + emailAddress);
		registerPage.inputToEmailTextBox(emailAddress);

		// click to Submit button
		log.info("Pre-condition - Step 03:  click to 'Submit' button");
		registerPage.clickToSubmitButton();

		// Get userID/ Password information
		log.info("Pre-condition - Step 04:  Get userID/ Password information");
		userID = registerPage.getUserIDText();
		password = registerPage.getPasswordText();
		
		// open login page
		loginPage = registerPage.openLoginPage(loginPageUrl);

		// First pass (step này sẽ bị pass )
		verifyTrue(loginPage.isLoginFormDisplayed());
		
		// input username/password textbox
		loginPage.inputToUserIDTextbox(userID);
		
		loginPage.inputToPasswordTextbox(password);

		// click to login button
		mainPage = loginPage.clickToLoginButton();

		// Second pass 
		// verify login success
		// cách 1: lưu 
		// Assert.assertEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
		// truyền sai message 
		verifyEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
		// cách 2: truyền cứng data test cho MainPageObject và MainPageUI
		//Assert.assertTrue(mainPage.isWelcomeMessageDisplayed());
		
		// Thrid  passed 
		//Assert.assertTrue(mainPage.isLoginFormUndisplayed());
		verifyTrue(mainPage.isLoginFormUndisplayed());
		
		// Get ra cookie lưu lại thành 1 biến static 
		
		removeBrowserDriver();		
	}

	public int randomNumber() {
		Random rand = new Random();
		// bắt đầu từ 0 -> 9999
		int number = rand.nextInt(10000);
		return number;
	}
}
