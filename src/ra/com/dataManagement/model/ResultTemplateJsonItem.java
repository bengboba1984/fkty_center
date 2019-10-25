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
	private String stb_id;
	private String tester;
	
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
	
	public String getStb_id() {
		return stb_id;
	}
	public void setStb_id(String stb_id) {
		this.stb_id = stb_id;
	}
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	
}
