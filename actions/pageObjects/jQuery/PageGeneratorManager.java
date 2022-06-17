package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObjet gethomePage(WebDriver driver) {
		return new HomePageObjet(driver);
	}
	
	public static UploadPageObject getUploadPage(WebDriver driver) {
		return new UploadPageObject(driver);
	}
}
