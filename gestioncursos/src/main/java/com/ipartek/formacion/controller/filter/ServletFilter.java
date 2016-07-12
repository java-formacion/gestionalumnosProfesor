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
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		
		String uri = req.getServletPath();
		if(!uri.contains(Constantes.SERVLET_LOGIN)){
			if(checkSession(req)){
				chain.doFilter(request, response);
			} else{
				// No tiene una sesion valida
				res.sendRedirect(Constantes.JSP_INDEX);
			}
		} else{
			chain.doFilter(request, response);
		}
	}

	private boolean checkSession(HttpServletRequest req) {
		boolean correcto = false;
		
		HttpSession session = req.getSession(false);
		
		if(session != null && session.getAttribute(Constantes.ATT_USUARIO)!=null){
			correcto = true;
		}
		
		return correcto;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
