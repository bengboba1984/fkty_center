package ra.com.common.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ra.com.system_mgt.model.User;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware,ServletRequestAware {

	protected Map session;
	protected HttpServletRequest request;

	public void setSession(Map session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;		
	}

	public User getUser(List u){
		User lu=new User();
		return lu;
	}
}
