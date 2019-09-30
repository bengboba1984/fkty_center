package ra.com.common.email;

import java.util.Properties;

public class MailSenderInfo {
	
	// 鍙戦�侀偖浠剁殑鏈嶅姟鍣ㄧ殑IP鍜岀鍙�
	private String mailServerHost;
	private String mailServerPort = "25";
	// 閭欢鍙戦�佽�呯殑鍦板潃
	private String fromAddress="bengboba1984@163.com";
	// 閭欢鎺ユ敹鑰呯殑鍦板潃
	private String toAddress;
	// 鐧婚檰閭欢鍙戦�佹湇鍔″櫒鐨勭敤鎴峰悕鍜屽瘑鐮�
	private String userName="bengboba1984@163.com";
	private String password="FGC+XPH=Frank";
	// 鏄惁闇�瑕佽韩浠介獙璇�
	private boolean validate = false;
	// 閭欢涓婚
	private String subject;
	// 閭欢鐨勬枃鏈唴瀹�
	private String content;
	// 閭欢闄勪欢鐨勬枃浠跺悕
	private String[] attachFileNames;

	public MailSenderInfo(String toAddress, String subject, String content) {
		super();
		this.toAddress = toAddress;
		this.subject = subject;
		this.content = content;
		this.mailServerHost= "smtp." + fromAddress.split("@")[1];
		this.validate=true;
	}

	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

}
