package browserFactory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class a04_FirefoxHeadlessDriverManager extends a00_DriverManager {

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options1 = new FirefoxOptions();
		options1.addArguments("-headless");
		options1.addArguments("window-size=1366x768");
		driver = new FirefoxDriver(options1);	
	}

}
