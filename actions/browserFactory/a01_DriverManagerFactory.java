package browserFactory;

public class a01_DriverManagerFactory {
	
	protected enum Browser {
		SAFARI, FIREFOX, FIREFOXHEADLESS, CHROME, CHROMEHEADLESS, EDGE, EDGEHEADLESS, IE;
	}
	public static a00_DriverManager getBrowserDriver(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		a00_DriverManager driverManager;
		switch (browser) {
		case SAFARI:
			driverManager = new a02_SafariDriverManager();
			break;
		case FIREFOX:
			driverManager = new a03_FirefoxDriverManager();
			break;
		case FIREFOXHEADLESS:
			driverManager = new a04_FirefoxHeadlessDriverManager();
			break;	
		case CHROME:
			driverManager = new a05_ChromeDriverManager();
			break;
		case CHROMEHEADLESS:
			driverManager = new a06_ChromeHeadlessDriverManager();
			break;			
//		case EDGE:
//			driverManager = new a07_EdgeDriverManager();
//			break;
//		case EDGEHEADLESS:
//			driverManager = new a08_EdgeHeadlessDriverManager();
//			break;		

		default:
			throw new RuntimeException("Please choose browser name!");
		}
		return driverManager;
	
		}
	
	}

