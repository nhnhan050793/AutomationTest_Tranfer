package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.saucelab.PageGeneratorManager;
import pageObjects.saucelab.ProductPageObject;

public class Level_20_Sort_Asc_Desc extends AbstractTest {
	WebDriver driver;
	boolean status;
	
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition - step 01: Open" + browserName + "browser and Navigate to app url");
		driver = getBrowserDriver(browserName, appUrl);

		productPage = PageGeneratorManager.getProductPage(driver);

	}
	
	@Test
	public void Sort_01_Name() {
		log.info("Sort_01 - Step 01: Select 'Name (A to Z)' in Sort dropdown");
		productPage.selectValueInSortDropdown("Name (A to Z)");
		productPage.sleepInSecond(3);
		
		log.info("Sort_01 - Step 02: verify Product Name are sorted ascending");
		status = productPage.areproductNameSortAscending();
		System.out.println(status);
		verifyTrue(status);
		
		log.info("Sort_01 - Step 03: Select 'Name (Z to A)' in Sort dropdown");
		productPage.selectValueInSortDropdown("Name (Z to A)");
		productPage.sleepInSecond(3);
		
		log.info("Sort_01 - Step 04: verify Product Name are sorted descending");
		status = productPage.areproductNameSortDescending();
		System.out.println(status);
		verifyTrue(status);
	}
	
	@Test
	public void Sort_02_Price() {
		log.info("Sort_02 - Step 01: Select 'Price (low to high)' in Sort dropdown");
		productPage.selectValueInSortDropdown("Price (low to high)");
		productPage.sleepInSecond(3);
		
		log.info("Sort_02 - Step 02: verify Product Price are sorted ascending");
		verifyTrue(productPage.areproductPriceSortAscending());
		
		log.info("Sort_02 - Step 03: Select 'Price (high to low)' in Sort dropdown");
		productPage.selectValueInSortDropdown("Price (high to low)");
		productPage.sleepInSecond(3);
		
		log.info("Sort_02 - Step 04: verify Product Price are sorted descending");
		verifyTrue(productPage.areproductPriceSortDescending());
	}
	
	@AfterClass
	public void afterClass() {
		removeBrowserDriver();
	}
	
	ProductPageObject productPage;
}
