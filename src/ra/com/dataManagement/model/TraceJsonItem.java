package ra.com.dataManagement.model;

public class TraceJsonItem {
	private int payloadSize;
	private int protocolType;
	private int maxHops;
	private int replyTimeout;
	private int packetCount;
	private int spacingTime;
	private int tos;
	private int hopTrimMaxCount = 0;
	private int hopTrimMinCount = 0;
	private boolean onlyTestingAndSummary = true;
	private boolean simplePath  =true;
	private boolean testMiddleRouter = false;
	private boolean whetherStatistical = true;
	private String optimizations = "{\"indexNameList\":[],\"conditionList\":[]}";
	public int getPayloadSize() {
		return payloadSize;
	}
	public void setPayloadSize(int payloadSize) {
		this.payloadSize = payloadSize;
	}
	public int getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(int protocolType) {
		this.protocolType = protocolType;
	}
	public int getMaxHops() {
		return maxHops;
	}
	public void setMaxHops(int maxHops) {
		this.maxHops = maxHops;
	}
	public int getReplyTimeout() {
		return replyTimeout;
	}
	public void setReplyTimeout(int replyTimeout) {
		this.replyTimeout = replyTimeout;
	}
	public int getPacketCount() {
		return packetCount;
	}
	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
	}
	public int getSpacingTime() {
		return spacingTime;
	}
	public void setSpacingTime(int spacingTime) {
		this.spacingTime = spacingTime;
	}
	public int getTos() {
		return tos;
	}
	public void setTos(int tos) {
		this.tos = tos;
	}
	public int getHopTrimMaxCount() {
		return hopTrimMaxCount;
	}
	public void setHopTrimMaxCount(int hopTrimMaxCount) {
		this.hopTrimMaxCount = hopTrimMaxCount;
	}
	public int getHopTrimMinCount() {
		return hopTrimMinCount;
	}
	public void setHopTrimMinCount(int hopTrimMinCount) {
		this.hopTrimMinCount = hopTrimMinCount;
	}
	public boolean isOnlyTestingAndSummary() {
		return onlyTestingAndSummary;
	}
	public void setOnlyTestingAndSummary(boolean onlyTestingAndSummary) {
		this.onlyTestingAndSummary = onlyTestingAndSummary;
	}
	public boolean isSimplePath() {
		return simplePath;
	}
	public void setSimplePath(boolean simplePath) {
		this.simplePath = simplePath;
	}
	public boolean isTestMiddleRouter() {
		return testMiddleRouter;
	}
	public void setTestMiddleRouter(boolean testMiddleRouter) {
		this.testMiddleRouter = testMiddleRouter;
	}
	public boolean isWhetherStatistical() {
		return whetherStatistical;
	}
	public void setWhetherStatistical(boolean whetherStatistical) {
		this.whetherStatistical = whetherStatistical;
	}
	public String getOptimizations() {
		return optimizations;
	}
	public void setOptimizations(String optimizations) {
		this.optimizations = optimizations;
	}
	
	
}
