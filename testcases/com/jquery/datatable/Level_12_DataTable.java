package com.jquery.datatable;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.jQuery.HomePageObjet;
import pageObjects.jQuery.PageGeneratorManager;

public class Level_12_DataTable extends AbstractTest {
	WebDriver driver;
	HomePageObjet homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.gethomePage(driver);
	}

	// @Test
	public void TC_01_Data_Table() {
		// input to textbox
		homePage.inputToTextBoxByColumnName("Country", "Singapore");
		homePage.inputToTextBoxByColumnName("Females", "35100");
		homePage.inputToTextBoxByColumnName("Males", "36800");
		homePage.inputToTextBoxByColumnName("Total", "71900");

	}

	// @Test
	public void TC_02_Navigate() {
		homePage.refreshCurrentPage(driver);

		// Navigate to any page
		homePage.navigateToPageByPageNumber("5");
		homePage.sleepInSecond(2);
		// verify page Navigate success
		Assert.assertTrue(homePage.isPageActivedByByPageNumber("5"));

		homePage.navigateToPageByPageNumber("12");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPageActivedByByPageNumber("12"));

		homePage.navigateToPageByPageNumber("22");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPageActivedByByPageNumber("22"));
	}

	// @Test
	public void TC_03_Icon() {
		homePage.refreshCurrentPage(driver);

		// delete by country
		homePage.clickToDeleteIconByCountryName("Afghanistan");
		homePage.sleepInSecond(2);

		homePage.clickToDeleteIconByCountryName("Angola");
		homePage.sleepInSecond(2);

		homePage.clickToDeleteIconByCountryName("Argentina");
		homePage.sleepInSecond(2);

		// edit by country
		homePage.refreshCurrentPage(driver);

		homePage.clickToEditconByCountryName("Argentina");
		homePage.sleepInSecond(2);

		homePage.refreshCurrentPage(driver);

		homePage.clickToEditconByCountryName("Afghanistan");
		homePage.sleepInSecond(2);
	}

	//@Test
	public void TC_04_Displayed() {
		Assert.assertTrue(homePage.isAllInforDisplayed("276880", "Angola", "276472", "553353"));
		Assert.assertTrue(homePage.isAllInforDisplayed("283821", "Algeria", "295140", "578961"));

		// Assert.assertTrue(homePage.isAllInforDisplayed("283821", "Algeria", "295140", "553353"));
	}

	@Test
	public void TC_05_Index() {
		homePage.openUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		
		// cột contact persion - dòng thứ 2 - giá trị nhập vào là Automation test
		homePage.inputToTextboxByColumnNameAtRowNumber("Contact Person", "2", "Automation test");
		homePage.sleepInSecond(1);
		
		// cột Company - dòng thứ 1 - giá trị nhập vào là Việt Nam
		homePage.inputToTextboxByColumnNameAtRowNumber("Company", "1", "Việt Nam");
		homePage.sleepInSecond(1);
		
		// cột Order Placed - dòng thứ 3 - giá trị nhập vào là 100
		homePage.inputToTextboxByColumnNameAtRowNumber("Order Placed", "3", "100");
		homePage.sleepInSecond(1);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Post-condition - Close browser");
		removeBrowserDriver();
	}

}
