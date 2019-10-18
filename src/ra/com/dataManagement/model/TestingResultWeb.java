package ra.com.dataManagement.model;

import java.util.List;

public class TestingResultWeb {
	private String resultWebId;
	private String hostIp;
	private String requestUrl;
	private int resolveTime;
	private int connectTime;
	private int firstByteTime;
	private int firstPageTime;
	private int meanQuality;
	private int responseCode;
	private int throughput;
	private int totalTime;
	//private List<TestingResultTraceSub> resultSubData;
	public String getResultWebId() {
		return resultWebId;
	}
	public void setResultWebId(String resultWebId) {
		this.resultWebId = resultWebId;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public int getResolveTime() {
		return resolveTime;
	}
	public void setResolveTime(int resolveTime) {
		this.resolveTime = resolveTime;
	}
	public int getConnectTime() {
		return connectTime;
	}
	public void setConnectTime(int connectTime) {
		this.connectTime = connectTime;
	}
	public int getFirstByteTime() {
		return firstByteTime;
	}
	public void setFirstByteTime(int firstByteTime) {
		this.firstByteTime = firstByteTime;
	}
	public int getFirstPageTime() {
		return firstPageTime;
	}
	public void setFirstPageTime(int firstPageTime) {
		this.firstPageTime = firstPageTime;
	}
	public int getMeanQuality() {
		return meanQuality;
	}
	public void setMeanQuality(int meanQuality) {
		this.meanQuality = meanQuality;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public int getThroughput() {
		return throughput;
	}
	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	/*public List<TestingResultTraceSub> getResultSubData() {
		return resultSubData;
	}
	public void setResultSubData(List<TestingResultTraceSub> resultSubData) {
		this.resultSubData = resultSubData;
	}*/
	
	
}
