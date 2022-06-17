package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	// tạo Constructor: 
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// tạo ra sự liên kết giữa value trong findby vs webElement
	}

	// định nghĩa locator dùng từ khóa như sau:
	// @FindBy/FindBys/FindAll

	// Cách 1:
//	@FindBy(how = How.XPATH, using = "//a[text()='here']")
//	private WebElement hereLink;

	// Cách 2: viết khác
	@FindBy(xpath = "//a[text()='here']")
	private WebElement here_Link;

	@FindBy(name = "uid")
	private WebElement userIDTextBox;

	@FindBy(name = "password")
	private WebElement passwordTextBox;

	@FindBy(css = "input[name='btnLogin']")
	private WebElement loginButton;

	@FindBy(css = "form[name='frmLogin']")
	private WebElement loginFrom;
	
	public void clickToHereLink() {
		waitToElementClickable(driver, here_Link);
		clickToElement(driver, here_Link);
	}

	public String getLoginPageUrl() {
		// TODO Auto-generated method stub
		return getCurrentPageUrl(driver);
	}

	public void inputToUserIDTextbox(String userID) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, userIDTextBox);
		SendkeyToElement(driver, userIDTextBox, userID);	
	}

	public void inputToPasswordTextbox(String password) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, passwordTextBox);
		SendkeyToElement(driver, passwordTextBox, password);
		
	}

	public void clickToLoginButton() {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);	
	}
	
	public boolean isLoginFormDisplayed() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, loginFrom);
		return isControlDisplayed(driver, loginFrom);
	}
}
