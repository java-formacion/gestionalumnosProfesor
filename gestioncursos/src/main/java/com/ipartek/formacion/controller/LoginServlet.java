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
	private Usuario usuario = null;
	private String alias = "imanol";
	private String password = "1111";
	private RequestDispatcher rwd;
	private HttpSession session = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request);
		if (alias.equals(usuario.getAlias())
				&& password.equals(usuario.getPassword())) {
			createSesion(request);
			usuario.setIdSession(session.getId());
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			rwd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
			rwd.forward(request, response);
		} else {
			Mensaje mensaje = new Mensaje();
			mensaje.setMsg("Usuario y/o contraseña incorrecta");
			mensaje.setTipo(Mensaje.MSG_TYPE_ERROR);
			createSesion(request);
			session.setAttribute(Constantes.MSG_ERROR, mensaje);
			response.sendRedirect("index.jsp");
		}

	}

	private void createSesion(HttpServletRequest request) {
		session = request.getSession(true);
		/**
		 * getSession(boolean) |--> true: Si no existe, crea una sesion |-->
		 * false: Coge la sesion existente, si no existe no lo crea
		 */
		session.setMaxInactiveInterval(60 * 60 * 15);
	}

	private void doProcess(HttpServletRequest request) {
		usuario = new Usuario();
		usuario.setAlias(request.getParameter(Constantes.PAR_USUARIO));
		usuario.setPassword(request.getParameter(Constantes.PAR_PASSWORD));
	}

}
