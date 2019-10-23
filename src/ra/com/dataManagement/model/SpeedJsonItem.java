package ra.com.dataManagement.model;

import java.util.Map;

public class SpeedJsonItem {
	private int continueTimes ;
	private int downloadSize ;
	private String hostIps = "";
	private int jitterThroughput ;
	private int maxTestTime ;
	private int minTestTime ;
	private boolean onlyTestingAndSummary = true;
	private int payloadSize ;
	private int requestPieceSize ;
	private int requestTimeout ;
	private String optimizations = "{\"indexNameList\":[],\"conditionList\":[]}";
	public int getContinueTimes() {
		return continueTimes;
	}
	public void setContinueTimes(int continueTimes) {
		this.continueTimes = continueTimes;
	}
	public int getDownloadSize() {
		return downloadSize;
	}
	public void setDownloadSize(int downloadSize) {
		this.downloadSize = downloadSize;
	}
	public String getHostIps() {
		return hostIps;
	}
	public void setHostIps(String hostIps) {
		this.hostIps = hostIps;
	}
	public int getJitterThroughput() {
		return jitterThroughput;
	}
	public void setJitterThroughput(int jitterThroughput) {
		this.jitterThroughput = jitterThroughput;
	}
	public int getMaxTestTime() {
		return maxTestTime;
	}
	public void setMaxTestTime(int maxTestTime) {
		this.maxTestTime = maxTestTime;
	}
	public int getMinTestTime() {
		return minTestTime;
	}
	public void setMinTestTime(int minTestTime) {
		this.minTestTime = minTestTime;
	}
	public boolean isOnlyTestingAndSummary() {
		return onlyTestingAndSummary;
	}
	public void setOnlyTestingAndSummary(boolean onlyTestingAndSummary) {
		this.onlyTestingAndSummary = onlyTestingAndSummary;
	}
	public int getPayloadSize() {
		return payloadSize;
	}
	public void setPayloadSize(int payloadSize) {
		this.payloadSize = payloadSize;
	}
	public int getRequestPieceSize() {
		return requestPieceSize;
	}
	public void setRequestPieceSize(int requestPieceSize) {
		this.requestPieceSize = requestPieceSize;
	}
	public int getRequestTimeout() {
		return requestTimeout;
	}
	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
	}
	public String getOptimizations() {
		return optimizations;
	}
	public void setOptimizations(String optimizations) {
		this.optimizations = optimizations;
	}
	
}
