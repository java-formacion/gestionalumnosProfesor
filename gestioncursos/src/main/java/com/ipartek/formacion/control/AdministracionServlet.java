package com.ipartek.formacion.control;

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

import com.ipartek.formacion.pojo.Mensaje;

/**
 * Servlet implementation class AdministracionServlet
 */
public class AdministracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(AdministracionServlet.class);
	private RequestDispatcher rd = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter(Constantes.PAR_SESIONID) != null) {
			expulsarUsuario(request);
		}

		cargarListausuarios(request);

		rd.forward(request, response);
	}

	private void expulsarUsuario(HttpServletRequest request) {
		String sesionId = null;
		sesionId = request.getParameter(Constantes.PAR_SESIONID);
		ServletContext context = getServletContext();
		Map<String, HttpSession> sesiones = (Map<String, HttpSession>) context
				.getAttribute(Constantes.ATT_LISTA_SESIONES);
		HttpSession sesion = sesiones.get(sesionId);
		Mensaje mensaje = new Mensaje();
		if (sesion != null) {
			sesion.invalidate();
			sesiones.remove(sesionId);
			context.setAttribute(Constantes.ATT_LISTA_SESIONES, sesiones);
			mensaje.setMsg("Usuario expulsado");
			mensaje.setType(Mensaje.MSG_TYPE_SUCCESS);
		} else {
			mensaje.setMsg("La operación no se ha realizado con exito, contacte con el administrador");
			mensaje.setType(Mensaje.MSG_TYPE_ERROR);
			logger.info("Error al expulsar la sessionID" + sesionId);
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
	}

	private void cargarListausuarios(HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_ADMIN);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
