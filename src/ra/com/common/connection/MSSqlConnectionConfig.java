package ra.com.common.connection;

import java.io.IOException;
import java.util.Properties;

public class MSSqlConnectionConfig {
private static Properties prop=new Properties();
static{
	
	try {
		prop.load(MSSqlConnectionConfig.class.getResourceAsStream("MSSqlConnectionConfig.properties"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static String driver=prop.getProperty("driver");
public static String url=prop.getProperty("url");
public static String user=prop.getProperty("user");
public static String password=prop.getProperty("password");
}
