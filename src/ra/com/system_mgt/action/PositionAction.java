package ra.com.system_mgt.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ra.com.system_mgt.bussiness.SystemMGTFacade;
import ra.com.system_mgt.model.MyTreeNode;
import ra.com.system_mgt.model.Position;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class PositionAction extends ActionSupport implements
ServletRequestAware {

private HttpServletRequest request;
private JSONObject resultList;
private JSONObject message;
private JSONArray departmentList;
private JSONArray moduleList;
private SystemMGTFacade biz;
private String positionName;
private String departmentName;

public PositionAction() {
}

public JSONObject getMessage() {
return message;
}

public void setMessage(JSONObject message) {
this.message = message;
}

public JSONArray getModuleList() {
return moduleList;
}

public void setModuleList(JSONArray moduleList) {
this.moduleList = moduleList;
}

public JSONArray getDepartmentList() {
return departmentList;
}

public void setDepartmentList(JSONArray departmentList) {
this.departmentList = departmentList;
}

public JSONObject getResultList() {
return resultList;
}

public void setResultList(JSONObject resultList) {
this.resultList = resultList;
}

public String positionListForDataGrid() throws IOException {
System.out.println("positionListForDataGrid():" + this.toString());
biz = SystemMGTFacade.getInstance();

try {
	Position pos = new Position();
	if(positionName != null && !"".equals(positionName)){
		pos.setPositionName(positionName);
	}
	if(departmentName != null && !"".equals(departmentName)){
		pos.setDepartmentName(departmentName);
	}
	resultList = biz.getPositionListForDataGrid(pos);//biz.getPositionListForDataGrid();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

return SUCCESS;
}

public String departmentList() throws IOException {
biz = SystemMGTFacade.getInstance();

try {
	departmentList = biz.getDepartmentListInPositionByJSon();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

return SUCCESS;
}

public String moduleListByPositionID() {
biz = SystemMGTFacade.getInstance();
try {
	request.setCharacterEncoding("UTF-8");
	String positionID = request.getParameter("positionID");

	try {
		moduleList = biz.getModuleListByPositionID(positionID);
		System.out.println(moduleList.toString());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

} catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

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

public String assignRole() {
try {
	boolean flag = false;
	Map temp = new HashMap();
	biz = SystemMGTFacade.getInstance();
	request.setCharacterEncoding("UTF-8");
	String saveModuleSTR = request.getParameter("saveModuleList");
	String positionID = request.getParameter("positionID");
	System.out.println("@@saveModuleSTR="+saveModuleSTR);
	List saveModuleList = JSON.parseArray(saveModuleSTR, MyTreeNode.class);
	System.out.println("@@saveModuleList="+saveModuleList);
	flag=biz.updateModuleListByPositionID(positionID,saveModuleList);
	if (!flag)
		temp.put("error", "error!");
	message = JSONObject.fromObject(temp);
} catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


return SUCCESS;
}

public String show() {
return "show";
}

@Override
public void setServletRequest(HttpServletRequest arg0) {
this.request = arg0;
}

public String getPositionName() {
return positionName;
}

public void setPositionName(String positionName) {
this.positionName = positionName;
}

public String getDepartmentName() {
return departmentName;
}

public void setDepartmentName(String departmentName) {
this.departmentName = departmentName;
}
}
