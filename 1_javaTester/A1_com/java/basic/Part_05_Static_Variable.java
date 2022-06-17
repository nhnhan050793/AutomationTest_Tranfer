package A1_com.java.basic;

public class Part_05_Static_Variable {
	public static String address = "Automation FC";	
	public static final String CITY = "HO Chi Minh City";
	public final String name = "Ha Noi";
	
	public static void setAddress(String address) {
		Part_05_Static_Variable.address = address;		
	} // => Tr hợp static,truy cập thông qua tên lớp không cần khởi tạo

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
