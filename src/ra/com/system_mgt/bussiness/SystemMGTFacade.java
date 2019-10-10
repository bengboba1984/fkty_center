package ra.com.system_mgt.bussiness;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ra.com.common.MailUtil;
//import ra.com.basic_mgt.model.Customer;
import ra.com.common.U;
import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
import ra.com.portal.DAO.PortalDAO;
import ra.com.portal.model.TreeNodeList;
import ra.com.system_mgt.DAO.SystemMGTDAO;
import ra.com.system_mgt.model.CommonDefine;
import ra.com.system_mgt.model.Department;
import ra.com.system_mgt.model.MyTreeNode;
import ra.com.system_mgt.model.Position;
import ra.com.system_mgt.model.User;
import ra.com.system_mgt.model.UserManagement;

public class SystemMGTFacade {
	private SystemMGTDAO dao;
	private PortalDAO portalDao;
	private static SystemMGTFacade me = new SystemMGTFacade();

	public static SystemMGTFacade getInstance() {
		return me;
	}

	public SystemMGTFacade() {
		try {
			dao = (SystemMGTDAO) ra.com.common.dao.DAOFactory
					.getDAO("ra.com.system_mgt.DAO.SystemMGTDAOImpl");
			portalDao = (PortalDAO)ra.com.common.dao.DAOFactory.getDAO("ra.com.portal.DAO.PortalDAOImpl");
		} catch (Exception e) {
			System.out.println(getClass().getName() + "/n" + e.toString());
		}
	}

	// department management begin
	public List getDepartmentList() throws Exception {
		return dao.getDepartmentList(null);
	}

	public List getDepartmentNameByPositionID(String id) throws Exception{
		return dao.getDepartmentNameByPositionID(id);
	}
	
	public JSONObject getDepartmentListForDataGrid() throws Exception {
		List lt = getDepartmentList();
		return changeToDataGridJSon(lt);
	}

	public JSONArray getUserListInDepartmentByJSon() throws Exception {
		List lt = dao.getUserListInDepartment();
		JSONArray list = new JSONArray();
		Iterator it = lt.iterator();
		while (it.hasNext()) {
			HashMap singleItem = (HashMap) it.next();
			JSONObject tm = JSONObject.fromObject(singleItem);
			list.add(tm);
		}
		return JSONArray.fromObject(list);
	}

	public JSONObject changeToDataGridJSon(List lt) throws Exception {
		if(lt!=null){
		Map ltJson = new HashMap();
		ltJson.put("total", lt.size());
		ltJson.put("rows", lt);
		
		return JSONObject.fromObject(ltJson);
		}else{
			return JSONObject.fromObject(null);
		}
	}

	public boolean insertDepartment(List departmentList) throws Exception {

		List lt = new ArrayList();
		Iterator it = departmentList.iterator();
		while (it.hasNext()) {
			// 锟斤拷锟斤拷顺锟斤拷要锟斤拷sql锟斤拷应
			Department d = (Department) it.next();
			// pl.add(d.getDepartmentCode()); //code 锟皆讹拷锟斤拷锟斤拷
			List oneDP = new ArrayList();
			oneDP.add(d.getDepartmentName());
			System.out.println("@@@@in facade:" + d.toString());
			if (dao.getDepartmentList(oneDP) != null) {
				return false;
			}
			oneDP.add(d.getDepartmentManager());
			lt.add(oneDP);
		}

		// check if exists
		return dao.insertDepartment(lt);
	}

	public boolean deleteDepartment(List departmentList) throws Exception {

		List lt = new ArrayList();
		Iterator it = departmentList.iterator();
		while (it.hasNext()) {
			// 锟斤拷锟斤拷顺锟斤拷要锟斤拷sql锟斤拷应
			Department d = (Department) it.next();
			List oneDP = new ArrayList();
			oneDP.add(d.getDepartmentID());
			System.out.println("@@@@in facade:" + d.toString());
			lt.add(oneDP);
		}
		
		return dao.deleteDepartment(lt);
	}

