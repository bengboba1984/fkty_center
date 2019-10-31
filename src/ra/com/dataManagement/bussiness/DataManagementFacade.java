package ra.com.dataManagement.bussiness;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

import ra.com.common.Const;
import ra.com.common.U;
import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
import ra.com.dataManagement.DAO.DataManagementDAO;
import ra.com.dataManagement.model.DnsJsonItem;
import ra.com.dataManagement.model.FtpFile;
import ra.com.dataManagement.model.PingJsonItem;
import ra.com.dataManagement.model.ResultTemplateJsonItem;
import ra.com.dataManagement.model.SpeedJsonItem;
import ra.com.dataManagement.model.TargetJsonItem;
import ra.com.dataManagement.model.TestingResult;
import ra.com.dataManagement.model.TestingResultDns;
import ra.com.dataManagement.model.TestingResultPing;
import ra.com.dataManagement.model.TestingResultSpeed;
import ra.com.dataManagement.model.TestingResultTrace;
import ra.com.dataManagement.model.TestingResultTraceSub;
import ra.com.dataManagement.model.TestingResultWeb;
import ra.com.dataManagement.model.TestingTemplate;
import ra.com.dataManagement.model.TestingTemplateJsonItem;
import ra.com.dataManagement.model.TestingTemplateParameterDns;
import ra.com.dataManagement.model.TestingTemplateParameterPing;
import ra.com.dataManagement.model.TestingTemplateParameterSpeed;
import ra.com.dataManagement.model.TestingTemplateParameterTrace;
import ra.com.dataManagement.model.TestingTemplateParameterWeb;
import ra.com.dataManagement.model.TestingTemplateTarget;
import ra.com.dataManagement.model.TraceJsonItem;
import ra.com.dataManagement.model.WebJsonItem;
import ra.com.system_mgt.DAO.SystemMGTDAO;
import ra.com.system_mgt.model.User;
import ra.com.system_mgt.model.UserManagement;

public class DataManagementFacade {
	private DataManagementDAO dao;
	private static DataManagementFacade me = new DataManagementFacade();

	public static DataManagementFacade getInstance() {
		return me;
	}

	public DataManagementFacade() {
		try {
			dao = (DataManagementDAO) ra.com.common.dao.DAOFactory
					.getDAO("ra.com.dataManagement.DAO.DataManagementDAOImpl");
		} catch (Exception e) {
			System.out.println(getClass().getName() + "/n" + e.toString());
		}
	}

	public Collection getTestingTemplateDataList(String roleId)
			throws Exception {
		ListChunk lc = dao.getTestingTemplateDataList(roleId, 1, 1000);
		return lc.getCollection();
	}

	public Collection getTestGroup() throws Exception {
		return dao.getTestGroup();
	}

	public Collection getTestType() throws Exception {
		return dao.getTestType();
	}

	public Collection getProtocolType() throws Exception {
		return dao.getProtocolType();
	}

	public Collection getTargetType() throws Exception {
		return dao.getTargetType();
	}

	public Collection getTestingTemplateTargetDataList(String templateId)
			throws Exception {
		ListChunk lc = dao
				.getTestingTemplateTargetDataList(templateId, 1, 1000);
		return lc.getCollection();
	}

