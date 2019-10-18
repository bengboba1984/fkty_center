package ra.com.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
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
			String strURL = req.getParameter("url");
			String params = req.getParameter("params");
			LOGGER.debug("==========TestingTemplateResultServlet doPost");
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
			params = "{\"items\":[{\"testType\":11,\"applicationType\":1,\"destName\":\"http://www.baidu.com\",\"destIp\":\"61.135.169.125\",\"errorCode\":0,\"account\":\"\",\"result\":{\"connectTime\":57054,\"firstByteTime\":158093,\"firstPageTime\":1942033,\"meanQuality\":64.02266667,\"requestUrl\":\"http://www.baidu.com\",\"testStartTime\":1517301997826691,\"throughput\":64141,\"totalTime\":2158529}}]}";
           JSONObject jsonStu = JSONObject.parseObject(params); 
            ResultJsonItem item = (ResultJsonItem) JSONObject.toJavaObject(jsonStu,ResultJsonItem.class);
            if(item!=null&&item.getItems()!=null){
            	for(ResultTemplateJsonItem i:item.getItems()){
            		System.out.println("===========atype:"+i.getApplicationType());
            		String id = biz.insertTestingResult(i);
            		if(Const.TEMPLATE_ARAMETER_PING==i.getTestType()){
            			TestingResultPing d = (TestingResultPing)i.getResult();
            			String typeId = biz.insertTestingResultPing(d);
            			biz.updateResultTestTypeId("result_ping_id",typeId,id);
            		}else if(Const.TEMPLATE_ARAMETER_TRACE==i.getTestType()){
            			TestingResultTrace d = (TestingResultTrace)i.getResult();
            			String typeId = biz.insertTestingResultTrace(d);
            			biz.updateResultTestTypeId("result_trace_id",typeId,id);
            		}else if(Const.TEMPLATE_ARAMETER_SPEED==i.getTestType()){
            			TestingResultSpeed d = (TestingResultSpeed)i.getResult();
            			String typeId = biz.insertTestingResultSpeed(d);
            			biz.updateResultTestTypeId("result_speed_id",typeId,id);
            		}else if(Const.TEMPLATE_ARAMETER_DNS==i.getTestType()){
            			TestingResultDns d = (TestingResultDns)i.getResult();
            			String typeId = biz.insertTestingResultDns(d);
            			biz.updateResultTestTypeId("result_dns_id",typeId,id);
            		}else if(Const.TEMPLATE_ARAMETER_WEB==i.getTestType()){
            			TestingResultWeb d = (TestingResultWeb)i.getResult();
            			String typeId = biz.insertTestingResultWeb(d);
            			biz.updateResultTestTypeId("result_web_id",typeId,id);
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

}