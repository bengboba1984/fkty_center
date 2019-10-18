package ra.com.dataManagement.model;

import java.util.List;

public class TestingResultSpeed {
	private String resultSpeedId;
	private int downloadMaxThroughput;
	private int downloadThroughput;
	private int uploadMaxThroughput;
	private int uploadThroughput;
	private List<TestingResultTraceSub> resultSubData;
	
	public String getResultSpeedId() {
		return resultSpeedId;
	}
	public void setResultSpeedId(String resultSpeedId) {
		this.resultSpeedId = resultSpeedId;
	}
	public int getDownloadMaxThroughput() {
		return downloadMaxThroughput;
	}
	public void setDownloadMaxThroughput(int downloadMaxThroughput) {
		this.downloadMaxThroughput = downloadMaxThroughput;
	}
	public int getDownloadThroughput() {
		return downloadThroughput;
	}
	public void setDownloadThroughput(int downloadThroughput) {
		this.downloadThroughput = downloadThroughput;
	}
	public int getUploadMaxThroughput() {
		return uploadMaxThroughput;
	}
	public void setUploadMaxThroughput(int uploadMaxThroughput) {
		this.uploadMaxThroughput = uploadMaxThroughput;
	}
	public int getUploadThroughput() {
		return uploadThroughput;
	}
	public void setUploadThroughput(int uploadThroughput) {
		this.uploadThroughput = uploadThroughput;
	}
	public List<TestingResultTraceSub> getResultSubData() {
		return resultSubData;
	}
	public void setResultSubData(List<TestingResultTraceSub> resultSubData) {
		this.resultSubData = resultSubData;
	}
	
	
}
