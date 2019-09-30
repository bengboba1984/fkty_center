package ra.com.common.connection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractConnectionImpl implements IDBConnection {

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
