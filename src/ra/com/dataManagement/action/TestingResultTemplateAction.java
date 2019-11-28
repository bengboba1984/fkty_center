package ra.com.dataManagement.action;

import java.io.IOException;
import java.io.InputStream;
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
	private String departmentSearch;
	private String stbidSearch;
	private int page;
	private int rows;
	private JSONObject message;
	private InputStream excelStream;
	private String excelFileName;
	public String show() {
		testTypeSearch = "-1";
		page = 1;
		rows = 25;
		return "show";
	}

	public String getTestingResultTemplateDataList() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			System.out.println("====testerSearch:"+testerSearch);
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.getTestingResultTemplateDataList(roleId,testingDateBegin,testingDateEnd,testTypeSearch,accountSearch,testerSearch,departmentSearch,stbidSearch,null,null,page,rows);
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
	
	public String testingResultTemplateDetail() throws IOException {
		biz = DataManagementFacade.getInstance();
		
		try {
			Map map = biz.getTestingResultTemplateDetail(targetId);
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
	
	public String downloadResultTemplateData() throws Exception {
		biz = DataManagementFacade.getInstance();
		excelStream = biz.downloadResultTemplateData(testingDateBegin,testingDateEnd,testTypeSearch,accountSearch,testerSearch,departmentSearch,stbidSearch,null);
		excelFileName = "ResultTemplateReport.xls";
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

	public String getDepartmentSearch() {
		return departmentSearch;
	}

	public void setDepartmentSearch(String departmentSearch) {
		this.departmentSearch = departmentSearch;
	}

	public String getStbidSearch() {
		return stbidSearch;
	}

	public void setStbidSearch(String stbidSearch) {
		this.stbidSearch = stbidSearch;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
}
