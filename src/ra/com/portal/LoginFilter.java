package ra.com.portal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	private String excludedPages;      
	private String[] excludedPageArray;  

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("###filter/path="+((HttpServletRequest) request).getServletPath());
		boolean isExcludedPage = false; 
		
		for (String page : excludedPageArray) {//判断是否在过滤url之外     
			System.out.println("##exclude:"+page);
			if(((HttpServletRequest) request).getServletPath().equals(page)){     
				isExcludedPage = true;     
				break;     
			}     
		}
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpSession httpSession = httpReq.getSession();
		System.out.println("##Path=" + httpReq.getServletPath());
		
		if (isExcludedPage) {
			chain.doFilter(request, response);
		} else {
			if (httpSession.getAttribute("loginUser") == null
					&& !httpReq.getServletPath().endsWith("login.action")
					&& !httpReq.getServletPath().endsWith(
							"login_doLogin.action")
					&& !httpReq.getServletPath().endsWith("logout.action")
					&& !httpReq.getServletPath().endsWith("login_menu.action")
					&& !httpReq.getServletPath().endsWith(
							"register_user.action")) {
				PrintWriter out = response.getWriter();
				out.print("<html><script>window.open('logout.action','_top');</script></html>");
				// httpRes.sendRedirect("/jxc_frank/logout.action");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		excludedPages = fConfig.getInitParameter("excludedPages");     
		if (excludedPages!=null && !"".equals(excludedPages)) {     
			excludedPageArray = excludedPages.split(",");     
		}    
	}
}
