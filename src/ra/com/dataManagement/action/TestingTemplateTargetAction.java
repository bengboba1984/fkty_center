package ra.com.dataManagement.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import ra.com.common.U;
import ra.com.common.action.BaseAction;
import ra.com.dataManagement.bussiness.DataManagementFacade;
import ra.com.dataManagement.model.TestingTemplateParameterPing;
import ra.com.dataManagement.model.TestingTemplateTarget;

public class TestingTemplateTargetAction extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private String testingTemplateId;
	private String targetType;
	private JSONObject message;
	private String testingTemplateTargetId;
	private String name;
	private String nodeIp;
	private String sortLevel;
	private String active;
	public String show() {
		return "show";
	}
	
	public String getTestingTemplateTargetDataList() throws IOException {
		biz = DataManagementFacade.getInstance();
		ArrayList para = null;
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.getTestingTemplateTargetDataList(testingTemplateId);
			Map map = new HashMap();
			map.put("rows", U.changeListToJSON(lc));
			System.out.println("====lc.getTotalCount():"+lc.size());
			dataList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}
	public String insertTargetType()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			temp = biz.insertTargetType(targetType);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	public String insertTestingTarget()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateTarget t = getTestingTarget();
			temp = biz.insertTestingTarget(t);
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	public String deleteTestingTarget()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			temp = biz.deleteTestingTarget(testingTemplateTargetId);
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	
	public String updateTestingTarget()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateTarget t = getTestingTarget();
			temp = biz.updateTestingTarget(t);
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	public TestingTemplateTarget  getTestingTarget(){
		TestingTemplateTarget t = new TestingTemplateTarget();
		t.setActive(active);
		t.setName(name);
		t.setNodeIp(nodeIp);
		t.setSortLevel(sortLevel);
		t.setTargetType(targetType);
		t.setTestingTemplateId(testingTemplateId);
		t.setTestingTemplateTargetId(testingTemplateTargetId);
		return t;
	}
	
	public void  setTestingTarget(TestingTemplateTarget t){
		active = t.getActive();
		name = t.getName();
		nodeIp = t.getNodeIp();
		sortLevel = t.getSortLevel();
		targetType = t.getTargetType();
		testingTemplateId = t.getTestingTemplateId();
		testingTemplateTargetId = t.getTestingTemplateTargetId();
	}
	
	public JSONObject getDataList() {
		return dataList;
	}

	public void setDataList(JSONObject dataList) {
		this.dataList = dataList;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getTestingTemplateId() {
		return testingTemplateId;
	}

	public void setTestingTemplateId(String testingTemplateId) {
		this.testingTemplateId = testingTemplateId;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public JSONObject getMessage() {
		return message;
	}

	public void setMessage(JSONObject message) {
		this.message = message;
	}

	public String getTestingTemplateTargetId() {
		return testingTemplateTargetId;
	}

	public void setTestingTemplateTargetId(String testingTemplateTargetId) {
		this.testingTemplateTargetId = testingTemplateTargetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNodeIp() {
		return nodeIp;
	}

	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}

	public String getSortLevel() {
		return sortLevel;
	}

	public void setSortLevel(String sortLevel) {
		this.sortLevel = sortLevel;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	
}
