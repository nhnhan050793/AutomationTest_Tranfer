package com.bankguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import pageFactory.LoginPageObject;
import pageFactory.MainPageObject;
import pageFactory.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_08_Selenium_Page_Factory extends AbstractTest {
	WebDriver driver;

	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl, emailAddress, userID, password;
	String name, dateOfBirth, address, city, state, pin, phone, email;
	// Import PageObject vào
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MainPageObject mainPage;
	
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
		// Khởi tạo login
		loginPage = new LoginPageObject(driver);

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
		loginPage.clickToHereLink();
		registerPage = new RegisterPageObject(driver); // Khởi tạo registerPage

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
		registerPage.openLoginPage(loginPageUrl);
		loginPage = new LoginPageObject(driver); // Khởi tạo login

		// input username/password textbox
		System.out.println("Login - step 02: input username/password textbox");
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);

		// click to login button
		System.out.println("Login - step 03: click to login button");
		loginPage.clickToLoginButton();
		mainPage = new MainPageObject(driver); // Khởi tạo mainPage

		// verify login success
		System.out.println("Login - step 04: Navigate to Home Page");
		// cách 1:
		 Assert.assertEquals(mainPage.getWelcomeManagerText(), "Welcome To Manager's Page of Guru99 Bank");
		// cách 2: truyền cứng data test cho MainPageObject và MainPageUI
		//Assert.assertTrue(mainPage.isWelcomeMessageDisplayed());
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
