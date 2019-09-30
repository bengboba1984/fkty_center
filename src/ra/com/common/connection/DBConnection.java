package ra.com.common.connection;

import java.sql.Connection;

public class DBConnection {
	public static Connection getConnection() {
		IDBConnection service = DBConnectionFactory.getInstance();
		Connection conn = service.getConnection();
		return conn;
	}

	public static void closeConnection(Connection conn) {
		IDBConnection service = DBConnectionFactory.getInstance();
		service.closeConnection(conn);
	}
}
