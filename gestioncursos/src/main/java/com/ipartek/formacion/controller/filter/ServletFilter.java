package com.ipartek.formacion.controller.filter;

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

import com.ipartek.formacion.controller.Constantes;

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
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest hsr=(HttpServletRequest)request;
		HttpServletResponse hsRespo=(HttpServletResponse)response;
		
		hsr.setCharacterEncoding("UTF-8");
		String uri=hsr.getServletPath();
		uri.equals("/"+Constantes.SERVLET_LOGIN);
		
		if (!uri.contains(Constantes.SERVLET_LOGIN)) {
			if (checkSession(hsr)) {
				chain.doFilter(request, response);
			}else {
				hsRespo.sendRedirect("index.jsp");
			}
		}else{
			chain.doFilter(request, response);
		}
		
		
		
		// pass the request along the filter chain
		
	}

	private boolean checkSession(HttpServletRequest hsr) {
		boolean ok=false;
		HttpSession session=hsr.getSession(false);
		if (session!=null && session.getAttribute(Constantes.ATT_USUARIO)!=null) {
			ok=true;
		}
		
		return ok;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
