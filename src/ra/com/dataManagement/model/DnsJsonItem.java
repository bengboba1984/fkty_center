package ra.com.dataManagement.model;

import java.util.Map;

public class DnsJsonItem {
	private int additionalAdvPing = 0;
	private int additionalPing = 0;
	private int additionalTrace = 0;
	private boolean excludeFailed = false;
	private int ignoreCount = 0;
	private boolean onlyTestingAndSummary = true;
	private int packetCount;
	private int packetTimeout;
	private int roundItemCount;
	private boolean saveSubResult = true;
	private int spacingTime;
	private String optimizations = "{\"indexNameList\":[],\"conditionList\":[]}";
	
	public boolean isExcludeFailed() {
		return excludeFailed;
	}
	public void setExcludeFailed(boolean excludeFailed) {
		this.excludeFailed = excludeFailed;
	}
	
	public boolean isOnlyTestingAndSummary() {
		return onlyTestingAndSummary;
	}
	public void setOnlyTestingAndSummary(boolean onlyTestingAndSummary) {
		this.onlyTestingAndSummary = onlyTestingAndSummary;
	}
	
	public boolean isSaveSubResult() {
		return saveSubResult;
	}
	public void setSaveSubResult(boolean saveSubResult) {
		this.saveSubResult = saveSubResult;
	}

	public int getAdditionalAdvPing() {
		return additionalAdvPing;
	}
	public void setAdditionalAdvPing(int additionalAdvPing) {
		this.additionalAdvPing = additionalAdvPing;
	}
	public int getAdditionalPing() {
		return additionalPing;
	}
	public void setAdditionalPing(int additionalPing) {
		this.additionalPing = additionalPing;
	}
	public int getAdditionalTrace() {
		return additionalTrace;
	}
	public void setAdditionalTrace(int additionalTrace) {
		this.additionalTrace = additionalTrace;
	}
	public int getIgnoreCount() {
		return ignoreCount;
	}
	public void setIgnoreCount(int ignoreCount) {
		this.ignoreCount = ignoreCount;
	}
	public int getPacketCount() {
		return packetCount;
	}
	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
	}
	public int getPacketTimeout() {
		return packetTimeout;
	}
	public void setPacketTimeout(int packetTimeout) {
		this.packetTimeout = packetTimeout;
	}
	public int getRoundItemCount() {
		return roundItemCount;
	}
	public void setRoundItemCount(int roundItemCount) {
		this.roundItemCount = roundItemCount;
	}
	public int getSpacingTime() {
		return spacingTime;
	}
	public void setSpacingTime(int spacingTime) {
		this.spacingTime = spacingTime;
	}
	public String getOptimizations() {
		return optimizations;
	}
	public void setOptimizations(String optimizations) {
		this.optimizations = optimizations;
	}
	
	
}
