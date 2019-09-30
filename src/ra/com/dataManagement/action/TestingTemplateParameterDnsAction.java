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
import ra.com.dataManagement.model.TestingTemplateParameterSpeed;

public class TestingTemplateParameterDnsAction extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private String testingTemplateParameterId;
	private String testingTemplateId;
	private String packetCount;
	private String ignoreCount;
	private String roundItemCount;
	private String packetTimeout;
	private String spacingTime;
	private JSONObject message;
	public String show() {
		initValue();
		return "show";
	}
	public String showEditData(){
		biz = DataManagementFacade.getInstance();
		try {
			Collection lc = biz.getTestingTemplateParameterDnsDataList(testingTemplateId);
			System.out.println("====lc.getTotalCount():"+lc.size());
			if(lc!=null&&lc.size()>0){
				TestingTemplateParameterDns item = (TestingTemplateParameterDns)((ArrayList)lc).get(0);
				setTestingTemplateParameterPing(item);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show";
	}
	public void initValue (){
		packetCount = "1";
		ignoreCount = "0";
		roundItemCount = "20";
		packetTimeout = "1";
		spacingTime = "2000";
	}
	public String getTestingTemplateParameterPingDataList() throws IOException {
		biz = DataManagementFacade.getInstance();
		ArrayList para = null;
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.getTestingTemplateParameterDnsDataList(testingTemplateId);
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

	public String insertTestingTemplateParameterDns()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterDns t = getTestingTemplateParameterDns();
			temp = biz.insertTestingTemplateParameterDns(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	
	public String updateTestingTemplateParameterDns()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplateParameterDns t = getTestingTemplateParameterDns();
			temp = biz.updateTestingTemplateParameterDns(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	private TestingTemplateParameterDns getTestingTemplateParameterDns(){
		TestingTemplateParameterDns c=new TestingTemplateParameterDns();
		c.setIgnoreCount(ignoreCount);
		c.setPacketCount(packetCount);
		c.setPacketTimeout(packetTimeout);
		c.setRoundItemCount(roundItemCount);
		c.setSpacingTime(spacingTime);
		c.setTestingTemplateId(testingTemplateId);
		c.setTestingTemplateParameterId(testingTemplateParameterId);
		
		
		return c;
	}
	private void setTestingTemplateParameterPing(TestingTemplateParameterDns c){
		if(c!=null){
			ignoreCount = c.getIgnoreCount();
			packetCount = c.getPacketCount();
			packetTimeout = c.getPacketTimeout();
			roundItemCount = c.getRoundItemCount();
			spacingTime = c.getSpacingTime();
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

	public String getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
	}

	public String getIgnoreCount() {
		return ignoreCount;
	}

	public void setIgnoreCount(String ignoreCount) {
		this.ignoreCount = ignoreCount;
	}

	public String getRoundItemCount() {
		return roundItemCount;
	}

	public void setRoundItemCount(String roundItemCount) {
		this.roundItemCount = roundItemCount;
	}

	public String getPacketTimeout() {
		return packetTimeout;
	}

	public void setPacketTimeout(String packetTimeout) {
		this.packetTimeout = packetTimeout;
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
	
}
