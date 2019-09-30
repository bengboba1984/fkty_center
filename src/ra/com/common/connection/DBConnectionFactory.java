package ra.com.common.connection;

public class DBConnectionFactory {
	private static IDBConnection instance;
	private static Object initLock = new Object();

	public static IDBConnection getInstance() {
		if (instance == null) {
			synchronized (initLock) {
				try {
					instance = (IDBConnection) Class.forName(
							DBConnectionConfig.className).newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return instance;
	}
}
