package browserFactory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class a03_FirefoxDriverManager extends a00_DriverManager {

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		// chạy bình thường
//		WebDriverManager.firefoxdriver().setup();
//		driver = new FirefoxDriver();

		// chạy ẩn danh hiện tại chỉ dành cho selenium version 3.0 trở lên
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--private-window");
		driver = new FirefoxDriver(options);

	}
}
