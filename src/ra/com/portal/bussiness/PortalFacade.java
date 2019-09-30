package ra.com.portal.bussiness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.KeyValueImpl;
import ra.com.common.model.ListChunk;
import ra.com.portal.DAO.PortalDAO;
import ra.com.portal.model.TreeNodeList;
import ra.com.system_mgt.model.User;

public class PortalFacade {
	private PortalDAO dao;
	private static PortalFacade me = new PortalFacade();

	public static PortalFacade getInstance() {
		return me;
	}

	public PortalFacade(){
	   try{
		   dao = (PortalDAO)ra.com.common.dao.DAOFactory.getDAO("ra.com.portal.DAO.PortalDAOImpl");
	   }catch(Exception e){
		   System.out.println(getClass().getName()+"/n"+e.toString());
	   }
	}
	
	public boolean checkPasswordByUserName(String userName, String password) throws Exception{
		boolean flag=false;
		try {
			flag= dao.checkPasswordByUserName(userName,  password);
		} catch (GenericDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("checkPasswordByUserName: error",e);
		}
		return flag;
	}
	
	public User getUserByProperty(User u) throws Exception{
		
		ListChunk lc=dao.getUserListByProperty(u);
		Collection col=lc.getCollection();
		if (col == null || (col.size() == 0)) {
			return null;
		} else {
			return (User)col.iterator().next();
		}
	}
	
	public boolean changePassword(Long userID,String newPassword) throws Exception{
		boolean flag=false;
		flag=dao.changePassword(userID,newPassword);
		
		return flag;
	}
	
	public JSON getModuleListByUserId(long userId) throws Exception{
		return createTree(userId,-1);
	}

	private JSON createTree(long userId,long pid)throws Exception{   
		StringBuffer sb = new StringBuffer(" ");
		String node = null;
		List<TreeNodeList> treeList=(List<TreeNodeList>) dao.getModuleListByUserId(userId,pid);
		for(TreeNodeList tree:treeList){ 
			sb.append(createNode(tree));
			
				List<TreeNodeList> tree2List=(List<TreeNodeList>) dao.getModuleListByUserId(userId,tree.getId());
				if(tree2List!=null&&tree2List.size()>0){
					sb.append(",\"iconCls\": \"fa fa-wpforms\",\"state\":\"open\"");
					Map<String, Object> jsonMap2 = new HashMap<String, Object>();
					StringBuffer chSb = new StringBuffer("");
					for(TreeNodeList tree2:tree2List){ 
						chSb.append(createNode(tree2)).append("},");
					}
					sb.append(",\"children\":[").append(chSb.toString().substring(0, chSb.length()-1)).append("]");//载 入子节点        
				}
				sb.append("},");
		}
		node = "["+sb.substring(0, sb.length()-1)+"]";
		System.out.println("=====sss:"+node);
		return JSONArray.fromObject(node);

	}
		

	private StringBuffer createNode(TreeNodeList tree){
		StringBuffer s = new StringBuffer("{");
		s.append("\"id\":").append(tree.getChildrenId()).append(",\"text\":\"").append(tree.getChildrenId()==-1?tree.getName():tree.getChildrenName())
		.append("\",\"url\":\"").append(tree.getAction()==null?"#":tree.getAction()).append("\"");	
		return s;
		
	}
	
	public boolean checkAdminByUserId(String userId) throws Exception{
		return dao.checkAdminByUserId(userId);
	}
	
}
