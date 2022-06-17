package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLJTDSConnUtils {

	public static Connection getSQLServerConnection() {
		String hostName = "localhost";
		String sqlInstanceName = "SQLEXPRESS";
		String database = "automationfc";
		String userName = "sa";
		String password = "P@ss!!!";
		return getSQLServerConnection(hostName, sqlInstanceName, database,  userName, password);
	}
	public static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String userName, String password) {
		Connection conn = null;
		try {
			// khai báo class Driver cho sql server
			// việc này cần thiết cho java 5
			// java 6 tự động tìm kiếm Driver thích hợp - không bắt buộc cần dòng này
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("net.sourceforge.jtds.jdbc.Driver");

			// Cấu trúc URL Connection dành cho SQL server
			// ví dụ: jdbc:jtds:sqlserver//localhost:3306/automationfc;instance=SQLEXPRESS
			String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/" + database + ";instance=" + sqlInstanceName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
