package com.ipartek.formacion.controller;

import java.io.IOException;
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



public class AdministracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rwd = null;
	private static final Logger log = Logger.getLogger(AdministracionServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter(Constantes.PAR_SESSION_ID)!=null){
			expulsarUsuario(request);
		}else{
			//cargar dispacher con la lista de usuarios (mejor hacerlo en un método aparte)
			cargarListadoUsuarios(request);
			
		}
		//redirigir
		rwd.forward(request,response);
	}

	
	private void expulsarUsuario(HttpServletRequest request) {
		String id =  request.getParameter(Constantes.PAR_SESSION_ID);
		ServletContext context = getServletContext();
		Map<String, HttpSession> sesiones = (Map<String, HttpSession>) context.getAttribute(Constantes.ATT_LIST_SESIONES);
		HttpSession session = sesiones.get(id);
		if(session != null){
			session.invalidate();
			sesiones.remove(id); //eliminamos la sesion del mapa
			context.setAttribute(Constantes.ATT_LIST_SESIONES, sesiones); //actualizamos el contexto
			//request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		}else{
			log.info("error al expulsar");
		}
		
	}


	private void cargarListadoUsuarios(HttpServletRequest request) {
		rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CONECTADOS);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
