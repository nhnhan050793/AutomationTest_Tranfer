package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class FundTransferPageObject extends AbstractPage {
	WebDriver driver;

	public FundTransferPageObject(WebDriver driver) {
		//super();
		this.driver = driver;
	}
}
