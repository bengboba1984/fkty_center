package ra.com.dataManagement.bussiness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import ra.com.common.Const;
import ra.com.common.model.ListChunk;
import ra.com.dataManagement.DAO.DataManagementDAO;
import ra.com.dataManagement.model.DnsJsonItem;
import ra.com.dataManagement.model.PingJsonItem;
import ra.com.dataManagement.model.SpeedJsonItem;
import ra.com.dataManagement.model.TargetJsonItem;
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
import ra.com.system_mgt.model.User;



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

	public Collection getTestingTemplateDataList(String roleId) throws Exception {
		ListChunk lc = dao.getTestingTemplateDataList(roleId,1,1000);
		return lc.getCollection();
	}
	
	public Collection getTestGroup()throws Exception{
		return dao.getTestGroup();
	}
	
	public Collection getTestType()throws Exception{
		return dao.getTestType();
	}
	
	public Collection getProtocolType()throws Exception{
		return dao.getProtocolType();
	}
	public Collection getTargetType()throws Exception{
		return dao.getTargetType();
	}
	
	public Collection getTestingTemplateTargetDataList(String templateId) throws Exception {
		ListChunk lc = dao.getTestingTemplateTargetDataList(templateId,1,1000);
		return lc.getCollection();
	}
	
	public Collection getTestingTemplateParameterPingDataList(String templateId) throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterPingDataList(templateId,1,1000);
		return lc.getCollection();
	}
	
	public Collection getTestingTemplateParameterDnsDataList(String templateId) throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterDnsDataList(templateId,1,1000);
		return lc.getCollection();
	}
	
	public Collection getTestingTemplateParameterSpeedDataList(String templateId) throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterSpeedDataList(templateId,1,1000);
		return lc.getCollection();
	}
	
	public Collection getTestingTemplateParameterTraceDataList(String templateId) throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterTraceDataList(templateId,1,1000);
		return lc.getCollection();
	}
	
	public Collection getTestingTemplateParameterWebDataList(String templateId) throws Exception {
		ListChunk lc = dao.getTestingTemplateParameterWebDataList(templateId,1,1000);
		return lc.getCollection();
	}
	
	public Map insertTestingTemplate(TestingTemplate t) throws Exception {
		
		Map temp=new HashMap();
		if(t.getTestingTemplateId() == null||"".equals(t.getTestingTemplateId())){
			Long templateId = dao.getTestTemplateId();
			t.setTestingTemplateId(templateId.toString());
		}
		dao.insertTestingTemplate(t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("testType",t.getTestType());
		temp.put("success","success");
		return temp;
	}
	
	public Map updateTestingTemplate(TestingTemplate t) throws Exception {
		
		Map temp=new HashMap();
		dao.updateTestingTemplate(t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("testType",t.getTestType());
		temp.put("success","success");
		return temp;
	}
	
	public Map removeitTestingTemplate(TestingTemplate t) throws Exception {
		Map temp = new HashMap();
		dao.removeitTestingTemplate(t);
		temp.put("success","success");
		return temp;  
	}
	
	public Map insertTestingTemplateParameterPing(TestingTemplateParameterPing t) throws Exception {
		Map temp=new HashMap();
		dao.insertTestingTemplateParameterPing(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map insertTestingTemplateParameterTrace(TestingTemplateParameterTrace t) throws Exception {
		Map temp=new HashMap();
		dao.insertTestingTemplateParameterTrace(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map insertTestingTemplateParameterSpeed(TestingTemplateParameterSpeed t) throws Exception {
		Map temp=new HashMap();
		dao.insertTestingTemplateParameterSpeed(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map insertTestingTemplateParameterDns(TestingTemplateParameterDns t) throws Exception {
		Map temp=new HashMap();
		dao.insertTestingTemplateParameterDns(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map insertTestingTemplateParameterWeb(TestingTemplateParameterWeb t) throws Exception {
		Map temp=new HashMap();
		dao.insertTestingTemplateParameterWeb(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map insertTargetType(String type) throws Exception {
		Map temp=new HashMap();
		dao.insertTargetType(type);
		temp.put("success","success");
		return temp;
	}
	
	public Map insertTestingTarget(TestingTemplateTarget item) throws Exception {
		Map temp=new HashMap();
		if(item.getTestingTemplateId() == null||"".equals(item.getTestingTemplateId())){
			Long templateId = dao.getTestTemplateId();
			item.setTestingTemplateId(templateId.toString());
		}
		
		dao.insertTestingTarget(item);
		temp.put("templateId",item.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map deleteTestingTarget(String targetId) throws Exception {
		Map temp=new HashMap();
		dao.deleteTestingTarget(targetId);
		temp.put("success","success");
		return temp;
	}
	
	public Map updateTestingTemplateParameterPing(TestingTemplateParameterPing t) throws Exception {
		Map temp=new HashMap();
		dao.updateTestingTemplateParameterPing(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map updateTestingTemplateParameterSpeed(TestingTemplateParameterSpeed t) throws Exception {
		Map temp=new HashMap();
		dao.updateTestingTemplateParameterSpeed(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map updateTestingTemplateParameterTrace(TestingTemplateParameterTrace t) throws Exception {
		Map temp=new HashMap();
		dao.updateTestingTemplateParameterTrace(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map updateTestingTemplateParameterWeb(TestingTemplateParameterWeb t) throws Exception {
		Map temp=new HashMap();
		dao.updateTestingTemplateParameterWeb(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map updateTestingTemplateParameterDns(TestingTemplateParameterDns t) throws Exception {
		Map temp=new HashMap();
		dao.updateTestingTemplateParameterDns(t.getTestingTemplateId(),t);
		temp.put("templateId",t.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public Map updateTestingTarget(TestingTemplateTarget item) throws Exception {
		Map temp=new HashMap();
		
		dao.updateTestingTarget(item);
		temp.put("templateId",item.getTestingTemplateId());
		temp.put("success","success");
		return temp;
	}
	
	public String getItestorTemplateList()throws Exception{
		ListChunk lc = dao.getTestingTemplateDataList();
		List<TestingTemplateJsonItem> list = new ArrayList<TestingTemplateJsonItem>();
		StringBuffer sb  = new StringBuffer("{\"errorCode\": 0,\"rows\": ");
		if(lc!=null&&lc.getTotalCount()>0){
			Collection<TestingTemplateJsonItem> templateList = lc.getCollection();
			for(TestingTemplateJsonItem t:templateList){
				ListChunk ll = dao.getTestingTemplateTargetDataJsonList(t.getId(), 1, 100);
				if(ll!=null&&ll.getTotalCount()>0){
					Collection<TargetJsonItem> iList = ll.getCollection();
					t.setDestinations((ArrayList)iList);
				}
				
				if(Const.TEMPLATE_ARAMETER_PING.equals(t.getTestType())){
					ListChunk l = dao.getTestingTemplateParameterPingDataJsonList(t.getId(), 1, 100);
					if(l!=null&&l.getTotalCount()>0){
						Collection<PingJsonItem> iList = l.getCollection();
						t.setParemeters(((ArrayList)iList).get(0));
					}
				}else if(Const.TEMPLATE_ARAMETER_TRACE.equals(t.getTestType())){
					ListChunk l = dao.getTestingTemplateParameterTraceDataJsonList(t.getId(), 1, 100);
					if(l!=null&&l.getTotalCount()>0){
						Collection<TraceJsonItem> iList = l.getCollection();
						t.setParemeters(((ArrayList)iList).get(0));
					}
				}else if(Const.TEMPLATE_ARAMETER_SPEED.equals(t.getTestType())){
					ListChunk l = dao.getTestingTemplateParameterSpeedDataJsonList(t.getId(), 1, 100);
					if(l!=null&&l.getTotalCount()>0){
						Collection<SpeedJsonItem> iList = l.getCollection();
						t.setParemeters(((ArrayList)iList).get(0));
					}
				}else if(Const.TEMPLATE_ARAMETER_DNS.equals(t.getTestType())){
					ListChunk l = dao.getTestingTemplateParameterDnsDataJsonList(t.getId(), 1, 100);
					if(l!=null&&l.getTotalCount()>0){
						Collection<DnsJsonItem> iList = l.getCollection();
						t.setParemeters(((ArrayList)iList).get(0));
					}
				}else if(Const.TEMPLATE_ARAMETER_WEB.equals(t.getTestType())){
					ListChunk l = dao.getTestingTemplateParameterWebDataJsonList(t.getId(), 1, 100);
					if(l!=null&&l.getTotalCount()>0){
						Collection<WebJsonItem> iList = l.getCollection();
						t.setParemeters(((ArrayList)iList).get(0));
					}
				}
				list.add(t);
			}
		}
		String json = JSON.toJSONString(list);
		sb.append(json).append("}");
		return sb.toString();
	}
}
