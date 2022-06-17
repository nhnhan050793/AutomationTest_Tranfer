package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLJDBCConnUtils {

	public static Connection getSQLServerConnection() {		
		String hostName = "localhost";
		String sqlInstanceName = "SQLEXPRESS";
		String database = "automationfc";
		String userName = "sa";
		String password = "P@ss!!!";
		return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);		
	}
	public static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String userName, String password) {
		Connection conn = null;
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// Cấu trúc URL Connection dành cho SQL server
			// ví dụ: jdbc:sqlserver://localhost:3306/automationfc;instance=SQLEXPRESS
			String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + database;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
