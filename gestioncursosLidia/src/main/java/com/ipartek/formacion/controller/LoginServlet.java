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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = null;
		String userName = request.getParameter(Constantes.PAR_USERNAME);
		String pass = request.getParameter(Constantes.PAR_PASSWORD);
		if("lidia".equals(userName)&&"lidia".equals(pass)){
			createSession(request);
			usuario = new Usuario();
			usuario.setUserName(userName);
			usuario.setUserPassword(pass);
			usuario.setNickname("alumna");
			//añadir el id de la sesión
			usuario.setSessionid(session.getId());//si es null no puede expulsar de la sesión
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
			rd.forward(request, response);

		}else{
			createSession(request);
			//	rd = request.getRequestDispatcher("index.jsp");
			Mensaje mensaje = new Mensaje();
			mensaje.setMsg("Usuario y/o contraseña incorrectos");
			mensaje.setType(Mensaje.MSG_TYPE_DANGER);
			//request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			session.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			response.sendRedirect("index.jsp");
		}

	}
	private void createSession(HttpServletRequest request){
		session = request.getSession(true);
		/*
		 * getSession(true) ---> Si la session no existe te la crea
		 * getSession(false) --> Te coge la session activa si no existe es null
		 *
		 */
		session.setMaxInactiveInterval(60*60*15);//15 mins
	}
}





