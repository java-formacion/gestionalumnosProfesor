package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.pojo.Mensaje;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private RequestDispatcher rwd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		session = request.getSession(false);
		if (session != null
				&& session.getAttribute(Constantes.ATT_USUARIO) != null) {
			session.invalidate();
			Mensaje mensaje = new Mensaje();
			mensaje.setMsg("¡Te has desconectado con exito!");
			mensaje.setTipo(Mensaje.MSG_TYPE_SUCCESS);
			session =  request.getSession();
			session.setAttribute(Constantes.MSG_EXITO, mensaje);
		}
		response.sendRedirect("index.jsp");

	}

}
