package ra.com.dataManagement.model;

public class TraceJsonItem {
	private String payloadSize;
	private String protocolType;
	private String maxHops;
	private String replyTimeout;
	private String packetCount;
	private String spacingTime;
	private String tos;
	private String hopTrimMaxCount = "0";
	private String hopTrimMinCount = "0";
	private String onlyTestingAndSummary = "true";
	private String simplePath  ="true";
	private String testMiddleRouter = "false";
	private String whetherStatistical = "true";
	private String optimizations = "{\"indexNameList\":[],\"conditionList\":[]}";
	public String getPayloadSize() {
		return payloadSize;
	}
	public void setPayloadSize(String payloadSize) {
		this.payloadSize = payloadSize;
	}
	public String getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}
	public String getMaxHops() {
		return maxHops;
	}
	public void setMaxHops(String maxHops) {
		this.maxHops = maxHops;
	}
	public String getReplyTimeout() {
		return replyTimeout;
	}
	public void setReplyTimeout(String replyTimeout) {
		this.replyTimeout = replyTimeout;
	}
	public String getPacketCount() {
		return packetCount;
	}
	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
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
	public String getTos() {
		return tos;
	}
	public void setTos(String tos) {
		this.tos = tos;
	}
	public String getHopTrimMaxCount() {
		return hopTrimMaxCount;
	}
	public void setHopTrimMaxCount(String hopTrimMaxCount) {
		this.hopTrimMaxCount = hopTrimMaxCount;
	}
	public String getHopTrimMinCount() {
		return hopTrimMinCount;
	}
	public void setHopTrimMinCount(String hopTrimMinCount) {
		this.hopTrimMinCount = hopTrimMinCount;
	}
	public String getOnlyTestingAndSummary() {
		return onlyTestingAndSummary;
	}
	public void setOnlyTestingAndSummary(String onlyTestingAndSummary) {
		this.onlyTestingAndSummary = onlyTestingAndSummary;
	}
	public String getSimplePath() {
		return simplePath;
	}
	public void setSimplePath(String simplePath) {
		this.simplePath = simplePath;
	}
	public String getTestMiddleRouter() {
		return testMiddleRouter;
	}
	public void setTestMiddleRouter(String testMiddleRouter) {
		this.testMiddleRouter = testMiddleRouter;
	}
	public String getWhetherStatistical() {
		return whetherStatistical;
	}
	public void setWhetherStatistical(String whetherStatistical) {
		this.whetherStatistical = whetherStatistical;
	}
	
	
}
