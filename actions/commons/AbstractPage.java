package commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.server.handler.GetElementAttribute;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.bankGuru.DepositPageObject;
import pageObjects.bankGuru.EditCustomerPageObject;
import pageObjects.bankGuru.FundTransferPageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageUI.bankGuru.AbstractPageUI;
import pageUI.bankGuru.DepositPageUI;
import pageUI.bankGuru.EditCustomerPageUI;
import pageUI.bankGuru.FundTransferPageUI;
import pageUI.bankGuru.MainPageUI;
import pageUI.bankGuru.NewCustomerPageUI;

//public abstract class AbstractPage { // tạm thời remove 
public abstract class AbstractPage {
	// Phần 1: Selenium Web browser
	// Khai báo thư viện
	private WebDriverWait explicitWait; // khai báo thư viện wait
	private Alert alert; // khai báo thư viện alert
	private Select select; // khai báo thư viện select
	private JavascriptExecutor jsExecutor; // khai báo JavascriptExecutor
	private Actions action; // khai báo thư viện user action
	private WebElement element; // khai báo biến WebElement
	private List<WebElement> elements; //
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;
	private String osName = System.getProperty("os.name");

	protected final Log log;// khai báo

	public AbstractPage() {
		log = LogFactory.getLog(getClass()); // đây là đoạn khởi tạo
	}

	// 1.Hàm open url -> Có tham số bắt buộc WebDriver driver
	public void openUrl(WebDriver driver, String url) {
		driver.get(url); // ví dụ: https://facebook.com
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	}

	// 2. Hàm gettitle:
	public String getpageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	// 3. Hàm getCurentUrl
	public String getCurrentPageUrl(WebDriver driver) {
		System.out.println("-----Driver at method----------");
		System.out.println(driver.toString());
		return driver.getCurrentUrl();
	}

