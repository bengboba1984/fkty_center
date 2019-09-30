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


public class RoleAction extends BaseAction {

	private JSONArray roleList;
	private SystemMGTFacade biz;
	private JSONObject roleUserList;
	private String positionName;
	private String moduleId;
	
	
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public RoleAction() {
	}

	public JSONArray getRoleList() {
		return roleList;
	}

	public void setRoleList(JSONArray roleList) {
		this.roleList = roleList;
	}

	

	public JSONObject getRoleUserList() {
		return roleUserList;
	}

	public void setRoleUserList(JSONObject roleUserList) {
		this.roleUserList = roleUserList;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	// do function
	public String show() {
		return "show";
	}

	public String showRoleList() throws IOException {
		System.out.println("roleUserListForDataGrid():"+positionName );
		biz = SystemMGTFacade.getInstance();
		ArrayList para = null;
		
		try {
			if(positionName!=null&&!"".equals(positionName)){
				para = new ArrayList();
				para.add(positionName);
			}
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.showRoleList(para,roleId);
			Map map = new HashMap();
//			map.put("total", lc.getTotalCount());
			map.put("rows", U.changeListToJSON(lc));
//			System.out.println("====lc.getTotalCount():"+lc.getTotalCount());
			roleUserList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}
	
	public String accept() throws Exception {
		biz = SystemMGTFacade.getInstance();
		request.setCharacterEncoding("UTF-8");

		String inserted = request.getParameter("inserted");
		String deleted = request.getParameter("deleted");
		String updated = request.getParameter("updated");
		System.out.println("@@inserted="+inserted);
		System.out.println("@@deleted="+deleted);
		System.out.println("@@updated="+updated);
		boolean flag = true;
		try {

			if (deleted != null && !"".equals(deleted)) {
				List deleteList = JSON.parseArray(deleted, Position.class);
				flag = biz.deletePosition(deleteList);
			}
			if (updated != null && !"".equals(updated)) {
				List updateList = JSON.parseArray(updated, Position.class);
				flag = biz.updatePosition(updateList);
			}
			if (inserted != null && !"".equals(inserted)) {
				List insertList = JSON.parseArray(inserted, Position.class);
				flag = biz.insertPoistion(insertList);
			}
			if (!flag)
				throw new Exception("duplicate date");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error", e);
		}
		return SUCCESS;
	}
	
}
