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

import ra.com.portal.bussiness.PortalFacade;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Log LOGGER = LogFactory
			.getLog(LoginServlet.class); 
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		LOGGER.debug("==========LoginServlet init"
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
		PortalFacade biz = PortalFacade.getInstance();
		String params = reqJson(request);
		JSONObject paramsJson = JSONObject.parseObject(params);
		String userName=paramsJson.getString("userName");
		String password=paramsJson.getString("password");
		try {
			if(biz.checkPasswordByUserName(userName, password)){
				returnMessage.put("errorCode", 0);
				returnMessage.put("message", "登录成功!");
				
			}else{
				returnMessage.put("errorCode", 17);
				returnMessage.put("message", "用户名密码错误！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMessage.put("errorCode", 3);
			returnMessage.put("message", "System error!");
		}
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
