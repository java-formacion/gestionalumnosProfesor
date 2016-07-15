package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Mensaje;

/**
 * @author Curso Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private HttpSession session;
  private static final Logger LOG = Logger.getLogger(LogoutServlet.class);

  /**
   * @see HttpServlet#HttpServlet()
   */
  public LogoutServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * @throws ServletException
   *           excepcion servlet
   * @throws IOException
   *           excepcion input/output
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doProcess(request, response);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * @throws ServletException
   *           excepcion servlet
   * @throws IOException
   *           excepcion input/output
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doProcess(request, response);
  }

  /**
   * 
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * 
   * @throws IOException
   *           excepcion input/output
   */
  private void doProcess(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    session = request.getSession(false);
    if (session != null && session.getAttribute(Constantes.ATT_USUARIO) != null) {
      LOG.info("Cerrando sesion");
      session.invalidate();
      Mensaje mensaje = new Mensaje();
      mensaje.setMsg("¡Te has desconectado con exito!");
      mensaje.setTipo(Mensaje.MSG_TYPE_SUCCESS);
      session = request.getSession();
      session.setAttribute(Constantes.MSG_EXITO, mensaje);

    }
    response.sendRedirect("index.jsp");

  }

}
