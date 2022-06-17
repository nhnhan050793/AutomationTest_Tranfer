package pageObjects.bankGuru;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import jdbcconnection.SQLJTDSConnUtils;
import pageUI.bankGuru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		// super();
		this.driver = driver;
		System.out.println("-----Driver at Register Page ----------");
		System.out.println(driver.toString());
	}

	@Step("Input to Email textbox with value: {0}")
	public void inputToEmailTextBox(String emailAddress) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		SendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	@Step("click To Submit Button")
	public void clickToSubmitButton() {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);

	}

	@Step("Get User ID Information")
	public String getUserIDText() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, RegisterPageUI.USER_Id_TEXT);
		return getElementText(driver, RegisterPageUI.USER_Id_TEXT);
	}

	@Step("Get Password Information")
	public String getPasswordText() {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getElementText(driver, RegisterPageUI.PASSWORD_TEXT);
	}

	@Step("Open Login Page Url With Value: {0}")
	public LoginPageObject openLoginPage(String loginPageUrl) {
		// TODO Auto-generated method stub
		openUrl(driver, loginPageUrl);
		// được new lên 2 lần
		// cách 2 tạo đối tượng vào trong chính cái hàm này luôn
		// return new LoginPageObject(driver); // Khởi tạo login
		// cách 3
		return PageGeneratorManager.getLoginPage(driver);
	}

	// dữ liệu giả
	public int getproductMobileNumberOnUI() {
		waitToElementVisible(driver, RegisterPageUI.PRODUCT_NAME_NUMBER);
		return countElementSize(driver, RegisterPageUI.PRODUCT_NAME_NUMBER);

	}

	// dữ liệu giả
	public int getproductMobileNumberInDB() {
		// TODO Auto-generated method stub
		ArrayList<String> listProduct = new ArrayList<>();
		Connection conn = null;
		try {
			conn = SQLJTDSConnUtils.getSQLServerConnection();
			String querysql = "SELECT *   FROM [automationfc].[dbo].[PRODUCT]";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(querysql);
			while (result.next()) {
				// get này lấy ra cột tương ứng
				System.out.println(result.getString("Name"));
				listProduct.add(result.getString("Name"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listProduct.size();
	}

}
