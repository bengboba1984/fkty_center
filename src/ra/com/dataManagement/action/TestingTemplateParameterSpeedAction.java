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
import ra.com.dataManagement.model.TestingTemplateParameterSpeed;

public class TestingTemplateParameterSpeedAction extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private String testingTemplateParameterId;
	private String testingTemplateId;
	private String hostIps;
	private String maxTestTime;
	private String minTestTime;
	private String continueTimes;
	private String jitterThroughput;
	private String requestTimeout;
	private String requestPieceSize;
	private String payloadSize;
	private String downloadSize;
	private JSONObject message;
	
	public String show() {
		init();
		return "show";
	}
	public String showEditData(){
		biz = DataManagementFacade.getInstance();
		try {
			Collection lc = biz.getTestingTemplateParameterSpeedDataList(testingTemplateId);
			System.out.println("====lc.getTotalCount():"+lc.size());
			if(lc!=null&&lc.size()>0){
				TestingTemplateParameterSpeed item = (TestingTemplateParameterSpeed)((ArrayList)lc).get(0);
				setTestingTemplateParameterSpeed(item);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show";
	}
	public void init(){
		hostIps = "";
		maxTestTime = "20";
		minTestTime = "5";
		continueTimes = "2";
		jitterThroughput = "10";
		requestTimeout ="15000";
		requestPieceSize = "32768";
		payloadSize = "50000";
		downloadSize = "500000";
	}
	public String getTestingTemplateParameterPingDataList() throws IOException {
		biz = DataManagementFacade.getInstance();
		ArrayList para = null;
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.getTestingTemplateParameterSpeedDataList(testingTemplateId);
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

	public String insertTestingTemplateParameterSpeed()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterSpeed t = getTestingTemplateParameterSpeed();
			temp = biz.insertTestingTemplateParameterSpeed(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	
	public String updateTestingTemplateParameterSpeed()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterSpeed t = getTestingTemplateParameterSpeed();
			temp = biz.updateTestingTemplateParameterSpeed(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	private TestingTemplateParameterSpeed getTestingTemplateParameterSpeed(){
		TestingTemplateParameterSpeed c=new TestingTemplateParameterSpeed();
		c.setContinueTimes(continueTimes);
		c.setDownloadSize(downloadSize);
		c.setHostIps(hostIps);
		c.setJitterThroughput(jitterThroughput);
		c.setMaxTestTime(maxTestTime);
		c.setMinTestTime(minTestTime);
		c.setPayloadSize(payloadSize);
		c.setRequestPieceSize(requestPieceSize);
		c.setRequestTimeout(requestTimeout);
		c.setTestingTemplateId(testingTemplateId);
		c.setTestingTemplateParameterId(testingTemplateParameterId);
		
		return c;
	}
	private void setTestingTemplateParameterSpeed(TestingTemplateParameterSpeed c){
		if(c!=null){
			continueTimes = c.getContinueTimes();
			downloadSize = c.getDownloadSize();
			hostIps = c.getHostIps();
			jitterThroughput = c.getJitterThroughput();
			maxTestTime = c.getMaxTestTime();
			minTestTime = c.getMinTestTime();
			payloadSize = c.getPayloadSize();
			requestPieceSize = c.getRequestPieceSize();
			requestTimeout = c.getRequestTimeout();
			testingTemplateId = c.getTestingTemplateId();
			testingTemplateParameterId = c.getTestingTemplateParameterId();
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

	public String getHostIps() {
		return hostIps;
	}

	public void setHostIps(String hostIps) {
		this.hostIps = hostIps;
	}

	public String getMaxTestTime() {
		return maxTestTime;
	}

	public void setMaxTestTime(String maxTestTime) {
		this.maxTestTime = maxTestTime;
	}

	public String getMinTestTime() {
		return minTestTime;
	}

	public void setMinTestTime(String minTestTime) {
		this.minTestTime = minTestTime;
	}

	public String getContinueTimes() {
		return continueTimes;
	}

	public void setContinueTimes(String continueTimes) {
		this.continueTimes = continueTimes;
	}

	public String getJitterThroughput() {
		return jitterThroughput;
	}

	public void setJitterThroughput(String jitterThroughput) {
		this.jitterThroughput = jitterThroughput;
	}

	public String getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(String requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public String getRequestPieceSize() {
		return requestPieceSize;
	}

	public void setRequestPieceSize(String requestPieceSize) {
		this.requestPieceSize = requestPieceSize;
	}

	public String getPayloadSize() {
		return payloadSize;
	}

	public void setPayloadSize(String payloadSize) {
		this.payloadSize = payloadSize;
	}

	public String getDownloadSize() {
		return downloadSize;
	}

	public void setDownloadSize(String downloadSize) {
		this.downloadSize = downloadSize;
	}

	public JSONObject getMessage() {
		return message;
	}

	public void setMessage(JSONObject message) {
		this.message = message;
	}
	
	
}
