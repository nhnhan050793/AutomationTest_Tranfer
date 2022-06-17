package com.bankguru.user;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractTest;

public class Level_19_Dependency_Test extends AbstractTest {

	@BeforeClass
	public void beforeClass() {
	}

	//@Test()
	public void TC_01_New_Customer() {
		System.out.println("Run TC_01_New_Customer");
		Assert.assertTrue(false);
	}

	//@Test(dependsOnMethods = "TC_01_New_Customer")
	public void TC_02_Edit_Customer() {
		System.out.println("TC_02_Edit_Customer");
	}

	//@Test(dependsOnMethods = "TC_01_New_Customer")
	public void TC_03_New_Account() {
		System.out.println("TC_03_New_Account");
	}

	//@Test(dependsOnMethods = "TC_03_New_Account")
	public void TC_04_Edit_Account() {
		System.out.println("TC_04_Edit_Account");
	}

	//@Test(dependsOnMethods = {"TC_01_New_Customer", "TC_03_New_Account"})
	public void TC_05_Deposit() {
		System.out.println("TC_05_Deposit");
	}
	//-----------------------------------------------------------------------------------------------
	@Test()
	public void TC_01_Login_Empty_Email_Password() {
		// Home 
		// Mở màn hình login
		
		// Lỗi
		
		// màn hình khác 
	}
	
	@Test()
	public void TC_02_Login_Invalid_Email() {
		// Home 
		// Mở màn hình login 
	}
	
	@Test()
	public void TC_03_Login_Incorrect_Email() {
		// Home 
		// Mở màn hình login 	
	}
	
	@Test()
	public void TC_04_Login_Invalid_Password() {
		// Home 
		// Mở màn hình login 	
	}
	
	@Test()
	public void TC_05_Login_Incorrect_Password() {
		// Home 
		// Mở màn hình login 	
	}
	
	@Test()
	public void TC_06_Login_Valid_Email_Password() {
		// Home 
		// Mở màn hình login 	
	}

	@AfterClass
	public void afterClass() {
	}
}
