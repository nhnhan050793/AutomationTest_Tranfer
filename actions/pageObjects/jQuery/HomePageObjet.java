package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.jQuery.HomePageUI;

public class HomePageObjet extends AbstractPage {

	WebDriver driver;

	public HomePageObjet(WebDriver driver) {
		//super();
		this.driver = driver;
	}

	public void inputToTextBoxByColumnName(String columnName, String value) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_NAME, columnName);
		// hàm senkey hơi ngượic 1 chút vì RestParameter nằm cuối cùng 
		SendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_NAME, value, columnName);
	}

	public void navigateToPageByPageNumber(String pageNumber) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, HomePageUI.DYNAMIC_PAGING_BY_PAGE_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.DYNAMIC_PAGING_BY_PAGE_NUMBER, pageNumber);
	}

	public boolean isPageActivedByByPageNumber(String pageNumber) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, HomePageUI.DYNAMIC_PAGE_ACTIVED_BY_PAGE_NUMBER, pageNumber);
		return isControlDisplayed(driver, HomePageUI.DYNAMIC_PAGE_ACTIVED_BY_PAGE_NUMBER, pageNumber);
	}

	public void clickToDeleteIconByCountryName(String countryName) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, HomePageUI.DYNAMIC_DELETE_ICON_BY_COUNTRY_NAME, countryName);
		clickToElement(driver, HomePageUI.DYNAMIC_DELETE_ICON_BY_COUNTRY_NAME, countryName );
	}

	public void clickToEditconByCountryName(String countryName) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, HomePageUI.DYNAMIC_EDIT_ICON_BY_COUNTRY_NAME, countryName);
		clickToElement(driver, HomePageUI.DYNAMIC_EDIT_ICON_BY_COUNTRY_NAME, countryName );		
	}

	public boolean isAllInforDisplayed(String females, String country, String males, String total) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, HomePageUI.DYNAMIC_ALL_INFOR_AT_ANY_ROW, females, country, males, total);
		return isControlDisplayed(driver, HomePageUI.DYNAMIC_ALL_INFOR_AT_ANY_ROW, females, country, males, total);
	}

	public void inputToTextboxByColumnNameAtRowNumber(String columnName, String rowNumber, String value) {
		// TODO Auto-generated method stub
		waitToElementVisible(driver, HomePageUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName);
		String columnNameIndex = String.valueOf(countElementSize(driver, HomePageUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName) +1);
			
		//waitwaitToElementVisible  kiểu string thì columnNameIndex dùng kiểu string và ép kiểu 
		waitToElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX, rowNumber, columnNameIndex);
		SendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX, value, rowNumber, columnNameIndex);	
	}

}
