package browserFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class a06_ChromeHeadlessDriverManager extends a00_DriverManager {

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1366x768");
		driver = new ChromeDriver(options);

	}
}
