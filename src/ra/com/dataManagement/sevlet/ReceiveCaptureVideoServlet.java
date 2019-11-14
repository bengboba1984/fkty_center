package ra.com.dataManagement.sevlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ra.com.dataManagement.bussiness.DataManagementFacade;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class receiveCaptureVideoServlet
 */
public class ReceiveCaptureVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Log LOGGER = LogFactory
			.getLog(ReceiveCaptureVideoServlet.class);   
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		LOGGER.debug("==========receiveCaptureVideoServlet init"
				+ new Date().toString());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject returnMessage=new JSONObject();
		DataManagementFacade biz = DataManagementFacade.getInstance();
		String params = reqJson(request);
		JSONObject paramsJson = JSONObject.parseObject(params);
		int type=paramsJson.getIntValue("type");
		String account=paramsJson.getString("account");
		String stbId=paramsJson.getString("stbId");
		String tester=paramsJson.getString("tester");
		String fileName=paramsJson.getString("fileName");
		try {
			String fileId=biz.insertCaptureFile(type,account,stbId,tester,fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMessage.put("errorCode", 3);
			returnMessage.put("message", "System error!");
		}
		returnMessage.put("errorCode", 0);
		returnMessage.put("message", "File have synced to web server successfully!");
		response.getOutputStream().write(returnMessage.toString().getBytes());
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
