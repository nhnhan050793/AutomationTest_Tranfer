package pageUI.bankGuru;

public class AbstractPageUI {
	public static final String LOGOUT_LINK  = "//a[text()='Log out']";
	public static final String NEW_CUSTOMER_LINK = "//a[text()='New Customer']";
	public static final String EDIT_CUSTOMER_PAGE_LINK = "//a[text()='Edit Customer']";	
	public static final String DEPOSIT_PAGE_LINK  = "//a[text()='Deposit']";	
	public static final String FUND_TRANSFER_PAGE_LINK = "//a[text()='Fund Transfer']";
	
	
	
	// Pattern Object
	public static final String DYNAMIC_MENU_PAGE_LINK = "//a[text()='%s']"; // Dynamic locator (Menu page)
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
	public static final String DYNAMIC_TEXTAREA_BY_NAME = "//textarea[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_VALUE= "//input[@value='%s']";
	public static final String DYNAMIC_BUTTON_BY_VALUE= "//input[@value='%s']";
	public static final String DYNAMIC_PAGE_HEADER_BY_TEXT= "//p[@class='heading3' and text()='%s']";
	public static final String DYNAMIC_ROW_VALUE_BY_ROW_NAME= "//td[text()='%s']/following-sibling::td";
	
	
	// upload file
	public static final String UPLOAD_FILE_TYPE ="//input[@type='file']";
	public static final String MEDIA_PROGRESS_BAR_ICON = "//input[@type='file']"; // xpath giả 
	public static final String ALL_UPLOAD_IMAGE ="//input[@type='file']"; // xpath giả 
}
