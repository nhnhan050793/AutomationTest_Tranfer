package com.bankguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;
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

public class Level_05a_Page_Object_Deep_Drive extends AbstractPage {
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
	public void TC_01_Empty_User_Password() {
		// Change locator (UI):
		loginPage.inputToUserIDTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();		
		// Output 
	}

	@Test
	public void TC_02_Login_To_System() {
		loginPage.inputToUserIDTextbox("");
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		//Output
	}
	
	@Test
	public void TC_03_Login_Empty_Password() {
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();		
		//Output
	}

	@Test
	public void TC_04_Invalid_User() {
		loginPage.inputToUserIDTextbox("User234$%@#");
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();		
		//Output
	}
	
	@Test
	public void TC_05_Invalid_Password() {
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();		
		//Output
	}
	
	@Test
	public void TC_06_InCorrect_Password() {
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();		
		//Output
	}
	
	@Test
	public void TC_07_Valid_User_Password() {
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();		
		//Output
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
