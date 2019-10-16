package ra.com.dataManagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestingTemplateJsonItem {
	private List destinations = new ArrayList();
	private int groupId;
	private int id;
	private String name;
	private Object parameters;
	private int rankClass;
	private int taskExecuteCount;
	private int taskInterval;
	private int testTimeout;
	private int testType;
	public List getDestinations() {
		return destinations;
	}
	public void setDestinations(List destinations) {
		this.destinations = destinations;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getParameters() {
		return parameters;
	}
	public void setParameters(Object parameters) {
		this.parameters = parameters;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRankClass() {
		return rankClass;
	}
	public void setRankClass(int rankClass) {
		this.rankClass = rankClass;
	}
	public int getTaskExecuteCount() {
		return taskExecuteCount;
	}
	public void setTaskExecuteCount(int taskExecuteCount) {
		this.taskExecuteCount = taskExecuteCount;
	}
	public int getTaskInterval() {
		return taskInterval;
	}
	public void setTaskInterval(int taskInterval) {
		this.taskInterval = taskInterval;
	}
	public int getTestTimeout() {
		return testTimeout;
	}
	public void setTestTimeout(int testTimeout) {
		this.testTimeout = testTimeout;
	}
	public int getTestType() {
		return testType;
	}
	public void setTestType(int testType) {
		this.testType = testType;
	}
	
}
