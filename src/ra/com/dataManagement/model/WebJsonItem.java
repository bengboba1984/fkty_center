package ra.com.dataManagement.model;

import java.util.Map;

public class WebJsonItem {
	private boolean addTestToAllSubItem = false;
	private String additionalAdvPing = "0";
	private String additionalPing = "0";
	private String additionalTrace = "0";
	private boolean analysisHtml = true;
	private boolean exportLocaitonInfo = false;
	private boolean headOnly = false;
	
	private String itemTimeoutTime = "10";
	private String maxDownloadSize = "1000";
	private String maxLoadTime="";
	private String maxPageDepth="";
	private String maxSubCount = "200";
	private String maxSubSaveCount="";
	private String maxThreadCount = "5";
	private String maxUrlRepeatCount="";
	private String minLoadPercent="";
	private boolean onlyTestingAndSummary = true;
	private String primaryResultIndex="";
	private String statOperatorId="";
	private boolean thresholdTriggerAddTest = false;
	private String tos = "0";
	private boolean useDnsCache = true;
	private String useSimulator = "0";
	private String userAgent = "Google Chrome (Windows)";
	private String validResponseCodes = "200,201,202,203,204,205,206,300,301,302,303,304,305,306,307";
	private String optimizations = "{}";
	public boolean isAddTestToAllSubItem() {
		return addTestToAllSubItem;
	}
	public void setAddTestToAllSubItem(boolean addTestToAllSubItem) {
		this.addTestToAllSubItem = addTestToAllSubItem;
	}
	public String getAdditionalAdvPing() {
		return additionalAdvPing;
	}
	public void setAdditionalAdvPing(String additionalAdvPing) {
		this.additionalAdvPing = additionalAdvPing;
	}
	public String getAdditionalPing() {
		return additionalPing;
	}
	public void setAdditionalPing(String additionalPing) {
		this.additionalPing = additionalPing;
	}
	public String getAdditionalTrace() {
		return additionalTrace;
	}
	public void setAdditionalTrace(String additionalTrace) {
		this.additionalTrace = additionalTrace;
	}
	public boolean isAnalysisHtml() {
		return analysisHtml;
	}
	public void setAnalysisHtml(boolean analysisHtml) {
		this.analysisHtml = analysisHtml;
	}
	public boolean isExportLocaitonInfo() {
		return exportLocaitonInfo;
	}
	public void setExportLocaitonInfo(boolean exportLocaitonInfo) {
		this.exportLocaitonInfo = exportLocaitonInfo;
	}
	public boolean isHeadOnly() {
		return headOnly;
	}
	public void setHeadOnly(boolean headOnly) {
		this.headOnly = headOnly;
	}
	public String getItemTimeoutTime() {
		return itemTimeoutTime;
	}
	public void setItemTimeoutTime(String itemTimeoutTime) {
		this.itemTimeoutTime = itemTimeoutTime;
	}
	public String getMaxDownloadSize() {
		return maxDownloadSize;
	}
	public void setMaxDownloadSize(String maxDownloadSize) {
		this.maxDownloadSize = maxDownloadSize;
	}
	public String getMaxLoadTime() {
		return maxLoadTime;
	}
	public void setMaxLoadTime(String maxLoadTime) {
		this.maxLoadTime = maxLoadTime;
	}
	public String getMaxPageDepth() {
		return maxPageDepth;
	}
	public void setMaxPageDepth(String maxPageDepth) {
		this.maxPageDepth = maxPageDepth;
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
	public String getMaxThreadCount() {
		return maxThreadCount;
	}
	public void setMaxThreadCount(String maxThreadCount) {
		this.maxThreadCount = maxThreadCount;
	}
	public String getMaxUrlRepeatCount() {
		return maxUrlRepeatCount;
	}
	public void setMaxUrlRepeatCount(String maxUrlRepeatCount) {
		this.maxUrlRepeatCount = maxUrlRepeatCount;
	}
	public String getMinLoadPercent() {
		return minLoadPercent;
	}
	public void setMinLoadPercent(String minLoadPercent) {
		this.minLoadPercent = minLoadPercent;
	}
	public boolean isOnlyTestingAndSummary() {
		return onlyTestingAndSummary;
	}
	public void setOnlyTestingAndSummary(boolean onlyTestingAndSummary) {
		this.onlyTestingAndSummary = onlyTestingAndSummary;
	}
	public String getPrimaryResultIndex() {
		return primaryResultIndex;
	}
	public void setPrimaryResultIndex(String primaryResultIndex) {
		this.primaryResultIndex = primaryResultIndex;
	}
	public String getStatOperatorId() {
		return statOperatorId;
	}
	public void setStatOperatorId(String statOperatorId) {
		this.statOperatorId = statOperatorId;
	}
	public boolean isThresholdTriggerAddTest() {
		return thresholdTriggerAddTest;
	}
	public void setThresholdTriggerAddTest(boolean thresholdTriggerAddTest) {
		this.thresholdTriggerAddTest = thresholdTriggerAddTest;
	}
	public String getTos() {
		return tos;
	}
	public void setTos(String tos) {
		this.tos = tos;
	}
	public boolean isUseDnsCache() {
		return useDnsCache;
	}
	public void setUseDnsCache(boolean useDnsCache) {
		this.useDnsCache = useDnsCache;
	}
	public String getUseSimulator() {
		return useSimulator;
	}
	public void setUseSimulator(String useSimulator) {
		this.useSimulator = useSimulator;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getValidResponseCodes() {
		return validResponseCodes;
	}
	public void setValidResponseCodes(String validResponseCodes) {
		this.validResponseCodes = validResponseCodes;
	}
	public String getOptimizations() {
		return optimizations;
	}
	public void setOptimizations(String optimizations) {
		this.optimizations = optimizations;
	}
	
	
}
