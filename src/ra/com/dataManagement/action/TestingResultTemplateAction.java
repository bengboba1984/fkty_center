package ra.com.dataManagement.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ra.com.common.U;
import ra.com.common.action.BaseAction;
import ra.com.dataManagement.bussiness.DataManagementFacade;

public class TestingResultTemplateAction  extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private JSONArray selectList;
	private int testType;
	private String testTypeSearch;
	private String testingDateBegin;
	private String testingDateEnd;
	private String targetId;
	private String accountSearch;
	private String testerSearch;
	private JSONObject message;
	public String show() {
		testTypeSearch = "-1";
		return "show";
	}

	public String getTestingResultTemplateDataList() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.getTestingResultTemplateDataList(roleId,testingDateBegin,testingDateEnd,testTypeSearch,accountSearch,testerSearch);
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
	
	public String getTestingResultDns() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Map map = biz.getTestingResultDns(targetId);
			dataList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}
	
	public String getTestingResultPing() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Map map = biz.getTestingResultPing(targetId);
			dataList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}
	
	public String getTestingResultSpeed() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Map map = biz.getTestingResultSpeed(targetId);
			dataList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}
	
	public String getTestingResultWeb() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Map map = biz.getTestingResultWeb(targetId);
			dataList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}
	
	public String getTestingResultTrace() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Map map = biz.getTestingResultTrace(targetId);
			dataList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}
	
	public String getTestingResultTraceSub() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			
			Collection lc = biz.getTestingResultTraceSubList(targetId);
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

	public JSONArray getSelectList() {
		return selectList;
	}

	public void setSelectList(JSONArray selectList) {
		this.selectList = selectList;
	}

	public int getTestType() {
		return testType;
	}

	public void setTestType(int testType) {
		this.testType = testType;
	}

	public JSONObject getMessage() {
		return message;
	}

	public void setMessage(JSONObject message) {
		this.message = message;
	}

	public String getTestTypeSearch() {
		return testTypeSearch;
	}

	public void setTestTypeSearch(String testTypeSearch) {
		this.testTypeSearch = testTypeSearch;
	}

	public String getTestingDateBegin() {
		return testingDateBegin;
	}

	public void setTestingDateBegin(String testingDateBegin) {
		this.testingDateBegin = testingDateBegin;
	}

	public String getTestingDateEnd() {
		return testingDateEnd;
	}

	public void setTestingDateEnd(String testingDateEnd) {
		this.testingDateEnd = testingDateEnd;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getAccountSearch() {
		return accountSearch;
	}

	public void setAccountSearch(String accountSearch) {
		this.accountSearch = accountSearch;
	}

	public String getTesterSearch() {
		return testerSearch;
	}

	public void setTesterSearch(String testerSearch) {
		this.testerSearch = testerSearch;
	}
	
	
}
