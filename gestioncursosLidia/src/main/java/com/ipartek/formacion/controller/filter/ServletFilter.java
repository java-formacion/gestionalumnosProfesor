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
	//método de filtrado
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//el response y request no son HTTpsERVLET,hay que hacer casting
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		//todas las peticiones sean UTF-8
		req.setCharacterEncoding("UTF-8");
		//vamos a coger la URL
		String uri=req.getServletPath();//webapp
		if(!uri.contains(Constantes.SERVLET_LOGIN)){//si no contiene esto q no lo ejecute
			
		
		//saber si hay una sesión,creamos método checksession
		if(checkSession(req)){//sesión válida
			chain.doFilter(request, response);//si todo va bien,sigue para delante
		}else{//no tiene sesión válida
			res.sendRedirect("index.jsp");//con esto no se ve nada de la app,login.do no se ejecuta
		}
		
		}else{
		chain.doFilter(request, response);
		}
	}

	private boolean checkSession(HttpServletRequest req) {
		boolean correcto=false;
		HttpSession session=req.getSession(false);
		if(session!=null && session.getAttribute(Constantes.ATT_USUARIO)!=null){//si la sesión es nula 
			correcto=true;
		}
		return correcto;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
