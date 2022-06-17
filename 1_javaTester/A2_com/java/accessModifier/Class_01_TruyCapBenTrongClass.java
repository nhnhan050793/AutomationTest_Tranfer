package A2_com.java.accessModifier;

public class Class_01_TruyCapBenTrongClass {
	/*
	 * AccessModifier (Private/ Default/ protected/ public) 
	 * OOP: Encapsulation -> đóng gói che giấu dữ liệu
	 */

	// 1. thuộc tính (biến): Fields/ Variable
	// Private Fields
	private String name = "Learn build framwwork";

	// Default
	String address = "340 Hong Ha";

	// protected
	protected String city = "Ho Chi Minh city";

	// public
	public String phone = "0779742888";

	// -----------------------------------------------------------------------------------------
	/*
	 * 2. Phương thức (hàm): Method/ Function getter ( lấy dữ liêu) setter ( set dữ liệu)
	 */

	// Private Method
	private String getName() {
		return name;
	}

	private void setName(String name1) {
		// biến name là biến toàn cục, biến name1 biến cục bộ
		this.name = name1;
	}

	// Default Method
	String getAddress() {
		return address;
	}

	void setAddress(String address1) {
		this.address = address1; // ( address = address1)
	}

	// protected method
	protected String getCity() {
		return city;
	}

	protected void setCity(String city1) {
		this.city = city1;
	}

	// public method
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone1) {
		this.phone = phone1;
	}

	public static void main(String[] args) {
		// Muốn sử dụng public class Class_01_TruyCapBenTrongClass {} thì phải khởi tạo
		Class_01_TruyCapBenTrongClass Class001 = new Class_01_TruyCapBenTrongClass();
		System.out.println(Class001.getName());
		Class001.setName("automation testing");
		System.out.println(Class001.getName());
	}

	public class InnerClass01 {
		public void printName() {
			System.out.println(getName());
		}
	}
}
