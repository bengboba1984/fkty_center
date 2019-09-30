package ra.com.system_mgt.model;

public class Position {
	private Long positionID;
	private String positionName;
	private String departmentName;
	private Long departmentID;
	
	public Long getPositionID() {
		return positionID;
	}
	public void setPositionID(Long positionID) {
		this.positionID = positionID;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(Long departmentID) {
		this.departmentID = departmentID;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		return "Position [positionID=" + positionID + ", positionName="
				+ positionName + ", departmentName=" + departmentName
				+ ", departmentID=" + departmentID + "]";
	}


	
}
