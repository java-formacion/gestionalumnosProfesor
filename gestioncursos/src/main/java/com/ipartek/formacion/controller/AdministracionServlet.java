package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class UsuariosServlet
 */
public class AdministracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger log =  Logger.getLogger(AdministracionServlet.class);
	private RequestDispatcher rwd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministracionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter(Constantes.PAR_SESSION_ID) != null){
			log.error(request.getParameter(Constantes.PAR_SESSION_ID));
		}
		rwd =  request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
		rwd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
