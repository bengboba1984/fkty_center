package ra.com.system_mgt.model;

public class Department {
	private Long departmentID;
	private String departmentName;
	private String departmentManagerName;
	private Long departmentManager;
	
	public String getDepartmentManagerName() {
		return departmentManagerName;
	}
	public void setDepartmentManagerName(String departmentManagerName) {
		this.departmentManagerName = departmentManagerName;
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
	public Long getDepartmentManager() {
		return departmentManager;
	}
	public void setDepartmentManager(Long departmentManager) {
		this.departmentManager = departmentManager;
	}
	@Override
	public String toString() {
		return "Department [departmentID=" + departmentID + ", departmentName="
				+ departmentName + ", departmentManagerName="
				+ departmentManagerName + ", departmentManager="
				+ departmentManager + "]";
	}

	
}
