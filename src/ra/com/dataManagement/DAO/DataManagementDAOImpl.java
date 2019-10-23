package ra.com.dataManagement.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ra.com.common.Const;
import ra.com.common.dao.GenericDAO;
import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
import ra.com.dataManagement.model.TestingResult;
import ra.com.dataManagement.model.TestingResultDns;
import ra.com.dataManagement.model.TestingResultPing;
import ra.com.dataManagement.model.TestingResultSpeed;
import ra.com.dataManagement.model.TestingResultTrace;
import ra.com.dataManagement.model.TestingResultTraceSub;
import ra.com.dataManagement.model.TestingResultWeb;
import ra.com.dataManagement.model.TestingTemplate;
import ra.com.dataManagement.model.TestingTemplateParameterDns;
import ra.com.dataManagement.model.TestingTemplateParameterPing;
import ra.com.dataManagement.model.TestingTemplateParameterSpeed;
import ra.com.dataManagement.model.TestingTemplateParameterTrace;
import ra.com.dataManagement.model.TestingTemplateParameterWeb;
import ra.com.dataManagement.model.TestingTemplateTarget;

public class DataManagementDAOImpl extends GenericDAO implements DataManagementDAO {

	public DataManagementDAOImpl() throws GenericDAOException {

		super();
		// TODO Auto-generated constructor stub
	}
	public ListChunk getTestingTemplateDataList(String roleId,int pageNo, int pageSize) throws GenericDAOException{
		StringBuffer sql = new StringBuffer(
				"SELECT  t.testing_template_id,testing_template_group_id,template_name,test_type,t.sort_level,description,test_timeout,test_interval,test_execute_count,rank_class,testing_duration,")
		.append(" (select value  FROM wasu.bs_common_def d where d.function_value = t.testing_template_group_id and d.type='template_type') testingTemplateGroupValue ,GROUP_CONCAT(tt.node_ip) node_ip,")
		.append(" (select value  FROM wasu.bs_common_def d where d.function_value = t.test_type and type='test_type') testTypeValue FROM wasu.testing_template t left join wasu.testing_template_target  tt on t.testing_template_id = tt.testing_template_id where 1 = 1 ");
		
		sql.append(" group by t.testing_template_id ");
		return getListChunkByProperty(sql.toString(), null,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplate");
	}
	
	public Collection getTestGroup()throws GenericDAOException {
		return simpleKVQuery("SELECT function_value,value FROM wasu.bs_common_def where type='template_type' order by value", null);
	}
	
	public Collection getTestType()throws GenericDAOException {
		return simpleKVQuery("SELECT function_value,value FROM wasu.bs_common_def where type='test_type' order by value", null);
	}
	public Collection getTargetType()throws GenericDAOException {
		return simpleKVQuery("SELECT def_id,value FROM wasu.bs_common_def where type='targetType' order by value", null);
	}
	public Collection getProtocolType()throws GenericDAOException {
		return simpleKVQuery("SELECT def_id,value FROM wasu.bs_common_def where type='protocolType' order by value", null);
	}
	
	public ListChunk getTestingTemplateTargetDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		StringBuffer sql = new StringBuffer("SELECT    testing_template_target_id,  testing_template_id, name, node_ip, sort_level, active, target_type, ")
		.append(" (select value from wasu.bs_common_def f where f.def_id = t.target_type and f.type='targetType') targetTypeValue")
		.append(" FROM wasu.testing_template_target t where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplateTarget");
	}
	
	public ListChunk getTestingTemplateParameterPingDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		StringBuffer sql = new StringBuffer("SELECT  testing_template_parameter_id,testing_template_id,packet_count,spaceing_time,packet_timeout,payload_size,payload_data,max_ttl,tos,save_Ip_Result,all_Loss_As_Fail,round_Trip ")
		.append(" FROM wasu.testing_template_parameter_ping where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplateParameterPing");
	}
	public ListChunk getTestingTemplateParameterDnsDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		StringBuffer sql = new StringBuffer("SELECT  testing_template_parameter_id,testing_template_id,packet_count,ignore_count,round_item_count,packet_timeout,spacing_time ")
		.append(" FROM wasu.testing_template_parameter_dns where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplateParameterDns");
	}
	
	public ListChunk getTestingTemplateParameterSpeedDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		StringBuffer sql = new StringBuffer("SELECT testing_template_parameter_id,testing_template_id,host_Ips,max_test_time,min_test_time,continue_times,jitter_throughput,request_timeout,request_piece_size,payload_size,download_size ")
		.append(" FROM wasu.testing_template_parameter_speed where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplateParameterSpeed");
	}
	
	public ListChunk getTestingTemplateParameterTraceDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		StringBuffer sql = new StringBuffer("SELECT testing_template_parameter_id,testing_template_id,payload_size,protocol_type,max_hops,reply_timeout,packet_count,spacing_time,tos ")
		.append(" FROM wasu.testing_template_parameter_trace where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplateParameterTrace");
	}
	
	public ListChunk getTestingTemplateParameterWebDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		StringBuffer sql = new StringBuffer("SELECT testing_template_parameter_id,testing_template_id,max_download_size,max_thread_count,max_sub_count,max_sub_save_count,item_timeout,use_dns_cache,user_agent,max_page_depth,valid_response_codes,tos,analysis_Html,primary_Result_Index,min_Load_Percent,max_Load_Time ")
		.append(" FROM wasu.testing_template_parameter_web where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplateParameterWeb");
	}
	
	public void insertTestingTemplateParameterPing(String templateId,TestingTemplateParameterPing item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		param.add(item.getPacketCount());
		param.add(item.getSpaceingTime());
		param.add(item.getPacketTimeout());
		param.add(item.getPayloadSize());
		param.add(item.getPayloadData());
		param.add(item.getMaxTtl());
		param.add(item.getTos());
		param.add(item.getSaveIpResult());
		param.add(item.getAllLossAsFail());
		param.add(item.getRoundTrip());
		StringBuffer sql = new StringBuffer("insert into wasu.testing_template_parameter_ping (testing_template_id,packet_count,spaceing_time,packet_timeout,payload_size,payload_data,max_ttl,tos,save_Ip_Result,all_Loss_As_Fail,round_Trip ) ")
											.append(" values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ");
		
		
		simpleExecute(sql.toString(), param);
	}
	public void insertTestingTemplateParameterDns(String templateId,TestingTemplateParameterDns item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		param.add(item.getPacketCount());
		param.add(item.getIgnoreCount());
		param.add(item.getRoundItemCount());
		param.add(item.getPacketTimeout());
		param.add(item.getSpacingTime());
		StringBuffer sql = new StringBuffer("insert into  wasu.testing_template_parameter_dns (testing_template_id,packet_count,ignore_count,round_item_count,packet_timeout,spacing_time) ")
		.append(" values( ?, ?, ?, ?, ?, ?) ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void insertTestingTemplateParameterSpeed(String templateId,TestingTemplateParameterSpeed item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		param.add(item.getHostIps());
		param.add(item.getMaxTestTime());
		param.add(item.getMinTestTime());
		param.add(item.getContinueTimes());
		param.add(item.getJitterThroughput());
		param.add(item.getRequestTimeout());
		param.add(item.getRequestPieceSize());
		param.add(item.getPayloadSize());
		param.add(item.getDownloadSize());
		StringBuffer sql = new StringBuffer("insert into  wasu.testing_template_parameter_speed (testing_template_id,host_Ips,max_test_time,min_test_time,continue_times,jitter_throughput,request_timeout,request_piece_size,payload_size,download_size )")
		.append(" values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void insertTestingTemplateParameterTrace(String templateId,TestingTemplateParameterTrace item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		param.add(item.getPayloadSize());
		param.add(item.getProtocolType());
		param.add(item.getMaxHops());
		param.add(item.getReplyTimeout());
		param.add(item.getPacketCount());
		param.add(item.getSpacingTime());
		param.add(item.getTos());
		StringBuffer sql = new StringBuffer("insert into  wasu.testing_template_parameter_trace (testing_template_id,payload_size,protocol_type,max_hops,reply_timeout,packet_count,spacing_time,tos) ")
		.append(" values( ?, ?, ?, ?, ?, ?, ?, ?)  ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void insertTestingTemplateParameterWeb(String templateId,TestingTemplateParameterWeb item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);//max_download_size,max_thread_count,max_sub_count,max_sub_save_count,item_timeout,use_dns_cache,user_agent,max_page_depth,valid_response_codes
		param.add(item.getMaxDownloadSize());
		param.add(item.getMaxThreadCount());
		param.add(item.getMaxSubCount());
		param.add(item.getMaxSubSaveCount());
		param.add(item.getItemTimeout());
		param.add(item.getUseDnsCache());
		param.add(item.getUserAgent());
		param.add(item.getMaxPageDepth());
		param.add(item.getValidResponseCodes());
		param.add(item.getTos());
		param.add(item.getAnalysisHtml());
		param.add(item.getPrimaryResultIndex());
		param.add(item.getMinLoadPercent());
		param.add(item.getMaxLoadTime());
		StringBuffer sql = new StringBuffer("insert into  wasu.testing_template_parameter_web (testing_template_id,max_download_size,max_thread_count,max_sub_count,max_sub_save_count,item_timeout,use_dns_cache,user_agent,max_page_depth,valid_response_codes,tos,analysis_Html,primary_Result_Index,min_Load_Percent,max_Load_Time) ")
		.append(" values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void insertTestingTemplate(TestingTemplate item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		//testing_template_id,testing_template_group_id,template_name,test_type,sort_level,description,test_timeout,test_interval,test_execute_count
		param.add(item.getTestingTemplateId());
		param.add(item.getTestingTemplateGroupId());
		param.add(item.getTemplateName());
		param.add(String.valueOf(item.getTestType()));
		param.add(item.getSortLevel());
		param.add(item.getDescription());
		param.add(item.getTestTimeout());
		param.add(item.getTestInterval());
		param.add(item.getTestExecuteCount());
		param.add(item.getRankClass());
		param.add(item.getTestingDuration());
		StringBuffer sql = new StringBuffer("insert into  wasu.testing_template (testing_template_id,testing_template_group_id,template_name,test_type,sort_level,description,test_timeout,test_interval,test_execute_count,rank_class,testing_duration )")
			.append(" values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public Long getTestTemplateId() throws GenericDAOException {
		 return (Long) queryOne("select IFNULL(MAX(testing_template_id),0)+1  from wasu.testing_template ", null);
	}
	
	public boolean removeitTestingTemplate(TestingTemplate item) throws GenericDAOException {
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getTestingTemplateId());
		StringBuffer sql ;
		String tempTable ="";
		System.out.println("============item.getTestType():"+item.getTestType());
		sql = new StringBuffer(" DELETE FROM   wasu.testing_template_target  WHERE testing_template_id = ? ");
		simpleExecute(sql.toString(),param);
		if(Const.TEMPLATE_ARAMETER_PING == item.getTestType()){
			tempTable = " wasu.testing_template_parameter_ping ";
		}else if(Const.TEMPLATE_ARAMETER_TRACE == item.getTestType()){
			tempTable = " wasu.testing_template_parameter_trace ";
		}else if(Const.TEMPLATE_ARAMETER_DNS == item.getTestType()){
			tempTable = " wasu.testing_template_parameter_dns ";
		}else if(Const.TEMPLATE_ARAMETER_SPEED == item.getTestType()){
			tempTable = " wasu.testing_template_parameter_speed ";
		}else if(Const.TEMPLATE_ARAMETER_WEB == item.getTestType()){
			tempTable = " wasu.testing_template_parameter_web ";
		}
		if(tempTable.length()>0){
			sql = new StringBuffer(" DELETE FROM ");
			sql.append(tempTable).append(" WHERE testing_template_id = ? ");
			simpleExecute(sql.toString(),param);
		}
		sql = new StringBuffer(" DELETE FROM   wasu.testing_template  WHERE testing_template_id = ? ");
		simpleExecute(sql.toString(),param);
		return true;
	}
	
	public void insertTargetType(String targetType) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(targetType);
		StringBuffer sql = new StringBuffer("insert into wasu.bs_common_def ( def_id,type,value) ")
		.append(" SELECT IFNULL(MAX(def_id),0)+1 ,'targetType', ?   FROM wasu.bs_common_def  ");		
		simpleExecute(sql.toString(), param);
	}
	
	public void insertTestingTarget(TestingTemplateTarget item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getTestingTemplateId());
		param.add(item.getName());
		param.add(item.getNodeIp());
		param.add(item.getSortLevel());
		param.add(item.getActive());
		param.add(item.getTargetType());
		StringBuffer sql = new StringBuffer("insert into  wasu.testing_template_target (testing_template_id,name,node_ip,sort_level,active,target_type)")
			.append(" values( ?, ?, ?, ?, ?, ?)  ");
		simpleExecute(sql.toString(), param);
	}
	
	public void deleteTestingTarget(String targetId) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(targetId);
		StringBuffer sql = new StringBuffer("DELETE FROM wasu.testing_template_target WHERE testing_template_target_id = ? ");
		simpleExecute(sql.toString(), param);
	}
	
	public void updateTestingTemplate(TestingTemplate item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getTestingTemplateGroupId());
		param.add(item.getTemplateName());
		//param.add(item.getTestType());
		param.add(item.getSortLevel());
		param.add(item.getDescription());
		param.add(item.getTestTimeout());
		param.add(item.getTestInterval());
		param.add(item.getTestExecuteCount());
		param.add(item.getRankClass());
		param.add(item.getTestingDuration());
		param.add(item.getTestingTemplateId());
		StringBuffer sql = new StringBuffer("UPDATE wasu.testing_template SET testing_template_group_id = ? ,template_name = ? ,sort_level = ? ,description = ? ,test_timeout = ? ,test_interval = ? ,test_execute_count = ? ,rank_class = ? ,testing_duration = ? ")
			.append(" WHERE testing_template_id = ?   ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void updateTestingTemplateParameterPing(String templateId,TestingTemplateParameterPing item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		
		param.add(item.getPacketCount());
		param.add(item.getSpaceingTime());
		param.add(item.getPacketTimeout());
		param.add(item.getPayloadSize());
		param.add(item.getPayloadData());
		param.add(item.getMaxTtl());
		param.add(item.getTos());
		param.add(item.getSaveIpResult());
		param.add(item.getAllLossAsFail());
		param.add(item.getRoundTrip());
		param.add(item.getTestingTemplateParameterId());
		StringBuffer sql = new StringBuffer("UPDATE wasu.testing_template_parameter_ping set packet_count = ?,spaceing_time = ?,packet_timeout = ?,payload_size = ?,payload_data = ?,max_ttl = ?,tos  = ?,save_Ip_Result = ?,all_Loss_As_Fail = ?,round_Trip = ?")
		.append(" where testing_template_parameter_id = ? ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void updateTestingTemplateParameterSpeed(String templateId,TestingTemplateParameterSpeed item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getHostIps());
		param.add(item.getMaxTestTime());
		param.add(item.getMinTestTime());
		param.add(item.getContinueTimes());
		param.add(item.getJitterThroughput());
		param.add(item.getRequestTimeout());
		param.add(item.getRequestPieceSize());
		param.add(item.getPayloadSize());
		param.add(item.getDownloadSize());
		param.add(item.getTestingTemplateParameterId());
		StringBuffer sql = new StringBuffer("UPDATE  wasu.testing_template_parameter_speed SET host_Ips = ?,max_test_time = ?,min_test_time = ?,continue_times = ?,jitter_throughput = ?,request_timeout = ?,request_piece_size = ?,payload_size = ?,download_size = ? ")
		.append(" where testing_template_parameter_id = ? ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void updateTestingTemplateParameterTrace(String templateId,TestingTemplateParameterTrace item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getPayloadSize());
		param.add(item.getProtocolType());
		param.add(item.getMaxHops());
		param.add(item.getReplyTimeout());
		param.add(item.getPacketCount());
		param.add(item.getSpacingTime());
		param.add(item.getTos());
		param.add(item.getTestingTemplateParameterId());
		StringBuffer sql = new StringBuffer("UPDATE  wasu.testing_template_parameter_trace SET payload_size= ?,protocol_type= ?,max_hops= ?,reply_timeout= ?,packet_count= ?,spacing_time= ?,tos = ? ")
		.append(" where testing_template_parameter_id = ?  ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void updateTestingTemplateParameterWeb(String templateId,TestingTemplateParameterWeb item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getMaxDownloadSize());
		param.add(item.getMaxThreadCount());
		param.add(item.getMaxSubCount());
		param.add(item.getMaxSubSaveCount());
		param.add(item.getItemTimeout());
		param.add(item.getUseDnsCache());
		param.add(item.getUserAgent());
		param.add(item.getMaxPageDepth());
		param.add(item.getValidResponseCodes());
		param.add(item.getTos());
		param.add(item.getAnalysisHtml());
		param.add(item.getPrimaryResultIndex());
		param.add(item.getMinLoadPercent());
		param.add(item.getMaxLoadTime());
		param.add(item.getTestingTemplateParameterId());
		StringBuffer sql = new StringBuffer("UPDATE  wasu.testing_template_parameter_web SET max_download_size= ?,max_thread_count= ?,max_sub_count= ?,max_sub_save_count= ?,item_timeout= ?,use_dns_cache= ?,user_agent = ?,max_page_depth= ?,valid_response_codes= ?,tos = ?,analysis_Html = ?,primary_Result_Index = ?,min_Load_Percent = ?,max_Load_Time = ? ")
		.append(" where testing_template_parameter_id = ?  ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void updateTestingTemplateParameterDns(String templateId,TestingTemplateParameterDns item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getPacketCount());
		param.add(item.getIgnoreCount());
		param.add(item.getRoundItemCount());
		param.add(item.getPacketTimeout());
		param.add(item.getSpacingTime());
		param.add(item.getTestingTemplateParameterId());
		StringBuffer sql = new StringBuffer("UPDATE  wasu.testing_template_parameter_dns set packet_count= ?,ignore_count= ?,round_item_count= ?,packet_timeout= ?,spacing_time= ? ")
		.append(" where testing_template_parameter_id = ?  ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void updateTestingTarget(TestingTemplateTarget item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		
		param.add(item.getName());
		param.add(item.getNodeIp());
		param.add(item.getSortLevel());
		param.add(item.getActive());
		param.add(item.getTargetType());
		param.add(item.getTestingTemplateTargetId());
		StringBuffer sql = new StringBuffer("UPDATE  wasu.testing_template_target set name =?,node_ip=?,sort_level= ?,active = ?,target_type = ? WHERE testing_template_target_id = ?");
		simpleExecute(sql.toString(), param);
	}
	
	public ListChunk getTestingTemplateDataList() throws GenericDAOException{
		StringBuffer sql = new StringBuffer("SELECT testing_template_id id,testing_template_group_id groupId,template_name name,test_type,test_timeout*1000 test_timeout,test_interval*60000  taskInterval,test_execute_count taskExecuteCount,rank_class FROM wasu.testing_template  ")
		.append(" order by rank_class desc");
		return getListChunkByProperty(sql.toString(), null,1,1000,true, "ra.com.dataManagement.model.TestingTemplateJsonItem");
	}
	
	public ListChunk getTestingTemplateParameterDnsDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(String.valueOf(templateId));
		StringBuffer sql = new StringBuffer("SELECT packet_count,ignore_count,round_item_count,packet_timeout,spacing_time ")
		.append(" FROM wasu.testing_template_parameter_dns where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.DnsJsonItem");
	}
	
	public ListChunk getTestingTemplateParameterSpeedDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(String.valueOf(templateId));
		StringBuffer sql = new StringBuffer("SELECT host_Ips,max_test_time,min_test_time,continue_times,jitter_throughput,request_timeout,request_piece_size,payload_size,download_size ")
		.append(" FROM wasu.testing_template_parameter_speed where testing_template_id = ? ");
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.SpeedJsonItem");
	}
	
	public ListChunk getTestingTemplateTargetDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(String.valueOf(templateId));
		StringBuffer sql = new StringBuffer("SELECT testing_template_target_id id,name, node_ip, sort_level rank ")
		.append(" FROM wasu.testing_template_target t where testing_template_id = ? and active ='1' order by sort_level ");
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TargetJsonItem");
	}
	
	public ListChunk getTestingTemplateParameterWebDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(String.valueOf(templateId));
		StringBuffer sql = new StringBuffer("SELECT max_download_size,max_thread_count,max_sub_count,max_sub_save_count,item_timeout itemTimeoutTime,use_dns_cache,user_agent,max_page_depth,valid_response_codes,tos,analysis_Html,primary_Result_Index,min_Load_Percent,max_Load_Time ")
		.append(" FROM wasu.testing_template_parameter_web where testing_template_id = ? ");
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.WebJsonItem");
	}
	
	public ListChunk getTestingTemplateParameterPingDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(String.valueOf(templateId));
		StringBuffer sql = new StringBuffer("SELECT packet_count,spaceing_time,packet_timeout,payload_size,payload_data,max_ttl,tos,save_Ip_Result,all_Loss_As_Fail,round_Trip")
		.append(" FROM wasu.testing_template_parameter_ping where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.PingJsonItem");
	}
	
	public ListChunk getTestingTemplateParameterTraceDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(String.valueOf(templateId));
		StringBuffer sql = new StringBuffer("SELECT payload_size,protocol_type,max_hops,reply_timeout,packet_count,spacing_time,tos ")
		.append(" FROM wasu.testing_template_parameter_trace where testing_template_id = ? ");
				
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TraceJsonItem");
	}
	
	public void insertTestingResult(TestingResult item)throws GenericDAOException{
		String sql = "insert into wasu.testing_result (testing_result_id,result_seq,testing_date,tester,account,stb_id,testing_template_group_id,result_dns_id,result_ping_id,result_speed_id,result_trace_id,result_web_id) "
					+ " values (?,?, ?,?, ?,?, ?,?, ?,?, ?,?) ";
		ArrayList<String> param = new ArrayList<String>();
		//result_trace_id,result_web_id
		param.add(item.getTestingResultId());
		param.add(item.getResultSeq());
		param.add(item.getTestingDate());
		param.add(item.getTester());
		param.add(item.getAccount());
		param.add(item.getStbId());
		param.add(item.getTestingTemplateGroupId());
		param.add(item.getResultDnsId());
		param.add(item.getResultPingId());
		param.add(item.getResultSpeedId());
		param.add(item.getResultTraceId());
		param.add(item.getResultWebId());
		simpleExecute(sql, param);
	}
	
	public void insertTestingResultDns(TestingResultDns item)throws GenericDAOException{
		String sql = "insert into wasu.testing_result_dns (result_dns_id,number_of_answers,resolve_time,success_percent ) "
				+ " values (?,?, ?,?) ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getResultDnsId());
		param.add(item.getNumberOfAnswers());
		param.add(item.getResolveTime()+"");
		param.add(item.getSuccessPercent()+"");
		simpleExecute(sql, param);
	}
	
	public void insertTestingResultPing(TestingResultPing item)throws GenericDAOException{
		String sql = "insert into wasu.testing_result_ping (result_ping_id,avg_delay,avg_jitter,host_ip,loss_percent ) "
				+ " values (?,?, ?,?, ?) ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getResultPingId());
		param.add(item.getAvgDelay()+"");
		param.add(item.getAvgJitter()+"");
		param.add(item.getHostIp());
		param.add(item.getLossPercent()+"");
		simpleExecute(sql, param);
	}
	
	public void insertTestingResultSpeed(TestingResultSpeed item)throws GenericDAOException{
		String sql = "insert into wasu.testing_result_speed (result_speed_id,download_max_throughput,download_throughput,upload_max_throughput,upload_throughput ) "
				+ " values (?,?, ?,?, ?) ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getResultSpeedId());
		param.add(item.getDownloadMaxThroughput()+"");
		param.add(item.getDownloadThroughput()+"");
		param.add(item.getUploadMaxThroughput()+"");
		param.add(item.getUploadThroughput()+"");
		simpleExecute(sql, param);
	}
	
	public void insertTestingResultWeb(TestingResultWeb item)throws GenericDAOException{
		String sql = "insert into wasu.testing_result_web (result_web_id,host_ip,request_url,resolve_time,connect_time,first_byte_time,first_page_time,mean_quality,response_code,throughput,total_time ) "
				+ " values (?,?, ?,?,?,?, ?,?,?,?, ?) ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getResultWebId());
		param.add(item.getHostIp());
		param.add(item.getRequestUrl());
		param.add(item.getResolveTime()+"");
		param.add(item.getConnectTime()+"");
		param.add(item.getFirstByteTime()+"");
		param.add(item.getFirstPageTime()+"");
		param.add(item.getMeanQuality()+"");
		param.add(item.getResponseCode()+"");
		param.add(item.getThroughput()+"");
		param.add(item.getTotalTime()+"");
		simpleExecute(sql, param);
	}
	
	public void insertTestingResultTrace(TestingResultTrace item)throws GenericDAOException{
		String sql = "insert into wasu.testing_result_trace (result_trace_id,avg_delay,avg_jitter,hop_count,host_ip,loss_percent) "
				+ " values (?,?, ?,?, ?,?) ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(item.getResultTraceId());
		param.add(item.getAvgDelay());
		param.add(item.getAvgJitter());
		param.add(item.getHopCount());
		param.add(item.getHostIp());
		param.add(item.getLossPercent());
		simpleExecute(sql, param);
	}
	public void insertTestingResultTraceSub(TestingResultTraceSub item)throws GenericDAOException{
		String sql = "insert into wasu.testing_result_trace_sub (result_trace_sub_id,result_trace_id,load_index,host_ip,avg_delay,loss_percent) "
				+ " values (?,?, ?,?, ?,?) ";
	ArrayList<String> param = new ArrayList<String>();
	param.add(item.getResultTraceSubId());
	param.add(item.getResultTraceId());
	param.add(item.getLoadIndex());
	param.add(item.getHostIp());
	param.add(item.getAvgDelay());
	param.add(item.getLossPercent());
	simpleExecute(sql, param);
	}
	
	public Long getResultTestTemplateTableId(String column,String table) throws GenericDAOException {
		 return (Long) queryOne("select IFNULL(MAX("+column+"),0)+1  from wasu."+table, null);
	}
	
	public void updateResultTestTypeId(String column,String typeId,String templateId)throws GenericDAOException {
		StringBuffer sql = new StringBuffer("update wasu.testing_result set ");
		sql.append(column).append(" = ? where testing_result_id = ? ");
		ArrayList<String> param = new ArrayList<String>();
		param.add(typeId);
		param.add(templateId);
		simpleExecute(sql.toString(), param);
	}
	
	public ListChunk getTestingResultTemplateDataList(String roleId,int pageNo, int pageSize) throws GenericDAOException{
		StringBuffer sql = new StringBuffer(
				"SELECT testing_result_id,result_seq,testing_date,tester,account,stb_id,testing_template_group_id,result_dns_id,result_ping_id,result_speed_id,result_trace_id,result_web_id FROM wasu.testing_result t ");
		
		sql.append(" ");
		return getListChunkByProperty(sql.toString(), null,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingResult");
	}
	
	
	public ListChunk getTestingResultDns(String id,int pageNo, int pageSize)throws GenericDAOException{
		String sql = "select number_of_answers,resolve_time,success_percent  from  wasu.testing_result_dns where result_dns_id = ?) ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(id);
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingResultDns");
	}
	
	public ListChunk getTestingResultPing(String id,int pageNo, int pageSize)throws GenericDAOException{
		String sql = "select avg_delay,avg_jitter,host_ip,loss_percent from  wasu.testing_result_ping where result_ping_id = ? ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(id);
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingResultPing");
	}
	
	public ListChunk getTestingResultSpeed(String id,int pageNo, int pageSize)throws GenericDAOException{
		String sql = "select download_max_throughput,download_throughput,upload_max_throughput,upload_throughput from wasu.testing_result_speed where result_speed_id = ? ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(id);
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingResultSpeed");
	}
	
	public ListChunk getTestingResultWeb(String id,int pageNo, int pageSize)throws GenericDAOException{
		String sql = "select host_ip,request_url,resolve_time,connect_time,first_byte_time,first_page_time,mean_quality,response_code,throughput,total_time from  wasu.testing_result_web  where result_web_id = ? ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(id);
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingResultWeb");
	}
	
	public ListChunk getTestingResultTrace(String id,int pageNo, int pageSize)throws GenericDAOException{
		String sql = "select avg_delay,avg_jitter,hop_count,host_ip,loss_percent from  wasu.testing_result_trace where result_trace_id = ? ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(id);
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingResultTrace");
	}
	public ListChunk getTestingResultTraceSub(String id,int pageNo, int pageSize)throws GenericDAOException{
		String sql = "select  result_trace_id,load_index,host_ip,avg_delay,loss_percent from  wasu.testing_result_trace_sub where result_trace_sub_id = ? ";
		ArrayList<String> param = new ArrayList<String>();
		param.add(id);
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingResultTraceSub");
	}
	
}
