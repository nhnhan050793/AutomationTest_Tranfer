package A1_com.java.basic;

public class Part_01_SystemProperty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
		System.out.println(System.getProperty("os.name"));
				
	}
}
