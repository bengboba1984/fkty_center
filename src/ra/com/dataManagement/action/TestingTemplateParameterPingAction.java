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
import ra.com.dataManagement.model.TestingTemplate;
import ra.com.dataManagement.model.TestingTemplateParameterPing;

public class TestingTemplateParameterPingAction extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private String testingTemplateId;
	private String testingTemplateParameterId;
	private String packetCount;
	private String spaceingTime;
	private String packetTimeout;
	private String payloadSize;
	private String payloadData;
	private String maxTtl;
	private String tos;
	private JSONObject message;
	public String show() {
		init();
		return "show";
	}
	
	public String showEditData(){
		biz = DataManagementFacade.getInstance();
		try {
			Collection lc = biz.getTestingTemplateParameterPingDataList(testingTemplateId);
			System.out.println("====lc.getTotalCount():"+lc.size());
			if(lc!=null&&lc.size()>0){
				TestingTemplateParameterPing item = (TestingTemplateParameterPing)((ArrayList)lc).get(0);
				setTestingTemplateParameterPing(item);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show";
	}
	public void init(){
		maxTtl = "255";
		packetCount = "100";
		packetTimeout = "1";
		payloadData = "92";
		payloadSize = "64";
		spaceingTime = "100";
		tos = "0";
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

	public String insertTestingTemplateParameterPing()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterPing t = getTestingTemplateParameterPing();
			temp = biz.insertTestingTemplateParameterPing(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	
	public String updateTestingTemplateParameterPing()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterPing t = getTestingTemplateParameterPing();
			temp = biz.updateTestingTemplateParameterPing(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	private TestingTemplateParameterPing getTestingTemplateParameterPing(){
		TestingTemplateParameterPing c=new TestingTemplateParameterPing();
		c.setMaxTtl(maxTtl);
		c.setPacketCount(packetCount);
		c.setPacketTimeout(packetTimeout);
		c.setPayloadData(payloadData);
		c.setPayloadSize(payloadSize);
		c.setSpaceingTime(spaceingTime);
		c.setTestingTemplateId(testingTemplateId);
		c.setTestingTemplateParameterId(testingTemplateParameterId);
		c.setTos(tos);
		
		return c;
	}
	private void setTestingTemplateParameterPing(TestingTemplateParameterPing c){
		if(c!=null){
			maxTtl = c.getMaxTtl();
			packetCount = c.getPacketCount();
			packetTimeout = c.getPacketTimeout();
			payloadData = c.getPayloadData();
			payloadSize = c.getPayloadSize();
			spaceingTime = c.getSpaceingTime();
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

	public String getTestingTemplateId() {
		return testingTemplateId;
	}

	public void setTestingTemplateId(String testingTemplateId) {
		this.testingTemplateId = testingTemplateId;
	}

	public String getTestingTemplateParameterId() {
		return testingTemplateParameterId;
	}

	public void setTestingTemplateParameterId(String testingTemplateParameterId) {
		this.testingTemplateParameterId = testingTemplateParameterId;
	}

	public String getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
	}

	public String getSpaceingTime() {
		return spaceingTime;
	}

	public void setSpaceingTime(String spaceingTime) {
		this.spaceingTime = spaceingTime;
	}

	public String getPacketTimeout() {
		return packetTimeout;
	}

	public void setPacketTimeout(String packetTimeout) {
		this.packetTimeout = packetTimeout;
	}

	public String getPayloadSize() {
		return payloadSize;
	}

	public void setPayloadSize(String payloadSize) {
		this.payloadSize = payloadSize;
	}

	public String getPayloadData() {
		return payloadData;
	}

	public void setPayloadData(String payloadData) {
		this.payloadData = payloadData;
	}

	public String getMaxTtl() {
		return maxTtl;
	}

	public void setMaxTtl(String maxTtl) {
		this.maxTtl = maxTtl;
	}

	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

	public JSONObject getMessage() {
		return message;
	}

	public void setMessage(JSONObject message) {
		this.message = message;
	}

}