	public boolean updateDepartment(List departmentList) throws Exception {

		List lt = new ArrayList();
		Iterator it = departmentList.iterator();
		while (it.hasNext()) {
			// 锟斤拷锟斤拷顺锟斤拷要锟斤拷sql锟斤拷应
			Department d = (Department) it.next();
			List oneDP = new ArrayList();

			System.out.println("@@@@in facade:" + d.toString());
			if (!dao.checkDepartment(d.getDepartmentName(), d.getDepartmentID())) {
				return false;
			}
			oneDP.clear();
			oneDP.add(d.getDepartmentName());
			oneDP.add(d.getDepartmentManager());
			oneDP.add(d.getDepartmentID());
			lt.add(oneDP);
		}
		return dao.updateDepartment(lt);
	}

	// department management end

	// position management begin
	public List getPositionList() throws Exception {
		return dao.getPositionList(null);
	}

	public JSONObject getPositionListForDataGrid() throws Exception {
		List lt = getPositionList();
		return changeToDataGridJSon(lt);
	}
	//modify PositionListForDataGrid ,add search function
	public JSONObject getPositionListForDataGrid(Position pos) throws Exception {
		ListChunk lt = dao.getPositionForSystemList(pos);
		return changeToDataGridJSon((List)lt.getCollection());
	}
	
	public JSONArray getDepartmentListInPositionByJSon() throws Exception {
		List lt = dao.getDepartmentListInPosition();
		JSONArray list = new JSONArray();
		Iterator it = lt.iterator();
		while (it.hasNext()) {
			HashMap singleItem = (HashMap) it.next();
			JSONObject tm = JSONObject.fromObject(singleItem);
			list.add(tm);
		}
		return JSONArray.fromObject(list);
	}

	private boolean existsNode(Map[] rows, String parentId){
        for(int i=0; i<rows.length; i++){
            if (((String)rows[i].get("id")).equals(parentId)) return true;
        }
        return false;
    }
	public JSONArray getModuleListByPositionID(String positionID) throws Exception{

		List lt=dao.getModuleListByPositionID(positionID);
		Map[] rows=(Map[])lt.toArray(new HashMap[lt.size()]);

	    
	    List nodes = new ArrayList();
	    // get the top level nodes
	    for(int i=0; i<rows.length; i++){
	    	Map row = rows[i];
	        if (!existsNode(rows, (String)row.get("parentId"))){
	        	Map topNode=new HashMap();
	        	topNode.put("id", (String)row.get("id"));
	        	topNode.put("text", (String)row.get("name"));
	            nodes.add(topNode);
	        }
	    }

	    Iterator it=nodes.iterator();
	    while(it.hasNext()){
	    	Map node = (Map)it.next();
	        // get the children nodes
	        for(int i=0; i<rows.length; i++){
	        	Map row = rows[i];
	            if (((String)row.get("parentId")).equals((String)node.get("id"))){
	            	Map child=new HashMap();
	            	child.put("id", (String)row.get("id"));
	            	child.put("text", (String)row.get("name"));
	            	if(((String)row.get("checked")).equals("Y")){
	            		child.put("checked", "true");
	            	}
	                
	                if (node.containsKey("children")){
	                    ((List)node.get("children")).add(child);
	                } else {
	                	List childList=new ArrayList();
	                	childList.add(child);
	                	node.put("children", childList);
	                }
	            }
	        }
	    }
	    return JSONArray.fromObject(nodes);
		
	}
	public boolean insertPoistion(List poistionList) throws Exception {

		List lt = new ArrayList();
		Iterator it = poistionList.iterator();
		while (it.hasNext()) {
			// 锟斤拷锟斤拷顺锟斤拷要锟斤拷sql锟斤拷应
			Position d = (Position) it.next();
			// pl.add(d.getDepartmentCode()); //code 锟皆讹拷锟斤拷锟斤拷
			List oneDP = new ArrayList();
			oneDP.add(d.getPositionName());
			System.out.println("@@@@in facade:" + d.toString());
			if (!dao.checkPosition(d.getPositionName())) {
				return false;
			}
			lt.add(oneDP);
		}

		// check if exists
		return dao.insertPosition(lt);
	}

