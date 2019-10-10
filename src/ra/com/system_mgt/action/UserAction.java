package ra.com.system_mgt.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import ra.com.basic_mgt.bussiness.BasicMGTFacade;
//import ra.com.basic_mgt.model.Supplier;
import ra.com.common.U;
import ra.com.common.model.ListChunk;
import ra.com.system_mgt.bussiness.SystemMGTFacade;
import ra.com.system_mgt.model.Position;
import ra.com.system_mgt.model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements
		ServletRequestAware {

	private HttpServletRequest request;
	private JSONObject userList;
	private JSONArray positionList;
	private JSONObject singleDepartment;
	private JSONObject result;
	private String singlePositionID;
	private SystemMGTFacade biz;
	private File fileCT;
	private String userIDSearch;
	private String userNameSearch;
	private String hireDateSearch;
	private String visibleSearch;
	private InputStream excelStream;
	private String excelFileName;
	private JSONObject message;
	private String moduleId;
	private String companyName;
	private String userName;
	private String position;
	private String jobTitle;
	private String telephoneNumber;
	private String phoneNumber;
	private String email;
	private String fullName;
	private String workId;
	private String departmentId;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public String getTelephoneNumber() {
		return telephoneNumber;
	}


	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getWorkId() {
		return workId;
	}


	public void setWorkId(String workId) {
		this.workId = workId;
	}


	public String getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public String getModuleId() {
		return moduleId;
	}


	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}


	public InputStream getExcelStream() {
		return excelStream;
	}


	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}


	public String getExcelFileName() {
		return excelFileName;
	}


	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}


	public UserAction() {
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


	public String getHireDateSearch() {
		return hireDateSearch;
	}


	public void setHireDateSearch(String hireDateSearch) {
		this.hireDateSearch = hireDateSearch;
	}


	public String getSinglePositionID() {
		return singlePositionID;
	}


	public void setSinglePositionID(String singlePositionID) {
		this.singlePositionID = singlePositionID;
	}

	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
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


	public JSONObject getSingleDepartment() {
		return singleDepartment;
	}


	public void setSingleDepartment(JSONObject singleDepartment) {
		this.singleDepartment = singleDepartment;
	}


	public File getFileCT() {
		return fileCT;
	}


	public void setFileCT(File fileCT) {
		this.fileCT = fileCT;
	}


	public JSONObject getMessage() {
		return message;
	}


	public void setMessage(JSONObject message) {
		this.message = message;
	}


	public String getVisibleSearch() {
		return visibleSearch;
	}


	public void setVisibleSearch(String visibleSearch) {
		this.visibleSearch = visibleSearch;
	}


	public String userListForDataGrid() throws IOException {
		System.out.println("positionListForDataGrid():" + this.toString());
		biz = SystemMGTFacade.getInstance();

		
		try {
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			ListChunk lc = biz.getUserListForDataGrid(userIDSearch,userNameSearch,hireDateSearch,visibleSearch,roleId);
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

	public String positionList() throws IOException {
		biz = SystemMGTFacade.getInstance();

		try {
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.showRoleList(null,roleId);
			ArrayList al = new ArrayList();
			Iterator it = lc.iterator();
			while (it.hasNext()) {
				Position d = (Position) it.next();
				Map m = new HashMap();
				m.put("key", d.getPositionID());
				m.put("value", d.getPositionName());
				al.add(m);
			}
			positionList = JSONArray.fromObject(al);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String showDepartmentList() throws IOException {
		biz = SystemMGTFacade.getInstance();

		try {
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			
			Collection lc = biz.showDepartmentList();
			ArrayList al = new ArrayList();
			Iterator it = lc.iterator();
			while (it.hasNext()) {
				Position d = (Position) it.next();
				Map m = new HashMap();
				m.put("key", d.getPositionID());
				m.put("value", d.getPositionName());
				al.add(m);
			}
			positionList = JSONArray.fromObject(al);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}

	
	public String singleDepartment() throws Exception {
		biz = SystemMGTFacade.getInstance();

		try {
			List lt=biz.getDepartmentNameByPositionID(singlePositionID);
			
			Map temp=(HashMap)lt.get(0);
			
			singleDepartment = JSONObject.fromObject(temp);
			System.out.println(singleDepartment);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("can not get department by position");
			
		}

		return SUCCESS;
	}
	
	public String accept() throws Exception {
		biz = SystemMGTFacade.getInstance();
		request.setCharacterEncoding("UTF-8");

		String inserted = request.getParameter("inserted");
		String deleted = request.getParameter("deleted");
		String updated = request.getParameter("updated");
		System.out.println("In Action : inserted="+inserted);
		System.out.println("In Action : deleted="+deleted);
		System.out.println("In Action : updated="+updated);
		Map temp=new HashMap();
		boolean flag = true;
		try {
			
			if (deleted != null && !"".equals(deleted)) {
				List deleteList = JSON.parseArray(deleted, User.class);
				flag = biz.deleteUser(deleteList);
			}
			if (updated != null && !"".equals(updated)) {
				List updateList = JSON.parseArray(updated, User.class);
				flag = biz.updateUser(updateList);
			}
			if (inserted != null && !"".equals(inserted)) {
				List insertList = JSON.parseArray(inserted, User.class);
				flag = biz.insertUser(insertList);
			}
			if(!flag)temp.put("error", "user name exists");
			result=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
	}

	public String show() {
		return "show";
	}
	
	public String upload() {
		biz = SystemMGTFacade.getInstance();
		String mess = null;
		try {
			mess = biz.uploadUserByExcel(fileCT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map temp = new HashMap();
		System.out.println("@@@in action:mess="+mess);
		if (mess==null||"".equals(mess)) {
			temp.put("success", "Upload Completed!");
			message = JSONObject.fromObject(temp);
			return SUCCESS;
		} else {
			temp.put("error", mess);
			message = JSONObject.fromObject(temp);
			return ERROR;
		}

	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	public String download() throws Exception {
		biz = SystemMGTFacade.getInstance();
		ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
		String roleId = user.getDepartmentID().toString();
		excelStream = biz.downloadUserReport(userIDSearch,userNameSearch,hireDateSearch,visibleSearch,roleId);
		excelFileName = "UserReport.xls";
		return SUCCESS;
	}
	
	public String registerUser() throws Exception{
		System.out.print("==========registerUser");
		biz = SystemMGTFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		try {
			User u =  getUserItem();
			u.setPassword("0000");
			Map temp = biz.registerUser(u);
//			message = JSONObject.fromObject(temp);
//			return SUCCESS;
			if ("success".equals((String)temp.get("success"))) {
				message = JSONObject.fromObject(temp);
				return SUCCESS;
			} else {
				message = JSONObject.fromObject(temp);
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
	}
	
	public User getUserItem(){
		User u = new User();
		u.setCompanyName(U.decode(companyName));
		u.setUserName(U.decode(userName));
		u.setPosition(U.decode(position));
		u.setJobTitle(U.decode(jobTitle));
		u.setTelephoneNumber(telephoneNumber);
		u.setPhoneNumber(phoneNumber);
		u.setEmail(U.decode(email));
		u.setWorkId(U.decode(workId));
		u.setDepartmentID(new Long(departmentId));
		u.setFullName(U.decode(fullName));
		return u;
	}
	
	public String restoreUser() throws Exception{
		biz = SystemMGTFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		boolean flag = true;
		try {
			
			User u =  new User();
			u.setUserName(userName);
			u.setPassword("0000");
			Map temp = biz.restoreUser(u);
			
			if ("success".equals((String)temp.get("success"))) {
				message = JSONObject.fromObject(temp);
				return SUCCESS;
			} else {
				message = JSONObject.fromObject(temp);
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
	}
	
	public String removeitUser() throws Exception{
		biz = SystemMGTFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		boolean flag = true;
		try {
			
			User u =  new User();
			u.setUserName(userName);
			Map temp = biz.removeitUser(u);
			
			if ("success".equals((String)temp.get("success"))) {
				message = JSONObject.fromObject(temp);
				return SUCCESS;
			} else {
				message = JSONObject.fromObject(temp);
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
	}
	public String actionButtonFlag(){
		System.out.println("actionButtonFlag ......................");
		biz = SystemMGTFacade.getInstance();
		Map temp = new HashMap();
		try {
			System.out.println("=============moduleId:"+moduleId);
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String v = biz.getActionRole(moduleId,user.getPositionID());
			temp.put("roleValue", v);
			
			result = JSONObject.fromObject(temp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
