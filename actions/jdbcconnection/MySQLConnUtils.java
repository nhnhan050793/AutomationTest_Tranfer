package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnUtils {
	public static Connection getMySQLConnection() {
		String hostName = "localhost";
		String dbName = "automationfc";
		String userName = "root";
		String password = "admin";
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
		Connection conn = null;
		try {
			// khai báo class Driver cho my SQL
			// việc này cần thiết cho java 5
			// java 6 tự động tìm kiếm Driver thích hợp - không bắt buộc cần dòng này
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Cấu trúc URL Connection dành cho MySQL
			// ví dụ: jdbc:mysql://localhost:3306/automationfc
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
