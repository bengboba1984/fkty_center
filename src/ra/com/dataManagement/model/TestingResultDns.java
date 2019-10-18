package ra.com.dataManagement.model;

import java.util.List;

public class TestingResultDns {
	private String  resultDnsId;
	private String  numberOfAnswers;
	private int  resolveTime;
	private int  successPercent;
	private List<TestingResultTraceSub> resultSubData;
	
	public String getResultDnsId() {
		return resultDnsId;
	}
	public void setResultDnsId(String resultDnsId) {
		this.resultDnsId = resultDnsId;
	}
	public String getNumberOfAnswers() {
		return numberOfAnswers;
	}
	public void setNumberOfAnswers(String numberOfAnswers) {
		this.numberOfAnswers = numberOfAnswers;
	}
	public int getResolveTime() {
		return resolveTime;
	}
	public void setResolveTime(int resolveTime) {
		this.resolveTime = resolveTime;
	}
	public int getSuccessPercent() {
		return successPercent;
	}
	public void setSuccessPercent(int successPercent) {
		this.successPercent = successPercent;
	}
	public List<TestingResultTraceSub> getResultSubData() {
		return resultSubData;
	}
	public void setResultSubData(List<TestingResultTraceSub> resultSubData) {
		this.resultSubData = resultSubData;
	}
	
	
}
