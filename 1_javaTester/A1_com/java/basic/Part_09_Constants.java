package A1_com.java.basic;

public class Part_09_Constants {
	//public static final String NAME = "AUTOTEST";
	public static String browserName = "chrome";
	public enum Browser {
		CHROME, FIREFOX, SAFARI;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(browserName);
		System.out.println(browserName.toUpperCase());
		Browser browser = Browser.valueOf(browserName.toUpperCase());		
	}
}
