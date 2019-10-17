package ra.com.dataManagement.model;

public class TargetJsonItem {
	private String defaultCheck = "on";
	private String deviceIp = "";
	private String group = "";
	private int id;
	private String linkAttributes="{}";
	private Object links = new Object();
	private String name;
	private String nodeIp;
	private int rank;
	private String attributes="{ }";
	public String getDefaultCheck() {
		return defaultCheck;
	}
	public void setDefaultCheck(String defaultCheck) {
		this.defaultCheck = defaultCheck;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}

	public String getLinkAttributes() {
		return linkAttributes;
	}
	public void setLinkAttributes(String linkAttributes) {
		this.linkAttributes = linkAttributes;
	}
	public Object getLinks() {
		return links;
	}
	public void setLinks(Object links) {
		this.links = links;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNodeIp() {
		return nodeIp;
	}
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	
}
