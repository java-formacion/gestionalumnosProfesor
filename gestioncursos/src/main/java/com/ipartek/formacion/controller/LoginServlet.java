package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.pojo.Mensaje;
import com.ipartek.formacion.pojo.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd = null;
	HttpSession session = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = null;
		
		String username = request.getParameter(Constantes.PAR_USERNAME);		
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		
		String user = "Tamasco";
		String pass = "123456";
		
		if(user.equals(username) && pass.equals(password)){
			createSession(request);
			
			usuario = new Usuario();
			usuario.setUserName(username);
			usuario.setUserPassword(password);
			usuario.setNickname("Alumno");
			usuario.setSessionid(session.getId());
			
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			
			rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
			rd.forward(request, response);
		} else{
			createSession(request);
			
			//rd = request.getRequestDispatcher(Constantes.JSP_INDEX);
			
			Mensaje mensaje = new Mensaje();
			mensaje.setMsg("Usuario y/o contrase&ntilde;a incorrectos");
			mensaje.setType(Mensaje.MSG_TYPE_DANGER);
			session.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			//request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			response.sendRedirect(Constantes.JSP_INDEX);
		}

	}
	
	private void createSession(HttpServletRequest request){
		/*
		 * getSession(true) 	--> Si la sesion no existe te la crea
		 * getSession(false) 	--> Te coge la sesion existente, si no existe es null
		 */
		
		session = request.getSession();
		session.setMaxInactiveInterval(60*60*15);
	}
	
	private void deleteSession(HttpServletRequest request){
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
