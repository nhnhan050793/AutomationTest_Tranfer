package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankGuru.NewCustomerPageUI;

public class NewCustomerPageObject extends AbstractPage{
	WebDriver driver;
	
	public NewCustomerPageObject(WebDriver driver) {
		
		// super();
		this.driver = driver;
		System.out.println("-----Driver at NewCustomer Page ----------");
		System.out.println(driver.toString());
	}
	
	public void inputToNameTextBox(String name) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.NAME_TEXTBOX);
		SendkeyToElement(driver, NewCustomerPageUI.NAME_TEXTBOX, name);			
	}

	public void inputToDateOfBirthTextBox(String dateOfBirth) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX);
		removeAttributeInDOM(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, "type");
		SendkeyToElement(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);			
	}

	public void inputToAddressTextArea(String address) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXTAREA);
		SendkeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXTAREA, address);				
	}

	public void inputToCityTextBox(String city) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		SendkeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, city);				
	}

	public void inputToStateTextBox(String state) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		SendkeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, state);
	}

	public void inputToPinTextBox(String pin) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		SendkeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, pin);			
	}

	public void inputToPhoneTextBox(String phone) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.PHONE_TEXTBOX);
		SendkeyToElement(driver, NewCustomerPageUI.PHONE_TEXTBOX, phone);		
	}

	public void inputToEmailTextBox(String email) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		SendkeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, email);	
	}

	public void inputToPasswordTextBox(String password) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.PASSWORD_TEXTBOX);
		SendkeyToElement(driver, NewCustomerPageUI.PASSWORD_TEXTBOX, password);		
	}

	public void ClickToSubmitButton() {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		sleepInSecond(2);
	}

	public String getSuccessMessage() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, NewCustomerPageUI.NEW_SUCESS_MESSAGE);
		return getElementText(driver, NewCustomerPageUI.NEW_SUCESS_MESSAGE);
	}
}
