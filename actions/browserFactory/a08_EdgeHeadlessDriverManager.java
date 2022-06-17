//package browserFactory;
//
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class a08_EdgeHeadlessDriverManager extends a00_DriverManager {
//
//	@Override
//	protected void createDriver() {
//		// TODO Auto-generated method stub
//		// chạy ẩn danh hiện tại chỉ dành cho selenium version 4.0 trở lên
//		WebDriverManager.edgedriver().setup();
//		EdgeOptions Options = new EdgeOptions();
//		Options.addArguments("headless");
//		driver = new EdgeDriver(Options);
//	}
//
//}
