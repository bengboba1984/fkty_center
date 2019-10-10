package ra.com.dataManagement.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ra.com.common.Const;
import ra.com.common.dao.GenericDAO;
import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
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
		.append(" (select value  FROM wasu.bs_common_def d where d.def_id = t.testing_template_group_id ) testingTemplateGroupValue ,GROUP_CONCAT(tt.node_ip) node_ip,")
		.append(" (select value  FROM wasu.bs_common_def d where d.def_id = t.test_type ) testTypeValue FROM wasu.testing_template t left join wasu.testing_template_target  tt on t.testing_template_id = tt.testing_template_id where 1 = 1 ");
		
		sql.append(" group by t.testing_template_id ");
		return getListChunkByProperty(sql.toString(), null,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplate");
	}
	
	public Collection getTestGroup()throws GenericDAOException {
		return simpleKVQuery("SELECT def_id,value FROM wasu.bs_common_def where type='template_type' order by value", null);
	}
	
	public Collection getTestType()throws GenericDAOException {
		return simpleKVQuery("SELECT def_id,value FROM wasu.bs_common_def where type='test_type' order by value", null);
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
		.append(" (select value from wasu.bs_common_def f where f.def_id = t.target_type) targetTypeValue")
		.append(" FROM wasu.testing_template_target t where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplateTarget");
	}
	
	public ListChunk getTestingTemplateParameterPingDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		StringBuffer sql = new StringBuffer("SELECT  testing_template_parameter_id,testing_template_id,packet_count,spaceing_time,packet_timeout,payload_size,payload_data,max_ttl,tos")
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
		StringBuffer sql = new StringBuffer("SELECT testing_template_parameter_id,testing_template_id,payload_size,protocol_type,max_hops,reply_timeout,packet_count,spacing_time ")
		.append(" FROM wasu.testing_template_parameter_trace where testing_template_id = ? ");
		
		
		return getListChunkByProperty(sql.toString(), param,pageNo,pageSize,true, "ra.com.dataManagement.model.TestingTemplateParameterTrace");
	}
	
	public ListChunk getTestingTemplateParameterWebDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		param.add(templateId);
		StringBuffer sql = new StringBuffer("SELECT testing_template_parameter_id,testing_template_id,max_download_size,max_thread_count,max_sub_count,max_sub_save_count,item_timeout,use_dns_cache,user_agent,max_page_depth,valid_response_codes ")
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
		StringBuffer sql = new StringBuffer("insert into wasu.testing_template_parameter_ping (testing_template_id,packet_count,spaceing_time,packet_timeout,payload_size,payload_data,max_ttl,tos ) ")
											.append(" values( ?, ?, ?, ?, ?, ?, ?, ?)  ");
		
		
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
		StringBuffer sql = new StringBuffer("insert into  wasu.testing_template_parameter_trace (testing_template_id,payload_size,protocol_type,max_hops,reply_timeout,packet_count,spacing_time) ")
		.append(" values( ?, ?, ?, ?, ?, ?, ?)  ");
		
		
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
		StringBuffer sql = new StringBuffer("insert into  wasu.testing_template_parameter_web (testing_template_id,max_download_size,max_thread_count,max_sub_count,max_sub_save_count,item_timeout,use_dns_cache,user_agent,max_page_depth,valid_response_codes) ")
		.append(" values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ");
		
		
		simpleExecute(sql.toString(), param);
	}
	
	public void insertTestingTemplate(TestingTemplate item) throws GenericDAOException{
		ArrayList<String> param = new ArrayList<String>();
		//testing_template_id,testing_template_group_id,template_name,test_type,sort_level,description,test_timeout,test_interval,test_execute_count
		param.add(item.getTestingTemplateId());
		param.add(item.getTestingTemplateGroupId());
		param.add(item.getTemplateName());
		param.add(item.getTestType());
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
		if(Const.TEMPLATE_ARAMETER_PING.equals(item.getTestType())){
			tempTable = " wasu.testing_template_parameter_ping ";
		}else if(Const.TEMPLATE_ARAMETER_TRACE.equals(item.getTestType())){
			tempTable = " wasu.testing_template_parameter_trace ";
		}else if(Const.TEMPLATE_ARAMETER_DNS.equals(item.getTestType())){
			tempTable = " wasu.testing_template_parameter_dns ";
		}else if(Const.TEMPLATE_ARAMETER_SPEED.equals(item.getTestType())){
			tempTable = " wasu.testing_template_parameter_speed ";
		}else if(Const.TEMPLATE_ARAMETER_WEB.equals(item.getTestType())){
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
		param.add(item.getTestingTemplateParameterId());
		StringBuffer sql = new StringBuffer("UPDATE wasu.testing_template_parameter_ping set packet_count = ?,spaceing_time = ?,packet_timeout = ?,payload_size = ?,payload_data = ?,max_ttl = ?,tos  = ? ")
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
		param.add(item.getTestingTemplateParameterId());
		StringBuffer sql = new StringBuffer("UPDATE  wasu.testing_template_parameter_trace SET payload_size= ?,protocol_type= ?,max_hops= ?,reply_timeout= ?,packet_count= ?,spacing_time= ? ")
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
		param.add(item.getTestingTemplateParameterId());
		StringBuffer sql = new StringBuffer("UPDATE  wasu.testing_template_parameter_web SET max_download_size= ?,max_thread_count= ?,max_sub_count= ?,max_sub_save_count= ?,item_timeout= ?,use_dns_cache= ?,user_agent,max_page_depth= ?,valid_response_codes= ? ")
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
}
