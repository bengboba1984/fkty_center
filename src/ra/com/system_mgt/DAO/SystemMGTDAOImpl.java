package ra.com.system_mgt.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ra.com.common.Const;
import ra.com.common.dao.GenericDAO;
import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
import ra.com.system_mgt.model.MyTreeNode;
import ra.com.system_mgt.model.Position;
import ra.com.system_mgt.model.User;
import ra.com.system_mgt.model.UserManagement;

public class SystemMGTDAOImpl extends GenericDAO implements SystemMGTDAO {

	public SystemMGTDAOImpl() throws GenericDAOException {

		super();
		// TODO Auto-generated constructor stub
	}

	// department management begin
	@Override
	public List getDepartmentList(List param) throws GenericDAOException {
		StringBuffer sql = new StringBuffer(
				"SELECT a.department_id AS departmentID,a.department_name as departmentName,"
						+ "a.department_manager as departmentManager,  "
						+ "(select b.user_name from jxc.bs_user b where b.user_id=a.department_manager) as departmentManagerName  "
						+ "FROM wasu.bs_department a ");

		if (param != null) {
			Iterator it = param.iterator();
			while (it.hasNext()) {
				sql.append(" WHERE a.department_name=? ");
				it.next();
			}
		}
		sql.append(" ORDER BY 1 ");
		List rs = query(sql.toString(), param);
		return rs;
	}

	@Override
	public List getUserListInDepartment() throws GenericDAOException {
		List rs = query(
				"SELECT user_id as userID ,user_name as userName  FROM wasu.bs_user ",
				null);
		return rs;
	}

	@Override
	public boolean insertDepartment(List paramList) throws GenericDAOException {
		int[] result = simpleExecuteBatch(
				"INSERT INTO wasu.bs_department(department_id,department_name,department_manager)  SELECT IFNULL(MAX(department_id),0)+1 , ?,? FROM wasu.bs_department ",
				paramList);
		return true;
	}

	@Override
	public boolean deleteDepartment(List paramList) throws GenericDAOException {
		
		
		return true;
	}

	@Override
	public boolean updateDepartment(List paramList) throws GenericDAOException {
		int[] result = simpleExecuteBatch(
				"UPDATE jxc.bs_department SET department_name=? , department_manager=? WHERE department_id=? ",
				paramList);
		return true;
	}

	// department management end

	@Override
	public List getPositionList(List param) throws GenericDAOException {
		StringBuffer sql = new StringBuffer(
				""
						+ "SELECT b.department_name AS departmentName,a.department_id AS departmentID,a.position_id AS positionID,a.position_name AS positionName "
						+ "FROM jxc.bs_position a,jxc.bs_department b "
						+ "WHERE a.department_id = b.department_id");
		if (param != null) {
			Iterator it = param.iterator();
			while (it.hasNext()) {
				sql.append(" AND a.position_name=?");
				it.next();
			}
		}
		sql.append(" ORDER BY b.department_name ");
		List rs = query(sql.toString(), param);
		return rs;
	}
	
	@Override
	public ListChunk getPositionForSystemList(Position param) throws GenericDAOException {
		StringBuffer sql = new StringBuffer(
				""
						+ "SELECT b.department_name AS departmentName,a.department_id AS departmentID,a.position_id AS positionID,a.position_name AS positionName "
						+ "FROM jxc.bs_position a,jxc.bs_department b "
						+ "WHERE a.department_id = b.department_id");
		
		ArrayList al = new ArrayList();
		if (param != null) {
			if(param.getPositionName() != null && !"".equals(param.getPositionName())){
				sql.append(" 	and 	a.position_name like ?");
				al.add(param.getPositionName().trim()+"%");
			}
			if(param.getDepartmentName() != null && !"".equals(param.getDepartmentName())){
				sql.append(" 	and 	b.department_name like ?");
				al.add(param.getDepartmentName().trim()+"%");
			}
		}
		sql.append(" ORDER BY b.department_name ");
		return getListChunkByProperty(sql.toString(), al, 1, 50000,
				true, "ra.com.system_mgt.model.Position");
	}

