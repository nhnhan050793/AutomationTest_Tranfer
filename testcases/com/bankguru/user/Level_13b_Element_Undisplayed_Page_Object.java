package com.bankguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.bankGuru.DepositPageObject;
import pageObjects.bankGuru.EditCustomerPageObject;
import pageObjects.bankGuru.FundTransferPageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.MainPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageObjects.bankGuru.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13b_Element_Undisplayed_Page_Object extends AbstractTest {
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
		System.out.println("Pre-condition - Open browser and Navigate to app url");
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

	@Test
	public void TC_01_Register_To_System() {
		// get login Page Url
		System.out.println("Register - step 01: get login Page Url");
		loginPageUrl = loginPage.getLoginPageUrl();

		// click to here link
		System.out.println("Register - step 02: click to here link");
		registerPage = loginPage.clickToHereLink();

		// Input to Email textbox
		System.out.println("Register - step 03: Input to Email textbox");
		registerPage.inputToEmailTextBox(emailAddress);

		// click to Submit button
		System.out.println("Register - step 04: click to Submit button");
		registerPage.clickToSubmitButton();

		// Get userID/ Password information
		System.out.println("Register - step 05: Get userID/ Password information");
		userID = registerPage.getUserIDText();
		password = registerPage.getPasswordText();
	}

	@Test
	public void TC_02_Login_To_System() {
		// open login page
		System.out.println("Login - step 01: get login Page");
		loginPage = registerPage.openLoginPage(loginPageUrl);

		System.out.println("Login - step 02: Verify Login form displayed");
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		
		// input username/password textbox
		System.out.println("Login - step 03: input username/password textbox");
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);

		// click to login button
		System.out.println("Login - step 04: click to login button");
		mainPage = loginPage.clickToLoginButton();

		// verify login success
		System.out.println("Login - step 05: Navigate to Home Page");
		// cách 1:
		// Assert.assertEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
		// cách 2: truyền cứng data test cho MainPageObject và MainPageUI
		Assert.assertTrue(mainPage.isWelcomeMessageDisplayed());
		
		System.out.println("Login - step 06: Verify login form undisplayed");
		Assert.assertTrue(mainPage.isLoginFormUndisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Post-condition - Close browser");
		removeBrowserDriver();
	}

	public int randomNumber() {
		Random rand = new Random();
		// bắt đầu từ 0 -> 9999
		int number = rand.nextInt(10000);
		return number;
	}
}
