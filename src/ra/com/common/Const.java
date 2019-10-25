package ra.com.common;

public class Const {
	public final static String IMG_PATH = "/remote_acquisition/image/";
	public final static String CLIENT_IMG_PATH = "D:/RA/img/";
	public final static String COLLECTOR_CONFIG= "/remote_acquisition/config/";
	public final static String CLIENT_COLLECTOR_CONFIG= "D:/RA/config/";
	public final static String COLLECTOR_TYPE_ELOG="1";
	public final static String COLLECTOR_TYPE_RTU="2";   
	public final static String COLLECTOR_TYPE_CR1000="3";
	public final static String COLLECTOR_TYPE_VIRTUAL="4";    
	public final static String COLLECTOR_CONFIG_MASK_TYPE=",inst,ave,tot,min,max,";
	public final static long IMG_MAX_LENGTH =  1024*1024*4; 
	public final static String[] CONFIG_MASK_TYPE= {"inst","avg","tot","min","max"};
	public final static String[] OPERATOR_TYPE= {"=",">","<",">=","<="};
	public final static String[] TELEPHONENUMBERTYPEKEY = {"a","c","b","d"};
	public final static String[] TELEPHONENUMBERTYPEVALUE = {"电信","电信2","移动","移动3"};
	public final static String TELEPHONENUMBERTYPECONTENT_A_1 = "120301,SVRIP,61.154.11.251,SVRPORT,";
	public final static String TELEPHONENUMBERTYPECONTENT_A_2 = ",RSTDTU";
	public final static String TELEPHONENUMBERTYPECONTENT_B_1 = "@DTU:0000:DSCADDR:TCP,61.154.11.251,";
	public final static String TELEPHONENUMBERTYPECONTENT_B_2 = "@DTU:0000:POWEROFF";
	public final static String TELEPHONENUMBERTYPECONTENT_C ="www.usr.cn#AT+SOCKA=TCP,61.154.11.251,8320";
	public static final String TELEPHONENUMBERTYPECONTENT_D_1 = "123*host#61.154.11.251";
	public static final String TELEPHONENUMBERTYPECONTENT_D_2 = "123*port#";
	public final static String CDV_DATA_ERROR="-99999";
	public final static String ADMIN_ROLE="1";
	public final static String[][] REGISTER_STATUS_LIST = {{"Y","同意"},{"N","拒绝"}};
	public final static String ADMIN_ROLE_ID = " 1, 8 ";
	public final static String[][] CONFIG_FUNCTION_TYPE= {{"13","1417.423*pow(((para1-para2)/1000),2.5)"},{"15","11840*pow(((para1-para2)/1000),2.5)"}};
	public final static int TEMPLATE_ARAMETER_PING = 1;
	public final static int TEMPLATE_ARAMETER_TRACE = 10;
	public final static int TEMPLATE_ARAMETER_SPEED = 1000;
	public final static int TEMPLATE_ARAMETER_DNS = 5;
	public final static int TEMPLATE_ARAMETER_WEB = 11;
	
}
