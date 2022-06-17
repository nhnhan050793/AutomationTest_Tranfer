package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUI.bankGuru.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	WebDriverWait expliciWait;

	// Constructor: hàm khởi tạo ( hoặc hàm xây dựng)
	/*
	 * có nhiệm vụ: 1. sẽ được gọi đầu tiên khi cái class này được khởi tạo 
	 * 2. hàm khởi tạo ko có kiểu trả về 
	 * 3. Hàm khởi tạo bắt buộc trùng tên class 
	 * 4. 1 class có thể có nhiều hàm khởi tạo ũng được lưu ý: 
	 * nhiều hàm khởi tạo: khác nhau bởi số lượng tham số hoặc kiểu dữ liệu. ví dụ
	 */
	// tính đa hình/ đa hình thái:
	// 1. khàm khởi tạo có nhiều hình thái khác nhau
	public LoginPageObject(WebDriver driver) {
		// super();
		this.driver = driver;
		//System.out.println("-----Driver at Login Page ----------");
		System.out.println("---- Driver at Login Page  = " + driver.toString() + "------");
		System.out.println(driver.toString());
	}

//	public LoginPageObject(WebDriverWait expliciWait) {
//		this.expliciWait = expliciWait;
//	}
//
//	public LoginPageObject(WebDriver driver, WebDriverWait expliciWait) {
//		this.driver = driver;
//		this.expliciWait = expliciWait;
//	}

	// Actions: chỉ thuộc về login page 
	@Step("click To 'Here' Link")
	public RegisterPageObject clickToHereLink() {
		waitToElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		if(driver.toString().contains("safari")) {
			sleepInSecond(2);
		}
	// cách 2 tạo đối tượng vào trong chính cái hàm này luôn 
	// return new RegisterPageObject(driver);	// Khởi tạo RegisterPage
	// cách 3:
		return PageGeneratorManager.getRegisterPage(driver);
	}

	@Step("Get Login Page Url")
	public String getLoginPageUrl() {
		// TODO Auto-generated method stub
		return getCurrentPageUrl(driver);
	}

	@Step("Input To User ID Textbox with value: {0}")
	public void inputToUserIDTextbox(String userID) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, LoginPageUI.USER_TEXTBOX);
		SendkeyToElement(driver, LoginPageUI.USER_TEXTBOX, userID);
	}

	@Step("Input To Password Textbox with value: {0}")
	public void inputToPasswordTextbox(String password) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		if(driver.toString().contains("safari")) {
			sleepInSecond(2);
		}
		SendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	@Step("click To Login Button")
	public MainPageObject clickToLoginButton() {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		if(driver.toString().contains("safari")) {
			sleepInSecond(2);
		}
		// cách 2  tạo đối tượng vào trong chính cái hàm này luôn 
		// return new MainPageObject(driver); // Khởi tạo mainPage
		// cách 3 
		return PageGeneratorManager.getMainPage(driver);
	}

	@Step("Verify Login Form Displayed")
	public boolean isLoginFormDisplayed() {
		// TODO Auto-generated method stub
		// tạm bỏ qua 
		// waitToElementVisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);
	}
}
