package com.bankguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
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

public class Level_09_Page_Generator_Manager extends AbstractTest {
	WebDriver driver;

	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl, emailAddress, userID, password;
	String name, dateOfBirth, address, city, state, pin, phone, email;
	// Import PageObject vào
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MainPageObject mainPage;
	NewCustomerPageObject newCustomerPage;

//	@Parameters({"browser", "url"})
//	@BeforeClass
//	public void beforeClass(String browserName, String appUrl) {
//		System.out.println("Pre-condition - Open browser and Navigate to app url");
//		driver = getBrowserDriver(browserName , appUrl);
//		System.out.println("---- Driver at Test Class = " + driver.toString() + "------");
//
//		System.out.println("Run on browser = " + browserName + " with thread id = " + Thread.currentThread().getId());
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Pre-condition - Open browser and Navigate to app url");
		driver = getBrowserDriver(browserName);
		System.out.println("---- Driver at Test Class = " + driver.toString() + "------");

		System.out.println("Run on browser = " + browserName + " with thread id = " + Thread.currentThread().getId());
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
	public void TC_03_New_Customer() {
		System.out.println("NewCustomer - Step 01 - Open New Customer page ");
		newCustomerPage = mainPage.openNewCustomerPage(driver);

		System.out.println("NewCustomer - Step 02 - Input to 'Name' textbox ");
		newCustomerPage.inputToNameTextBox(name);

		System.out.println("NewCustomer - Step 03 - Input to 'Date Of Birth' textbox ");
		newCustomerPage.inputToDateOfBirthTextBox(dateOfBirth);

		System.out.println("NewCustomer - Step 04 - Input to 'Address' textArea ");
		newCustomerPage.inputToAddressTextArea(address);

		System.out.println("NewCustomer - Step 05 - Input to 'City' textbox ");
		newCustomerPage.inputToCityTextBox(city);

		System.out.println("NewCustomer - Step 06 - Input to 'State' textbox ");
		newCustomerPage.inputToStateTextBox(state);

		System.out.println("NewCustomer - Step 07 - Input to 'Pin' textbox ");
		newCustomerPage.inputToPinTextBox(pin);

		System.out.println("NewCustomer - Step 08 - Input to 'Phone' textbox ");
		newCustomerPage.inputToPhoneTextBox(phone);

		System.out.println("NewCustomer - Step 09 - Input to 'Email' textbox ");
		newCustomerPage.inputToEmailTextBox(email);

		System.out.println("NewCustomer - Step 10 - Input to 'Password' textbox ");
		newCustomerPage.inputToPasswordTextBox(password);

		System.out.println("NewCustomer - Step 11 - Click to 'Submit' Button ");
		newCustomerPage.ClickToSubmitButton();

		System.out.println("NewCustomer - Step 12 - Verify Success message diplayed");
		Assert.assertEquals(newCustomerPage.getSuccessMessage(), "Customer Registered Successfully!!!");
	}

	@Test
	public void TC_04_Logout() {
		System.out.println("Logout - Step 01 - Click to 'Logout' Link ");
		loginPage = newCustomerPage.ClickToLogOutLink(driver);

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
