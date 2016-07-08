package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.pojo.Mensaje;
import com.ipartek.formacion.pojo.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd = null;


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
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		if("pikachu".equals(userName)&&"pika".equals(password)){
			usuario = new Usuario();
			usuario.setUserName(userName);
			usuario.setUserPassword(password);
			usuario.setNickname("Profe");

			rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);

		}else{
			rd = request.getRequestDispatcher("index.jsp");
			Mensaje mensaje = new Mensaje();
			mensaje.setMsg("Usuario y/o contraseña incorrectos");
			mensaje.setType(Mensaje.MSG_TYPE_DANGER);
			request.setAttribute(Constantes.ATT_MENSAJE, mensaje);

		}

		rd.forward(request, response);
	}

}
