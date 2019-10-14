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
import ra.com.dataManagement.model.TestingTemplateParameterTrace;
import ra.com.dataManagement.model.TestingTemplateParameterWeb;

public class TestingTemplateParameterWebAction extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private String testingTemplateParameterId;
	private String testingTemplateId;
	private String maxDownloadSize;
	private String maxThreadCount;
	private String maxSubCount;
	private String maxSubSaveCount;
	private String itemTimeout;
	private String useDnsCache;
	private String userAgent;
	private String maxPageDepth;
	private String validResponseCodes;
	private String tos;
	private String analysisHtml;
	private String primaryResultIndex;
	private String minLoadPercent;
	private String maxLoadTime;
	private JSONObject message;
	public String show() {
		init();
		return "show";
	}
	public String showEditData(){
		biz = DataManagementFacade.getInstance();
		try {
			Collection lc = biz.getTestingTemplateParameterWebDataList(testingTemplateId);
			System.out.println("====lc.getTotalCount():"+lc.size());
			if(lc!=null&&lc.size()>0){
				TestingTemplateParameterWeb item = (TestingTemplateParameterWeb)((ArrayList)lc).get(0);
				setTestingTemplateParameterWeb(item);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show";
	}
	public void init(){
		maxDownloadSize = "1000";
		maxThreadCount = "5";
		maxSubCount = "200";
		maxSubSaveCount = "200";
		itemTimeout = "10";
		useDnsCache = "0";
		userAgent = "Google Chrome (Windows)";
		maxPageDepth = "4";
		validResponseCodes = "200,201,202,203,204,205,206,300,301,302,303,304,305,306,307";	
		tos = "0";
		analysisHtml = "true";
		primaryResultIndex = "1";
		minLoadPercent = "90";
		maxLoadTime = "30000000";
	}
	public String getTestingTemplateParameterPingDataList() throws IOException {
		biz = DataManagementFacade.getInstance();
		ArrayList para = null;
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.getTestingTemplateParameterPingDataList(testingTemplateId);
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

	public String insertTestingTemplateParameterWeb()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterWeb t = getTestingTemplateParameterWeb();
			temp = biz.insertTestingTemplateParameterWeb(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	
	public String updateTestingTemplateParameterWeb()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterWeb t = getTestingTemplateParameterWeb();
			temp = biz.updateTestingTemplateParameterWeb(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	
	private TestingTemplateParameterWeb getTestingTemplateParameterWeb(){
		TestingTemplateParameterWeb c=new TestingTemplateParameterWeb();
		c.setItemTimeout(itemTimeout);
		c.setMaxDownloadSize(maxDownloadSize);
		c.setMaxPageDepth(maxPageDepth);
		c.setMaxSubCount(maxSubCount);
		c.setMaxThreadCount(maxThreadCount);
		c.setMaxSubSaveCount(maxSubSaveCount);
		c.setTestingTemplateId(testingTemplateId);
		c.setTestingTemplateParameterId(testingTemplateParameterId);
		c.setUseDnsCache(useDnsCache);
		c.setUserAgent(userAgent);
		c.setValidResponseCodes(validResponseCodes);
		c.setTos(tos);
		c.setAnalysisHtml(analysisHtml);
		c.setPrimaryResultIndex(primaryResultIndex);
		c.setMinLoadPercent(minLoadPercent);
		c.setMaxLoadTime(maxLoadTime);
		
		return c;
	}
	private void setTestingTemplateParameterWeb(TestingTemplateParameterWeb c){
		if(c!=null){
			itemTimeout = c.getItemTimeout();
			maxDownloadSize = c.getMaxDownloadSize();
			maxPageDepth = c.getMaxPageDepth();
			maxSubCount = c.getMaxSubCount();
			maxThreadCount = c.getMaxThreadCount();
			maxSubSaveCount = c.getMaxSubSaveCount();
			testingTemplateId = c.getTestingTemplateId();
			testingTemplateParameterId = c.getTestingTemplateParameterId();
			useDnsCache = c.getUseDnsCache();
			userAgent = c.getUserAgent();
			validResponseCodes = c.getValidResponseCodes();
			tos = c.getTos();
			analysisHtml = c.getAnalysisHtml();
			primaryResultIndex = c.getPrimaryResultIndex();
			minLoadPercent = c.getMinLoadPercent();
			maxLoadTime = c.getMaxLoadTime();
		}
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

	public JSONObject getMessage() {
		return message;
	}

	public void setMessage(JSONObject message) {
		this.message = message;
	}

	public String getTestingTemplateParameterId() {
		return testingTemplateParameterId;
	}

	public void setTestingTemplateParameterId(String testingTemplateParameterId) {
		this.testingTemplateParameterId = testingTemplateParameterId;
	}

	public String getTestingTemplateId() {
		return testingTemplateId;
	}

	public void setTestingTemplateId(String testingTemplateId) {
		this.testingTemplateId = testingTemplateId;
	}

	public String getMaxDownloadSize() {
		return maxDownloadSize;
	}

	public void setMaxDownloadSize(String maxDownloadSize) {
		this.maxDownloadSize = maxDownloadSize;
	}

	public String getMaxThreadCount() {
		return maxThreadCount;
	}

	public void setMaxThreadCount(String maxThreadCount) {
		this.maxThreadCount = maxThreadCount;
	}

	public String getMaxSubCount() {
		return maxSubCount;
	}

	public void setMaxSubCount(String maxSubCount) {
		this.maxSubCount = maxSubCount;
	}

	public String getMaxSubSaveCount() {
		return maxSubSaveCount;
	}

	public void setMaxSubSaveCount(String maxSubSaveCount) {
		this.maxSubSaveCount = maxSubSaveCount;
	}

	public String getItemTimeout() {
		return itemTimeout;
	}

	public void setItemTimeout(String itemTimeout) {
		this.itemTimeout = itemTimeout;
	}

	public String getUseDnsCache() {
		return useDnsCache;
	}

	public void setUseDnsCache(String useDnsCache) {
		this.useDnsCache = useDnsCache;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getMaxPageDepth() {
		return maxPageDepth;
	}

	public void setMaxPageDepth(String maxPageDepth) {
		this.maxPageDepth = maxPageDepth;
	}

	public String getValidResponseCodes() {
		return validResponseCodes;
	}

	public void setValidResponseCodes(String validResponseCodes) {
		this.validResponseCodes = validResponseCodes;
	}
	public String getTos() {
		return tos;
	}
	public void setTos(String tos) {
		this.tos = tos;
	}
	public String getAnalysisHtml() {
		return analysisHtml;
	}
	public void setAnalysisHtml(String analysisHtml) {
		this.analysisHtml = analysisHtml;
	}
	public String getPrimaryResultIndex() {
		return primaryResultIndex;
	}
	public void setPrimaryResultIndex(String primaryResultIndex) {
		this.primaryResultIndex = primaryResultIndex;
	}
	public String getMinLoadPercent() {
		return minLoadPercent;
	}
	public void setMinLoadPercent(String minLoadPercent) {
		this.minLoadPercent = minLoadPercent;
	}
	public String getMaxLoadTime() {
		return maxLoadTime;
	}
	public void setMaxLoadTime(String maxLoadTime) {
		this.maxLoadTime = maxLoadTime;
	}

}