	// 4. Hàm getPageSource
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	// 5. Hàm back
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	// 6. Hàm refresh
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	// 7. Hàm forward
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	// 8. Hàm Alert
	// 8a: wait alertIsPresent
	/*
	 * Muốn dùng hàm waitToAlertPresence của Alert này phải dùng thư viện WebDriverWait của selenium (private WebDriverWait explicitWait;)
	 */
	public void waitToAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	// 8b: accep Alert
	public void acceptAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.accept();
	}

	// 8c: Cancel tAlert
	public void cancelAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	// 8d: sendkey Alert
	public void sendkeyTolAlert(WebDriver driver, String value) {
		alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}

	// 8e: getText Alert
	public String getTextInlAlert(WebDriver driver) {
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	// 9. Hàm window
	// 9a: switchToWindowByID ( dùng trường hợp cho 2 cửa sổ > 2 cửa sổ chạy không đúng)
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	// 9b: switchToWindowByTitle ( > 2 cửa sổ dùng title)
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	// 9c: closeAllWindowsWithoutParent ( đóng hết tất cả cửa sổ ngoại trừ trang parent)
	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

// -------------------------------------------------------------------------------------------------------------------
	// Phần 2: Selenium Web Element
	// 1. xpath
	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	// 1a1. Dynamic Locator RestParameter (DynamicLocator & RestParameter)
	public String castRestParameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	// 2. findElement(s)
	public WebElement find(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> finds(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	// 3.clickToElement
	public void clickToElement(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		// driver.findElement(By.xpath(locator)).click(); // dài dòng
		if (driver.toString().toLowerCase().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(3);
		} else {
			find(driver, locator).click();// ngắn gọn
		}
	}

	// 3a1. clickToElement RestParameter (DynamicLocator & RestParameter)
	public void clickToElement(WebDriver driver, String locator, String... values) {
		highlightElement(driver, castRestParameter(locator, values));
		if (driver.toString().toLowerCase().contains("internet explorer")) {
			clickToElementByJS(driver, castRestParameter(locator, values));
			sleepInSecond(3);
		} else {
			find(driver, castRestParameter(locator, values)).click(); // ngắn gọn
		}
	}

	// 4. SendKeyToElement
	// textbox, textarea, dropdown list (editable), uploadfile
	public void SendkeyToElement(WebDriver driver, String locator, String value) {
		highlightElement(driver, locator);
		find(driver, locator).clear(); // Trước khi sendkey cần phải clear trả về empty
		find(driver, locator).sendKeys(value);
	}

	// 4a1. SendKeyToElement RestParameter (DynamicLocator & RestParameter)
	public void SendkeyToElement(WebDriver driver, String locator, String value, String... values) {
		highlightElement(driver, castRestParameter(locator, values));
		find(driver, castRestParameter(locator, values)).clear(); // Trước khi sendkey cần phải clear trả về empty
		find(driver, castRestParameter(locator, values)).sendKeys(value);
	}

	// 5. selectItemInDropdown
	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
		select = new Select(find(driver, locator));
		select.selectByVisibleText(itemValue);
	}

	// 6. getSelectItemInDropdown
	public String getFirstSelectItemInDropdown(WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	// 7. isDropdownMultiple
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(find(driver, locator));
		return select.isMultiple();
	}

	// 8. selectItemInCustomDropdown
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		// step 1 - Click vào thẻ (cha) để xổ ra tất cả các item con
		find(driver, parentLocator).click();
		sleepInSecond(1);

		// step 2 - Chờ cho tất cả các item con được load ra trong dom
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childLocator)));
		// Đưa tất cả các item trong dropdown vào 1 list để kiểm tra
		List<WebElement> allItems = finds(driver, childLocator);

		// step 3 - Chạy qua tất cả các giá trị đang có trong list
		for (WebElement item : allItems) {

			// step 4 - Kiểm tra xem text của các giá trị có item nào bằng vs text mong muốn ko
			if (item.getText().equals(expectedItem)) {

				// step 5 - Scroll xuống đến đúng item này
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				// step 6 - Click vào cái item này
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 9a. getAttributeValue
	public String GetElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return find(driver, locator).getAttribute(attributeName);
	}

	// 9b. getAttributeValue RestParameter (DynamicLocator & RestParameter)
	public String GetElementAttributeValue(WebDriver driver, String locator, String attributeName, String... values) {
		return find(driver, castRestParameter(locator, values)).getAttribute(attributeName);
	}

	// 10. getElementText
	public String getElementText(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		return find(driver, locator).getText().trim();
	}

	// 10a1. getElementText RestParameter (DynamicLocator & RestParameter)
	public String getElementText(WebDriver driver, String locator, String... values) {
		highlightElement(driver, castRestParameter(locator, values));
		return find(driver, castRestParameter(locator, values)).getText().trim();
	}

	// 11. countElementNumber(findElements) => Lấy ra số lượng elements giống nhau
	public int countElementSize(WebDriver driver, String locator) {
		return finds(driver, locator).size();
	}

	// 11a1. countElementNumber RestParameter (DynamicLocator & RestParameter)
	public int countElementSize(WebDriver driver, String locator, String... values) {
		return finds(driver, castRestParameter(locator, values)).size();
	}

	// 12. checkTheCheckBox
	public void checkTheCheckBox(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		// cầm kiểm tra trc nếu chọn rồi thì thôi ko chọn thì click, phủ định dấu ! điềuk kiện này lại thì chưa chọn
		if (!find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}

	// 13. unCheckTheCheckBox
	public void unCheckTheCheckBox(WebDriver driver, String locator) {
		// thằng nào chọn rồi mình click cho mất chọn
		if (find(driver, locator).isSelected()) {
			find(driver, locator).click();
		}
	}

	// 14a1. isControlDisplayed
	public boolean isControlDisplayed(WebDriver driver, String locator) {
		try {
			return find(driver, locator).isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Element is not displayed with message: " + e.getMessage());
			return false;
		}
	}

	// 14a2. isControlDisplayed RestParameter (DynamicLocator & RestParameter)
	public boolean isControlDisplayed(WebDriver driver, String locator, String... values) {
		return find(driver, castRestParameter(locator, values)).isDisplayed();
	}

	// 14b1. isControlUnDisplayed
	public boolean isControlUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeOut);
		elements = finds(driver, locator);
		overrideGlobalTimeout(driver, longTimeOut);

		if (elements.size() == 0) {
			// System.out.println("Element not in DOM");
			log.info("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() >= 0 && !elements.get(0).isDisplayed()) {
			// System.out.println("Element in DOM but not visible/ displayed");
			log.info("Element in DOM and undisplayed on UI");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			log.info("Element in DOM and displayed on UI");
			return false;
		}
	}

	// 14b2. isControlUnDisplayed RestParameter (DynamicLocator & RestParameter)
	public boolean isControlUndisplayed(WebDriver driver, String locator, String... values) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeOut);
		elements = finds(driver, castRestParameter(locator, values));
		overrideGlobalTimeout(driver, longTimeOut);

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() >= 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	// 15. isControlEnabled
	public boolean isControlEnabled(WebDriver driver, String locator) {
		return find(driver, locator).isEnabled();
	}

	// 16. isControlSelected
	public boolean isControlSelected(WebDriver driver, String locator) {
		return find(driver, locator).isSelected();
	}

	// 17. Frame/IFrame
	// 17a: switchToFrame/IFrame
	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(find(driver, locator));
	}

	// 17b: switchToDefaultContent ( back default content)
	public void switchToDefaultPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	// 18. User Actions (thao tác chuột và bàn phím)
	// các hàm actions bắt buộc phải có sự kiện perform
	// 18a. doubleClickToElement
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(find(driver, locator)).perform();
	}

	// 18b. rightClickToElement
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(find(driver, locator)).perform();
	}

	// 18c. hoverMouseToElement-> moveToElement ( hove chuột dùng nhiều nhất)
	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(find(driver, locator)).perform();
	}

	// 18d. dragAndDrop
	public void dragAndDropElement(WebDriver driver, String sourcelocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(find(driver, sourcelocator), find(driver, targetLocator)).perform();
	}

	// 18e. sendKeyboardToElement
	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, locator), key).perform();
	}

	// 19. upload file RestParameter (DynamicLocator & RestParameter)
	public String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}

	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0 || osName.toLowerCase().indexOf("aix") >= 0);
	}

	public boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		// Lấy ra đường dẫn của thư mục chứa file
		String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
		String fullFileName = "";

		// Duyệt qua từng file để nối đường dẫn tất cả các file vào
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		SendkeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);
	}
