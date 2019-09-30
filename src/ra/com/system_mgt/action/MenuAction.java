package ra.com.system_mgt.action;


import java.io.IOException;

import ra.com.common.action.BaseAction;
import ra.com.portal.bussiness.PortalFacade;
import ra.com.system_mgt.model.User;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class MenuAction extends BaseAction{

	private long userId;
	private PortalFacade biz;
	private JSON menuList;
	
	


	public MenuAction() {
	}

	
	public String menuList() {
		biz=PortalFacade.getInstance();
		System.out.print("menuList():userId="+userId);
		try {
			menuList = biz.getModuleListByUserId(userId);
			System.out.println("menuList():userId="+menuList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public JSON getMenuList() {
		return menuList;
	}

	public void setMenuList(JSON menuList) {
		this.menuList = menuList;
	}
	
}
