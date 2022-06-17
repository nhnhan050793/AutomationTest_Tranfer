package com.bankguru.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Repeat_Yourseft {
	WebDriver driver;
	// Gọi đường dẫn
	String projectPath = System.getProperty("user.dir");
	// Khai báo biến toàn cục để dùng chung:
	String loginUrl,emailAddress, userID, password; 
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe"); // Trên windows
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver"); //macOS
		driver = new FirefoxDriver();
		
		// Data test new customer
		emailAddress = "autofc" + randomNumber() + "@hotmail.com";
		
		// nhập link:
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Register_To_System() {
		// get login Page Url
		System.out.println("step 01 - get login Page Url");
		loginUrl = driver.getCurrentUrl();
		
		// click to here link
		System.out.println("step 02 - click to here link");
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		// Input to Email textbox
		System.out.println("step 03 - Input to Email textbox");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddress);
		
		// click to Submit button
		System.out.println("step 04 - click to Submit button");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();	
		
		//Get userID/ Password information
		System.out.println("step 05 - Get userID/ Password information");
		userID= driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password  = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();	
		
	}

	@Test
	public void TC_02_Login_To_System() {
		System.out.println(userID);
		System.out.println(password);
		//open login page
		driver.get(loginUrl);
		//input username/password textbox
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		// click to login button
		driver.findElement(By.name("btnLogin")).click();
		//verify login success
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class ='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
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