	public boolean deletePosition(List positionList) throws Exception {

		List lt = new ArrayList();
		Iterator it = positionList.iterator();
		while (it.hasNext()) {
			Position d = (Position) it.next();
			List oneDP = new ArrayList();
			oneDP.add(d.getPositionID());
			System.out.println("@@@@in facade:" + d.toString());
			lt.add(oneDP);
		}
		return dao.deletePosition(lt);
	}

	public boolean updatePosition(List positionList) throws Exception {

		List lt = new ArrayList();
		Iterator it = positionList.iterator();
		while (it.hasNext()) {
			// 锟斤拷锟斤拷顺锟斤拷要锟斤拷sql锟斤拷应
			Position d = (Position) it.next();
			System.out.println("@@@@in facade:" + d.toString());
			if (!dao.checkPosition(d.getPositionName())) {
				return false;
			}
			List oneDP = new ArrayList();
			oneDP.add(d.getPositionName());
			oneDP.add(d.getPositionID());
			lt.add(oneDP);
		}
		return dao.updatePosition(lt);
	}

	public boolean updateModuleListByPositionID(String positionID,List moduleList) {
		try {
			return dao.assignRole(positionID,moduleList);
		} catch (GenericDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	// position management end

	// user management begin
	public ListChunk getUserList(String userIDSearch,String userNameSearch,String hireDateSearch,String visibleSearch,String roleId) throws Exception {
		return dao.getUserList(userIDSearch,userNameSearch,hireDateSearch,visibleSearch,roleId);
	}

	public List getUserList(List userName) throws Exception {
		return dao.getUserList(userName);
	}
	
	public ListChunk getUserListForDataGrid(String userIDSearch,String userNameSearch,String hireDateSearch,String visibleSearch,String roleId) throws Exception {
		return  getUserList(userIDSearch,userNameSearch,hireDateSearch,visibleSearch,roleId);
		//return changeToDataGridJSon(lt);
	}

	public JSONArray getPositionListInUserByJSon() throws Exception {
		List lt = dao.getPositionListInUser();
		JSONArray list = new JSONArray();
		Iterator it = lt.iterator();
		while (it.hasNext()) {
			HashMap singleItem = (HashMap) it.next();
			JSONObject tm = JSONObject.fromObject(singleItem);
			list.add(tm);
		}
		return JSONArray.fromObject(list);
	}

	public boolean insertUser(List userList) throws Exception {

//		List lt = new ArrayList();
//		Iterator it = userList.iterator();
//		while (it.hasNext()) {
//			// 锟斤拷锟斤拷顺锟斤拷要锟斤拷sql锟斤拷应
//			User d = (User) it.next();
//			// pl.add(d.getDepartmentCode()); //code 锟皆讹拷锟斤拷锟斤拷
//			List oneDP = new ArrayList();
//			oneDP.add(d.getUserName());
//			
//			if (dao.getUserList(oneDP) != null) {
//				return false;
//			}
//			oneDP.add(d.getGender());
//			oneDP.add(d.getBirthday());//锟斤拷锟斤拷锟叫讹拷锟街凤拷锟角凤拷为锟斤拷
//			oneDP.add(d.getBirthday());
//			oneDP.add(d.getHireDate());//锟斤拷锟斤拷锟叫讹拷锟街凤拷锟角凤拷为锟斤拷
//			oneDP.add(d.getHireDate());
//			oneDP.add(d.getDegree());
//			oneDP.add(d.getTelephoneNumber());
//			oneDP.add(d.getAddress());
//			oneDP.add(d.getIDNumber());
//			oneDP.add(d.getMemo());
//			oneDP.add(d.getEmail());
//			System.out.println("@@@@in facade:" + d.toString());
//			lt.add(oneDP);
//		}
//
//		// check if exists
		return dao.insertUser(userList);
	}
//
	public boolean deleteUser(List userList) throws Exception {

		List lt = new ArrayList();
		Iterator it = userList.iterator();
		while (it.hasNext()) {
			// 锟斤拷锟斤拷顺锟斤拷要锟斤拷sql锟斤拷应
			User d = (User) it.next();
			List oneDP = new ArrayList();
			oneDP.add(d.getUserID());
			System.out.println("@@@@in facade:" + d.toString());
			lt.add(oneDP);
		}
		return dao.deleteUser(lt);
	}

	public boolean updateUser(List userList) throws Exception {

//		List lt = new ArrayList();
//		List deleteList = new ArrayList();
//		List updateList = new ArrayList();
//		Iterator it = userList.iterator();
//		while (it.hasNext()) {
//			// 锟斤拷锟斤拷顺锟斤拷要锟斤拷sql锟斤拷应
//			User d = (User) it.next();
//			List oneDP = new ArrayList();
//			List positionDP = new ArrayList();
//			oneDP.add(d.getUserName());
//			List isExists=dao.getUserList(oneDP);
//			
//			if ( isExists!= null) {
//				Map s=(HashMap)isExists.get(0);
//				if(!String.valueOf(d.getUserID()).equals(String.valueOf(s.get("userID")))){
//					return false;
//				}
//			}
//			oneDP.add(d.getGender());
//			oneDP.add(d.getBirthday());//锟斤拷锟斤拷锟叫讹拷锟街凤拷锟角凤拷为锟斤拷
//			oneDP.add(d.getBirthday());
//			oneDP.add(d.getHireDate());//锟斤拷锟斤拷锟叫讹拷锟街凤拷锟角凤拷为锟斤拷
//			oneDP.add(d.getHireDate());
//			oneDP.add(d.getDegree());
//			oneDP.add(d.getTelephoneNumber());
//			oneDP.add(d.getAddress());
//			oneDP.add(d.getIDNumber());
//			oneDP.add(d.getMemo());
//			oneDP.add(d.getEmail());
//			oneDP.add(d.getUserID());
//			lt.add(oneDP);
//			positionDP.add(d.getUserID());
//			deleteList .add(positionDP);
//			
//			String[] positionIDs = d.getPositionID().split(",");
//			for(String id:positionIDs){
//				positionDP = new ArrayList();
//				positionDP.add(d.getUserID());
//				positionDP.add(id);
//				updateList.add(positionDP);
//			}
////			"UPDATE ra.bs_user SET "
////			+ "user_name=? , gender=?,birthday=if(?='',null,str_to_date(?,'%Y-%m-%d')), hire_date=if(?='',null,str_to_date(?,'%Y-%m-%d')),"
////			+ "degree=?,telephone_number=?,address=?,id_number=?,memo=?,"
////			+ "work_id=? ,email=? "
////			+ " WHERE user_id=? ",
//		}
		return dao.updateUser(userList);
	}
	// user management end
	
//	public List getRoleList() throws Exception {
//		return dao.getRoleList();
//	}

	public InputStream downloadUserReport(String userIDSearch,
			String userNameSearch, String hireDateSearch,String visibleSearch,String roleId) {
		Collection rs = null;
		String[] columns = { "userID", "userName", "gender",
				"birthday", "hireDate", "degree", "positionID",
				"positionName", "departmentID", "departmentName", "telephoneNumber",
				"emergencyContact", "address", "IDnumber",
				"memo" };
		try {
			rs=(Collection)getUserList(userIDSearch,userNameSearch,hireDateSearch,visibleSearch,roleId);
		//	rs = getSupplierList(s, 1, 10000).getCollection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return U.downloadSimpleExcel(rs,
				"ra.com.system_mgt.model.User", columns,null);
	}
	
	public String uploadUserByExcel(File f) throws Exception {
		String[] columns = { "userName", "gender", "birthday", "hireDate",
				"degree", "positionName","departmentName", "telephoneNumber",
				"emergencyContact","emergencyContactTel","address","IDNumber","workId","email","memo"};
		List lt = U.excelToList(f, "ra.com.system_mgt.model.User",
				columns);
		List list = new ArrayList();
		String mess = null;
		return mess;
	}
	
	public Collection showRoleList(List param,String roleId) throws Exception {
		ListChunk lc = dao.showRoleList(param,roleId,1,100);
		
		return lc.getCollection();
	}
	public Collection showDepartmentList() throws Exception {
		ListChunk lc = dao.showDepartmentList();
		
		return lc.getCollection();
	}
	
	public JSON createTree(String positionId,long pid)throws Exception{   
		StringBuffer sb = new StringBuffer(" ");
		String node = null;
//		String positionId = dao.getPositionId(userId);
		List<TreeNodeList> treeList=(List<TreeNodeList>) dao.getApList();
		for(TreeNodeList tree:treeList){ 
			sb.append(createNode(tree));
			
				List<TreeNodeList> tree2List=(List<TreeNodeList>) dao.getModuleListByPositionId(positionId,tree.getId());
				
				if(tree2List!=null&&tree2List.size()>0){
					Map<String, Object> jsonMap2 = new HashMap<String, Object>();
					StringBuffer chSb = new StringBuffer("");
					for(TreeNodeList tree2:tree2List){ 
						chSb.append(createNode(tree2));
						List<TreeNodeList> nodeList=(List<TreeNodeList>) dao.getActionNode(tree2.getChildrenId(),positionId);
						if(nodeList!=null&&nodeList.size()>0){
							StringBuffer noSb = new StringBuffer("");
							for(TreeNodeList nodes:nodeList){ 
								noSb.append(createActionNode(nodes));
							}
							chSb.append(",\"children\":[").append(noSb.toString().substring(0, noSb.length()-1)).append("]");//杞�鍏ュ瓙鑺傜偣        
						}
						chSb.append("},");
					}
					sb.append(",\"children\":[").append(chSb.toString().substring(0, chSb.length()-1)).append("]");//杞�鍏ュ瓙鑺傜偣        
				}
				sb.append("},");
		}
		node = "["+sb.substring(0, sb.length()-1)+"]";
		System.out.print("=====sss:"+sb.toString());
		return JSONArray.fromObject(node);

	}
		

	private StringBuffer createNode(TreeNodeList tree){
		StringBuffer s = new StringBuffer("{");
		s.append("\"id\":").append("''").append(",\"text\":\"").append(tree.getChildrenId()==-1?tree.getName():tree.getChildrenName())
		.append("\",\"iconCls\":\"icon-person\"").append(",\"state\":\"open\"");	
		return s;
		
	}
	
	private StringBuffer createActionNode(TreeNodeList tree){
		StringBuffer s = new StringBuffer("{");
		s.append("\"id\":").append(tree.getChildrenId()).append(",\"text\":\"").append(tree.getName())
		.append("\" ").append((tree.getLayoutId()==null||"-1".equals(tree.getLayoutId()))?"":" ,\"checked\":true").append(" },");	//,\"iconCls\":\"icon-person\"
		return s;
		
	}
	
	public boolean positionModuleSave(String postionId,String moduleIds)throws Exception{
		
		 return dao.positionModuleSave(postionId,moduleIds);
	}
	
	public Map registerUser(User u ) throws Exception {
		Map temp = new HashMap();
		ArrayList param = new ArrayList();
		param.add(u.getUserName());
		List checkList = dao.getUserList(param);
		if (checkList!=null&&checkList.size()>0) {
			temp.put("error", "'"+u.getUserName()+"' 这个姓名已经存在,请修改");
		}else{
		  dao.registerUser(u);
		  StringBuffer content = new StringBuffer("");
		  content.append("姓名:").append(u.getUserName()).append("<br>注册成功,请尽快登陆系统审核!");
			List mailList = dao.getEmailForAdmin();
			Map[] rows=(Map[])mailList.toArray(new HashMap[mailList.size()]);
			String[] mails =new String[rows.length];
			for(int i=0; i<rows.length; i++){
		    	Map row = rows[i];
		    	mails[i] = (String)row.get("email");
		    }
			MailUtil.sendMail(mails,"福建省森林生态定位站用户审核",content.toString());
		  temp.put("success","success");
		}
		 return temp;  
	}
	
	public boolean registerUserApprove(List userList) throws Exception {
		boolean flag =  dao.registerUserApprove(userList);
		 if(flag){//send mail
			 List lt = new ArrayList();
				Iterator it = userList.iterator();
				while (it.hasNext()) {
					UserManagement d = (UserManagement) it.next();
					StringBuffer content = new StringBuffer("");
					if("Y".equals(d.getRegisterStatusKey())){
						content.append("登录名:").append(d.getUserName()).append(", 初始密码:").append("0000")
						.append("<br>您的注册审核已经通过,请尽快登陆!");
					}else{
						content.append("姓名:").append(d.getUserName()).append("<br>您的注册审核被拒绝!");
					}
					String[] to = new String[]{d.getEmail()};
					MailUtil.sendMail(to,"福建省森林生态定位站用户审核通知",content.toString());
				} 
		 }
		 return flag;
	}
	
	public ListChunk getUserPositionList(String positionID, String userNameSearch,String roleId) throws Exception {
		return  dao.getUserPositionList(positionID,userNameSearch,roleId);
		//return changeToDataGridJSon(lt);
	}
	
	public Collection showAccessCollectorList(String weatherNo)throws Exception{
		
		System.out.println("============weatherNo"+weatherNo+"========");
		if(weatherNo==null||"".equals(weatherNo)){
			weatherNo = "-999";
		}else if(weatherNo.startsWith(",")){
			weatherNo = weatherNo.substring(1);
		}
		return dao.showAccessCollectorList(weatherNo);
	}
	
	public boolean updateAccessCollector(String weatherStationNo,String collectorNo,String effectiveDate,String expireDate,String userId ) throws Exception {
		return dao.updateAccessCollector(weatherStationNo,collectorNo,effectiveDate,expireDate,userId );
		
	}
	
	public Map getAccessCollectorForUser(String userId)throws Exception {
		List lt = dao.getAccessCollectorForUser(userId);
		Map temp = new HashMap();
		if(lt!=null){
			Map[] rows=(Map[])lt.toArray(new HashMap[lt.size()]);
			String weatherStationNo =  (String)rows[0].get("weather_station_id");
			String collectorNo =  (String)rows[0].get("collector_id");
			String effectiveDate =  (String)rows[0].get("effective_date");
			String expireDate =  (String)rows[0].get("expire_date");
			temp.put("weatherStationNo", weatherStationNo);
			temp.put("collectorNo", collectorNo);
			temp.put("effectiveDate", effectiveDate);
			temp.put("expireDate", expireDate);
			temp.put("success", "success");
		}else{
			temp.put("success", "error");
		}
		 return temp; 
	}
	
	public Map restoreUser(User u) throws Exception {
		Map temp = new HashMap();
		dao.restoreUser(u);
		temp.put("success","success");
		return temp;  
	}
	
	public Map removeitUser(User u) throws Exception {
		Map temp = new HashMap();
		List lt = new ArrayList();
		List oneDP = new ArrayList();
		oneDP.add(u.getUserName());
		lt.add(oneDP);
		dao.deleteUser(lt);
		temp.put("success","success");
		return temp;  
	}
	
	public String getActionRole(String  moduleId,String positionId){
		StringBuffer values = new StringBuffer(""); 
		try{
			ListChunk l = dao.getActionRole(moduleId,positionId);
			Collection<CommonDefine> lc =l.getCollection();
			for(CommonDefine item:lc){
				values.append(item.getTypeName()).append(",");
			}
		}catch (Exception e) {
			e.printStackTrace();
        } 
		System.out.println("=============values:"+values.toString());
		return values.toString();
	}
}
