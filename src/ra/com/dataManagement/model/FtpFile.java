package ra.com.dataManagement.model;

public class FtpFile {
	@Override
	public String toString() {
		return "FtpFile [fileId=" + fileId + ", fileName=" + fileName
				+ ", tester=" + tester + ", account=" + account + ", stbId="
				+ stbId + ", createdDate=" + createdDate + ", uuguid=" + uuguid
				+ ", type=" + type + "]";
	}
	private String  fileId;
	private String  fileName;
	private String  tester;
	private String  account;
	private String  stbId;
	private String  createdDate;
	private String   uuguid;
	private String type;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	public String getStbId() {
		return stbId;
	}
	public void setStbId(String stbId) {
		this.stbId = stbId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUuguid() {
		return uuguid;
	}
	public void setUuguid(String uuguid) {
		this.uuguid = uuguid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