	public Collection getTestingTemplateParameterPingDataList(String templateId)
			throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterPingDataList(templateId,
				1, 1000);
		return lc.getCollection();
	}

	public Collection getTestingTemplateParameterDnsDataList(String templateId)
			throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterDnsDataList(templateId,
				1, 1000);
		return lc.getCollection();
	}

	public Collection getTestingTemplateParameterSpeedDataList(String templateId)
			throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterSpeedDataList(templateId,
				1, 1000);
		return lc.getCollection();
	}

	public Collection getTestingTemplateParameterTraceDataList(String templateId)
			throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterTraceDataList(templateId,
				1, 1000);
		return lc.getCollection();
	}

	public Collection getTestingTemplateParameterWebDataList(String templateId)
			throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterWebDataList(templateId,
				1, 1000);
		return lc.getCollection();
	}

	public Map insertTestingTemplate(TestingTemplate t) throws Exception {

		Map temp = new HashMap();
		if (t.getTestingTemplateId() == null
				|| "".equals(t.getTestingTemplateId())) {
			Long templateId = dao.getTestTemplateId();
			t.setTestingTemplateId(templateId.toString());
		}
		dao.insertTestingTemplate(t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("testType", t.getTestType());
		temp.put("success", "success");
		return temp;
	}

	public Map updateTestingTemplate(TestingTemplate t) throws Exception {

		Map temp = new HashMap();
		dao.updateTestingTemplate(t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("testType", t.getTestType());
		temp.put("success", "success");
		return temp;
	}

	public Map removeitTestingTemplate(TestingTemplate t) throws Exception {
		Map temp = new HashMap();
		dao.removeitTestingTemplate(t);
		temp.put("success", "success");
		return temp;
	}

	public Map insertTestingTemplateParameterPing(TestingTemplateParameterPing t)
			throws Exception {
		Map temp = new HashMap();
		dao.insertTestingTemplateParameterPing(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map insertTestingTemplateParameterTrace(
			TestingTemplateParameterTrace t) throws Exception {
		Map temp = new HashMap();
		dao.insertTestingTemplateParameterTrace(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map insertTestingTemplateParameterSpeed(
			TestingTemplateParameterSpeed t) throws Exception {
		Map temp = new HashMap();
		dao.insertTestingTemplateParameterSpeed(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map insertTestingTemplateParameterDns(TestingTemplateParameterDns t)
			throws Exception {
		Map temp = new HashMap();
		dao.insertTestingTemplateParameterDns(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map insertTestingTemplateParameterWeb(TestingTemplateParameterWeb t)
			throws Exception {
		Map temp = new HashMap();
		dao.insertTestingTemplateParameterWeb(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map insertTargetType(String type) throws Exception {
		Map temp = new HashMap();
		dao.insertTargetType(type);
		temp.put("success", "success");
		return temp;
	}

	public Map insertTestingTarget(TestingTemplateTarget item) throws Exception {
		Map temp = new HashMap();
		if (item.getTestingTemplateId() == null
				|| "".equals(item.getTestingTemplateId())) {
			Long templateId = dao.getTestTemplateId();
			item.setTestingTemplateId(templateId.toString());
		}

		dao.insertTestingTarget(item);
		temp.put("templateId", item.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map deleteTestingTarget(String targetId) throws Exception {
		Map temp = new HashMap();
		dao.deleteTestingTarget(targetId);
		temp.put("success", "success");
		return temp;
	}

	public Map updateTestingTemplateParameterPing(TestingTemplateParameterPing t)
			throws Exception {
		Map temp = new HashMap();
		dao.updateTestingTemplateParameterPing(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map updateTestingTemplateParameterSpeed(
			TestingTemplateParameterSpeed t) throws Exception {
		Map temp = new HashMap();
		dao.updateTestingTemplateParameterSpeed(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map updateTestingTemplateParameterTrace(
			TestingTemplateParameterTrace t) throws Exception {
		Map temp = new HashMap();
		dao.updateTestingTemplateParameterTrace(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map updateTestingTemplateParameterWeb(TestingTemplateParameterWeb t)
			throws Exception {
		Map temp = new HashMap();
		dao.updateTestingTemplateParameterWeb(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map updateTestingTemplateParameterDns(TestingTemplateParameterDns t)
			throws Exception {
		Map temp = new HashMap();
		dao.updateTestingTemplateParameterDns(t.getTestingTemplateId(), t);
		temp.put("templateId", t.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public Map updateTestingTarget(TestingTemplateTarget item) throws Exception {
		Map temp = new HashMap();

		dao.updateTestingTarget(item);
		temp.put("templateId", item.getTestingTemplateId());
		temp.put("success", "success");
		return temp;
	}

	public String getItestorTemplateList() throws Exception {
		ListChunk lc = dao.getTestingTemplateDataList();
		List<TestingTemplateJsonItem> list = new ArrayList<TestingTemplateJsonItem>();
		StringBuffer sb = new StringBuffer("{\"errorCode\": 0,\"rows\": ");
		if (lc != null && lc.getTotalCount() > 0) {
			Collection<TestingTemplateJsonItem> templateList = lc
					.getCollection();
			for (TestingTemplateJsonItem t : templateList) {
				ListChunk ll = dao.getTestingTemplateTargetDataJsonList(
						t.getId(), 1, 100);
				if (ll != null && ll.getTotalCount() > 0) {
					Collection<TargetJsonItem> iList = ll.getCollection();
					t.setDestinations((ArrayList) iList);
				}

				if (Const.TEMPLATE_ARAMETER_PING == t.getTestType()) {
					ListChunk l = dao
							.getTestingTemplateParameterPingDataJsonList(
									t.getId(), 1, 100);
					if (l != null && l.getTotalCount() > 0) {
						Collection<PingJsonItem> iList = l.getCollection();
						t.setParameters(((ArrayList) iList).get(0));
					}
				} else if (Const.TEMPLATE_ARAMETER_TRACE == t.getTestType()) {
					ListChunk l = dao
							.getTestingTemplateParameterTraceDataJsonList(
									t.getId(), 1, 100);
					if (l != null && l.getTotalCount() > 0) {
						Collection<TraceJsonItem> iList = l.getCollection();
						t.setParameters(((ArrayList) iList).get(0));
					}
				} else if (Const.TEMPLATE_ARAMETER_SPEED == t.getTestType()) {
					ListChunk l = dao
							.getTestingTemplateParameterSpeedDataJsonList(
									t.getId(), 1, 100);
					if (l != null && l.getTotalCount() > 0) {
						Collection<SpeedJsonItem> iList = l.getCollection();
						t.setParameters(((ArrayList) iList).get(0));
					}
				} else if (Const.TEMPLATE_ARAMETER_DNS == t.getTestType()) {
					ListChunk l = dao
							.getTestingTemplateParameterDnsDataJsonList(
									t.getId(), 1, 100);
					if (l != null && l.getTotalCount() > 0) {
						Collection<DnsJsonItem> iList = l.getCollection();
						t.setParameters(((ArrayList) iList).get(0));
					}
				} else if (Const.TEMPLATE_ARAMETER_WEB == t.getTestType()) {
					ListChunk l = dao
							.getTestingTemplateParameterWebDataJsonList(
									t.getId(), 1, 100);
					if (l != null && l.getTotalCount() > 0) {
						Collection<WebJsonItem> iList = l.getCollection();
						t.setParameters(((ArrayList) iList).get(0));
					}
				}
				list.add(t);
			}
		}
		String json = JSON.toJSONString(list);
		sb.append(json).append("}");
		return sb.toString();
	}

	public String insertTestingResult(ResultTemplateJsonItem item)
			throws Exception {
		TestingResult res = new TestingResult();
		Long templateId = dao.getResultTestTemplateTableId("testing_result_id",
				"testing_result");
		res.setTestingResultId(templateId.toString());
		res.setTestingTemplateGroupId(item.getTestTemplateType() + "");
		res.setAccount(item.getAccount());
		res.setResultSeq(item.getResultSeq());
		res.setStbId(item.getStbId());
		res.setTester(item.getTester());
		res.setWoNumber(item.getWoNumber());
		dao.insertTestingResult(res);
		return templateId.toString();
	}

	public String insertTestingResultDns(TestingResultDns item)
			throws Exception {
		Long templateId = dao.getResultTestTemplateTableId("result_dns_id",
				"testing_result_dns");
		item.setResultDnsId(templateId.toString());
		dao.insertTestingResultDns(item);
		return templateId.toString();
	}

	public String insertTestingResultPing(TestingResultPing item)
			throws Exception {
		Long templateId = dao.getResultTestTemplateTableId("result_ping_id",
				"testing_result_ping");
		item.setResultPingId(templateId.toString());
		dao.insertTestingResultPing(item);
		return templateId.toString();
	}

	public String insertTestingResultSpeed(TestingResultSpeed item)
			throws Exception {
		Long templateId = dao.getResultTestTemplateTableId("result_speed_id",
				"testing_result_speed");
		item.setResultSpeedId(templateId.toString());
		dao.insertTestingResultSpeed(item);
		return templateId.toString();
	}

	public String insertTestingResultWeb(TestingResultWeb item)
			throws Exception {
		Long templateId = dao.getResultTestTemplateTableId("result_web_id",
				"testing_result_web");
		item.setResultWebId(templateId.toString());
		dao.insertTestingResultWeb(item);
		return templateId.toString();
	}

	public String insertTestingResultTrace(TestingResultTrace item)
			throws Exception {
		Long templateId = dao.getResultTestTemplateTableId("result_trace_id",
				"testing_result_trace");
		item.setResultTraceId(templateId.toString());
		dao.insertTestingResultTrace(item);
		return templateId.toString();
	}

	public String insertTestingResultTraceSub(TestingResultTraceSub item)
			throws Exception {
		Long templateId = dao.getResultTestTemplateTableId(
				"result_trace_sub_id", "testing_result_trace_sub");
		item.setResultTraceSubId(templateId.toString());
		dao.insertTestingResultTraceSub(item);
		return templateId.toString();
	}

	public void updateResultTestTypeId(String column, String typeId,
			String templateId) throws Exception {
		dao.updateResultTestTypeId(column, typeId, templateId);
	}

	public Collection getTestingResultTemplateDataList(String roleId,String testingDateBegin,String testingDateEnd,String testTypeSearch,String accountSearch,String testerSearch,String resultSeqSearch,String resultId)
			throws Exception {
		ListChunk lc = dao.getTestingResultTemplateDataList(roleId,testingDateBegin,testingDateEnd,testTypeSearch,accountSearch,testerSearch,resultSeqSearch,resultId,1, 1000);
		return lc.getCollection();
	}

	public Map getTestingResultDns(String id) throws Exception {
		ListChunk lc = dao.getTestingResultDns(id, 1, 1000);
		Map map = new HashMap();
		if (lc != null && lc.getCollection() != null
				&& lc.getCollection().size() > 0) {
			TestingResultDns d = (TestingResultDns) ((ArrayList) lc
					.getCollection()).get(0);
			map.put("result", d);
		} else {
			map.put("null", "null");
		}
		return map;
	}

	public Map getTestingResultPing(String id) throws Exception {
		ListChunk lc = dao.getTestingResultPing(id, 1, 1000);
		Map map = new HashMap();
		if (lc != null && lc.getCollection() != null
				&& lc.getCollection().size() > 0) {
			TestingResultPing d = (TestingResultPing) ((ArrayList) lc
					.getCollection()).get(0);
			map.put("result", d);
		} else {
			map.put("null", "null");
		}
		return map;
	}

	public Map getTestingResultSpeed(String id) throws Exception {
		ListChunk lc = dao.getTestingResultSpeed(id, 1, 1000);
		Map map = new HashMap();
		if (lc != null && lc.getCollection() != null
				&& lc.getCollection().size() > 0) {
			TestingResultSpeed d = (TestingResultSpeed) ((ArrayList) lc
					.getCollection()).get(0);
			map.put("result", d);
		} else {
			map.put("null", "null");
		}
		return map;
	}

	public Map getTestingResultWeb(String id) throws Exception {
		ListChunk lc = dao.getTestingResultWeb(id, 1, 1000);
		Map map = new HashMap();
		if (lc != null && lc.getCollection() != null
				&& lc.getCollection().size() > 0) {
			TestingResultWeb d = (TestingResultWeb) ((ArrayList) lc
					.getCollection()).get(0);
			map.put("result", d);
		} else {
			map.put("null", "null");
		}
		return map;
	}

	public Map getTestingResultTrace(String id) throws Exception {
		ListChunk lc = dao.getTestingResultTrace(id, 1, 1000);
		Map map = new HashMap();
		if (lc != null && lc.getCollection() != null
				&& lc.getCollection().size() > 0) {
			TestingResultTrace d = (TestingResultTrace) ((ArrayList) lc
					.getCollection()).get(0);
			map.put("result", d);
			map.put("success", "success");
		} else {
			map.put("null", "null");
		}
		return map;
	}

	public Map getTestingResultTraceSub(String id) throws Exception {
		ListChunk lc = dao.getTestingResultTraceSub(id, 1, 1000);
		Map map = new HashMap();
		if (lc != null && lc.getCollection() != null
				&& lc.getCollection().size() > 0) {
			TestingResultTraceSub d = (TestingResultTraceSub) ((ArrayList) lc
					.getCollection()).get(0);
			map.put("result", d);
		} else {
			map.put("null", "null");
		}
		return map;
	}
	
	public Collection getTestingResultTraceSubList(String id) throws Exception {
		return dao.getTestingResultTraceSub(id, 1, 1000).getCollection();
	}

	public String getResultSeq() throws Exception {
		int seqLen = 5;
		Date curr = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String currentDate = formatter.format(curr);
		String seq = null;
		String oldCode = dao.getMaxResult(currentDate);

		if (StringUtils.isEmpty(oldCode)) {
			seq = "1";
		} else {
			Integer maxNo = Integer.parseInt(oldCode.substring(8));
			seq = String.valueOf(maxNo + 1);
		}

		for (int i = seq.length(); i < 5; i++) {
			seq = "0".concat(seq);
		}
		seq = currentDate + seq;
		System.out.println("++++++++++++++++seq:" + seq);
		return seq;
	}
	
	public InputStream downloadResultTemplateData(String testingDateBegin,String testingDateEnd,String testTypeSearch,String accountSearch,String testerSearch,String resultSeqSearch)throws Exception{
		String[] columns = new String[]{"resultSeq","testingDate","tester","account","stbId","testingTemplateGroupId"};
		String[] titles = new String[]{"测试帐号","时间","工号","宽带帐号","STBID","测试类型"};
		ListChunk lc = dao.getTestingResultTemplateDataList(null,testingDateBegin,testingDateEnd,testTypeSearch,accountSearch,testerSearch,resultSeqSearch,null,1, 1000);
		Collection rs = lc.getCollection();
		return U.downloadSimpleExcel(rs, "ra.com.dataManagement.model.TestingResult", titles,
				columns);
	}
	
	public ListChunk getFtpFileDataList(String testingDateBegin,String testingDateEnd,String testTypeSearch,String accountSearch,String testerSearch,String stbidSearch,int pageNo, int pageSize)
			throws Exception {
		return dao.getFtpFileDataList(testingDateBegin,testingDateEnd,testTypeSearch,accountSearch,testerSearch,stbidSearch,null,pageNo,pageSize);
		
	}
	public Collection getFileType() throws Exception {
		return dao.getFileType();
	}
	
	public Map deleteFtpFile(String fileIds) throws Exception {
		Map temp = new HashMap();
		String[] fileIdList = fileIds.split(",");
		StringBuffer mess = new StringBuffer("");
		for(String id:fileIdList){
			ArrayList<FtpFile> col = (ArrayList)dao.getFtpFileDataList(null,null,null,null,null,null,fileIds,1,10).getCollection();
			if(col!=null){
				for(FtpFile f:col){
					String path = Const.CLIENT_COLLECTOR_CONFIG+File.separator+f.getType()+File.separator+f.getTester()+File.separator;
					File file=new File(path+f.getUuguid());
					if(file.exists()){ file.delete();}else{ mess.append(f.getFileName() +"不存在该文件\n"); }
					dao.deleteFtpFile(f.getFileId());
				}
			}
		}
		if(mess.length()<1){
		  temp.put("success","success");
		}else{
			temp.put("mess",mess.toString());
			temp.put("warning","warning");
		}
		
		 return temp;  
	}
	public void downloadFtpFile(String fileIds) throws Exception {
		String[] fileIdList = fileIds.split(",");
		StringBuffer mess = new StringBuffer("");
		 ZipOutputStream zipStream = null;
	     FileInputStream zipSource = null;
	     BufferedInputStream bufferStream = null;
		for(String id:fileIdList){
			ArrayList<FtpFile> col = (ArrayList)dao.getFtpFileDataList(null,null,null,null,null,null,fileIds,1,10).getCollection();
			if(col!=null){
				for(FtpFile f:col){
					String path = Const.CLIENT_COLLECTOR_CONFIG+File.separator+f.getType()+File.separator+f.getTester()+File.separator;
					File file=new File(path+f.getUuguid());
					
				}
			}
		}
		 
	}
	
	public void updateWOByResultSeq(String resultSeq, String woNumber) throws Exception {
		dao.updateWOByResultSeq(resultSeq, woNumber);
	}
	
	public boolean checkWONumber(String woNumber) throws Exception{
		boolean isExists=false;
		Object testResultID = dao.getTestResultIDByWONumber(woNumber);
		
		if (testResultID != null ) {
			isExists=true; 
		} 
		return isExists;
	}
	
	public Map getTestingResultTemplateDetail(String id) throws Exception {
		Collection lc = getTestingResultTemplateDataList(null,null,null,null,null,null,null,id);
		TestingResult item = new TestingResult();
		Map map = new HashMap();
		if(lc==null||lc.size()<1){
			map.put("detailNull", "null");
		}else{
			item = (TestingResult)((ArrayList)lc).get(0);  
			map.put("result",item);
			if(item.getResultDnsId()!=null&&Integer.parseInt(item.getResultDnsId())>0){
				Map m = getTestingResultDns(item.getResultDnsId());
				if("null".equals(m.get("null"))){
					map.put("dnsNull", "null");
				}else{
					map.put("dns",m.get("result"));
				}
			}else{
				map.put("dnsNull", "null");
			}
			if(item.getResultPingId()!=null&&Integer.parseInt(item.getResultPingId())>0){
				Map m = getTestingResultPing(item.getResultPingId());
				if("null".equals(m.get("null"))){
					map.put("pingNull", "null");
				}else{
					map.put("ping",m.get("result"));
				}
			}else{
				map.put("pingNull", "null");
			}
			if(item.getResultSpeedId()!=null&&Integer.parseInt(item.getResultSpeedId())>0){
				Map m = getTestingResultSpeed(item.getResultSpeedId());
				if("null".equals((String)m.get("null"))){
					map.put("speedNull", "null");
				}else{
					map.put("speed",m.get("result"));
				}
			}else{
				map.put("speedNull", "null");
			}
			if(item.getResultTraceId()!=null&&Integer.parseInt(item.getResultTraceId())>0){
				Map m = getTestingResultTrace(item.getResultTraceId());
				if("null".equals(m.get("null"))){
					map.put("traceNull", "null");
				}else{
					map.put("trace",m.get("result"));
				}
			}else{
				map.put("traceNull", "null");
			}
			if(item.getResultWebId()!=null&&Integer.parseInt(item.getResultWebId())>0){
				Map m = getTestingResultWeb(item.getResultWebId());
				if("null".equals(m.get("null"))){
					map.put("webNull", "null");
				}else{
					map.put("web",m.get("result"));
				}
			}else{
				map.put("webNull", "null");
			}
			//tester info
			if(item.getTester()!=null&&!"".equals(item.getTester())){
				SystemMGTDAO userDao = (SystemMGTDAO) ra.com.common.dao.DAOFactory
						.getDAO("ra.com.system_mgt.DAO.SystemMGTDAOImpl");
				Collection testerCol =userDao.getUserList(null,item.getTester(),null,null,null).getCollection();
				if(testerCol!=null&&testerCol.size()>0){
				UserManagement testerInfo = (UserManagement)((ArrayList)testerCol).get(0);  
				map.put("testerInfo",testerInfo);
				}else{
					map.put("testerInfoNull","null");
				}
			}
			
			
		}
		
		return map;
	}
}
