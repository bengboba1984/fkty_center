package ra.com.dataManagement.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ra.com.common.U;
import ra.com.common.action.BaseAction;
import ra.com.common.model.KeyValueImpl;
import ra.com.common.model.ListChunk;
import ra.com.dataManagement.bussiness.DataManagementFacade;

public class DownloadFtpFileAction extends BaseAction {

	private DataManagementFacade biz;
	private JSONObject dataList;
	private String moduleId;
	private JSONArray selectList;
	private int testType;
	private String testTypeSearch;
	private String testingDateBegin;
	private String testingDateEnd;
	private String targetId;
	private String accountSearch;
	private String testerSearch;// 工号
	private String stbIDSearch;
	private String fileId;
	private int page;
	private int rows;

	private JSONObject message;

	public String show() {
		page = 1;
		rows = 25;
		testTypeSearch = "-1";
		return "show";
	}

	public String getFtpFileDataList() throws IOException {
		biz = DataManagementFacade.getInstance();

		try {

			ListChunk lc = biz.getFtpFileDataList(testingDateBegin,
					testingDateEnd, testTypeSearch, accountSearch,
					testerSearch, stbIDSearch, page, rows);
			Map map = new HashMap();
			map.put("total", lc.getTotalCount());
			map.put("rows", U.changeListToJSON(lc.getCollection()));
			dataList = JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SUCCESS");
		return SUCCESS;
	}

	public String getFileType() throws IOException {
		biz = DataManagementFacade.getInstance();

		try {
			Collection lc = biz.getFileType();
			ArrayList al = new ArrayList();
			Iterator it = lc.iterator();
			Map m = new HashMap();
			m.put("key", "-1");
			m.put("value", "-全部-");
			al.add(m);
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

	public String deleteFtpFile() throws Exception {
		biz = DataManagementFacade.getInstance();
		request.setCharacterEncoding("UTF-8");
		boolean flag = true;
		try {
			Map temp = biz.deleteFtpFile(fileId);
			if ("success".equals((String) temp.get("success"))
					|| "warning".equals((String) temp.get("warning"))) {
				message = JSONObject.fromObject(temp);
				return SUCCESS;
			} else {
				message = JSONObject.fromObject(temp);
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("JSON parseArray error", e);
		}
	}

	public void downloadBatchByFile(HttpServletResponse response) {
		
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

	public int getTestType() {
		return testType;
	}

	public void setTestType(int testType) {
		this.testType = testType;
	}

	public String getTestTypeSearch() {
		return testTypeSearch;
	}

	public void setTestTypeSearch(String testTypeSearch) {
		this.testTypeSearch = testTypeSearch;
	}

	public String getTestingDateBegin() {
		return testingDateBegin;
	}

	public void setTestingDateBegin(String testingDateBegin) {
		this.testingDateBegin = testingDateBegin;
	}

	public String getTestingDateEnd() {
		return testingDateEnd;
	}

	public void setTestingDateEnd(String testingDateEnd) {
		this.testingDateEnd = testingDateEnd;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getAccountSearch() {
		return accountSearch;
	}

	public void setAccountSearch(String accountSearch) {
		this.accountSearch = accountSearch;
	}

	public String getTesterSearch() {
		return testerSearch;
	}

	public void setTesterSearch(String testerSearch) {
		this.testerSearch = testerSearch;
	}

	public String getStbIDSearch() {
		return stbIDSearch;
	}

	public void setStbIDSearch(String stbIDSearch) {
		this.stbIDSearch = stbIDSearch;
	}

	public JSONObject getMessage() {
		return message;
	}

	public void setMessage(JSONObject message) {
		this.message = message;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
