package ra.com.dataManagement.model;

public class PingJsonItem {
	private String packetCount;
	private String spaceingTime;
	private String packetTimeout;
	private String payloadSize;
	private String payloadData;
	private String maxTtl;
	private String tos;
	private String saveIpResult;
	private String allLossAsFail;
	private String roundTrip;
	
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
	
	
}
