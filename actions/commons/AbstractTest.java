package commons;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;

import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	// private WebDriver driver;
	// protected static WebDriver driver;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	// Init log
	protected final Log log; // khai báo

	// Constructor
	protected AbstractTest() {
		log = LogFactory.getLog(getClass()); // đây là đoạn khởi tạo
	}

	// dùng enum cố định các giá trị ít khi thay đổi
	public enum Browser {
		SAFARI, EDGE, EDGEHEADLESS, FIREFOX, CHROME, FIREFOXHEADLESS, CHROMEHEADLESS, IE;
	}

	// dùng cho level còn lại
	protected WebDriver getBrowserDriver(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
//		// Cách 1C: Dùng if else
//		if (browser == Browser.SAFARI) {
//			threadDriver.set(new SafariDriver());
//		} else if (browser == Browser.EDGE) {
//			//WebDriverManager.edgedriver().setup();
//			//threadDriver.set(new EdgeDriver());
//			
//			WebDriverManager.edgedriver().setup();
//			// cài extension google translate
//			File file_edge = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/extension_2_0_9_0.crx");
//			EdgeOptions EdgeUI = new EdgeOptions();
//			EdgeUI.addExtensions(file_edge);
//			threadDriver.set(new EdgeDriver(EdgeUI));	
//		} else if (browser == Browser.EDGEHEADLESS) {
//			WebDriverManager.edgedriver().setup();
//			EdgeOptions EdgeUI = new EdgeOptions();
//			EdgeUI.addArguments("--headless");
//			EdgeUI.addArguments("window-size=1366x768");
//			threadDriver.set(new EdgeDriver(EdgeUI));	
//		} else if (browser == Browser.CHROME) {
//			//WebDriverManager.chromedriver().setup();
//			//threadDriver.set(new ChromeDriver());
//			
//			WebDriverManager.chromedriver().setup();
//			// cài extension google translate
//			File file_chrome = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/extension_2_0_9_0.crx");
//			ChromeOptions chromeUI = new ChromeOptions();
//			chromeUI.addExtensions(file_chrome);
//			threadDriver.set(new ChromeDriver(chromeUI));	
//		} else if (browser == Browser.FIREFOX) {
//			//WebDriverManager.firefoxdriver().setup();
//			//threadDriver.set(new FirefoxDriver());
//			
//			WebDriverManager.firefoxdriver().setup();
//			// cài extension google translate
//			FirefoxProfile profile = new FirefoxProfile();
//			File file_firefox = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/to_google_translate-4.1.0-fx.xpi");
//			profile.addExtension(file_firefox);
//			FirefoxOptions firefoxUI = new FirefoxOptions();
//			firefoxUI.setProfile(profile);
//			threadDriver.set(new FirefoxDriver(firefoxUI));
//	
//		} else if (browser == Browser.CHROMEHEADLESS) {
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions chromeheadless = new ChromeOptions();
//			//chromeheadless.setHeadless(true); // cách 1: 
//			chromeheadless.addArguments("--headless"); // cách 2:
//			threadDriver.set(new ChromeDriver(chromeheadless));
//		} else if (browser == Browser.FIREFOXHEADLESS) {
//			//WebDriverManager.firefoxdriver().setup();
//			//FirefoxOptions firefoxheadless = new FirefoxOptions();
//		    ////firefoxheadless.setHeadless(true); // cách 1: 
//			//firefoxheadless.addArguments("-headless"); // cách 2:
//			//threadDriver.set(new FirefoxDriver(firefoxheadless));
//			//break;
//			
//			// cách 3:
//			WebDriverManager.firefoxdriver().setup();
//			FirefoxBinary firefoxBinary = new FirefoxBinary();
//			firefoxBinary.addCommandLineOptions("--headless");
//			FirefoxOptions firefoxheadless = new FirefoxOptions();
//			firefoxheadless.setBinary(firefoxBinary);
//			threadDriver.set(new FirefoxDriver(firefoxheadless));
//		} else if (browser == Browser.IE) {
//			// Init internet explorer browser
//		} else {
//			throw new RuntimeException("Please choose browser name!");
//		}
//		
		// Cách 2C: switch case:
		switch (browser) {
		case SAFARI:
			threadDriver.set(new SafariDriver());
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			threadDriver.set(new EdgeDriver());
			break;

//			WebDriverManager.edgedriver().setup();
//			// WebDriverManager.edgedriver().setup()("83.0.4103.14").setup();// chọn 1 version cũ hơn
//			// cài extension google translate
//			File file_edge = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/extension_2_0_9_0.crx");
//			EdgeOptions EdgeUI = new EdgeOptions();
//			EdgeUI.addExtensions(file_edge);
//			threadDriver.set(new EdgeDriver(EdgeUI));
//			break;	
//		case EDGEHEADLESS:
//			WebDriverManager.edgedriver().setup();
//			EdgeOptions Options = new EdgeOptions();
//			Options.addArguments("--headless");
//			Options.addArguments("window-size=1366x768");
//			threadDriver.set(new EdgeDriver(Options));
//			break;
		case CHROME:
			// WebDriverManager.chromedriver().setup();// mặc định mới nhất
			//// WebDriverManager.chromedriver().version("83.0.4103.14").setup();// chọn 1 version cũ hơn
			// threadDriver.set(new ChromeDriver());
			// break;
			WebDriverManager.chromedriver().setup();// mặc định mới nhất
			// WebDriverManager.chromedriver().version("83.0.4103.14").setup();// chọn 1 version cũ hơn
			ChromeOptions chromeUI = new ChromeOptions();
			// disable log:
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");

			// Auto Save/Douwnload in chrome
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", GlobalConstants.ROOT_FOLDER + "/downloadFiles");
			chromeUI.setExperimentalOption("prefs", chromePrefs);

			// cài extension google translate
			File file_chrome = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/extension_2_0_9_0.crx");
			chromeUI.addExtensions(file_chrome);

			// chỉnh ngôn ngữ trình duyệt
			chromeUI.addArguments("--lang=vi");

			// automation info Bar chorme firefox không có
			chromeUI.setExperimentalOption("useAutomationExtension", false);
			chromeUI.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			// chạy ẩn danh:
			chromeUI.addArguments("--incognito");

			threadDriver.set(new ChromeDriver(chromeUI));
			break;
		case FIREFOX:
			// WebDriverManager.firefoxdriver().setup();
			// threadDriver.set(new FirefoxDriver());
			// break;
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxUI = new FirefoxOptions();

			// disable log
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.ROOT_FOLDER + "/browserLogs/FirefoxLog.log");

			// Auto Save/Douwnload in firefox
			firefoxUI.addPreference("browser.download.folderList", 2);
			firefoxUI.addPreference("browser.download.dir", GlobalConstants.ROOT_FOLDER + "/downloadFiles");
			firefoxUI.addPreference("browser.download.useDownloadDir", true);
			firefoxUI.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			firefoxUI.addPreference("pdfjs.disabled", true);

			// cài extension google translate
			File file_firefox = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/to_google_translate-4.1.0-fx.xpi");
			FirefoxProfile profile = new FirefoxProfile();
			profile.addExtension(file_firefox);
			firefoxUI.setProfile(profile);

			// chỉnh ngôn ngữ trình duyệt
			firefoxUI.addPreference("intl.accept_languages", "vi-vn, vi, en-us, en");
			threadDriver.set(new FirefoxDriver(firefoxUI));

			// chạy ẩn danh:
			firefoxUI.addArguments("--private-window");
			break;

		case CHROMEHEADLESS:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeheadless = new ChromeOptions();
			// chromeheadless.setHeadless(true); // cách 1:
			chromeheadless.addArguments("--headless"); // cách 2:
			threadDriver.set(new ChromeDriver(chromeheadless));
			break;
		case FIREFOXHEADLESS:
			// WebDriverManager.firefoxdriver().setup();
			// FirefoxOptions firefoxheadless = new FirefoxOptions();
			//// firefoxheadless.setHeadless(true); // cách 1:
			// firefoxheadless.addArguments("-headless"); // cách 2:
			// threadDriver.set(new FirefoxDriver(firefoxheadless));
			// break;

			// cách 3:
			WebDriverManager.firefoxdriver().setup();
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			FirefoxOptions firefoxheadless = new FirefoxOptions();
			firefoxheadless.setBinary(firefoxBinary);
			threadDriver.set(new FirefoxDriver(firefoxheadless));
			break;
		case IE:
			// Init internet explorer browser;
			WebDriverManager.iedriver().arch32().setup();
			InternetExplorerOptions ieUI = new InternetExplorerOptions();
			ieUI.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			threadDriver.set(new InternetExplorerDriver());
			break;

		default:
			throw new RuntimeException("Please choose browser name!");
		}
		// dùng cho level 12, 20
		// threadDriver.get().get(appurl); //dùng cho level 12, 20

		// dùng cho level còn lại
		threadDriver.get().get(GlobalConstants.BANK_DEV_URL);
		threadDriver.get().manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		threadDriver.get().manage().window().maximize();
		System.out.println("---- Driver at Abstract Test = " + threadDriver.get().toString() + "------");
		return threadDriver.get();
	}

