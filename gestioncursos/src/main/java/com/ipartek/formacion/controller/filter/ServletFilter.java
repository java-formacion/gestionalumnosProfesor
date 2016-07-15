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
 * @author Curso Servlet Filter implementation class ServletFilter
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
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * @param chain
   *          cadena
   * @throws IOException
   *           excepcion input/output
   * @throws ServletException
   *           excepcion servlet
   * 
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // TODO Auto-generated method stub
    HttpServletRequest auxRequest = (HttpServletRequest) request;
    HttpServletResponse auxResponse = (HttpServletResponse) response;
    auxRequest.setCharacterEncoding("UTF-8");
    String uri = auxRequest.getRequestURI();
    // auxRequest.getRequestURI().contains("login")
    if (checkSession(auxRequest) || uri.contains(Constantes.SERVLET_LOGIN)) {
      chain.doFilter(request, response);
    } else {
      auxResponse.sendRedirect("index.jsp");
    }

  }

  /**
   * 
   * @param request
   *          peticion
   * @return existe boolean si existe la sesion
   */
  private boolean checkSession(HttpServletRequest request) {
    boolean existe = false;
    HttpSession session = request.getSession(false);
    if (session != null && session.getAttribute(Constantes.ATT_USUARIO) != null) {
      existe = true;
    }
    return existe;

  }

  /**
   * @see Filter#init(FilterConfig)
   * @param fConfig
   *          configuracion del filtro
   * @throws ServletException
   *           excepcion servlet
   * 
   */
  public void init(FilterConfig fConfig) throws ServletException {
  }

}
