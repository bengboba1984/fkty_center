package ra.com.common.connection;

import java.sql.Connection;

public interface IDBConnection {
public abstract Connection getConnection();
public abstract void closeConnection(Connection conn);
}
