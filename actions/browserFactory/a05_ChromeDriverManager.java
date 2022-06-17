package browserFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class a05_ChromeDriverManager extends a00_DriverManager {

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		//Cách 1: chạy ẩn danh hiện tại chỉ dành cho selenium version 2.0
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--incognito");
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);		
//		driver = new ChromeDriver(options);
		
		// chạy bình thường 
//		WebDriverManager.chromedriver().setup();	
//		driver = new ChromeDriver();	
				
		//Cách 2: chạy ẩn danh hiện tại chỉ dành cho selenium version 3.0 trở lên
		WebDriverManager.chromedriver().setup();		
		ChromeOptions options1 = new ChromeOptions();
		options1.addArguments("--incognito");
		driver = new ChromeDriver(options1);		
	}
}
