package ra.com.dataManagement.DAO;

import java.util.Collection;

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

public interface DataManagementDAO {
	public ListChunk getTestingTemplateDataList(String roleId,int pageNo, int pageSize) throws GenericDAOException;
	
	public Collection getTestGroup()throws GenericDAOException;
	
	public Collection getTestType()throws GenericDAOException;
	
	public Collection getTargetType() throws GenericDAOException;
	
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
	
	public ListChunk getTestingTemplateDataList() throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterDnsDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateTargetDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterSpeedDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterWebDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterTraceDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingTemplateParameterPingDataJsonList(int templateId,int pageNo, int pageSize) throws GenericDAOException;
	
	public void insertTestingResult(TestingResult item)throws GenericDAOException;
	
	public void insertTestingResultDns(TestingResultDns item)throws GenericDAOException;
	
	public void insertTestingResultPing(TestingResultPing item)throws GenericDAOException;
	
	public void insertTestingResultSpeed(TestingResultSpeed item)throws GenericDAOException;
	
	public void insertTestingResultWeb(TestingResultWeb item)throws GenericDAOException;
	
	public void insertTestingResultTrace(TestingResultTrace item)throws GenericDAOException;
	
	public void insertTestingResultTraceSub(TestingResultTraceSub item)throws GenericDAOException;
	
	public Long getResultTestTemplateTableId(String column,String table) throws GenericDAOException;
	
	public void updateResultTestTypeId(String column,String typeId,String templateId)throws GenericDAOException;
	
	public ListChunk getTestingResultTemplateDataList(String roleId,String testingDateBegin,String testingDateEnd,String testTypeSearch,String accountSearch,String testerSearch,String resultSeqSearch,String resultId,int pageNo, int pageSize) throws GenericDAOException;
	
	public ListChunk getTestingResultDns(String id,int pageNo, int pageSize)throws GenericDAOException;
	
	public ListChunk getTestingResultPing(String id,int pageNo, int pageSize)throws GenericDAOException;
	
	public ListChunk getTestingResultSpeed(String id,int pageNo, int pageSize)throws GenericDAOException;
	
	public ListChunk getTestingResultWeb(String id,int pageNo, int pageSize)throws GenericDAOException;
	
	public ListChunk getTestingResultTrace(String id,int pageNo, int pageSize)throws GenericDAOException;
	
	public ListChunk getTestingResultTraceSub(String id,int pageNo, int pageSize)throws GenericDAOException;
	
	public String getMaxResult(String date) throws GenericDAOException;
	
	public ListChunk getFtpFileDataList(String testingDateBegin,String testingDateEnd,String testTypeSearch,String accountSearch,String testerSearch,String stbID,String fileId,int pageNo, int pageSize) throws GenericDAOException;
	
	public Collection getFileType()throws GenericDAOException;
	
	public void deleteFtpFile(String id)throws GenericDAOException;
	
	public void updateWOByResultSeq(String resultSeq,String woNumber)throws GenericDAOException;

	public Object getTestResultIDByWONumber(String woNumber)throws GenericDAOException;

	public void insertCaptureFile(int type,String account,String stbId,String tester,String fileName,Long fileId) throws GenericDAOException;
}
