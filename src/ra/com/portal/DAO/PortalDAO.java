package ra.com.portal.DAO;

import java.util.Collection;

import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
import ra.com.system_mgt.model.User;

public interface PortalDAO {

	public boolean checkPasswordByUserName(String userName, String password)
			throws GenericDAOException;

	public ListChunk getUserListByProperty(User u) throws GenericDAOException;

	public boolean changePassword(Long userID, String newPassword)
			throws GenericDAOException;
	
	public Collection getModuleListByUserId(long userId,long id) throws GenericDAOException;
	
	public boolean checkAdminByUserId(String userId)throws GenericDAOException;
}
