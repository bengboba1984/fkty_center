package ra.com.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ra.com.common.Const;
import ra.com.dataManagement.bussiness.DataManagementFacade;
import ra.com.dataManagement.model.ResultJsonItem;
import ra.com.dataManagement.model.ResultTemplateJsonItem;
import ra.com.dataManagement.model.TestingResultDns;
import ra.com.dataManagement.model.TestingResultPing;
import ra.com.dataManagement.model.TestingResultSpeed;
import ra.com.dataManagement.model.TestingResultTrace;
import ra.com.dataManagement.model.TestingResultTraceSub;
import ra.com.dataManagement.model.TestingResultWeb;
import ra.com.system_mgt.model.User;

public class TestingTemplateResultServlet  extends HttpServlet {
	private final Log LOGGER = LogFactory.getLog(TestingTemplateResultServlet.class);

	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		LOGGER.debug("==========TestingTemplateResultServlet init" + new Date().toString());
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		DataManagementFacade biz = DataManagementFacade.getInstance();
		String json = "";
		BufferedReader reader = null;
		String result = "";
		try {
			json = biz.getItestorTemplateList();
			String params = reqJson(req);
			LOGGER.debug("==========TestingTemplateResultServlet doPost");
			System.out.println("====params="+params);
			/*URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            //connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();*/
//			params = "{\"items\":[{\"testType\":2,\"applicationType\":1,\"destName\":\"http://www.baidu.com\",\"destIp\":\"61.135.169.125\",\"errorCode\":0,\"account\":\"\",\"result\":{\"provinceCode\":\"SH\",\"cityCode\":\"\",\"resultSubData\":[{\"applicationType\":1,\"cacheMaxAge\":0,\"connectBeginTime\":1517301997847591,\"connectTime\":57054,\"contentEncoding\":\"\",\"contentLength\":225,\"contentType\":\"text/html\",\"dnsResponseCode\":0,\"downloadLength\":953,\"downloadTime\":158992,\"firstByteTime\":158093,\"hostIp\":\"61.135.169.125\",\"hostIpv4\":2108262205,\"isLocal\":true,\"loadDepth\":0,\"loadIndex\":1,\"meanQuality\":60.97533333,\"requestUrl\":\"http://www.baidu.com\",\"resolveBeginTime\":1517301997826691,\"resolveTime\":20820,\"responseCode\":302,\"throughput\":5994,\"totalTime\":236866,\"transferBeginTime\":1517301997904678}],\"cacheMaxAge\":0,\"connectBeginTime\":1517301997847591,\"connectEndTime\":1517301997904645,\"connectTime\":57054,\"connectedPercent\":100,\"contentEncoding\":\"\",\"contentLength\":81068,\"contentType\":\"text/html\",\"dnsResponseCode\":0,\"downloadLength\":133449,\"downloadTime\":2080542,\"elementCount\":10,\"failedElementCount\":0,\"firstByteTime\":158093,\"firstPagePercent\":100,\"firstPageTime\":1942033,\"fistByteReceived\":1,\"hostIp\":\"61.135.169.125\",\"hostIpv4\":2108262205,\"isLocal\":true,\"loadDepth\":0,\"loadIndex\":1,\"loadThroughput\":45263,\"loadedPercent\":100,\"maxDownloadSpeed\":0,\"meanQuality\":64.02266667,\"normalDownloadSpeed\":0,\"pageLoadTime\":2153385,\"requestUrl\":\"http://www.baidu.com\",\"resolveBeginTime\":1517301997826691,\"resolveEndTime\":1517301997847511,\"resolveTime\":20820,\"resolvedPercent\":100,\"responseCode\":302,\"statOperatorId\":0,\"successPercent\":100,\"testStartTime\":1517301997826691,\"throughput\":64141,\"totalTime\":2158529,\"transferBeginTime\":1517301997904678,\"transferCompletely\":1,\"transferEndTime\":1517301999985220,\"subItemCount\":10}}]}";
           JSONObject jsonStu = JSONObject.parseObject(params); 
            ResultJsonItem item = (ResultJsonItem) JSONObject.toJavaObject(jsonStu,ResultJsonItem.class);
            if(item!=null&&item.getItems()!=null){
            	for(ResultTemplateJsonItem i:item.getItems()){
            		System.out.println("===========atype:"+i.getApplicationType());
            		String id = biz.insertTestingResult(i);
            		if(Const.TEMPLATE_ARAMETER_PING==i.getTestType()){
            			TestingResultPing d = new TestingResultPing();
            			JSONArray jsonArray = (JSONArray)jsonStu.getJSONArray("items");
            			//获取jsonArray数组数据
            			  for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            			      JSONObject jsonObject1 = (JSONObject) iterator.next();
            			             //将数组转换成字符串
            			             JSONObject obj = jsonObject1.getJSONObject("result");
            			             String avgDelay  = obj.getString("avgDelay");
            			             String avgJitter  = obj.getString("avgJitter");
            			             String lossPercent  = obj.getString("lossPercent");
            			             String hostIp  = obj.getString("hostIp"); 
            			             d.setAvgDelay(avgDelay);
            			             d.setAvgJitter(avgJitter);
            			             d.setLossPercent(lossPercent);
            			             d.setHostIp(hostIp);
            			             String typeId = biz.insertTestingResultPing(d);
            	            		biz.updateResultTestTypeId("result_ping_id",typeId,id);
            			 } 
            			
            		}else if(Const.TEMPLATE_ARAMETER_TRACE==i.getTestType()){
            			TestingResultTrace d = new TestingResultTrace();
            			JSONArray jsonArray = (JSONArray)jsonStu.getJSONArray("items");
            			//获取jsonArray数组数据
            			  for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            			      JSONObject jsonObject1 = (JSONObject) iterator.next();
            			             //将数组转换成字符串
            			             JSONObject obj = jsonObject1.getJSONObject("result");
            			             String resultTraceId  = obj.getString("resultTraceId");
            			             String avgDelay  = obj.getString("avgDelay");
            			             String avgJitter  = obj.getString("avgJitter");
            			             String hopCount  = obj.getString("hopCount");
            			             String hostIp  = obj.getString("hostIp");
            			             String lossPercent  = obj.getString("lossPercent");
            			             String typeId = biz.insertTestingResultTrace(d);
            	            		biz.updateResultTestTypeId("result_trace_id",typeId,id);
            	            		JSONArray subArray = (JSONArray)obj.getJSONArray("resultSubData");
            	            		for (Iterator iterator1 = subArray.iterator(); iterator1.hasNext();) {
                      			      JSONObject subObject = (JSONObject) iterator1.next();
                      			             //将数组转换成字符串
                      			             String loadIndex  = subObject.getString("loadIndex");
                      			             String hostIp1  = subObject.getString("hostIp");
                      			             String avgDelay1  = subObject.getString("avgDelay");
                      			             String lossPercent1  = subObject.getString("lossPercent");
                      			           TestingResultTraceSub sub = new TestingResultTraceSub();
                      			           sub.setResultTraceId(typeId);
                      			           sub.setLoadIndex(loadIndex);
                      			           sub.setHostIp(hostIp1);
                      			           sub.setAvgDelay(avgDelay1);
                      			           sub.setLossPercent(lossPercent1);
                      			           biz.insertTestingResultTraceSub(sub);
                      			 }
            	            		
            			 }
            		}else if(Const.TEMPLATE_ARAMETER_SPEED==i.getTestType()){
            			TestingResultSpeed d = (TestingResultSpeed)i.getResult();
            			JSONArray jsonArray = (JSONArray)jsonStu.getJSONArray("items");
            			//获取jsonArray数组数据
            			  for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            			      JSONObject jsonObject1 = (JSONObject) iterator.next();
            			             //将数组转换成字符串
            			             JSONObject obj = jsonObject1.getJSONObject("result");
            			             int downloadMaxThroughput  = obj.getIntValue("downloadMaxThroughput");
            			             int downloadThroughput  = obj.getIntValue("downloadThroughput");
            			             int uploadMaxThroughput  = obj.getIntValue("uploadMaxThroughput");
            			             int uploadThroughput  = obj.getIntValue("uploadThroughput");
            			             d.setDownloadMaxThroughput(downloadMaxThroughput);
            			             d.setDownloadThroughput(downloadThroughput);
            			             d.setUploadMaxThroughput(uploadMaxThroughput);
            			             d.setUploadThroughput(uploadThroughput);
            			             String typeId = biz.insertTestingResultSpeed(d);
            	            		biz.updateResultTestTypeId("result_speed_id",typeId,id);
            			 } 
            		}else if(Const.TEMPLATE_ARAMETER_DNS==i.getTestType()){
            			TestingResultDns d = new TestingResultDns();
            			JSONArray jsonArray = (JSONArray)jsonStu.getJSONArray("items");
            			//获取jsonArray数组数据
            			  for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            			      JSONObject jsonObject1 = (JSONObject) iterator.next();
            			             //将数组转换成字符串
            			             JSONObject obj = jsonObject1.getJSONObject("result");
            			             int resolveTime  = obj.getIntValue("resolveTime");
            			             int successPercent  = obj.getIntValue("successPercent");
            			             String numberOfAnswers  = obj.getString("numberOfAnswers");
            			             d.setResolveTime(resolveTime);
            			             d.setSuccessPercent(successPercent);
            			             d.setNumberOfAnswers(numberOfAnswers);
            			             String typeId = biz.insertTestingResultDns(d);
            			             biz.updateResultTestTypeId("result_dns_id",typeId,id);
            			 } 
            		}else if(Const.TEMPLATE_ARAMETER_WEB==i.getTestType()){
            			TestingResultWeb d = new TestingResultWeb();
            			JSONArray jsonArray = (JSONArray)jsonStu.getJSONArray("items");
            			//获取jsonArray数组数据
            			  for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            			      JSONObject jsonObject1 = (JSONObject) iterator.next();
            			             //将数组转换成字符串
            			             JSONObject obj = jsonObject1.getJSONObject("result");
            			             int connectTime  = obj.getIntValue("connectTime");
            			             int resolveTime  = obj.getIntValue("resolveTime");
            			             int firstByteTime  = obj.getIntValue("firstByteTime");
            			             int firstPageTime  = obj.getIntValue("firstPageTime");
            			             int meanQuality  = obj.getIntValue("meanQuality");
            			             int responseCode  = obj.getIntValue("responseCode");
            			             String throughput  = obj.getString("throughput");
            			             String totalTime  = obj.getString("totalTime");
            			             String hostIp  = obj.getString("hostIp");
            			             String nodeIp  = obj.getString("nodeIp");
            			             d.setConnectTime(connectTime);
            			             d.setResolveTime(resolveTime);
            			             d.setFirstByteTime(firstByteTime);
            			             d.setFirstPageTime(firstPageTime);
            			             d.setMeanQuality(meanQuality);
            			             d.setResponseCode(responseCode);
            			             d.setThroughput(throughput);
            			             d.setTotalTime(totalTime);
            			             d.setHostIp(hostIp);
            			             d.setNodeIp(nodeIp);
            			             String typeId = biz.insertTestingResultWeb(d);
            			             biz.updateResultTestTypeId("result_web_id",typeId,id);
            			 }   
            		}
            		
            	}
           }
            
           result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		res.getOutputStream().write(result.getBytes());

	}
	private String reqJson(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}