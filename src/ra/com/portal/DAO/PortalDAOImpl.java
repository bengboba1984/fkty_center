package ra.com.portal.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ra.com.common.Const;
import ra.com.common.dao.GenericDAO;
import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
import ra.com.system_mgt.model.User;


public class PortalDAOImpl extends GenericDAO implements PortalDAO {

	public PortalDAOImpl() throws GenericDAOException {
		
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkPasswordByUserName(String userName, String password)
			throws GenericDAOException {
		List pmList=new ArrayList();
		pmList.add(userName);
		pmList.add(userName);
		pmList.add(password);
		Object flag=queryOne("SELECT 1 FROM wasu.bs_user a WHERE (a.user_name=? or a.telephone_number=?) AND password=?",pmList);
		return flag!=null?true:false;
	}

	@Override
	public ListChunk getUserListByProperty(User u) throws GenericDAOException {
		StringBuffer sql=new StringBuffer("SELECT a.user_ID,user_name,password,gender,birthday,"
				+ "hire_date,degree,min(p.role_id) department_ID,telephone_number,"
				+ "emergency_contact,address,ID_number,memo,GROUP_CONCAT(p.role_id) positionID "
				+ " FROM wasu.bs_user a left join wasu.account_position p ON a.user_id = p.user_id "
				+ " WHERE 1=1");
		ArrayList pl=new ArrayList();
		if(u.getUserID()!=null){
			sql.append(" AND a.user_id=?");
			pl.add(u.getUserID());
		}
		if(u.getUserName()!=null){
			sql.append(" AND a.user_Name=?");
			pl.add(u.getUserName());
		}
		if(u.getTelephoneNumber()!=null){
			sql.append(" AND a.telephone_number=?");
			pl.add(u.getTelephoneNumber());
		}
		if(u.getPassword()!=null){
			sql.append(" AND a.password=?");
			pl.add(u.getPassword());
		}
		sql.append(" GROUP BY a.user_id ");
		return getListChunkByProperty(sql.toString(), pl, 1, 5000, true,
                "ra.com.system_mgt.model.User"); 
	}

	public boolean changePassword(Long userID, String newPassword)
			throws GenericDAOException {
		ArrayList pl=new ArrayList();
		pl.add(newPassword);
		pl.add(userID);
		return simpleExecute("UPDATE wasu.bs_user SET password=? WHERE user_id=?", pl);
	}

	@Override
	public Collection getModuleListByUserId(long userId,long id)
			throws GenericDAOException {
		ArrayList p=new ArrayList();
		p.add(userId);
		StringBuffer sql = new StringBuffer("SELECT distinct\n" +
                           "    d.ap_name name,\n" +
                           "    a.module_name children_name,\n" +
                           "    a.action,\n" +
                           "    c.module_id children_id,\n" +
                           (id==-1?"d.ap_id":"-1") + 
                           "  id\n" +
                           "FROM\n" +
                           "    wasu.account_position b,\n" +
                           "    wasu.position_module c\n" +
                           (id==-1?"left ":"right ")+
                           "          join\n" +
                           "    wasu.bs_module a ON c.module_id = a.module_id,\n" +
                           "    wasu.bs_ap d\n" +
                           "where\n" +
                           "    b.user_id = ? \n" +
                           "        and b.role_id = c.position_id\n" +
                           "        and d.ap_id = c.ap_id\n" );
		if(id==-1){
			sql.append(" and c.module_id =  - 1 " );
		}else{
			sql.append(" and c.module_id <> - 1 and c.ap_id = ?  ");
			p.add(id);
		}
		sql.append( "order by seq_no,d.ap_name , a.module_name");
		return getListChunkByProperty(sql.toString(), p, 1, 500, true,
                "ra.com.portal.model.TreeNodeList").getCollection(); 
	}
	
	public boolean checkAdminByUserId(String userId)throws GenericDAOException {
		List<String> pmList=new ArrayList<String>();
		pmList.add(userId);
		Object flag=queryOne("select 1 from wasu.account_position c where c.user_id = ? and c.role_id in ("+Const.ADMIN_ROLE_ID+" ) ",pmList);
		return flag!=null?true:false;
	}
}
