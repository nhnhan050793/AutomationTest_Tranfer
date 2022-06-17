package A2_com.java.accessModifier;

public class Class_02_TruyCapBenTrongPackage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// class 02 muốn nhìn thấy class 01 cần khởi tạo Class_01
		Class_01_TruyCapBenTrongClass Class001 = new Class_01_TruyCapBenTrongClass();
		System.out.println(Class001.getAddress());
		Class001.address ="333 Tan Phu";
		System.out.println(Class001.getAddress());
	}
}