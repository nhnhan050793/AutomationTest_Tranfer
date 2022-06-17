package com.bankguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Abstract_Page_Init {
	WebDriver driver;
	// Gọi đường dẫn
	String projectPath = System.getProperty("user.dir");
	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl,emailAddress, userID, password; 
	// Khai báo AbstractPage 
	AbstractPage abstractPage; 

	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe"); // Trên windows
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver"); //macOS
		driver = new FirefoxDriver();
		
		// Data test new customer
		emailAddress = "autofc" + randomNumber() + "@hotmail.com";
		
		// khởi tạo:
		//abstractPage = new AbstractPage();
			
		// nhập link:
		abstractPage.openUrl(driver, "http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_Register_To_System() {
		// get login Page Url
		System.out.println("step 01 - get login Page Url");
		loginPageUrl = abstractPage.getCurrentPageUrl(driver);
		
		// click to here link
		System.out.println("step 02 - click to here link");
		abstractPage.clickToElement(driver, "//a[text()='here']");
		
		// Input to Email textbox
		System.out.println("step 03 - Input to Email textbox");
		abstractPage.SendkeyToElement(driver, "//input[@name='emailid']", emailAddress);
		
		// click to Submit button
		System.out.println("step 04 - click to Submit button");
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		
		//Get userID/ Password information
		System.out.println("step 05 - Get userID/ Password information");
		userID = abstractPage.getElementText(driver, "//td[text()='User ID :']/following-sibling::td");
		password = abstractPage.getElementText(driver, "//td[text()='Password :']/following-sibling::td");
		
	}

	@Test
	public void TC_02_Login_To_System() {
		//open login page
		System.out.println("step 01 - get login Page");	
		abstractPage.openUrl(driver, loginPageUrl);
		
		//input username/password textbox
		System.out.println("step 02 - input username/password textbox");	
		abstractPage.SendkeyToElement(driver, "//input[@name='uid']", userID);
		abstractPage.SendkeyToElement(driver, "//input[@name='password']", password);
		
		// click to login button
		System.out.println("step 03 - click to login button");	
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		
		//verify login success
		System.out.println("step 04 - Navigate to Home Page");	
		Assert.assertEquals(abstractPage.getElementText(driver, "//marquee[@class ='heading3']"), "Welcome To Manager's Page of Guru99 Bank");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random rand = new Random();
		// bắt đầu từ 0 -> 9999
		int number = rand.nextInt(10000);
		return number;
	}

}
