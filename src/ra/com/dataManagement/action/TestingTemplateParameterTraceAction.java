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
import ra.com.dataManagement.model.TestingTemplateParameterDns;
import ra.com.dataManagement.model.TestingTemplateParameterPing;
import ra.com.dataManagement.model.TestingTemplateParameterTrace;

public class TestingTemplateParameterTraceAction extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private String testingTemplateParameterId;
	private String testingTemplateId;
	private String payloadSize;
	private String protocolType;
	private String maxHops;
	private String replyTimeout;
	private String packetCount;
	private String spacingTime;
	private String tos;
	private JSONObject message;
	public String show() {
		init();
		return "show";
	}
	public String showEditData(){
		biz = DataManagementFacade.getInstance();
		try {
			Collection lc = biz.getTestingTemplateParameterTraceDataList(testingTemplateId);
			System.out.println("====lc.getTotalCount():"+lc.size());
			if(lc!=null&&lc.size()>0){
				TestingTemplateParameterTrace item = (TestingTemplateParameterTrace)((ArrayList)lc).get(0);
				setTestingTemplateParameterTrace(item);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show";
	}
	public void init(){
		payloadSize = "32";
		maxHops = "30";
		replyTimeout = "20000";
		packetCount = "3";
		spacingTime = "100";
		tos = "121";
	}
	public String getTestingTemplateParameterPingDataList() throws IOException {
		biz = DataManagementFacade.getInstance();
		ArrayList para = null;
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.getTestingTemplateParameterTraceDataList(testingTemplateId);
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
	public String insertTestingTemplateParameterTrace()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterTrace t = getTestingTemplateParameterTrace();
			temp = biz.insertTestingTemplateParameterTrace(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	
	public String updateTestingTemplateParameterTrace()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterTrace t = getTestingTemplateParameterTrace();
			temp = biz.updateTestingTemplateParameterTrace(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	private TestingTemplateParameterTrace getTestingTemplateParameterTrace(){
		TestingTemplateParameterTrace c=new TestingTemplateParameterTrace();
		c.setMaxHops(maxHops);
		c.setPacketCount(packetCount);
		c.setPayloadSize(payloadSize);
		c.setProtocolType(protocolType);
		c.setReplyTimeout(replyTimeout);
		c.setSpacingTime(spacingTime);
		c.setTestingTemplateId(testingTemplateId);
		c.setTestingTemplateParameterId(testingTemplateParameterId);
		c.setTos(tos);
		return c;
	}
	private void setTestingTemplateParameterTrace(TestingTemplateParameterTrace c){
		if(c!=null){
			maxHops = c.getMaxHops();
			packetCount = c.getPacketCount();
			payloadSize = c.getPayloadSize();
			protocolType = c.getProtocolType();
			replyTimeout = c.getReplyTimeout();
			spacingTime = c.getSpacingTime();
			testingTemplateId = c.getTestingTemplateId();
			testingTemplateParameterId = c.getTestingTemplateParameterId();
			tos = c.getTos();
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

	public String getPayloadSize() {
		return payloadSize;
	}

	public void setPayloadSize(String payloadSize) {
		this.payloadSize = payloadSize;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getMaxHops() {
		return maxHops;
	}

	public void setMaxHops(String maxHops) {
		this.maxHops = maxHops;
	}

	public String getReplyTimeout() {
		return replyTimeout;
	}

	public void setReplyTimeout(String replyTimeout) {
		this.replyTimeout = replyTimeout;
	}

	public String getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
	}

	public String getSpacingTime() {
		return spacingTime;
	}

	public void setSpacingTime(String spacingTime) {
		this.spacingTime = spacingTime;
	}

	public JSONObject getMessage() {
		return message;
	}

	public void setMessage(JSONObject message) {
		this.message = message;
	}
	public String getTos() {
		return tos;
	}
	public void setTos(String tos) {
		this.tos = tos;
	}

}