//-------------------- thêm 
	public boolean areFileUploadedDisplay(WebDriver driver, String...fileNames) {
		boolean status = false;
		int number = fileNames.length;
		waitToElementInvisible(driver, AbstractPageUI.MEDIA_PROGRESS_BAR_ICON);
		sleepInSecond(5);
		elements = finds(driver, AbstractPageUI.ALL_UPLOAD_IMAGE);
		List<String> imageValues = new ArrayList<String>();
		
		int i = 0;
		for (WebElement image : elements) {
			System.out.println(image.getAttribute("src"));
			imageValues.add(image.getAttribute("src"));
			i++;
			if(i == number) {
				break;
			}
		}
		for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();
			for (i = 0; i < imageValues.size(); i++) {
				if (!imageValues.get(i).contains(fileName)) {
					status = false;
					if(i == imageValues.size() -1) {
						return status;					
					}
				} else {
					status = true;
					break;
				}
			}
		}
		return false;	
	}
	
	
	// 20. JavascriptExecutor
	// 20a: executeForBrowser
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	// 20b: verifyTextInInnerText
	public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	// 20c: scrollToBottomPage
	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	// 20d: navigateToUrlByJS
	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	// 20e:highlightElement
	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	// 20f:clickToElementByJS
	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	// 20g:scrollToElementByJS
	public void scrollToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// 20h:sendkeyToElementByJS
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	// 20i:removeAttributeInDOM
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		sleepInSecond(1);
	}

	// 20i1:removeAttributeInDOM RestParameter (DynamicLocator & RestParameter)
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, castRestParameter(locator, values));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		sleepInSecond(1);
	}

	// 20j: checkImageLoaded
	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = find(driver, locator);
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0", element);
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	// 21.Wait
	// 21a. waitToElementPresence
	public void waitToElementPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut); // do bị lặp con số 40 nhiều lần cần khai báo biến
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
	}

	// 21b. waitToElementVisible
	public void waitToElementVisible(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, longTimeOut); // do bị lặp con số 40 nhiều lần cần khai báo biến
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
		} catch (Exception e) {
			log.info("Waiting for element visible with message: " + e.getMessage());
		}
	}

	// 21b1. waitToElementVisible RestParameter (DynamicLocator & RestParameter)
	public void waitToElementVisible(WebDriver driver, String locator, String... values) {
		try {
			explicitWait = new WebDriverWait(driver, longTimeOut); // do bị lặp con số 40 nhiều lần cần khai báo biến
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castRestParameter(locator, values))));
		} catch (Exception e) {
			log.info("Waiting for element visible with message: " + e.getMessage());
		}
	}

	// 21c. waitToElementInvisible
	public void waitToElementInvisible(WebDriver driver, String locator) {
		try {
			// chỉ có giá trị khi element có trong DOM
			explicitWait = new WebDriverWait(driver, longTimeOut); // do bị lặp con số 40 nhiều lần cần khai báo biến
			overrideGlobalTimeout(driver, shortTimeOut);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
			overrideGlobalTimeout(driver, longTimeOut);
		} catch (Exception e) {
			log.info("Waiting for element invisible with message: " + e.getMessage());
		}
	}

	// 21d. waitToElementClickable
	public void waitToElementClickable(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, longTimeOut); // do bị lặp con số 40 nhiều lần cần khai báo biến
			explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
		} catch (Exception e) {
			log.info("Waiting for element clickable with message: " + e.getMessage());
		}
	}

	// 21d1. waitToElementClickable RestParameter (DynamicLocator & RestParameter)
	public void waitToElementClickable(WebDriver driver, String locator, String... values) {
		try {
			explicitWait = new WebDriverWait(driver, longTimeOut); // do bị lặp con số 40 nhiều lần cần khai báo biến
			explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castRestParameter(locator, values))));
		} catch (Exception e) {
			log.info("Waiting for element clickable with message: " + e.getMessage());
		}
	}

	// 21e. wait alertIsPresent đã viết trên phần 1

	// 22a. Sort Ascending (String) cách 1
	public boolean isDataStringSortedAscending(WebDriver driver, String locator) {
		// khai báo 1 Array List
		ArrayList<String> arrayList = new ArrayList<>();

		// Tìm tất cả element matching vs điều kiện (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		// lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println("----------- Dữ liệu trên UI:-------");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// copy qua 1 array list mới để SORT trong code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println("----------- Dữ liệu đã SORT ASC trong code:-------");
		for (String name : sortedList) {
			System.out.println(name);
		}

		// verify 2 array bằng nhau - nếu dữ liệu sort trên UI không chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	// 22 a1 Sort java 8 cách 2
	public boolean isDataSortedAscending(WebDriver driver, String locator) {
		List<WebElement> elementLists = driver.findElements(By.xpath(locator));
		List<String> names = elementLists.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<String>(names);
		Collections.sort(sortedNames);
		return names.equals(sortedNames);
	}

	// 22b. Sort Descending (String)
	public boolean isDataStringSortedDescending(WebDriver driver, String locator) {
		// khai báo 1 Array List (DEV)
		ArrayList<String> arrayList = new ArrayList<>();

		// Tìm tất cả element matching vs điều kiện (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		// lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println("----------- Dữ liệu trên UI:-------");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// copy qua 1 array list mới để SORT trong code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println("----------- Dữ liệu đã SORT ASC trong code:-------");
		for (String name : sortedList) {
			System.out.println(name);
		}

		// Reverse data để sort DESC (Dùng 1 trong 2 cách bên dưới)
		Collections.reverse(sortedList);

		// Collections.sort(arrayList, Collections.reverseOrder());
		System.out.println("----------- Dữ liệu đã SORT DESC trong code:-------");
		for (String name : sortedList) {
			System.out.println(name);
		}

		// verify 2 array bằng nhau - nếu dữ liệu sort trên UI không chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	// 22c. Sort Ascending (Float)
	public boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
		// khai báo 1 Array List
		ArrayList<Float> arrayList = new ArrayList<Float>();

		// Tìm tất cả element matching vs điều kiện (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		// lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println("----------- Dữ liệu trên UI:-------");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		// copy qua 1 array list mới để SORT trong code
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println("----------- Dữ liệu đã SORT ASC trong code:-------");
		for (Float name : sortedList) {
			System.out.println(name);
		}

		// verify 2 array bằng nhau - nếu dữ liệu sort trên UI không chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	// 22d. Sort Descending (Float)
	public boolean isDataFloatSortedDescending(WebDriver driver, String locator) {
		// khai báo 1 Array List
		ArrayList<Float> arrayList = new ArrayList<Float>();

		// Tìm tất cả element matching vs điều kiện (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		// lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println("----------- Dữ liệu trên UI:-------");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		// copy qua 1 array list mới để SORT trong code
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println("----------- Dữ liệu đã SORT ASC trong code:-------");
		for (Float name : sortedList) {
			System.out.println(name);
		}

		// Thực hiện SORT DESC
		Collections.reverse(sortedList);

		System.out.println("----------- Dữ liệu đã SORT DESC trong code:-------");
		for (Float name : sortedList) {
			System.out.println(name);
		}

		// verify 2 array bằng nhau - nếu dữ liệu sort trên UI không chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	// 22e. Sort Ascending (Date)
	public boolean isDataDateSortedAscending(WebDriver driver, String locator) {
		// khai báo 1 Array List
		ArrayList<Date> arrayList = new ArrayList<Date>();

		// Tìm tất cả element matching vs điều kiện (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		// lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(convertStringToDate(element.getText()));
		}

		System.out.println("----------- Dữ liệu trên UI:-------");
		for (Date name : arrayList) {
			System.out.println(name);
		}

		// copy qua 1 array list mới để SORT trong code
		ArrayList<Date> sortedList = new ArrayList<Date>();
		for (Date child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println("----------- Dữ liệu đã SORT ASC trong code:-------");
		for (Date name : sortedList) {
			System.out.println(name);
		}

		// verify 2 array bằng nhau - nếu dữ liệu sort trên UI không chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	// 22f. Sort Desscending (Date)
	public boolean isDataDateSortedDescending(WebDriver driver, String locator) {
		// khai báo 1 Array List
		ArrayList<Date> arrayList = new ArrayList<Date>();

		// Tìm tất cả element matching vs điều kiện (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));

		// lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(convertStringToDate(element.getText()));
		}

		System.out.println("----------- Dữ liệu trên UI:-------");
		for (Date name : arrayList) {
			System.out.println(name);
		}

		// copy qua 1 array list mới để SORT trong code
		ArrayList<Date> sortedList = new ArrayList<Date>();
		for (Date child : arrayList) {
			sortedList.add(child);
		}

		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println("----------- Dữ liệu đã SORT ASC trong code:-------");
		for (Date name : sortedList) {
			System.out.println(name);
		}

		// Thực hiện SORT DESC
		Collections.reverse(sortedList);

		System.out.println("----------- Dữ liệu đã SORT DESC trong code:-------");
		for (Date name : sortedList) {
			System.out.println(name);
		}

		// verify 2 array bằng nhau - nếu dữ liệu sort trên UI không chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	public Date convertStringToDate(String dateInString) {
		dateInString = dateInString.replace(",", "");
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// Phần 3: Open Dynamic Page Menu: tối ưu hàm dùng dynamic locator
	/* cách 1 số lượng page ít tầm 1 ->20 page */
	public AbstractPage openMenuPageByName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);

		// dùng if- else
		if (pageName.equals("New Customer")) {
			return PageGeneratorManager.getNewCustomerPage(driver);
		} else if (pageName.equals("Edit Customer")) {
			return PageGeneratorManager.getEditCustomerPage(driver);
		} else if (pageName.equals("Deposit")) {
			return PageGeneratorManager.getDepositPage(driver);
		} else if (pageName.equals("Fund Transfer")) {
			return PageGeneratorManager.getFundTransferPage(driver);
		} else {
			throw new RuntimeException();
		}

		// dùng switch-Case:
//			switch (pageName) {
//			case "New Customer":
//				return PageGeneratorManager.getNewCustomerPage(driver);
//			case "Edit Customer":
//				return PageGeneratorManager.getEditCustomerPage(driver);
//			case "Deposit":
//				return PageGeneratorManager.getDepositPage(driver);
//			case "Fund Transfer":	
//				return PageGeneratorManager.getFundTransferPage(driver);
//			default:
//				throw new RuntimeException();
//			}
	}

	/* cách 2: số lượng page nhiều > hơn 20 page */
	public void openMenuPagesByName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
		if (driver.toString().toLowerCase().contains("internet explorer")) {
			openUrl(driver, GetElementAttributeValue(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, "href", pageName)); // cách 2
		} else {
			clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName); // cách 1:
		}
		if (driver.toString().contains("safari")) {
			sleepInSecond(2);
		}
//			public static final String NEW_CUSTOMER_LINK = "//a[text()='New Customer']";
//			public static final String EDIT_CUSTOMER_PAGE_LINK = "//a[text()='Edit Customer']";	
//			public static final String DEPOSIT_PAGE_LINK  = "//a[text()='Deposit']";	
//			public static final String FUND_TRANSFER_PAGE_LINK = "//a[text()='Fund Transfer']";		
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	// ----------------------------------------------------------------------------------------------------------------------
	// Phần 4: Pattern Object
	// 1. inputToTextboxByNameAttribute
	public void inputToTextboxByNameAttribute(WebDriver driver, String nameAttribute, String value) {
		// lưu ý thứ tự: WebDriver driver, String locator, String value, String... values
		// luu y textbox textarea
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, nameAttribute);
		if (nameAttribute.equals("dob")) {
			removeAttributeInDOM(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, "type", nameAttribute);
		}
		SendkeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, value, nameAttribute);
	}

	// 2. inputToTextAreaByNameAttribute
	public void inputToTextAreaByNameAttribute(WebDriver driver, String nameAttribute, String value) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BY_NAME, nameAttribute);
		// lưu ý thứ tự: WebDriver driver, String locator, String value, String... values
		// luu y textbox textarea
		SendkeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BY_NAME, value, nameAttribute);
	}

	// 3. clickToRadioButtonByValue
	public void clickToRadioButtonByValue(WebDriver driver, String radioValue) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON_BY_VALUE, radioValue);
		clickToElement(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON_BY_VALUE, radioValue);
	}

	// 4. clickToButtonByValue
	public void clickToButtonByValue(WebDriver driver, String buttonValue) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_BUTTON_BY_VALUE, buttonValue);
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON_BY_VALUE, buttonValue);
	}

	// 5. Check displayed
	public boolean isPageHeaderDisplayedByText(WebDriver driver, String headerTextValue) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_HEADER_BY_TEXT, headerTextValue);
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_PAGE_HEADER_BY_TEXT, headerTextValue);
	}

	// 6. getRowValueByRowName
	public String getRowValueByRowName(WebDriver driver, String rowName) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_BY_ROW_NAME, rowName);
		return getElementText(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_BY_ROW_NAME, rowName);
	}

	// ----------------------------------------------------------------------------------------------------------------------
	// open Bank Guru page: chưa tối ưu
	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
		clickToElement(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
		// cách 2 tạo đối tượng vào trong chính cái hàm này luôn
		// return new NewCustomerPageObject(driver); // Khởi tạo newCustomerPage
		// cách 3
		return PageGeneratorManager.getNewCustomerPage(driver);
	}

	public DepositPageObject openDepositPage(WebDriver driver) {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, AbstractPageUI.DEPOSIT_PAGE_LINK);
		clickToElement(driver, AbstractPageUI.DEPOSIT_PAGE_LINK);
		return PageGeneratorManager.getDepositPage(driver);
	}

	public EditCustomerPageObject openEditCustomerPage(WebDriver driver) {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, AbstractPageUI.EDIT_CUSTOMER_PAGE_LINK);
		clickToElement(driver, AbstractPageUI.EDIT_CUSTOMER_PAGE_LINK);
		return PageGeneratorManager.getEditCustomerPage(driver);
	}

	public FundTransferPageObject openFundTransferPage(WebDriver driver) {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, AbstractPageUI.FUND_TRANSFER_PAGE_LINK);
		clickToElement(driver, AbstractPageUI.FUND_TRANSFER_PAGE_LINK);
		return PageGeneratorManager.getFundTransferPage(driver);
	}

	public LoginPageObject ClickToLogOutLink(WebDriver driver) {
		// TODO Auto-generated method stub
		scrollToBottomPage(driver);
		sleepInSecond(2);
		waitToElementClickable(driver, AbstractPageUI.LOGOUT_LINK);
		// clickToElement(driver, AbstractPageUI.LOGOUT_LINK);
		clickToElementByJS(driver, AbstractPageUI.LOGOUT_LINK);
		waitToAlertPresence(driver);
		acceptAlert(driver);
		// được new lên lần
		// cách 2 tạo đối tượng vào trong chính cái hàm này luôn
		// return new LoginPageObject(driver); // Khởi tạo loginPage
		// cách 3
		return PageGeneratorManager.getLoginPage(driver);
	}
}