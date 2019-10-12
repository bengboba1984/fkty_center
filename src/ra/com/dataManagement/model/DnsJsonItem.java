package ra.com.dataManagement.model;

import java.util.Map;

public class DnsJsonItem {
	private String additionalAdvPing = "0";
	private String additionalPing = "0";
	private String additionalTrace = "0";
	private boolean excludeFailed = false;
	private String ignoreCount = "0";
	private boolean onlyTestingAndSummary = true;
	private String packetCount;
	private String packetTimeout;
	private String roundItemCount;
	private boolean saveSubResult = true;
	private String spacingTime;
	private String optimizations = "{\"indexNameList\":[],\"conditionList\":[]}";
	public String getAdditionalAdvPing() {
		return additionalAdvPing;
	}
	public void setAdditionalAdvPing(String additionalAdvPing) {
		this.additionalAdvPing = additionalAdvPing;
	}
	public String getAdditionalPing() {
		return additionalPing;
	}
	public void setAdditionalPing(String additionalPing) {
		this.additionalPing = additionalPing;
	}
	public String getAdditionalTrace() {
		return additionalTrace;
	}
	public void setAdditionalTrace(String additionalTrace) {
		this.additionalTrace = additionalTrace;
	}
	public boolean isExcludeFailed() {
		return excludeFailed;
	}
	public void setExcludeFailed(boolean excludeFailed) {
		this.excludeFailed = excludeFailed;
	}
	public String getIgnoreCount() {
		return ignoreCount;
	}
	public void setIgnoreCount(String ignoreCount) {
		this.ignoreCount = ignoreCount;
	}
	public boolean isOnlyTestingAndSummary() {
		return onlyTestingAndSummary;
	}
	public void setOnlyTestingAndSummary(boolean onlyTestingAndSummary) {
		this.onlyTestingAndSummary = onlyTestingAndSummary;
	}
	public String getPacketCount() {
		return packetCount;
	}
	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
	}
	public String getPacketTimeout() {
		return packetTimeout;
	}
	public void setPacketTimeout(String packetTimeout) {
		this.packetTimeout = packetTimeout;
	}
	public String getRoundItemCount() {
		return roundItemCount;
	}
	public void setRoundItemCount(String roundItemCount) {
		this.roundItemCount = roundItemCount;
	}
	public boolean isSaveSubResult() {
		return saveSubResult;
	}
	public void setSaveSubResult(boolean saveSubResult) {
		this.saveSubResult = saveSubResult;
	}
	public String getSpacingTime() {
		return spacingTime;
	}
	public void setSpacingTime(String spacingTime) {
		this.spacingTime = spacingTime;
	}
	public String getOptimizations() {
		return optimizations;
	}
	public void setOptimizations(String optimizations) {
		this.optimizations = optimizations;
	}
	
	
}
