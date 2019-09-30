package ra.com.common.connection;

import java.io.IOException;
import java.util.Properties;

public class DBConnectionConfig {
	private static Properties prop = new Properties();

	static {
		try {
			prop.load(DBConnectionConfig.class
					.getResourceAsStream("DBConnectionConfig.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String className = prop.getProperty("className");
}
