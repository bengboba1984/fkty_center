package ra.com.dataManagement.model;

public class ResultTemplateJsonItem {
	private int testType;
	private int errorCode;
	private String resultSeq;
	private int testTemplateType;
	private String destName;
	private String destIp;
	private String account;
	private Object result;
	private String stbId;
	private String tester;
	private String woNumber;
	
	//*******盒子信息**********//
	private String deviceSeq;
	private String ottConnectType;
	private String androidVersion;
	private String boxIP;
	
	public String getDeviceSeq() {
		return deviceSeq;
	}
	public void setDeviceSeq(String deviceSeq) {
		this.deviceSeq = deviceSeq;
	}
	public String getOttConnectType() {
		return ottConnectType;
	}
	public void setOttConnectType(String ottConnectType) {
		this.ottConnectType = ottConnectType;
	}
	public String getAndroidVersion() {
		return androidVersion;
	}
	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}
	public String getBoxIP() {
		return boxIP;
	}
	public void setBoxIP(String boxIP) {
		this.boxIP = boxIP;
	}
	
	
	public int getTestType() {
		return testType;
	}
	public void setTestType(int testType) {
		this.testType = testType;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public int getTestTemplateType() {
		return testTemplateType;
	}
	public void setTestTemplateType(int testTemplateType) {
		this.testTemplateType = testTemplateType;
	}
	public String getDestName() {
		return destName;
	}
	public void setDestName(String destName) {
		this.destName = destName;
	}
	public String getDestIp() {
		return destIp;
	}
	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getResultSeq() {
		return resultSeq;
	}
	public void setResultSeq(String resultSeq) {
		this.resultSeq = resultSeq;
	}
	
	public String getStbId() {
		return stbId;
	}
	public void setStbId(String stbId) {
		this.stbId = stbId;
	}
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	public String getWoNumber() {
		return woNumber;
	}
	public void setWoNumber(String woNumber) {
		this.woNumber = woNumber;
	}
	
}
