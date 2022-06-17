package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "emailid")
	private WebElement emailTextBox;

	@FindBy(name = "btnLogin")
	private WebElement submitButton;

	@FindBy(xpath = "//td[text()='User ID :']/following-sibling::td")
	private WebElement userIDText;

	@FindBy(xpath = "//td[text()='Password :']/following-sibling::td")
	private WebElement passwordText;
	
	public void inputToEmailTextBox(String emailAddress) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, emailTextBox);
		SendkeyToElement(driver, emailTextBox, emailAddress);	
	}

	public void clickToSubmitButton() {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, submitButton);
		clickToElement(driver, submitButton);
	}

	public String getUserIDText() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, userIDText);
		return getElementText(driver, userIDText);
	}

	public String getPasswordText() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, passwordText);
		return getElementText(driver, passwordText);
	}

	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
		
	}
}
