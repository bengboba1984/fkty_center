package ra.com.dataManagement.model;

import java.util.List;

public class TestingResultPing {
	private String resultPingId;
	private String avgDelay;
	private String avgJitter;
	private String hostIp;
	private String lossPercent;
	private List<TestingResultTraceSub> resultSubData;
	
	public String getResultPingId() {
		return resultPingId;
	}
	public void setResultPingId(String resultPingId) {
		this.resultPingId = resultPingId;
	}
	
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getAvgDelay() {
		return avgDelay;
	}
	public void setAvgDelay(String avgDelay) {
		this.avgDelay = avgDelay;
	}
	public String getAvgJitter() {
		return avgJitter;
	}
	public void setAvgJitter(String avgJitter) {
		this.avgJitter = avgJitter;
	}
	public String getLossPercent() {
		return lossPercent;
	}
	public void setLossPercent(String lossPercent) {
		this.lossPercent = lossPercent;
	}
	public List<TestingResultTraceSub> getResultSubData() {
		return resultSubData;
	}
	public void setResultSubData(List<TestingResultTraceSub> resultSubData) {
		this.resultSubData = resultSubData;
	}
	
	
}
