package ra.com.common.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSSqlConnectionImpl extends AbstractConnectionImpl {

	@Override
	public Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(MSSqlConnectionConfig.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(MSSqlConnectionConfig.url,MSSqlConnectionConfig.user,MSSqlConnectionConfig.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public void closeConnection(Connection conn) {
		// TODO Auto-generated method stub
		super.closeConnection(conn);
	}

}
