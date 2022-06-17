package pageFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	private WebDriverWait explicitWait; // khai báo thư viện wait
	private WebElement element; // khai báo biến WebElement
	private long longTimeOut = 40;
	
	// 3. Hàm getCurentUrl
	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	// 1.Hàm open url -> Có tham số bắt buộc WebDriver driver
	public void openUrl(WebDriver driver, String url) {
		driver.get(url); // ví dụ: https://facebook.com
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	}

	// 21b. waitToElementVisible
	public void waitToElementVisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeOut); // do bị lặp con số 40 nhiều lần cần khai báo biến
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	// 21d. waitToElementClickable
	public void waitToElementClickable(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeOut); // do bị lặp con số 40 nhiều lần cần khai báo biến
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// 3.clickToElement
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();// ngắn gọn
	}

	// 4. SendKeyToElement
	public void SendkeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear(); // Trước khi sendkey cần phải clear trả về empty
		element.sendKeys(value);
	}

	// 14. isControlDisplayed
	public boolean isControlDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}

	// 10. getElementText
	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
}
