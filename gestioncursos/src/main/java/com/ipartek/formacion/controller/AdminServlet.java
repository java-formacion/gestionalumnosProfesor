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

import com.ipartek.formacion.pojo.Mensaje;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd = null;
	private static final Logger log = Logger.getLogger(AdminServlet.class);

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter(Constantes.PAR_SESSIONID)!=null)	{ 		
		patearUsuario(request);
	}
		cargarListaUsuarios(request);
		// REDIRIGIR a JSP
		rd.forward(request, response);
	}

	private void patearUsuario(HttpServletRequest request) {
		String sessionId = request.getParameter(Constantes.PAR_SESSIONID);
		ServletContext context = getServletContext();
		Map<String,HttpSession> sesiones= (Map<String, HttpSession>) context.getAttribute(Constantes.ATT_LIST_SESIONES);
		HttpSession session = sesiones.get(sessionId);
		Mensaje m = new Mensaje();

		if(session != null)
			{
				session.invalidate();
				sesiones.remove(sessionId);
				context.setAttribute(Constantes.ATT_LIST_SESIONES, sesiones);
				m.setMsg("Usuario Pateado");
				m.setType(Mensaje.MSG_TYPE_SUCCESS);
			}
		else
			{
				m.setMsg("La Operacion 65412589x00 no ha resultado existosa, porfavor contacte al administrador");
				m.setType(Mensaje.MSG_TYPE_SUCCESS);
				log.info("Error al patear usuario, SessionId enviada: "+sessionId);
			}
		request.setAttribute(Constantes.ATT_MENSAJE, m);

	}

	private void cargarListaUsuarios(HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ADMIN);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
