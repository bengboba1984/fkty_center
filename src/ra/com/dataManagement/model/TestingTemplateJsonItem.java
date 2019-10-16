package ra.com.dataManagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestingTemplateJsonItem {
	private List destinations = new ArrayList();
	private String groupId;
	private String id;
	private String name;
	private Object paremeters;
	private String rankClass;
	private String taskExecuteCount;
	private String taskInterval;
	private String testTimeout;
	private String testType;
	public List getDestinations() {
		return destinations;
	}
	public void setDestinations(List destinations) {
		this.destinations = destinations;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Object getParemeters() {
		return paremeters;
	}
	public void setParemeters(Object paremeters) {
		this.paremeters = paremeters;
	}
	public String getRankClass() {
		return rankClass;
	}
	public void setRankClass(String rankClass) {
		this.rankClass = rankClass;
	}
	public String getTaskExecuteCount() {
		return taskExecuteCount;
	}
	public void setTaskExecuteCount(String taskExecuteCount) {
		this.taskExecuteCount = taskExecuteCount;
	}
	public String getTaskInterval() {
		return taskInterval;
	}
	public void setTaskInterval(String taskInterval) {
		this.taskInterval = taskInterval;
	}
	public String getTestTimeout() {
		return testTimeout;
	}
	public void setTestTimeout(String testTimeout) {
		this.testTimeout = testTimeout;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	
}
