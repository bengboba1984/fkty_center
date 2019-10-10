package ra.com.dataManagement.DAO;

import java.util.Collection;
import java.util.List;

import ra.com.common.dao.GenericDAOException;
import ra.com.common.model.ListChunk;
import ra.com.dataManagement.model.TestingTemplate;
import ra.com.dataManagement.model.TestingTemplateParameterDns;
import ra.com.dataManagement.model.TestingTemplateParameterPing;
import ra.com.dataManagement.model.TestingTemplateParameterSpeed;
import ra.com.dataManagement.model.TestingTemplateParameterTrace;
import ra.com.dataManagement.model.TestingTemplateParameterWeb;
import ra.com.dataManagement.model.TestingTemplateTarget;

public interface DataManagementDAO {
	public ListChunk getTestingTemplateDataList(String roleId,int pageNo, int pageSize) throws GenericDAOException;
	
	public Collection getTestGroup()throws GenericDAOException;
	
	public Collection getTestType()throws GenericDAOException;
	
	public Collection getTargetType()throws GenericDAOException;
	
	public ListChunk getTestingTemplateTargetDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterPingDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterDnsDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterSpeedDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterTraceDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterWebDataList(String templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public void insertTestingTemplateParameterPing(String templateId,TestingTemplateParameterPing item) throws GenericDAOException;
	
	public void insertTestingTemplateParameterDns(String templateId,TestingTemplateParameterDns item) throws GenericDAOException;
	
	public void insertTestingTemplateParameterSpeed(String templateId,TestingTemplateParameterSpeed item) throws GenericDAOException;
	
	public void insertTestingTemplateParameterTrace(String templateId,TestingTemplateParameterTrace item) throws GenericDAOException;
	
	public void insertTestingTemplateParameterWeb(String templateId,TestingTemplateParameterWeb item) throws GenericDAOException;
	
	public void insertTestingTemplate(TestingTemplate item) throws GenericDAOException;
	
	public Long getTestTemplateId() throws GenericDAOException;
	
	public boolean removeitTestingTemplate(TestingTemplate item) throws GenericDAOException;
	
	public Collection getProtocolType()throws GenericDAOException;
	
	public void insertTargetType(String targetType) throws GenericDAOException;
	
	public void insertTestingTarget(TestingTemplateTarget item) throws GenericDAOException;
	
	public void deleteTestingTarget(String targetId) throws GenericDAOException;
	
	public void updateTestingTemplate(TestingTemplate item) throws GenericDAOException;
	
	public void updateTestingTemplateParameterPing(String templateId,TestingTemplateParameterPing item) throws GenericDAOException;
	
	public void updateTestingTemplateParameterSpeed(String templateId,TestingTemplateParameterSpeed item) throws GenericDAOException;
	
	public void updateTestingTemplateParameterTrace(String templateId,TestingTemplateParameterTrace item) throws GenericDAOException;
	
	public void updateTestingTemplateParameterWeb(String templateId,TestingTemplateParameterWeb item) throws GenericDAOException;
	
	public void updateTestingTemplateParameterDns(String templateId,TestingTemplateParameterDns item) throws GenericDAOException;
	
	public void updateTestingTarget(TestingTemplateTarget item) throws GenericDAOException;
}
