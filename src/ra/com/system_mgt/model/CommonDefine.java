package ra.com.system_mgt.model;

public class CommonDefine {
	private Long defID;
	private String type;
	private String typeName;
	private String value;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public Long getDefID() {
		return defID;
	}
	public void setDefID(Long defID) {
		this.defID = defID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
