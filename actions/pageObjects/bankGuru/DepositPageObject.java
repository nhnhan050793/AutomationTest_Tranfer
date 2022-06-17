package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DepositPageObject extends AbstractPage {
	WebDriver driver;

	public DepositPageObject(WebDriver driver) {
		//super();
		this.driver = driver;
	}
}
