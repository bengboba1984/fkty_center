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

import com.alibaba.fastjson.JSONObject;

import ra.com.dataManagement.bussiness.DataManagementFacade;

/**
 * Servlet implementation class SubmitWOServlet
 */
public class SubmitWOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Log LOGGER = LogFactory
			.getLog(SubmitWOServlet.class);   
    
	
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		LOGGER.debug("==========SubmitWOServlet init"
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
		String resultSeq=paramsJson.getString("resultSeq");
		String woNumber=paramsJson.getString("woNumber");
		if((resultSeq!=null && resultSeq.length()>0) && (woNumber!=null && woNumber.length()>0)){
			try {
				if(!biz.checkWONumber(woNumber)){
					try {
						biz.updateWOByResultSeq(resultSeq,woNumber);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						returnMessage.put("errorCode", 3);
						returnMessage.put("message", "System error!");
					}
					returnMessage.put("errorCode", 0);
					returnMessage.put("message", "Submit WO success!");
				}else{
					returnMessage.put("errorCode", 7);
					returnMessage.put("message", "WO Number exists!");
				}
			} catch (Exception e) {
				returnMessage.put("errorCode", 3);
				returnMessage.put("message", "System error!");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			returnMessage.put("errorCode", 2);
			returnMessage.put("message", "resultSeq & woNumber are required!");
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