	public List getDepartmentListInPosition() throws GenericDAOException {
		List rs = query(
				"SELECT department_id as departmentID ,department_name as departmentName  FROM jxc.bs_department ",
				null);
		return rs;
	}

	@Override
	public boolean insertPosition(List paramList) throws GenericDAOException {
		int[] result = simpleExecuteBatch(
				"INSERT INTO wasu.bs_position(id,position_name,status )  SELECT IFNULL(MAX(id),0)+1 , ?,'Y'  FROM wasu.bs_position ",
				paramList);
		return true;
	}

	@Override
	public boolean deletePosition(List paramList) throws GenericDAOException {
		int[] result = simpleExecuteBatch(
				"UPDATE wasu.bs_position SET status='N' WHERE id=? ", paramList);
		return true;
	}

	@Override
	public boolean updatePosition(List paramList) throws GenericDAOException {
		int[] result = simpleExecuteBatch(
				"UPDATE wasu.bs_position SET position_name=?   WHERE id =? ",
				paramList);
		return true;
	}

	@Override
	public List getUserList(List param) throws GenericDAOException {
		StringBuffer sql = new StringBuffer(
				"SELECT a.user_id as userID,department_id,(SELECT value FROM wasu.bs_common_def d where d.def_id=a.department_id and d.type='department') departmentName,"
						+ "a.user_name as userName,a.gender,date_format(a.birthday,'%Y-%m-%d') as birthday,"
						+ "date_format(a.hire_date,'%Y-%m-%d') as hireDate,a.degree,"
						+ "GROUP_CONCAT(a.position_id) positionID,"
						+ "GROUP_CONCAT(b.position_name) positionName," 
						+ "a.telephone_number as telephoneNumber,"
						+ "a.emergency_contact as emergencyContact,a.address,"
						+ "a.memo,a.email,work_id "
						//+ "(select e.user_name from ra.bs_department d,ra.bs_user e where a.department_id=d.department_id and d.department_manager=e.user_id) as manager "
						+ " FROM wasu.bs_user a LEFT JOIN wasu.bs_position b ON a.position_id = b.id "
						+ "		 WHERE 1=1");
		if (param != null) {
			Iterator it = param.iterator();
			while (it.hasNext()) {
				sql.append(" AND a.user_name=?");
				it.next();
			}
		}
		sql.append(" GROUP BY a.user_id ORDER BY user_name ");
		List rs = query(sql.toString(), param);
		return rs;
	}

	@Override
	public List getPositionListInUser() throws GenericDAOException {
		List rs = query(
				"SELECT position_id as positionID ,position_name as positionName  FROM jxc.bs_position ",
				null);
		return rs;
	}

