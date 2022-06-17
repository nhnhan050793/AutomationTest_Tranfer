package commons;

public abstract class GlobalConstants {
	public static String ROOT_FOLDER = System.getProperty("user.dir");
	public static String BROWSER_LOG_FOLDER = ROOT_FOLDER + "\\browserLog";
	public static String DOWNLOAD_FOLDER = ROOT_FOLDER + "\\downloadFiles";
	public static String UPLOAD_FILE_FOLDER = ROOT_FOLDER + "\\uploadFiles";
	public static String BANK_DEV_URL = "http://demo.guru99.com/v4/";
	public static String BANK_TEST_URL = "http://test.guru99.com/v4/";
	public static String BANK_STAGING_URL = "http://staging.guru99.com/v4/";
	public static long LONG_TIMEOUT = 40;
	public static long SHORT_TIMEOUT = 10;
	
	
}
