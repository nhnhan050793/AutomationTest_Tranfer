package A1_com.java.basic;

public class Part_08_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String browserName = "ChrOMe";
		
		// contains: kiểm tra tương đối
		if (browserName.contains("chrome")) {
			System.out.println(browserName);
			System.out.println("Init Chrome browser");
		}		
		// equals: Tuyệt đối
		if (browserName.equals("chrome")) {
			System.out.println(browserName);
			System.out.println("Init Chrome browser");
		}
		
		// equalsIgnoreCase	
		if (browserName.equalsIgnoreCase("chrome")) {
			//CHROME, Chrome, chrome, ChrOMe
			System.out.println(browserName);
			System.out.println("Init Chrome browser");
		}
	}
}
