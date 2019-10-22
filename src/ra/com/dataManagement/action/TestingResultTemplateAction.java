package ra.com.dataManagement.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ra.com.common.action.BaseAction;
import ra.com.dataManagement.bussiness.DataManagementFacade;

public class TestingResultTemplateAction  extends BaseAction {
	
	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private JSONArray selectList;
	private String showAllFlag;
	private int testType;
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

}
