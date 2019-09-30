package ra.com.dataManagement.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ra.com.common.U;
import ra.com.common.action.BaseAction;
import ra.com.common.model.KeyValueImpl;
import ra.com.dataManagement.bussiness.DataManagementFacade;
import ra.com.dataManagement.model.TestingTemplate;

public class TestingTemplateAction  extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private JSONArray selectList;
	private String showAllFlag;
	private String testType;
	private String testGroupId;
	private String templateName;
	private String sortLevel;
	private String description;
	private String testTimeout;
	private String testInterval;
	private String testExecuteCount;
	private String testingTemplateId;
	private String rankClass;
	private String testingDuration;
	private String action;
	
	private JSONObject message;
	public String show() {
		return "show";
	}
	public String showPing(){
		return "showPing";
	}
	public String getTestingTemplateDataList() throws IOException {
		biz = DataManagementFacade.getInstance();
		ArrayList para = null;
		
		try {
			
			ra.com.system_mgt.model.User user=(ra.com.system_mgt.model.User)request.getSession().getAttribute("loginUser");
			String roleId = user.getDepartmentID().toString();
			Collection lc = biz.getTestingTemplateDataList(roleId);
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
	
	public String getTestGroup() throws IOException {
		biz = DataManagementFacade.getInstance();

		try {
			Collection lc = biz.getTestGroup();
			ArrayList al = new ArrayList();
			Iterator it = lc.iterator();
			Map m = new HashMap();
			if("1".equals(showAllFlag)){
				m.put("key", "-1");
				m.put("value", "-全部-");
				al.add(m);
			}
			while (it.hasNext()) {
				KeyValueImpl d = (KeyValueImpl) it.next();
				m = new HashMap();
				m.put("key", d.getKey());
				m.put("value", d.getValue());
				al.add(m);
			}
			selectList = JSONArray.fromObject(al);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String getTestTypeList() throws IOException {
		biz = DataManagementFacade.getInstance();

		try {
			Collection lc = biz.getTestType();
			ArrayList al = new ArrayList();
			Iterator it = lc.iterator();
			Map m = new HashMap();
			if("1".equals(showAllFlag)){
				m.put("key", "-1");
				m.put("value", "-全部-");
				al.add(m);
			}
			while (it.hasNext()) {
				KeyValueImpl d = (KeyValueImpl) it.next();
				m = new HashMap();
				m.put("key", d.getKey());
				m.put("value", d.getValue());
				al.add(m);
			}
			selectList = JSONArray.fromObject(al);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String getTargetTypeList() throws IOException {
		biz = DataManagementFacade.getInstance();

		try {
			Collection lc = biz.getTargetType();
			ArrayList al = new ArrayList();
			Iterator it = lc.iterator();
			Map m = new HashMap();
			if("1".equals(showAllFlag)){
				m.put("key", "-1");
				m.put("value", "-全部-");
				al.add(m);
			}
			while (it.hasNext()) {
				KeyValueImpl d = (KeyValueImpl) it.next();
				m = new HashMap();
				m.put("key", d.getKey());
				m.put("value", d.getValue());
				al.add(m);
			}
			selectList = JSONArray.fromObject(al);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String getProtocolType() throws IOException {
		biz = DataManagementFacade.getInstance();

		try {
			Collection lc = biz.getProtocolType();
			ArrayList al = new ArrayList();
			Iterator it = lc.iterator();
			Map m = new HashMap();
			if("1".equals(showAllFlag)){
				m.put("key", "-1");
				m.put("value", "-全部-");
				al.add(m);
			}
			while (it.hasNext()) {
				KeyValueImpl d = (KeyValueImpl) it.next();
				m = new HashMap();
				m.put("key", d.getKey());
				m.put("value", d.getValue());
				al.add(m);
			}
			selectList = JSONArray.fromObject(al);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String insertTestingTemplate()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplate t = getTestingTemplate();
			temp = biz.insertTestingTemplate(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	public String updateTestingTemplate()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplate t = getTestingTemplate();
			temp = biz.updateTestingTemplate(t);
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
		
	}
	
	public String removeitTestingTemplate()throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		Map temp=new HashMap();
		
		try{
			TestingTemplate t = getTestingTemplate();
			temp = biz.removeitTestingTemplate(t);
			
			message=JSONObject.fromObject(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error",e);
		}
		return SUCCESS;
	}
	private TestingTemplate getTestingTemplate(){
		TestingTemplate c=new TestingTemplate();
		c.setDescription(U.decodeString(description));
		c.setSortLevel(sortLevel);
		c.setTemplateName(U.decodeString(templateName));
		c.setTestExecuteCount(testExecuteCount);
		c.setTestingTemplateGroupId(testGroupId);
		c.setTestingTemplateId(testingTemplateId);
		c.setTestInterval(testInterval);
		c.setTestTimeout(testTimeout);
		c.setTestType(testType);
		c.setRankClass(rankClass);
		c.setTestingDuration(U.decodeString(testingDuration));
		return c;
	}
	private void setTestingTemplate(TestingTemplate c){
		if(c!=null){
			description = c.getDescription();
			sortLevel = c.getSortLevel();
			templateName = c.getTemplateName();
			testExecuteCount = c.getTestExecuteCount();
			testGroupId = c.getTestingTemplateGroupId();
			testingTemplateId = c.getTestingTemplateId();
			testInterval = c.getTestInterval();
			testTimeout = c.getTestTimeout();
			testType = c.getTestType();
			rankClass = c.getRankClass();
			testingDuration = c.getTestingDuration();
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
	public JSONArray getSelectList() {
		return selectList;
	}
	public void setSelectList(JSONArray selectList) {
		this.selectList = selectList;
	}
	public String getShowAllFlag() {
		return showAllFlag;
	}
	public void setShowAllFlag(String showAllFlag) {
		this.showAllFlag = showAllFlag;
	}
	public String getTestGroupId() {
		return testGroupId;
	}
	public void setTestGroupId(String testGroupId) {
		this.testGroupId = testGroupId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getSortLevel() {
		return sortLevel;
	}
	public void setSortLevel(String sortLevel) {
		this.sortLevel = sortLevel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestingTemplateId() {
		return testingTemplateId;
	}
	public void setTestingTemplateId(String testingTemplateId) {
		this.testingTemplateId = testingTemplateId;
	}
	public String getTestTimeout() {
		return testTimeout;
	}
	public void setTestTimeout(String testTimeout) {
		this.testTimeout = testTimeout;
	}
	public String getTestInterval() {
		return testInterval;
	}
	public void setTestInterval(String testInterval) {
		this.testInterval = testInterval;
	}
	public String getTestExecuteCount() {
		return testExecuteCount;
	}
	public void setTestExecuteCount(String testExecuteCount) {
		this.testExecuteCount = testExecuteCount;
	}
	
	public JSONObject getMessage() {
		return message;
	}
	public void setMessage(JSONObject message) {
		this.message = message;
	}
	public String getRankClass() {
		return rankClass;
	}
	public void setRankClass(String rankClass) {
		this.rankClass = rankClass;
	}
	public String getTestingDuration() {
		return testingDuration;
	}
	public void setTestingDuration(String testingDuration) {
		this.testingDuration = testingDuration;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	

}
