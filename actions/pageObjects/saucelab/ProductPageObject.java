package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.saucelab.ProductPageUI;

public class ProductPageObject extends AbstractPage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		//super();
		this.driver = driver;
	}

	public void selectValueInSortDropdown(String valueOfItem) {
		// TODO Auto-generated method stub
		waitToElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
		selectItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN, valueOfItem);
		
	}

	public boolean areproductNameSortAscending() {
		// TODO Auto-generated method stub
		// cách 1: 
		//return isDataStringSortedAscending(driver, ProductPageUI.PRODUCT_NAME);	
		// cách 2: 
		return isDataSortedAscending(driver, ProductPageUI.PRODUCT_NAME);	
		
	}


	public boolean areproductNameSortDescending() {
		// TODO Auto-generated method stub
		return isDataStringSortedDescending(driver, ProductPageUI.PRODUCT_NAME);
	}

	public boolean areproductPriceSortAscending() {
		// TODO Auto-generated method stub
		return isDataFloatSortedAscending(driver, ProductPageUI.PRODUCT_PRICE);
	}

	public boolean areproductPriceSortDescending() {
		// TODO Auto-generated method stub
		return isDataFloatSortedDescending(driver, ProductPageUI.PRODUCT_PRICE);
	}
}
