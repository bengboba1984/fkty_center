package ra.com.dataManagement.model;

public class TraceJsonItem {
	private String payloadSize;
	private String protocolType;
	private String maxHops;
	private String replyTimeout;
	private String packetCount;
	private String spacingTime;
	private String tos;
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
	
	
}
