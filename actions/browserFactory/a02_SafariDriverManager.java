package browserFactory;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class a02_SafariDriverManager extends a00_DriverManager {

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		SafariOptions Options = new SafariOptions();
		Options.setCapability("--Private-window", true);
		driver = new SafariDriver();
	}

}
