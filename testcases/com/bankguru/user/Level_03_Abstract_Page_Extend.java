package com.bankguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Abstract_Page_Extend extends AbstractPage {
	WebDriver driver;
	// Gọi đường dẫn
	String projectPath = System.getProperty("user.dir");
	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl,emailAddress, userID, password; 
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("Pre-condition - step 01: Open browser");
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe"); // Trên windows
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver"); //macOS
		driver = new FirefoxDriver();
		
		// Data test new customer
		emailAddress = "autofc" + randomNumber() + "@hotmail.com";		
			
		// nhập link:
		System.out.println("Pre-condition - step 02: Navigate to app url");
		openUrl(driver, "http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_Register_To_System() {
		// get login Page Url
		System.out.println("Register - step 01: get login Page Url");
		loginPageUrl = getCurrentPageUrl(driver);
		
		// click to here link
		System.out.println("Register - step 02: click to here link");
		clickToElement(driver, "//a[text()='here']");
		
		// Input to Email textbox
		System.out.println("Register - step 03: Input to Email textbox");
		SendkeyToElement(driver, "//input[@name='emailid']", emailAddress);
		
		// click to Submit button
		System.out.println("Register - step 04: click to Submit button");
		clickToElement(driver, "//input[@name='btnLogin']");
		
		//Get userID/ Password information
		System.out.println("Register - step 05: Get userID/ Password information");
		userID = getElementText(driver, "//td[text()='User ID :']/following-sibling::td");
		password = getElementText(driver, "//td[text()='Password :']/following-sibling::td");
		
	}

	@Test
	public void TC_02_Login_To_System() {
		//open login page
		System.out.println("Login - step 01: get login Page");	
		openUrl(driver, loginPageUrl);
		
		//input username/password textbox
		System.out.println("Login - step 02: input username/password textbox");	
		SendkeyToElement(driver, "//input[@name='uid']", userID);
		SendkeyToElement(driver, "//input[@name='password']", password);
		
		// click to login button
		System.out.println("Login - step 03: click to login button");	
		clickToElement(driver, "//input[@name='btnLogin']");
		
		//verify login success
		System.out.println("Login - step 04: Navigate to Home Page");	
		Assert.assertEquals(getElementText(driver, "//marquee[@class ='heading3']"), "Welcome To Manager's Page of Guru99 Bank");
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
