package com.bankguru.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.common.Common_01_Register;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.bankGuru.DepositPageObject;
import pageObjects.bankGuru.EditCustomerPageObject;
import pageObjects.bankGuru.FundTransferPageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.MainPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageObjects.bankGuru.RegisterPageObject;

@Epic("Bank Guru User")
@Feature("Register and Login")
public class Level_16_Share_Class_State_01 extends AbstractTest {
	WebDriver driver;

	// Khai báo biến toàn cục để dùng chung:

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
		driver = getBrowserDriver(browserName);
		// được new lên 1 lần
		// Khởi tạo login
		// loginPage = new LoginPageObject(driver);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Data test new customer
		name = "John Nhan";
		dateOfBirth = "05-07-1960";
		address = "226 PO address";
		city = "Califonia";
		state = "NewYork";
		pin = "567889";
		phone = "09874589874";
		email = "johnKen" + randomNumber() + "@hotmail.com";
		
		// input username/password textbox
		log.info("Pre-Condition - Step 01 - Input to UserID textbox");
		loginPage.inputToUserIDTextbox(Common_01_Register.userID);
		
		log.info("Pre-Condition - Step 02 - Input to Password textbox ");
		loginPage.inputToPasswordTextbox(Common_01_Register.password);

		// click to login button
		log.info("Pre-Condition - Step 03 - Click to Login button");
		mainPage = loginPage.clickToLoginButton();
		
	}
	@Test
	public void TC_01_Create_New_Customer() {
		
	}
	@Test
	public void TC_02_View_Customer() {
		
	}
	@Test
	public void TC_03_Edit_Customer() {
		
	}
	@Test
	public void TC_04_Delete_Customer() {
		
	}

	@AfterClass
	public void afterClass() {
		log.info("Post-condition - Close browser");
		removeBrowserDriver();
	}

	public int randomNumber() {
		Random rand = new Random();
		// bắt đầu từ 0 -> 9999
		int number = rand.nextInt(10000);
		return number;
	}
}
