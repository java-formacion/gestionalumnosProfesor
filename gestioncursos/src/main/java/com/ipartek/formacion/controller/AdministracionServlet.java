package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Usuario;

/**
 * @author Imanol Servlet implementation class UsuariosServlet
 */
public class AdministracionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final static Logger LOG = Logger.getLogger(AdministracionServlet.class);
  private RequestDispatcher rwd;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdministracionServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   * @param response
   *          respuesta
   * @param request
   *          peticion
   * @throws ServletException
   *           excepcion servlet
   * @throws IOException
   *           excepcion input/output
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String sessionId = request.getParameter(Constantes.PAR_SESSION_ID);
    if (sessionId != null) {
      desconectarUsuario(request, sessionId);
    }
    cargarListadoUsuarios(request);
    rwd.forward(request, response);
  }

  /**
   * 
   * @param request
   *          peticion
   * @param sessionId
   *          id de la sesion
   */
  @SuppressWarnings("unchecked")
  private void desconectarUsuario(HttpServletRequest request, String sessionId) {
    ServletContext context = getServletContext();
    /*
     * TODO Fix cast warning desconectarUsuario
     */
    Map<String, HttpSession> sesiones = (Map<String, HttpSession>) context
        .getAttribute(Constantes.ATT_LISTADO_SESIONES);
    HttpSession session = sesiones.get(sessionId);
    if (session != null) {
      Usuario user = (Usuario) session.getAttribute(Constantes.ATT_USUARIO);
      session.invalidate();
      sesiones.remove(sessionId);
      context.setAttribute(Constantes.ATT_LISTADO_SESIONES, sesiones);

      LOG.info("Se ha expulsado a " + user.getAlias());
    } else {
      LOG.error("Error al expulsar");
    }

  }

  /**
   * 
   * @param request
   *          peticion
   */
  private void cargarListadoUsuarios(HttpServletRequest request) {
    rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * @throws ServletException
   *           excepcion de servlet
   * @throws IOException
   *           excepcion input/outpu
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

}
