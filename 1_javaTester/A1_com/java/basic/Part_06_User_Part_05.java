package A1_com.java.basic;

public class Part_06_User_Part_05 {

	public static void main(String[] args) {
		System.out.println(Part_05_Static_Variable.address);
		Part_05_Static_Variable.address = "Automation Testing";
		
		System.out.println(Part_05_Static_Variable.address);
		Part_05_Static_Variable.setAddress("Manual Testing");
		System.out.println(Part_05_Static_Variable.address);
	}
}

