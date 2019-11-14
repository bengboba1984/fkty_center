package ra.com.dataManagement.sevlet;

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

public class TestingTemplateServlet extends HttpServlet {
	private final Log LOGGER = LogFactory.getLog(TestingTemplateServlet.class);

	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		LOGGER.debug("==========TestingTemplateServlet init" + new Date().toString());
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		DataManagementFacade biz = DataManagementFacade.getInstance();
		String json = "";
		try {
			json = biz.getItestorTemplateList();
			LOGGER.debug("==========TestingTemplateServlet doPost");
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		res.getOutputStream().write(json.getBytes());

	}

}