package com.jquery.upload;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import commons.AbstractTest;
import pageObjects.jQuery.PageGeneratorManager;
import pageObjects.jQuery.UploadPageObject;

public class Level_12b_Upload_File extends AbstractTest {

	WebDriver driver;
	UploadPageObject uploadPage; // cach 2: private UploadPageObject uploadPage;

	String audiName = "audi.PNG";
	String hondaName = "honda.PNG";
	String toyotaName = "toyota.PNG";
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		uploadPage = PageGeneratorManager.getUploadPage(driver);

		uploadPage.openUrl(driver, "http://blueimp.github.io/jQuery-File-Upload/");
	}

	@Test
	public void TC_01_Upload_1_File_Per_Time() {		
		uploadPage.uploadMultipleFiles(driver, audiName);
		uploadPage.sleepInSecond(3);
			
		verifyTrue(uploadPage.isFileLoaded(audiName));	
		uploadPage.clickToStartButton(audiName);
		verifyTrue(uploadPage.isFileUploadedSuccess(audiName));	
	}

	@Test
	public void TC_02_Upload_Multiple_File_Per_Time() {			
		uploadPage.refreshCurrentPage(driver);	
		
		uploadPage.uploadMultipleFiles(driver, audiName, hondaName, toyotaName);
		uploadPage.sleepInSecond(3);
		
		verifyTrue(uploadPage.isFileLoaded(audiName));	
		uploadPage.clickToStartButton(audiName);
		verifyTrue(uploadPage.isFileUploadedSuccess(audiName));	
		
		verifyTrue(uploadPage.isFileLoaded(hondaName));	
		uploadPage.clickToStartButton(hondaName);
		verifyTrue(uploadPage.isFileUploadedSuccess(hondaName));	
		
		verifyTrue(uploadPage.isFileLoaded(toyotaName));	
		uploadPage.clickToStartButton(toyotaName);
		verifyTrue(uploadPage.isFileUploadedSuccess(toyotaName));	
		
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Post-condition - Close browser");
		removeBrowserDriver();
	}

}
