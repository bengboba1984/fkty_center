package ra.com.system_mgt.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ra.com.common.action.BaseAction;
import ra.com.portal.bussiness.PortalFacade;
import ra.com.system_mgt.bussiness.SystemMGTFacade;
import ra.com.system_mgt.model.Department;
import ra.com.system_mgt.model.Position;
import ra.com.system_mgt.model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class PasswordAction extends BaseAction {

	private JSONObject resultList;
	private PortalFacade pBiz;
	private String oldPassword;
	private String newPassword;
	private String reNewPassword;

	public PasswordAction() {
	}

	public JSONObject getResultList() {
		return resultList;
	}

	public void setResultList(JSONObject resultList) {
		this.resultList = resultList;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

	public String change() {
		Map temp = new HashMap();
		User u = (User) session.get("loginUser");
		System.out.println("user="+u.toString());
		if (oldPassword.equals(u.getPassword())) {
			try {
				pBiz=PortalFacade.getInstance();
				boolean flag = pBiz.changePassword(u.getUserID(), newPassword);
				if (flag) {
					temp.put("success", "Change Complete!");
				} else {
					temp.put("error", "System Error!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			temp.put("error", "oldPassword is incorrect!");
		}
		resultList = JSONObject.fromObject(temp);
		return SUCCESS;
	}

	public String show() {
		return "show";
	}
}
