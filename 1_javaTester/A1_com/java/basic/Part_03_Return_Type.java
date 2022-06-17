package A1_com.java.basic;

public class Part_03_Return_Type {

	// biến toàn cục khai báo trong class có thể truy cập toàn bộ class đó
	static String title;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Part_03_Return_Type part03 = new Part_03_Return_Type();

		part03.getTitle();
		// title = part03.getTitle(); => Không lấy được kiểu dữ liệu trả về getTitle() do kiểu void
		// part03.getTitles();
		title = part03.getTitles();
		System.out.println("title = " + title);
	}

	public void getTitle() {
		String title = "Facebook";
		System.out.println(title);
	}

	public String getTitles() {
	// Biến cục bộ
		String title = "Facebook";
		System.out.println(title);
		return title;
	}
}
