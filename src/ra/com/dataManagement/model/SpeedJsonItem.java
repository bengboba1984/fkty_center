package ra.com.dataManagement.model;

import java.util.Map;

public class SpeedJsonItem {
	private String continueTimes = "";
	private String downloadSize = "";
	private String hostIps = "";
	private String jitterThroughput = "";
	private String maxTestTime = "";
	private String minTestTime = "";
	private boolean onlyTestingAndSummary = true;
	private String payloadSize = "";
	private String requestPieceSize = "";
	private String requestTimeout = "";
	private String optimizations = "{}";
	public String getContinueTimes() {
		return continueTimes;
	}
	public void setContinueTimes(String continueTimes) {
		this.continueTimes = continueTimes;
	}
	public String getDownloadSize() {
		return downloadSize;
	}
	public void setDownloadSize(String downloadSize) {
		this.downloadSize = downloadSize;
	}
	public String getHostIps() {
		return hostIps;
	}
	public void setHostIps(String hostIps) {
		this.hostIps = hostIps;
	}
	public String getJitterThroughput() {
		return jitterThroughput;
	}
	public void setJitterThroughput(String jitterThroughput) {
		this.jitterThroughput = jitterThroughput;
	}
	public String getMaxTestTime() {
		return maxTestTime;
	}
	public void setMaxTestTime(String maxTestTime) {
		this.maxTestTime = maxTestTime;
	}
	public String getMinTestTime() {
		return minTestTime;
	}
	public void setMinTestTime(String minTestTime) {
		this.minTestTime = minTestTime;
	}
	public boolean isOnlyTestingAndSummary() {
		return onlyTestingAndSummary;
	}
	public void setOnlyTestingAndSummary(boolean onlyTestingAndSummary) {
		this.onlyTestingAndSummary = onlyTestingAndSummary;
	}
	public String getPayloadSize() {
		return payloadSize;
	}
	public void setPayloadSize(String payloadSize) {
		this.payloadSize = payloadSize;
	}
	public String getRequestPieceSize() {
		return requestPieceSize;
	}
	public void setRequestPieceSize(String requestPieceSize) {
		this.requestPieceSize = requestPieceSize;
	}
	public String getRequestTimeout() {
		return requestTimeout;
	}
	public void setRequestTimeout(String requestTimeout) {
		this.requestTimeout = requestTimeout;
	}
	public String getOptimizations() {
		return optimizations;
	}
	public void setOptimizations(String optimizations) {
		this.optimizations = optimizations;
	}
		
}
