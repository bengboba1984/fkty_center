package ra.com.system_mgt.model;

import java.util.HashMap;

public class UserManagement implements java.io.Serializable{
	private Long userID;
	private String userName;
	private String gender;
	private String birthday;
	private String hireDate;
	private String degree;
	private String positionID;
	private String positionName;
	private Long departmentId;
	private String departmentName;
	private String telephoneNumber;
	private String emergencyContact;
	private String address;
	private String IDNumber;
	private String memo;
	private String password;
	private String email;
	private String workId;
//	private HashMap moduleList;
	private String visible;
	private String departureDate;
	private String departmentManager;
	private String hiddenPositionID;
	private String companyName;
	private String position;
	private String jobTitle;
	private String phoneNumber;
	private String purposeData;
	private String registerStatusKey;
	private String registerStatusValue;
	
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPositionID() {
		return positionID;
	}

	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName
				+ ", gender=" + gender + ", birthday=" + birthday
				+ ", hireDate=" + hireDate + ", degree=" + degree
				+ ", positionID=" + positionID + ", positionName="
				+ positionName + ", departmentId=" + departmentId
				+ ", departmentName=" + departmentName + ", telephoneNumber="
				+ telephoneNumber + ", emergencyContact=" + emergencyContact
				+ ", address=" + address + ", IDNumber=" + IDNumber + ", memo="
				+ memo + ", password=" + password + "]";
	}

	
	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartmentManager() {
		return departmentManager;
	}

	public void setDepartmentManager(String departmentManager) {
		this.departmentManager = departmentManager;
	}

	public String getHiddenPositionID() {
		return hiddenPositionID;
	}

	public void setHiddenPositionID(String hiddenPositionID) {
		this.hiddenPositionID = hiddenPositionID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPurposeData() {
		return purposeData;
	}

	public void setPurposeData(String purposeData) {
		this.purposeData = purposeData;
	}

	public String getRegisterStatusKey() {
		return registerStatusKey;
	}

	public void setRegisterStatusKey(String registerStatusKey) {
		this.registerStatusKey = registerStatusKey;
	}

	public String getRegisterStatusValue() {
		return registerStatusValue;
	}

	public void setRegisterStatusValue(String registerStatusValue) {
		this.registerStatusValue = registerStatusValue;
	}

	

}
