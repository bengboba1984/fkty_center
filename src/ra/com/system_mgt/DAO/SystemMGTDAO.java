package ra.com.system_mgt.DAO;

import java.util.Collection;
import java.util.List;

import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
import ra.com.system_mgt.model.Position;
import ra.com.system_mgt.model.User;

public interface SystemMGTDAO {

	// department management begin
	public List getDepartmentList(List param) throws GenericDAOException;
	
	public boolean checkDepartment(String departmentName,Long departmentID) throws GenericDAOException;

	public List getDepartmentNameByPositionID(String id)
			throws GenericDAOException;

	public List getUserListInDepartment() throws GenericDAOException;

	public boolean insertDepartment(List paramList) throws GenericDAOException;

	public boolean deleteDepartment(List paramList) throws GenericDAOException;

	public boolean updateDepartment(List paramList) throws GenericDAOException;

	// department management end

	// position management begin
	public List getPositionList(List param) throws GenericDAOException;
	
	public ListChunk getPositionForSystemList(Position pos) throws GenericDAOException;

	public boolean checkPosition(String positionName)
			throws GenericDAOException;

	public List getDepartmentListInPosition() throws GenericDAOException;

	public List getModuleListByPositionID(String positionID)
			throws GenericDAOException;

	public boolean insertPosition(List paramList) throws GenericDAOException;

	public boolean deletePosition(List paramList) throws GenericDAOException;

	public boolean updatePosition(List paramList) throws GenericDAOException;

	public boolean assignRole(String positionID, List moduleList)
			throws GenericDAOException;

	// position management end

	// user management begin
	public List getUserList(List param) throws GenericDAOException;

	public ListChunk getUserList(String userIDSearch,String userNameSearch,String hireDateSearch,String visibleSearch,String roleId) throws GenericDAOException;
	
	public List getPositionListInUser() throws GenericDAOException;

	public boolean insertUser(List paramList) throws GenericDAOException;

	public boolean deleteUser(List paramList) throws GenericDAOException;

	public boolean updateUser(List updateList) throws GenericDAOException;

	// user management end

	
	public ListChunk showRoleList(List param,String roleId,int pageNo, int pageSize)
			throws GenericDAOException;
	
	public ListChunk getRoleUserListForDataGrid(long roleId) throws GenericDAOException;
	
	public Collection getActionNode(long modelId,String positionId) throws GenericDAOException ;
	
	public String getPositionId(long userId)throws GenericDAOException;
	
	public Collection getModuleListByPositionId(String positionId,long id) throws GenericDAOException ;
	
	public Collection getApList() throws GenericDAOException ;
	
	public boolean positionModuleSave(String postionId,String moduleIds) throws GenericDAOException;
	
	public void registerUser(User u)throws GenericDAOException;
	
	public boolean registerUserApprove(List updateList) throws GenericDAOException;
	
	public ListChunk getUserPositionList(String positionID, String userNameSearch,String roleId) throws GenericDAOException;
	
	public Collection showAccessCollectorList(String weatherNo)throws GenericDAOException;
	
	public boolean updateAccessCollector(String weatherStationNo,String collectorNo,String effectiveDate,String expireDate,String userId )throws GenericDAOException;
	
	public List getAccessCollectorForUser(String userId)throws GenericDAOException;
	
	public boolean restoreUser(User u) throws GenericDAOException;
	
	public List getEmailForAdmin()throws GenericDAOException;
	
	public ListChunk getActionRole(String moduleId,String positionId)throws GenericDAOException;
}
