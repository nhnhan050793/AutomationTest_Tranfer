package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageObject extends AbstractPage {
	WebDriver driver;

	public MainPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//marquee[@class ='heading3']")
	private WebElement welcomeMessageText;
	
	@FindBy(xpath = "//a[text()='New Customer']")
	private WebElement newCustomerLink;
	
	public String getWelcomeManagerText() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, welcomeMessageText);
		return getElementText(driver, welcomeMessageText);
	}
	
	public void openNewCustomerPage() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, newCustomerLink);
		clickToElement(driver, newCustomerLink);
	}
}
