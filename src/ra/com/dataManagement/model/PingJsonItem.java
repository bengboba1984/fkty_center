package ra.com.dataManagement.model;

public class PingJsonItem {
	private String packetCount = "";
	private String spaceingTime = "";
	private String packetTimeout = "";
	private String payloadSize = "";
	private String payloadData = "";
	private String maxTtl = "";
	private String tos = "";
	private String saveIpResult = "";
	private String allLossAsFail = "";
	private String roundTrip = "";
	private String additionalAdvPing = "0";
	private String additionalPing = "0";
	private String additionalTrace = "0";
	private String captureServerUrl = "";
	private String dynamicBind = "false";
	private String exportLocaitonInfo = "false";
	private String onlyTestingAndSummary = "true";
	private String saveSubData = "0";
	private String subDataMinInterval = "20000";
	private String subDataStatisticsInterval = "0,1000,2000,3000,4000,5000";
	private String thresholdTriggerAddTest = "false";
	
	private String optimizations = "{\"indexNameList\":[],\"conditionList\":[]}";
	public String getPacketCount() {
		return packetCount;
	}
	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
	}
	public String getSpaceingTime() {
		return spaceingTime;
	}
	public void setSpaceingTime(String spaceingTime) {
		this.spaceingTime = spaceingTime;
	}
	public String getPacketTimeout() {
		return packetTimeout;
	}
	public void setPacketTimeout(String packetTimeout) {
		this.packetTimeout = packetTimeout;
	}
	public String getPayloadSize() {
		return payloadSize;
	}
	public void setPayloadSize(String payloadSize) {
		this.payloadSize = payloadSize;
	}
	public String getPayloadData() {
		return payloadData;
	}
	public void setPayloadData(String payloadData) {
		this.payloadData = payloadData;
	}
	public String getMaxTtl() {
		return maxTtl;
	}
	public void setMaxTtl(String maxTtl) {
		this.maxTtl = maxTtl;
	}
	public String getTos() {
		return tos;
	}
	public void setTos(String tos) {
		this.tos = tos;
	}
	public String getOptimizations() {
		return optimizations;
	}
	public void setOptimizations(String optimizations) {
		this.optimizations = optimizations;
	}
	public String getSaveIpResult() {
		return saveIpResult;
	}
	public void setSaveIpResult(String saveIpResult) {
		this.saveIpResult = saveIpResult;
	}
	public String getAllLossAsFail() {
		return allLossAsFail;
	}
	public void setAllLossAsFail(String allLossAsFail) {
		this.allLossAsFail = allLossAsFail;
	}
	public String getRoundTrip() {
		return roundTrip;
	}
	public void setRoundTrip(String roundTrip) {
		this.roundTrip = roundTrip;
	}
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
	public String getCaptureServerUrl() {
		return captureServerUrl;
	}
	public void setCaptureServerUrl(String captureServerUrl) {
		this.captureServerUrl = captureServerUrl;
	}
	public String getDynamicBind() {
		return dynamicBind;
	}
	public void setDynamicBind(String dynamicBind) {
		this.dynamicBind = dynamicBind;
	}
	public String getExportLocaitonInfo() {
		return exportLocaitonInfo;
	}
	public void setExportLocaitonInfo(String exportLocaitonInfo) {
		this.exportLocaitonInfo = exportLocaitonInfo;
	}
	public String getOnlyTestingAndSummary() {
		return onlyTestingAndSummary;
	}
	public void setOnlyTestingAndSummary(String onlyTestingAndSummary) {
		this.onlyTestingAndSummary = onlyTestingAndSummary;
	}
	public String getSaveSubData() {
		return saveSubData;
	}
	public void setSaveSubData(String saveSubData) {
		this.saveSubData = saveSubData;
	}
	public String getSubDataMinInterval() {
		return subDataMinInterval;
	}
	public void setSubDataMinInterval(String subDataMinInterval) {
		this.subDataMinInterval = subDataMinInterval;
	}
	public String getSubDataStatisticsInterval() {
		return subDataStatisticsInterval;
	}
	public void setSubDataStatisticsInterval(String subDataStatisticsInterval) {
		this.subDataStatisticsInterval = subDataStatisticsInterval;
	}
	public String getThresholdTriggerAddTest() {
		return thresholdTriggerAddTest;
	}
	public void setThresholdTriggerAddTest(String thresholdTriggerAddTest) {
		this.thresholdTriggerAddTest = thresholdTriggerAddTest;
	}
	
	
}
