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

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13a_Element_Undisplayed extends AbstractTest {
	WebDriver driver;

	// Khai báo biến toàn cục để dùng chung:
	String loginPageUrl, emailAddress, userID, password;
	String name, dateOfBirth, address, city, state, pin, phone, email;
	
	// Khai báo AbstractPage 
	AbstractPage abstractPage; 
	

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

		// khởi tạo:
		//abstractPage = new AbstractPage();
			
		// nhập link:
		abstractPage.openUrl(driver, "https://www.facebook.com/");
	}

	@Test
	public void TC_01_Element_Undisplayed() throws InterruptedException {	
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		// kiểm tra 1 element hiển thị 
		System.out.println("Case 01 - Start: " + getimeNow());
		abstractPage.waitToElementVisible(driver, "//input[@name = 'lastname']");
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//input[@name = 'lastname']"));
		System.out.println("Case 01 - End: " + getimeNow());
		

		// kiểm tra 1 element không hiển thị có 2 trường hợp
		// a) không có trên UI nhưng lại xuất hiện ở trong 
		System.out.println("Case 02 - Start: " + getimeNow());
		abstractPage.waitToElementInvisible(driver, "//input[@name = 'reg_email_confirmation__']");
		Assert.assertFalse(abstractPage.isControlDisplayed(driver, "//input[@name = 'reg_email_confirmation__']"));
		System.out.println("Case 02 - End: " + getimeNow());
		
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();			
		Thread.sleep(2000);
	    /* b) không có trên UI nhưng không  xuất hiện ở trong 
		* hàm isDisplayed nó không kiểm tra 1 element không có trong dom 
		* findElement() trước -> nếu như không tìm thấy đánh thì đánh fail testcase và throw rax exception : No such Element 
		* Nó sẽ chờ hết timeout của implicitWait 
		*/ 	
		

		// lưu ý implicitlyWait : chỉ dùng cho hàm findElement, findElements 
		
		System.out.println("Case 03.1 - Start: " + getimeNow());		
		abstractPage.waitToElementInvisible(driver, "//input[@name = 'reg_email_confirmation__']");
		System.out.println("Case 03.1 - End: " + getimeNow());
		
		System.out.println("Case 03.2 - Start: " + getimeNow());	
		Assert.assertTrue(abstractPage.isControlUndisplayed(driver, "//input[@name = 'reg_email_confirmation__']"));
		System.out.println("Case 03.2 - End: " + getimeNow());
		
		// chia làm 3 step 
		 /*
		 *  step 1: driver.findElement(By.xpath("//div[@id = 'reg_box']") -> found / not found(fail) 
		 *  Nếu như found (tìm thấy) qua step 2: isDisplayed() -> true (visible - Displayed ) hoặc  false (invisible - unDisplay) 
		 *  chạy step 2 xong mới chạy qua step 3: Assert.assertFalse
		 */

	}

public String getimeNow() {
	Date date = new Date();
	return date.toString();
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
