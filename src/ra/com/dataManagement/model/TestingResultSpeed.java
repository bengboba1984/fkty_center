package ra.com.dataManagement.model;

import java.util.List;

public class TestingResultSpeed {
	private String resultSpeedId;
	private int downloadMaxThroughput;
	private String downloadThroughput;
	private int uploadMaxThroughput;
	private String uploadThroughput;
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

	public int getUploadMaxThroughput() {
		return uploadMaxThroughput;
	}
	public void setUploadMaxThroughput(int uploadMaxThroughput) {
		this.uploadMaxThroughput = uploadMaxThroughput;
	}

	public String getUploadThroughput() {
		return uploadThroughput;
	}
	public void setUploadThroughput(String uploadThroughput) {
		this.uploadThroughput = uploadThroughput;
	}
	public List<TestingResultTraceSub> getResultSubData() {
		return resultSubData;
	}
	public void setResultSubData(List<TestingResultTraceSub> resultSubData) {
		this.resultSubData = resultSubData;
	}
	public String getDownloadThroughput() {
		return downloadThroughput;
	}
	public void setDownloadThroughput(String downloadThroughput) {
		this.downloadThroughput = downloadThroughput;
	}
	
	
}
