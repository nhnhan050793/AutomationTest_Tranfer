package com.bankguru.user;

import org.testng.annotations.Test;

import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.MainPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageObjects.bankGuru.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_04_Page_Object_Pattern {
	WebDriver driver;
	// Gọi đường dẫn
	String projectPath = System.getProperty("user.dir");
	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl,emailAddress, userID, password; 
	String name, dateOfBirth, address, city, state, pin, phone, email;
	// Import PageObject vào
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MainPageObject mainPage;
	NewCustomerPageObject newCustomerPage;
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("Pre-condition - Open browser and Navigate to app url");
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe"); // Trên windows
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver"); //macOS
		driver = new FirefoxDriver();
		
		// Driver at testclass
		System.out.println("-----Driver at testclass----------");
		System.out.println(driver.toString());
		
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().window().maximize();
		
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
		registerPage = new RegisterPageObject(driver); //Khởi tạo registerPage
		
		// Input to Email textbox
		System.out.println("Register - step 03: Input to Email textbox");
		registerPage.inputToEmailTextBox(emailAddress);
		
		// click to Submit button
		System.out.println("Register - step 04: click to Submit button");
		registerPage.clickToSubmitButton();

		
		//Get userID/ Password information
		System.out.println("Register - step 05: Get userID/ Password information");
		userID = registerPage.getUserIDText();
		password = registerPage.getPasswordText();		
	}

	@Test
	public void TC_02_Login_To_System() {
		//open login page
		System.out.println("Login - step 01: get login Page");	
		registerPage.openLoginPage(loginPageUrl);
		loginPage = new LoginPageObject(driver); //Khởi tạo login
		
		//input username/password textbox
		System.out.println("Login - step 02: input username/password textbox");	
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		
		// click to login button
		System.out.println("Login - step 03: click to login button");	
		loginPage.clickToLoginButton();
		mainPage = new MainPageObject(driver); //Khởi tạo mainPage
		
		//verify login success
		System.out.println("Login - step 04: Navigate to Home Page");
		// cách 1: 
		//Assert.assertEquals(mainPage.getWelcomeMessageText(), "Welcome To Manager's Page of Guru99 Bank");
		// cách 2: truyền cứng data test cho MainPageObject và MainPageUI 
		Assert.assertTrue(mainPage.isWelcomeMessageDisplayed());
	}
	
	@Test
	public void TC_03_New_Customer() {
		System.out.println("NewCustomer - Step 01 - Open New Customer page ");
		mainPage.openNewCustomerPage(driver);
		newCustomerPage = new NewCustomerPageObject(driver); //Khởi tạo newCustomerPage

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
		newCustomerPage.ClickToLogOutLink(driver);
		loginPage = new LoginPageObject(driver); //Khởi tạo loginPage

		System.out.println("Logout - Step 02 - verify login form displayed");
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
	}

	
	@AfterClass	
	public void afterClass() {
		System.out.println("Post-condition - Close browser");
		driver.quit();
	}
	
	public int randomNumber() {
		Random rand = new Random();
		// bắt đầu từ 0 -> 9999
		int number = rand.nextInt(10000);
		return number;
	}
}