	@Override
	public boolean insertUser(List insertList) throws GenericDAOException {

		StringBuffer sql = new StringBuffer(
				"INSERT INTO `wasu`.`bs_user` ("
						+ " `user_name`, `password`, `gender`, `birthday`, `hire_date`, "
						+ "`degree`, `telephone_number`, `address`,`work_id`, "
						+ " `memo`,`email` ,`user_id`,`visible`,"
						+ " `company_name`,`position`,`job_title`,`phone_number`,`full_name`,`department_id` )"
						+ " SELECT distinct "
						+ " ?, '0000', ?, if(?='',null,str_to_date(?,'%Y-%m-%d')), if(?='',null,str_to_date(?,'%Y-%m-%d')), "
						+ "?, ?, ?, " 
						+ " ?, ?, ?, ?,'Y' ,"
						+ " ?, ?, ?,?, ?, ? " 
						+ "from wasu.bs_user;");
		Connection cn = getConnection();
		try {
			cn.setAutoCommit(false);
			List lt = new ArrayList();
			Iterator it = insertList.iterator();
			while (it.hasNext()) {
				List paramList = new ArrayList();
				User d = (User) it.next();
				paramList.add(d.getUserName());
				if (getUserList(paramList) != null) {
					return false;
				}
				
				paramList.add(d.getGender());
				paramList.add(d.getBirthday());//锟斤拷锟斤拷锟叫讹拷锟街凤拷锟角凤拷为锟斤拷
				paramList.add(d.getBirthday());
				paramList.add(d.getHireDate());//锟斤拷锟斤拷锟叫讹拷锟街凤拷锟角凤拷为锟斤拷
				paramList.add(d.getHireDate());
				paramList.add(d.getDegree());
				paramList.add(d.getTelephoneNumber());
				paramList.add(d.getAddress());
				paramList.add(d.getWorkId());
				paramList.add(d.getMemo());
				paramList.add(d.getEmail());
				Long id = (Long) queryOne("select IFNULL(MAX(user_id),0)+1  from wasu.bs_user", null);
				paramList.add(id);
				paramList.add(d.getCompanyName());
				paramList.add(d.getPosition());
				paramList.add(d.getJobTitle());
				paramList.add(d.getPhoneNumber());
				paramList.add(d.getFullName());
				paramList.add(d.getDepartmentID().toString());
				simpleExecute(cn,
						sql.toString(), paramList);//insert user
				
				//insert position
				paramList = new ArrayList(); 
				String[] positionIDs = d.getPositionID().split(",");
				for(String pid:positionIDs){
					paramList = new ArrayList(); 
					paramList.add(id);
					paramList.add(pid);
					simpleExecute(cn,"INSERT INTO `wasu`.`account_position` (id,user_id,role_id) " +
							" select IFNULL(MAX(id),0)+1,?,? from wasu.account_position  ",
							paramList);
				}
				
				
			}

			cn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean deleteUser(List paramList) throws GenericDAOException {
		int[] result = simpleExecuteBatch(
				"delete from  wasu.account_position  WHERE user_id=? ", paramList);
		 result = simpleExecuteBatch(
				"UPDATE wasu.bs_user SET visible = 'N' WHERE user_id=? ", paramList);
		return true;
	}

	@Override
	public boolean updateUser(List updateList) throws GenericDAOException {
		
		
		Connection cn = getConnection();
		try {
			cn.setAutoCommit(false);
			List lt = new ArrayList();
			Iterator it = updateList.iterator();
			while (it.hasNext()) {
				List paramList = new ArrayList();
				User d = (User) it.next();
				paramList.add(d.getUserName());
				List isExists=getUserList(paramList);
				if ( isExists!= null) {
					Map s=(HashMap)isExists.get(0);
					if(!String.valueOf(d.getUserID()).equals(String.valueOf(s.get("userID")))){
						return false;
					}
				}
				paramList.add(d.getGender());
				paramList.add(d.getBirthday());//锟斤拷锟斤拷锟叫讹拷锟街凤拷锟角凤拷为锟斤拷
				paramList.add(d.getBirthday());
				paramList.add(d.getHireDate());//锟斤拷锟斤拷锟叫讹拷锟街凤拷锟角凤拷为锟斤拷
				paramList.add(d.getHireDate());
				paramList.add(d.getDegree());
				paramList.add(d.getTelephoneNumber());
				paramList.add(d.getAddress());
				paramList.add(d.getWorkId());
				paramList.add(d.getMemo());
				paramList.add(d.getEmail());
				paramList.add(d.getCompanyName());
				paramList.add(d.getPosition());
				paramList.add(d.getJobTitle());
				paramList.add(d.getPhoneNumber());
				paramList.add(d.getFullName());
				paramList.add(d.getDepartmentID().toString());
				paramList.add(d.getUserID());
				simpleExecute(cn,
						"UPDATE wasu.bs_user SET "
						+ "user_name=? , gender=?,birthday=if(?='',null,str_to_date(?,'%Y-%m-%d')), hire_date=if(?='',null,str_to_date(?,'%Y-%m-%d')),"
						+ "degree=?,telephone_number=?,address=?,work_id=?, memo=?,"
						+ "email=?,company_name=?,position=?,job_title=?,phone_number=?,full_name=?,department_id =?  "
						+ " WHERE user_id=? ",
						paramList);//update user
				//delete position
				paramList = new ArrayList();
				paramList.add(d.getUserID());
				simpleExecute(cn,
						"delete from wasu.account_position  where user_id = ? ",
						paramList);
				//update position
				paramList = new ArrayList(); 
				String[] positionIDs = d.getPositionID().split(",");
				for(String id:positionIDs){
					paramList = new ArrayList(); 
					paramList.add(d.getUserID());
					paramList.add(id);
					simpleExecute(cn,"INSERT INTO `wasu`.`account_position` (id,user_id,role_id) " +
							" select IFNULL(MAX(id),0)+1,?,? from wasu.account_position  ",
							paramList);
				}
				
				
			}

			cn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public List getDepartmentNameByPositionID(String id)
			throws GenericDAOException {
		// TODO Auto-generated method stub
		List p = new ArrayList();
		p.add(id);
		return query(
				"select a.department_name as departmentName,a.department_id as departmentID "
						+ "from jxc.bs_department a,jxc.bs_position b "
						+ "where a.department_id=b.department_id and b.position_id=?",
				p);
	}

	@Override
	public List getModuleListByPositionID(String positionID)
			throws GenericDAOException {
		List p = new ArrayList();
		p.add(positionID);
		return query(
				"select r.* from (select bm.module_id as id,bm.module_name as 'name',bm.ap_id as parentId,"
				+ "(select if(count(*)>0,'Y','N') from jxc.position_module pm where pm.module_id=bm.module_id and pm.position_id=?) as 'checked' "
				+ "from jxc.bs_module bm "
				+ "union all "
				+ "select ba.ap_id as id,ba.ap_name as 'name','0' as parentId,'' as checked "
				+ "from jxc.bs_ap ba ) r"
				+ " order by r.parentId,r.id",
				p);
//		List p = new ArrayList();
//		p.add(positionID);
//		String sql="select bm.module_id as id,bm.module_name as 'name',bm.ap_id as parent_ID,"
//				+ "(select if(count(*)>0,'Y','N') from jxc.position_module pm where pm.module_id=bm.module_id and pm.position_id=?) as 'checked' "
//				+ "from jxc.bs_module bm "
//				+ "order by bm.ap_id,bm.module_id";
//		
//		return getListChunkByProperty(sql, p, 1, 10000,
//				true, "ra.com.system_mgt.model.MyTreeNode");
		
	}

	@Override
	public boolean assignRole(String positionID, List moduleList)
			throws GenericDAOException {
		boolean flag = false;
		Connection cn = getConnection();
		ArrayList params = new ArrayList();
		StringBuffer delSQL=new StringBuffer("delete from jxc.position_module where position_id=?");
		StringBuffer insertSQL = new StringBuffer(
				"insert into jxc.position_module values(?,?,(select ap_id from jxc.bs_module where module_id=?))");
		try {
			cn.setAutoCommit(false);
			params.add(positionID);
			
			flag = simpleExecute(cn,
					delSQL.toString(), params);
			if (!flag)
				return flag;
			params.clear();
			Iterator it=moduleList.iterator();
			while(it.hasNext()){
				MyTreeNode node=(MyTreeNode)it.next();
				params.add(positionID);
				params.add(node.getId());
				params.add(node.getId());
				flag = simpleExecute(cn,
						insertSQL.toString(), params);
				params.clear();
			}
			cn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean checkPosition(String positionName) throws GenericDAOException {
		StringBuffer sql = new StringBuffer("SELECT 1 "
						+ "FROM wasu.bs_position a "
						+ "WHERE  a.position_name=? AND status='Y' ");
		ArrayList param=new ArrayList();
		param.add(positionName);
		List rs = query(sql.toString(), param);
		return (rs!=null && rs.size()>0)?false:true;
	}

//	@Override
//	public List getRoleList() throws GenericDAOException {
//		// TODO Auto-generated method stub
//		return query("select bp.position_name,ba.ap_name,bm.module_name "
//				+ "from jxc.position_module a,jxc.bs_position bp,jxc.bs_module bm,jxc.bs_ap ba "
//				+ "where a.position_id=bp.position_id and a.module_id=bm.module_id "
//				+ "and a.ap_id=ba.ap_id "
//				+ "order by bp.position_name,ba.ap_name,bm.module_name", null);
//	}

	@Override
	public ListChunk getUserList(String userIDSearch, String userNameSearch,
			String hireDateSearch,String visibleSearch,String roleId) throws GenericDAOException {
		ArrayList pl=new ArrayList();
		StringBuffer sql = new StringBuffer(
				"SELECT a.user_id as userID,"
						+ "a.user_name as userName,a.gender,date_format(a.birthday,'%Y-%m-%d') as birthday,"
						+ "date_format(a.hire_date,'%Y-%m-%d') as hireDate,a.degree,department_id,(SELECT value FROM wasu.bs_common_def d where d.def_id=a.department_id and d.type='department') departmentName,"
						+ "GROUP_CONCAT(p.role_id) positionID,work_id,"
						+ "GROUP_CONCAT(p.role_id) hiddenPositionID,"
						+ " (select GROUP_CONCAT(b.position_name) from wasu.bs_position b where b.id in (GROUP_CONCAT(p.role_id) ) and b.status = 'Y')  positionName, "
						+ "a.telephone_number as telephoneNumber,"
						+ "a.emergency_contact as emergencyContact,a.address,"
						+ "a.visible,a.company_name,a.position,a.job_title,a.phone_number,a.full_name, a.email,"
						+ "a.memo"
						+ " FROM   wasu.bs_user a left join  wasu.account_position   p  on a.user_id = p.user_id "
						+ "	 WHERE 1=1  ");
		if (userIDSearch != null && !"".equals(userIDSearch)) {
			sql.append(" AND a.work_id=?");
			pl.add(userIDSearch);
		}
		if (userNameSearch != null && !"".equals(userNameSearch)) {
			sql.append(" AND a.user_name=?");
			pl.add(userNameSearch);
		}
		if (hireDateSearch != null && !"".equals(hireDateSearch)) {
			sql.append(" AND a.hire_date=str_to_date(?,'%Y-%m-%d')");
			pl.add(hireDateSearch);
		}
		if("N".equals(visibleSearch)){
			sql.append(" AND a.visible ='N' ");
		}else if("T".equals(visibleSearch)){
			sql.append(" AND a.visible ='T' ");
		}else if("A".equals(visibleSearch)){//search all
			
		}else{
			sql.append(" AND a.visible ='Y' ");
		}
		if(!"8".equals(roleId)){
			sql.append(" AND p.role_id not in (8) "); 
		}
		sql.append(" GROUP BY a.user_id ORDER BY A.user_name ");
		return getListChunkByProperty(sql.toString(), pl, 1,1000,
				true, "ra.com.system_mgt.model.UserManagement");
	}

	@Override
	public boolean checkDepartment(String departmentName,Long departmentID) throws GenericDAOException {
		StringBuffer sql = new StringBuffer("SELECT department_name "
						+ "FROM wasu.bs_department  ");
		
		sql.append(" WHERE department_name=?");
		sql.append(" AND department_id<>?");
		ArrayList param=new ArrayList();
		param.add(departmentName);
		param.add(departmentID);
		sql.append(" ORDER BY department_name ");
		List rs = query(sql.toString(), param);
		return (rs!=null && rs.size()>0)?false:true;
	}
	
	public ListChunk showRoleList(List param,String roleId,int pageNo, int pageSize)
			throws GenericDAOException {
		StringBuffer sql = new StringBuffer(
				"SELECT"
						+ " a.id positionID,a.position_name  "
						+ " FROM wasu.bs_position a "
						+ " WHERE   a.status = 'Y' ");
		if (param != null) {
				sql.append(" AND a.position_name=?"); 
		}
		if(!"8".equals(roleId)){
			sql.append(" AND a.id not in (8) "); 
		}
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,
				true, "ra.com.system_mgt.model.Position");
	}
	
	public ListChunk showDepartmentList()throws GenericDAOException {
		StringBuffer sql = new StringBuffer(
				"SELECT def_id positionID,value position_name FROM wasu.bs_common_def where type='department' ");
		
		return getListChunkByProperty(sql.toString(), null,1,1000,
				true, "ra.com.system_mgt.model.Position");
	}
	
	@Override
	public ListChunk getRoleUserListForDataGrid(long roleId) throws GenericDAOException {
		ArrayList pl=new ArrayList();
		StringBuffer sql = new StringBuffer(
				"SELECT \n" +
	                    "    c.value, u.user_name\n" +
	                    "FROM\n" +
	                    "    wasu.account_position ap,\n" +
	                    "    wasu.bs_user u,\n" +
	                    "    wasu.bs_common_def c\n" +
	                    "where \n" +
	                    "    c.type = 'role_type' and c.def_id = ? \n" +
	                    "        and u.user_id = ap.user_id\n" +
	                    "        and ap.role_id = c.def_id order by u.user_name" );//	LEFT JOIN jxc.bs_department c ON a.department_id = c.department_id
		pl.add(roleId);
		sql.append(" ORDER BY A.user_name ");
		return getListChunkByProperty(sql.toString(), pl, 1,1000,
				true, "ra.com.system_mgt.model.UserManagement");
	}
	
	public Collection getActionNode(long modelId,String positionId) throws GenericDAOException {
		ArrayList pl=new ArrayList();
		StringBuffer sql = new StringBuffer("SELECT distinct t.layout_id childrenId, a.value name,  ifnull(d.layout_detail_id,-1) layout_id " );
		sql.append( " FROM wasu.bs_common_def a, wasu.table_layout t left join wasu.table_layout_detail d on t.layout_id = d.layout_id and d.position_id in (").append(positionId).append(" )" )
		.append(" where type = 'action_type' and a.def_id = t.def_id and t.model_id = ? order by name");
		pl.add(modelId);
		return getListChunkByProperty(sql.toString(), pl, 1, 500, true,
                "ra.com.portal.model.TreeNodeList").getCollection(); 
	}
	
	public String getPositionId(long userId)throws GenericDAOException {
		ArrayList pl=new ArrayList();
		pl.add(userId);
		StringBuffer sql = new StringBuffer("SELECT GROUP_CONCAT(p.role_id) position_id FROM wasu.bs_user U ,wasu.account_position p  WHERE u.USER_ID=? and u.user_id = p.user_id ");
		return (String)queryOne(sql.toString(), pl);
	}
	
	public Collection getModuleListByPositionId(String positionId,long id)
			throws GenericDAOException {
		ArrayList p=new ArrayList();
		p.add(positionId);
		StringBuffer sql = new StringBuffer("SELECT distinct " + 
				" d.ap_name name," + 
				" a.module_name children_name," + 
				" a.action," + 
				" a.module_id children_id," + 
				" - 1 id" + 
				" FROM" + 
				" wasu.bs_module a" + 
				" left join" + 
				" wasu.position_module c ON c.module_id = a.module_id" + 
				" and c.position_id = ? " + 
				"  and c.module_id <> - 1," + 
				" wasu.bs_ap d" + 
				" where" + 
				" d.ap_id = a.ap_id and d.ap_id = ? " + 
				" order by d.ap_name , a.module_name ");
			p.add(id);   
		return getListChunkByProperty(sql.toString(), p, 1, 500, true,
                "ra.com.portal.model.TreeNodeList").getCollection(); 
	}
	
	public Collection getApList() throws GenericDAOException{
		return getListChunkByProperty("SELECT distinct\n"+
	              "    d.ap_name name,\n"+
	              "    '' children_name,\n"+
	              "    '' action,\n"+
	              "    -1 children_id,\n"+
	              "d.ap_id  id\n"+
	              "FROM \n"+
	              "    wasu.bs_ap d \n"+
	              " order by d.ap_name", null, 1, 500, true,
                "ra.com.portal.model.TreeNodeList").getCollection(); 
	}

	public boolean positionModuleSave(String postionId,String moduleIds) throws GenericDAOException {
		String[] moduleIdArry = moduleIds.split(",");
		ArrayList paramList = new ArrayList();
		Connection cn = getConnection();
		try {
			cn.setAutoCommit(false);
			paramList.add(postionId);
			simpleExecute(cn,
					"delete from wasu.table_layout_detail  where  position_id = ? ",
					paramList);
			simpleExecute(cn,
					"delete from wasu.position_module  where  position_id = ? ",
					paramList);
			simpleExecute(cn,
					"insert into wasu.position_module(position_id,module_id,ap_id  ) " +
					 "SELECT distinct ? ,-1, b.ap_id  FROM wasu.table_layout a ,wasu.bs_module b  where layout_id in ( " +moduleIds+" ) " +
					"and a.model_id = b.module_id",
					paramList);
			simpleExecute(cn,
					"insert into wasu.position_module(position_id,module_id,ap_id  ) " +
					 "SELECT distinct ? ,a.model_id, b.ap_id  FROM wasu.table_layout a ,wasu.bs_module b  where layout_id in ( " +moduleIds+" ) " +
					"and a.model_id = b.module_id",
					paramList);
		 for(String moduleId:moduleIdArry){
			 paramList = new ArrayList();
			 paramList.add(postionId);
			 paramList.add(moduleId); 
			 simpleExecute(cn,
						"insert into wasu.table_layout_detail(layout_detail_id,position_id,layout_id  ) SELECT IFNULL(MAX(layout_detail_id),0)+1 , ? ,? FROM wasu.table_layout_detail",
						paramList);
		 }
		 cn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public void registerUser(User u)throws GenericDAOException {
		Long id = (Long) queryOne("select IFNULL(MAX(user_id),0)+1  from wasu.bs_user", null);
		StringBuffer sql = new StringBuffer("INSERT INTO  wasu.bs_user (user_id,user_name,password,telephone_number,email,"
				+ "company_name,position,job_title,phone_number,full_name,visible,work_id ) "
				+ " VALUES (? ,? ,? ,? ,? ,"
				+ "	? ,? ,? ,? ,? ,'T', ?)");
		List<String> para = new ArrayList<String>();
		para.add(id+"");
		para.add(u.getUserName());
		para.add(u.getPassword());
		para.add(u.getTelephoneNumber());
		para.add(u.getEmail());
		para.add(u.getCompanyName());
		para.add(u.getPosition());
		para.add(u.getJobTitle());
		para.add(u.getPhoneNumber());
		//para.add(u.getPurposeData());
		para.add(u.getFullName());
		para.add(u.getWorkId());
		simpleExecute(sql.toString(),para);
	}

	public boolean registerUserApprove(List updateList) throws GenericDAOException {
		
		
		Connection cn = getConnection();
		try {
			cn.setAutoCommit(false);
			List lt = new ArrayList();
			Iterator it = updateList.iterator();
			while (it.hasNext()) {
				List paramList = new ArrayList();
				UserManagement d = (UserManagement) it.next();
				paramList.add(d.getRegisterStatusKey());
				paramList.add(d.getUserID());
				simpleExecute(cn, "UPDATE wasu.bs_user SET  visible=?  WHERE user_id= ? ", paramList);//update user
				
				//update position
				paramList = new ArrayList(); 
				String[] positionIDs = d.getPositionID().split(",");
				for(String id:positionIDs){
					paramList = new ArrayList(); 
					paramList.add(d.getUserID());
					paramList.add(id);
					simpleExecute(cn,"INSERT INTO `wasu`.`account_position` (id,user_id,role_id) " +
							" select IFNULL(MAX(id),0)+1,?,? from wasu.account_position  ",
							paramList);
				}
			}
			cn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}
	
	public ListChunk getUserPositionList(String positionID, String userNameSearch,String roleId) throws GenericDAOException {
		ArrayList pl=new ArrayList();
		StringBuffer sql = new StringBuffer(
				"SELECT distinct a.user_id as userID,"
						+ "a.user_name as userName FROM   wasu.bs_user a , wasu.account_position   p  "
						+ "	 WHERE a.user_id = p.user_id AND a.visible ='Y'  ");
		if (positionID != null && !"".equals(positionID)) {
			sql.append(" AND p.role_id =?");
			pl.add(positionID);
		}
		if (userNameSearch != null && !"".equals(userNameSearch)) {
			sql.append(" AND a.user_name like ?");
			pl.add(userNameSearch+"%");
		}
		if(!"8".equals(roleId)){
			sql.append(" AND p.role_id not in (8) "); 
		}
		sql.append("  ORDER BY A.user_name ");
		return getListChunkByProperty(sql.toString(), pl, 1,1000,
				true, "ra.com.system_mgt.model.UserManagement");
	}
	
	public Collection showAccessCollectorList(String weatherNo)throws GenericDAOException{ 
		return simpleKVQuery("SELECT id,collector_name from wasu.bs_collector a "+ 
				"where exists (select 1 from wasu.bs_weather_station_collector b "+ 
				"where a.id = b.collector_id and b.weather_station_id in ("+ weatherNo +" ) )  "+ 
				"order by collector_name", null);
	}
	
	public boolean updateAccessCollector(String weatherStationNo,String collectorNo,String effectiveDate,String expireDate,String userId )throws GenericDAOException{ 
		Connection cn = getConnection();
		try {
			cn.setAutoCommit(false);
				List paramList = new ArrayList();
				paramList.add(userId);
				simpleExecute(cn, "DELETE FROM  wasu.bs_data_access_management    WHERE user_id= ? ", paramList);//鍒犻櫎鏃ц祫鏂�
				if(weatherStationNo.startsWith(",")){
					weatherStationNo = weatherStationNo.substring(1);
				}
				if(collectorNo.startsWith(",")){
					collectorNo = collectorNo.substring(1);
				}
				paramList = new ArrayList();
				paramList.add(userId);
				paramList.add(effectiveDate);
				paramList.add(effectiveDate);
				paramList.add(expireDate);
				paramList.add(expireDate);
				StringBuffer sql = new StringBuffer("insert into wasu.bs_data_access_management (user_id,weather_station_id,collector_id, effective_date,expire_date) " +
							" select ? ,weather_station_id,collector_id,if(?='',null,str_to_date(?,'%Y-%m-%d')) ,if(?='',null,str_to_date(?,'%Y-%m-%d'))  "
							+ " from wasu.bs_weather_station_collector c where weather_station_id in (").append(weatherStationNo).append(" ) and collector_id in (").append(collectorNo).append(" )");
					simpleExecute(cn,sql.toString(),paramList);
				
			cn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}
	
	public List getAccessCollectorForUser(String userId)throws GenericDAOException{ 
		List<String> params = new ArrayList<String>();
		params.add(userId);
		return query("select  CONCAT(GROUP_CONCAT(distinct CAST(a.weather_station_id AS char)),'') weather_station_id,"
				+ " CONCAT(GROUP_CONCAT(CAST(a.collector_id AS char)),'') collector_id, "
				+ " date_format(a.effective_date,'%Y-%m-%d') effective_date ,date_format(a.expire_date,'%Y-%m-%d') expire_date "
				+ " from wasu.bs_data_access_management a where user_id = ?  GROUP BY a.user_id ", params);
	}
	
	public boolean restoreUser(User u) throws GenericDAOException {

		Connection cn = getConnection();
		try {
			cn.setAutoCommit(false); 
				List paramList = new ArrayList();
				paramList.add(u.getPassword());
				paramList.add(u.getUserName());
				simpleExecute(cn,"update wasu.bs_user set visible='Y',password = ?   where user_id = ?  ",
						paramList);	

			cn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public List getEmailForAdmin()throws GenericDAOException{
		List<String> params = new ArrayList<String>();
		params.add(Const.ADMIN_ROLE);
		return query("select email from  wasu.bs_user u where exists( select 2 from wasu.account_position ap " +
				"where ap.user_id = u.user_id  and ap.role_id = ? ) and email is not null", params);
	}

	public ListChunk getActionRole(String moduleId,String positionId)throws GenericDAOException{
		ArrayList pl=new ArrayList();
		//pl.add(positionId);
		pl.add(moduleId);
		StringBuffer sql = new StringBuffer(
				 "SELECT distinct function_value typeName FROM wasu.bs_common_def c where exists (select 1 from   wasu.table_layout l, wasu.table_layout_detail d "
				 + "where l.layout_id = d.layout_id  and d.position_id in ( ").append(positionId).append("   ) and model_id = ? and l.def_id =  c.def_id )");
		return getListChunkByProperty(sql.toString(), pl, 1,1000,
				true, "ra.com.system_mgt.model.CommonDefine");
	}
}
