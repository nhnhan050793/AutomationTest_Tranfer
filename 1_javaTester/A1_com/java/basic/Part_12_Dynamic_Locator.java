package A1_com.java.basic;

//public class Part_12_Dynamic_Locator {
//	static String NEW_CUSTOMER_LINK = "//a[text()='New Customer']";
//	static String DEPOSIT_PAGE_LINK  = "//a[text()='Deposit']";	
//	static String FUND_TRANSFER_PAGE_LINK = "//a[text()='Fund Transfer']";s
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		clickToMenu(NEW_CUSTOMER_LINK);
//		clickToMenu(DEPOSIT_PAGE_LINK);
//		clickToMenu(FUND_TRANSFER_PAGE_LINK);
//	}
//
//	public static void clickToMenu(String locator) {
//		System.out.println("Click to: " + locator);
//	}
//}

//// viết tối ưu các locator dùng chung chỉ khác value 
//public class Part_12_Dynamic_Locator {
//	// 14 locator = 14 pag
//	static String NEW_CUSTOMER_LINK = "//a[text()='New Customer']";
//	static String DEPOSIT_PAGE_LINK = "//a[text()='Deposit']";
//	static String FUND_TRANSFER_PAGE_LINK = "//a[text()='Fund Transfer']";
//
//	// 1 tham số truyền vào (1 locator đại diện cho n pages )
//	// %s đại diện cho 1 chuỗi và nó giống như 1 tham số mình cần truyền tham số vào đây
//	static String DYNAMIC_MENU_PAGE_LINK = "//a[text()='%s']";
//
//	// 2 tham số truyền vào
//	// static String DYNAMIC_ROW_COUNTRY_TOTAL = "//td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
//	static String DYNAMIC_ROW_COUNTRY_TOTAL = "//td[@data-key='country' and text()='%s']" + "/following-sibling::td[@data-key='total' and text()='%s']";
//
//	// 3 tham số truyền vào
//	static String DYNAMIC_ROW_COUNTRY_TOTAL_FEMALE= "//td[@data-key='country' and text()='%s']" + "/following-sibling::td[@data-key='males' and text()='%s']" + "/following-sibling::td[@data-key='total' and text()='%s']";
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		clickToMenu(DYNAMIC_MENU_PAGE_LINK, "Deposit");
//		clickToMenu(DYNAMIC_MENU_PAGE_LINK, "Fund Transfer");
//		clickToMenu(DYNAMIC_MENU_PAGE_LINK, "New Customer");
//
//		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL, "Afghanistan", "791312");
//		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL, "France", "774134");
//		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL, "United Kingdom", "751000");
//		
//		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL_FEMALE, "Afghanistan", "407124", "791312");
//		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL_FEMALE, "France", "396318", "774134");
//		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL_FEMALE, "United Kingdom", "385000", "751000");
//	}
//
//	// menuLink = //a[text()='%s']
//	// pageName = Deposit
//	public static void clickToMenu(String locator, String value) {
//		// //a[text()='Deposit']
//		locator = String.format(locator, value);
//		System.out.println("Click to: " + locator);
//	}
//
//	public static void clickToMenu(String locator, String firstValue, String secondValue) {
//		// //a[text()='Deposit']
//		locator = String.format(locator, firstValue, secondValue);
//		System.out.println("Click to: " + locator);
//	}
//	
//	public static void clickToMenu(String locator, String firstValue, String secondValue, String thridValue) {
//		// //a[text()='Deposit']
//		locator = String.format(locator, firstValue, secondValue, thridValue);
//		System.out.println("Click to: " + locator);
//	}
//	
//}

//viết tối ưu các locator dùng chung chỉ khác value dùng Rest parameter:
public class Part_12_Dynamic_Locator {
	// 14 locator = 14 pag
	static String NEW_CUSTOMER_LINK = "//a[text()='New Customer']";
	static String DEPOSIT_PAGE_LINK = "//a[text()='Deposit']";
	static String FUND_TRANSFER_PAGE_LINK = "//a[text()='Fund Transfer']";

	// 1 tham số truyền vào (1 locator đại diện cho n pages )
	// %s đại diện cho 1 chuỗi và nó giống như 1 tham số mình cần truyền tham số vào đây
	static String DYNAMIC_MENU_PAGE_LINK = "//a[text()='%s']";

	// 2 tham số truyền vào
	// static String DYNAMIC_ROW_COUNTRY_TOTAL = "//td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
	static String DYNAMIC_ROW_COUNTRY_TOTAL = "//td[@data-key='country' and text()='%s']" + "/following-sibling::td[@data-key='total' and text()='%s']";

	// 3 tham số truyền vào
	static String DYNAMIC_ROW_COUNTRY_TOTAL_FEMALE = "//td[@data-key='country' and text()='%s']" + "/following-sibling::td[@data-key='males' and text()='%s']" + "/following-sibling::td[@data-key='total' and text()='%s']";

	// 5 tham số truyền vào
	static String DYNAMIC_VALUES = "//td[text()='%s']" + "/following-sibling::tdtd[text()='%s']" + "/following-sibling::tdtd[text()='%s']" + "/following-sibling::tdtd[text()='%s']" + "/following-sibling::tdtd[text()='%s']";;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clickToMenu(DYNAMIC_MENU_PAGE_LINK, "Deposit");
		clickToMenu(DYNAMIC_MENU_PAGE_LINK, "Fund Transfer");
		clickToMenu(DYNAMIC_MENU_PAGE_LINK, "New Customer");

		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL, "Afghanistan", "791312");
		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL, "France", "774134");
		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL, "United Kingdom", "751000");

		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL_FEMALE, "Afghanistan", "407124", "791312");
		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL_FEMALE, "France", "396318", "774134");
		clickToMenu(DYNAMIC_ROW_COUNTRY_TOTAL_FEMALE, "United Kingdom", "385000", "751000");

		clickToMenu(DYNAMIC_VALUES, "1", "2", "3", "4", "5");
	}

	// menuLink = //a[text()='%s']
	// pageName = Deposit

	// Rest parameter:
	public static void clickToMenu(String locator, String... value) {
		// //a[text()='Deposit']
		locator = String.format(locator, (Object[]) value);
		System.out.println("Click to: " + locator);
	}
}
