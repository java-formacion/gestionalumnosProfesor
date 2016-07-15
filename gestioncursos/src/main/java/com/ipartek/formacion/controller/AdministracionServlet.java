package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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


public class AdministracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final int ID = -1;
	 private RequestDispatcher rd = null;
	 private static final Logger LOG = Logger.getLogger(AdministracionServlet.class);
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	 
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter(Constantes.PAR_SESIONID) != null){
			expulsarUsuario(request);
		}
		
		cargarListadoUsuarios(request);
		//rd = request.getRequestDispatcher(Constantes.JSP_ADMINISTRACION); Lo hace en un método aparte
		rd.forward(request, response); 
	}

	private void expulsarUsuario(HttpServletRequest request) {
		String sesionId = request.getParameter(Constantes.PAR_SESIONID);
		ServletContext contexto = getServletContext();
		Map<String, HttpSession> sesiones = (Map<String, HttpSession>) contexto.getAttribute(Constantes.ATT_LISTADO_SESIONES);
		HttpSession session = sesiones.get(sesionId); 
		Mensaje m = new Mensaje();
		if(session != null){
			session.invalidate();
			sesiones.remove(sesionId);
			contexto.setAttribute(Constantes.ATT_LISTADO_SESIONES, sesiones);
			m.setMsg("Usuario Expulsado");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);	
			
		}else{
			LOG.info("error al expulsar " + sesionId);
			m.setMsg("No se ha podido realizar la operación");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);			
		}
		request.setAttribute(Constantes.ATT_MENSAJE, m);
		
	}

	private void cargarListadoUsuarios(HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_ADMINISTRACION);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
