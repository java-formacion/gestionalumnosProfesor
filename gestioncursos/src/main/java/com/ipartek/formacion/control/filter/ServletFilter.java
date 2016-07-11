package com.ipartek.formacion.control.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.control.Constantes;

/**
 * Servlet Filter implementation class ServletFilter
 */
public class ServletFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ServletFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding("UTF-8");
		String url = req.getServletPath();
		if (!url.contains(Constantes.SERVLET_LOGIN)){
			if(checkSession(req)){
				chain.doFilter(request, response);
			} else{
				res.sendRedirect("index.jsp");
			}
		}else{
			chain.doFilter(request, response);
		}
		// pass the request along the filter chain
		
	}

	private boolean checkSession(HttpServletRequest req) {
		boolean bool=false;
		
		HttpSession session = req.getSession(false);
		if (session!=null && session.getAttribute(Constantes.ATT_USUARIO)!=null){
			bool=true;
		}
		
		return bool;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
