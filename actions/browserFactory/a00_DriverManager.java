package browserFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public abstract class a00_DriverManager {
	protected WebDriver driver;

	protected abstract void createDriver();

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public WebDriver getDriver() {
		if (driver == null) {
			createDriver();
		}
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("---- Driver at Abstract Test = " + driver.toString() + "------");
		return driver;
	}
}
