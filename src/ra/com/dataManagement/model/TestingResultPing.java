package ra.com.dataManagement.model;

import java.util.List;

public class TestingResultPing {
	private String resultPingId;
	private int avgDelay;
	private int avgJitter;
	private String hostIp;
	private int lossPercent;
	private List<TestingResultTraceSub> resultSubData;
	
	public String getResultPingId() {
		return resultPingId;
	}
	public void setResultPingId(String resultPingId) {
		this.resultPingId = resultPingId;
	}
	public int getAvgDelay() {
		return avgDelay;
	}
	public void setAvgDelay(int avgDelay) {
		this.avgDelay = avgDelay;
	}
	public int getAvgJitter() {
		return avgJitter;
	}
	public void setAvgJitter(int avgJitter) {
		this.avgJitter = avgJitter;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public int getLossPercent() {
		return lossPercent;
	}
	public void setLossPercent(int lossPercent) {
		this.lossPercent = lossPercent;
	}
	public List<TestingResultTraceSub> getResultSubData() {
		return resultSubData;
	}
	public void setResultSubData(List<TestingResultTraceSub> resultSubData) {
		this.resultSubData = resultSubData;
	}
	
	
}
