package ra.com.system_mgt.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;







import com.alibaba.fastjson.JSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import ra.com.common.Const;
import ra.com.common.U;
import ra.com.common.action.BaseAction;
import ra.com.common.model.ListChunk;
import ra.com.system_mgt.bussiness.SystemMGTFacade;
import ra.com.system_mgt.model.Position;
import ra.com.system_mgt.model.User;
import ra.com.system_mgt.model.UserManagement;

public class RegisterUserApproveAction  extends BaseAction{
	
	private SystemMGTFacade biz;
	
	private JSONObject userList;
	private JSONArray positionList; 
	private JSONObject result;
	private String userIDSearch;
	private String userNameSearch;
	
	
	
	public String show() {
		return "show";
	}
	
	public String showRegisterUserList(){
		System.out.println("showRegisterUserList():");
		biz = SystemMGTFacade.getInstance();

		
		try {
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			ListChunk lc = biz.getUserListForDataGrid(userIDSearch,userNameSearch,null,"T",roleId);
			Map map = new HashMap();
			map.put("total", lc.getTotalCount());
			map.put("rows", U.changeListToJSON(lc.getCollection()));
			System.out.println("====lc.getTotalCount():"+lc.getTotalCount());
			userList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}
	
	public String getRegisterStatusList() throws IOException {
		biz = SystemMGTFacade.getInstance();

		try {
			ArrayList al = new ArrayList();
			for(int ind = 0;ind<Const.REGISTER_STATUS_LIST.length;ind++) {
				Map m = new HashMap();
				m.put("key", Const.REGISTER_STATUS_LIST[ind][0]);
				m.put("value", Const.REGISTER_STATUS_LIST[ind][1]);
				al.add(m);
			}
			positionList = JSONArray.fromObject(al);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String registerUserApprove() throws Exception {
		biz = SystemMGTFacade.getInstance();
		request.setCharacterEncoding("UTF-8");

		String updated = request.getParameter("updated");
		System.out.println("In Action : updated="+updated);
		Map temp=new HashMap();
		boolean flag = true;
		try {
			
			if (updated != null && !"".equals(updated)) {
				List updateList = JSON.parseArray(updated, UserManagement.class);
				flag = biz.registerUserApprove(updateList);
			}
			if(!flag)temp.put("error", "审核失败");
			result=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
	}
	
	public JSONObject getUserList() {
		return userList;
	}

	public void setUserList(JSONObject userList) {
		this.userList = userList;
	}

	public JSONArray getPositionList() {
		return positionList;
	}

	public void setPositionList(JSONArray positionList) {
		this.positionList = positionList;
	}

	public String getUserIDSearch() {
		return userIDSearch;
	}

	public void setUserIDSearch(String userIDSearch) {
		this.userIDSearch = userIDSearch;
	}

	public String getUserNameSearch() {
		return userNameSearch;
	}

	public void setUserNameSearch(String userNameSearch) {
		this.userNameSearch = userNameSearch;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}



}
