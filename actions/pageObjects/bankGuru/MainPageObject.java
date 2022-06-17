package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import io.qameta.allure.Step;
import pageUI.bankGuru.MainPageUI;

public class MainPageObject extends AbstractPage {
	WebDriver driver;
	
	public MainPageObject(WebDriver driver) {
		// super();
		this.driver = driver;
		System.out.println("-----Driver at MainPage ----------");
		System.out.println(driver.toString());
	}
	
	@Step("Get Welcome Message at Main Page ")
	public String getWelcomeMessageText() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, MainPageUI.WELCOME_MESSAGE_TEXT);
		return getElementText(driver, MainPageUI.WELCOME_MESSAGE_TEXT);
	}
	
	public boolean isWelcomeMessageDisplayed() {
		waitToElementVisible(driver, MainPageUI.WELCOME_MESSAGE_DISPLAYED);
		return isControlDisplayed(driver, MainPageUI.WELCOME_MESSAGE_DISPLAYED);
	}

	public boolean isLoginFormUndisplayed() {	
		// TODO Auto-generated method stub
		waitToElementInvisible(driver, MainPageUI.LOGIN_FORM);	
		return isControlUndisplayed(driver, MainPageUI.LOGIN_FORM);
	}
}