//-----------------------------------------------------------------------------------------------------------
//	// dùng cho level 12, 20, 24
	protected WebDriver getBrowserDriver(String browserName, String appurl) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
//		// Cách 1C: Dùng if else
//		if (browser == Browser.SAFARI) {
//			threadDriver.set(new SafariDriver());
//		} else if (browser == Browser.EDGE) {
//			//WebDriverManager.edgedriver().setup();
//			//threadDriver.set(new EdgeDriver());
//			
//			WebDriverManager.edgedriver().setup();
//			// cài extension google translate
//			File file_edge = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/extension_2_0_9_0.crx");
//			EdgeOptions EdgeUI = new EdgeOptions();
//			EdgeUI.addExtensions(file_edge);
//			threadDriver.set(new EdgeDriver(EdgeUI));	
//		} else if (browser == Browser.EDGEHEADLESS) {
//			WebDriverManager.edgedriver().setup();
//			EdgeOptions EdgeUI = new EdgeOptions();
//			EdgeUI.addArguments("--headless");
//			EdgeUI.addArguments("window-size=1366x768");
//			threadDriver.set(new EdgeDriver(EdgeUI));	
//		} else if (browser == Browser.CHROME) {
//			//WebDriverManager.chromedriver().setup();
//			//threadDriver.set(new ChromeDriver());
//			
//			WebDriverManager.chromedriver().setup();
//			// cài extension google translate
//			File file_chrome = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/extension_2_0_9_0.crx");
//			ChromeOptions chromeUI = new ChromeOptions();
//			chromeUI.addExtensions(file_chrome);
//			threadDriver.set(new ChromeDriver(chromeUI));	
//		} else if (browser == Browser.FIREFOX) {
//			//WebDriverManager.firefoxdriver().setup();
//			//threadDriver.set(new FirefoxDriver());
//			
//			WebDriverManager.firefoxdriver().setup();
//			// cài extension google translate
//			FirefoxProfile profile = new FirefoxProfile();
//			File file_firefox = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/to_google_translate-4.1.0-fx.xpi");
//			profile.addExtension(file_firefox);
//			FirefoxOptions firefoxUI = new FirefoxOptions();
//			firefoxUI.setProfile(profile);
//			threadDriver.set(new FirefoxDriver(firefoxUI));
//	
//		} else if (browser == Browser.CHROMEHEADLESS) {
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions chromeheadless = new ChromeOptions();
//			//chromeheadless.setHeadless(true); // cách 1: 
//			chromeheadless.addArguments("--headless"); // cách 2:
//			threadDriver.set(new ChromeDriver(chromeheadless));
//		} else if (browser == Browser.FIREFOXHEADLESS) {
//			//WebDriverManager.firefoxdriver().setup();
//			//FirefoxOptions firefoxheadless = new FirefoxOptions();
//		    ////firefoxheadless.setHeadless(true); // cách 1: 
//			//firefoxheadless.addArguments("-headless"); // cách 2:
//			//threadDriver.set(new FirefoxDriver(firefoxheadless));
//			//break;
//			
//			// cách 3:
//			WebDriverManager.firefoxdriver().setup();
//			FirefoxBinary firefoxBinary = new FirefoxBinary();
//			firefoxBinary.addCommandLineOptions("--headless");
//			FirefoxOptions firefoxheadless = new FirefoxOptions();
//			firefoxheadless.setBinary(firefoxBinary);
//			threadDriver.set(new FirefoxDriver(firefoxheadless));
//		} else if (browser == Browser.IE) {
//			// Init internet explorer browser
//		} else {
//			throw new RuntimeException("Please choose browser name!");
//		}
//		
		// Cách 2C: switch case:
		switch (browser) {
		case SAFARI:
			threadDriver.set(new SafariDriver());
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			threadDriver.set(new EdgeDriver());
			break;

//			WebDriverManager.edgedriver().setup();
//			// WebDriverManager.edgedriver().setup()("83.0.4103.14").setup();// chọn 1 version cũ hơn
//			// cài extension google translate
//			File file_edge = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/extension_2_0_9_0.crx");
//			EdgeOptions EdgeUI = new EdgeOptions();
//			EdgeUI.addExtensions(file_edge);
//			threadDriver.set(new EdgeDriver(EdgeUI));
//			break;	
//		case EDGEHEADLESS:
//			WebDriverManager.edgedriver().setup();
//			EdgeOptions Options = new EdgeOptions();
//			Options.addArguments("--headless");
//			Options.addArguments("window-size=1366x768");
//			threadDriver.set(new EdgeDriver(Options));
//			break;
		case CHROME:
			// WebDriverManager.chromedriver().setup();// mặc định mới nhất
			//// WebDriverManager.chromedriver().version("83.0.4103.14").setup();// chọn 1 version cũ hơn
			// threadDriver.set(new ChromeDriver());
			// break;

			WebDriverManager.chromedriver().setup();// mặc định mới nhất
			// WebDriverManager.chromedriver().version("83.0.4103.14").setup();// chọn 1 version cũ hơn
			// cài extension google translate
			File file_chrome = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/extension_2_0_9_0.crx");
			ChromeOptions chromeUI = new ChromeOptions();
			chromeUI.addExtensions(file_chrome);
			threadDriver.set(new ChromeDriver(chromeUI));
			break;
		case FIREFOX:
			// WebDriverManager.firefoxdriver().setup();
			// threadDriver.set(new FirefoxDriver());
			// break;

			WebDriverManager.firefoxdriver().setup();
			// cài extension google translate
			FirefoxProfile profile = new FirefoxProfile();
			File file_firefox = new File(GlobalConstants.ROOT_FOLDER + "/browserExtensions/to_google_translate-4.1.0-fx.xpi");
			profile.addExtension(file_firefox);
			FirefoxOptions firefoxUI = new FirefoxOptions();
			firefoxUI.setProfile(profile);
			threadDriver.set(new FirefoxDriver(firefoxUI));
			break;

		case CHROMEHEADLESS:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeheadless = new ChromeOptions();
			// chromeheadless.setHeadless(true); // cách 1:
			chromeheadless.addArguments("--headless"); // cách 2:
			threadDriver.set(new ChromeDriver(chromeheadless));
			break;
		case FIREFOXHEADLESS:
			// WebDriverManager.firefoxdriver().setup();
			// FirefoxOptions firefoxheadless = new FirefoxOptions();
			//// firefoxheadless.setHeadless(true); // cách 1:
			// firefoxheadless.addArguments("-headless"); // cách 2:
			// threadDriver.set(new FirefoxDriver(firefoxheadless));
			// break;

			// cách 3:
			WebDriverManager.firefoxdriver().setup();
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			FirefoxOptions firefoxheadless = new FirefoxOptions();
			firefoxheadless.setBinary(firefoxBinary);
			threadDriver.set(new FirefoxDriver(firefoxheadless));
			break;
		case IE:
			// Init internet explorer browser;
			WebDriverManager.iedriver().arch32().setup();
			InternetExplorerOptions ieUI = new InternetExplorerOptions();
			ieUI.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			threadDriver.set(new InternetExplorerDriver());
			break;
		default:
			throw new RuntimeException("Please choose browser name!");
		}
		// dùng cho level 12, 20
		threadDriver.get().get(appurl); // dùng cho level 12, 20,24

		// dùng cho level còn lại
		// threadDriver.get().get(GlobalConstants.BANK_DEV_URL);
		threadDriver.get().manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		threadDriver.get().manage().window().maximize();
		System.out.println("---- Driver at Abstract Test = " + threadDriver.get().toString() + "------");
		return threadDriver.get();
	}

//----------------------------------------------------------------------------------------------------------------------------	
	protected void removeBrowserDriver() {
		WebDriver driver = threadDriver.get();
		try {
			// Get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = "";
			if (driver != null) {
				// clean cookie
				driver.manage().deleteAllCookies();
				driver.quit();
			}

			// Quit driver executable file in Task Manager
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("edge")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill msedgedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		threadDriver.remove();
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public static WebDriver getDriver() {
		// TODO Auto-generated method stub
		return threadDriver.get();
	}

	public int GetrandomNumber() {
		Random rand = new Random();
		// bắt đầu từ 0 -> 9999
		int number = rand.nextInt(10000);
		return number;
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				log.info("----------" + logging.getLevel().toString() + "---------- \n" + logging.getMessage());
			}
		}
	}

}
