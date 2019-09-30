package ra.com.portal;


import java.io.IOException;

import ra.com.common.U;
import ra.com.common.action.BaseAction;
import ra.com.portal.bussiness.PortalFacade;
import ra.com.system_mgt.model.User;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class LoginAction extends BaseAction{

	private String userName;
	private String password;
	private PortalFacade biz;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginAction [userName=" + userName + ", password=" + password
				+ "]";
	}

	public LoginAction() {
	}

	public String doLogin() throws IOException {
		System.out.print("doLogin():"+this.toString()+" utf:"+U.decode(userName));
		biz=PortalFacade.getInstance();
		//check password
		try {
			userName = U.decode(userName) ;
			if (biz.checkPasswordByUserName(userName, password)) {
				User u=new User();
				u.setUserName(userName);
				u.setPassword(password);
				User su=biz.getUserByProperty(u);
				if(su==null){
					u.setUserName(null);
					u.setTelephoneNumber(userName);
					su=biz.getUserByProperty(u);
				}
//				JSON s = biz.getModuleListByUserId(su.getUserID());
//				System.out.print("=====sss:"+s.toString());
				session.put("loginUser",su);
				session.put("isAdmin", biz.checkAdminByUserId(su.getUserID()+""));
				
				ServletActionContext.getResponse().getWriter().print("success");
			} else {
				ServletActionContext.getResponse().getWriter().print("fail");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String menu() {
		// get role menuitems
		return "menu";
	}
	
	public String logout() {
		session.put("loginUser",null);
		return "loginPage";
	}

	
}
