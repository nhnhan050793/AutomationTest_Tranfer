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

public class Level_11_Dynamic_Page_Rest_Parameter extends AbstractTest {
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

		// input username/password textbox
		System.out.println("Login - step 02: input username/password textbox");
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);

		// click to login button
		System.out.println("Login - step 03: click to login button");
		mainPage = loginPage.clickToLoginButton();

		// verify login success
		System.out.println("Login - step 04: Navigate to Home Page");
		// cách 1:
		// Assert.assertEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
		// cách 2: truyền cứng data test cho MainPageObject và MainPageUI
		Assert.assertTrue(mainPage.isWelcomeMessageDisplayed());
	}

	@Test
	public void TC_03_Dynamic_less_Page() {
		// cách 1: ít page
		// ép kiểu cha abstract page - openMenuPageByName xuống kiểu con newCustomerPage
		// Main Page -> New Customer
		newCustomerPage = (NewCustomerPageObject) mainPage.openMenuPageByName(driver, "New Customer");

		// New Customer -> Deposit
		depositPage = (DepositPageObject) newCustomerPage.openMenuPageByName(driver, "Deposit");

		// Deposit -> Edit Customer
		editCustomerPage = (EditCustomerPageObject) depositPage.openMenuPageByName(driver, "Edit Customer");

		// Edit Customer -> Fund Transfer
		fundTransferPage = (FundTransferPageObject) editCustomerPage.openMenuPageByName(driver, "Fund Transfer");

		// Fund Transfer -> Deposit
		depositPage = (DepositPageObject) fundTransferPage.openMenuPageByName(driver, "Deposit");

		// Deposit-> New Customer
		newCustomerPage = (NewCustomerPageObject) depositPage.openMenuPageByName(driver, "New Customer");

		// Fund Transfer -> Edit Customer
		editCustomerPage = (EditCustomerPageObject) fundTransferPage.openMenuPageByName(driver, "Edit Customer");

		// Edit Customer -> New Customer
		newCustomerPage = (NewCustomerPageObject) mainPage.openMenuPageByName(driver, "New Customer");
	}

	@Test
	public void TC_04_Dynamic_More_Page() {
		// cách 2: nhiều page

		// Main Page -> New Customer
		mainPage.openMenuPageByName(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		// New Customer -> Deposit
		newCustomerPage.openMenuPageByName(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);

		// Deposit -> Edit Customer
		depositPage.openMenuPageByName(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		// Edit Customer -> Fund Transfer
		editCustomerPage.openMenuPageByName(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);

		// Fund Transfer -> Deposit
		fundTransferPage.openMenuPageByName(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);

		// Deposit-> New Customer
		depositPage.openMenuPageByName(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		// Fund Transfer -> Edit Customer
		fundTransferPage.openMenuPageByName(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		
		// Edit Customer -> New Customer
		mainPage.openMenuPageByName(driver, "New Customer");
		newCustomerPage =  PageGeneratorManager.getNewCustomerPage(driver);
	}

	@Test
	public void TC_05_Logout() {
		System.out.println("Logout - Step 01 - Click to 'Logout' Link ");
		loginPage = newCustomerPage.ClickToLogOutLink(driver);
//		loginPage = editCustomerPage.ClickToLogOutLink(driver);
//		loginPage = depositPage.ClickToLogOutLink(driver);
//		loginPage = depositPage.ClickToLogOutLink(driver);

		System.out.println("Logout - Step 02 - verify login form displayed");
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
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
