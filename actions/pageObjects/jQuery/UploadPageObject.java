package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

import pageUI.jQuery.UploadPageUI;

public class UploadPageObject extends AbstractPage {
	WebDriver driver;

	public UploadPageObject(WebDriver driver) {
		// super();
		this.driver = driver;
	}

	public boolean isFileLoaded(String fileName) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, UploadPageUI.DYNAMIC_IMAGE_TEXT, fileName);	
		return isControlDisplayed(driver, UploadPageUI.DYNAMIC_IMAGE_TEXT, fileName);
	}

	public void clickToStartButton(String fileName) {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, UploadPageUI.DYNAMIC_IMAGE_START_BUTTON, fileName);
		clickToElement(driver, UploadPageUI.DYNAMIC_IMAGE_START_BUTTON, fileName);
		
	}

	public boolean isFileUploadedSuccess(String fileName) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, UploadPageUI.DYNAMIC_IMAGE_LINK, fileName);	
		return isControlDisplayed(driver, UploadPageUI.DYNAMIC_IMAGE_LINK, fileName);
	}
}
