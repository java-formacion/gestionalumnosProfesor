package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String nUsuario = "", nPass = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("usuario")) {
					nUsuario = cookie.getValue();
				} else {
					if (cookie.getName().equals("password")) {
						nPass = cookie.getValue();
					}
				}
			}
			
			
			if(!"".equals(nUsuario) && !"".equals(nPass)){
				createSession(request);
				//falta hacer la redireccion
				rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
				rd.forward(request, response);
			}
			
		}
		Usuario usuario = null;
		String userName = request.getParameter(Constantes.PAR_USERNAME);
		String pass = request.getParameter(Constantes.PAR_PASSWORD);
		String[] checkBoxes = request
				.getParameterValues(Constantes.PAR_REMEMBER);
		if (checkBoxes != null && checkBoxes.length == 1) {
			Cookie cookieName = new Cookie("usuario", usuario.getUserName());// Cada uno de los parametros que queremos guardar, van a ir a una cookie diferente
			Cookie cookiePass = new Cookie("pass", usuario.getUserPassword());
			cookieName.setMaxAge(24 * 60 * 60);// tiempo de la cookie, hasta su
												// eliminacion
			cookiePass.setMaxAge(24 * 60 * 60);
			response.addCookie(cookiePass);
			response.addCookie(cookieName);
		}
		if ("anabel".equals(userName) && "anabel".equals(pass)
				|| "violeta".equals(userName) && "violeta".equals(pass)) {
			createSession(request);
			usuario = new Usuario();
			usuario.setUserName(userName);
			usuario.setUserPassword(pass);
			usuario.setNickname("Alumno");
			usuario.setSessionId(session.getId());
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
			rd.forward(request, response);
		} else {
			createSession(request);
			// rd = request.getRequestDispatcher("index.jsp");
			Mensaje mensaje = new Mensaje();
			mensaje.setMsg("Usuario y/o contraseña incorrectos");
			mensaje.setType(Mensaje.MSG_TYPE_DANGER);
			// request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			session.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			response.sendRedirect("index.jsp");
		}

	}

	private void createSession(HttpServletRequest request) {
		session = request.getSession(true);
		/*
		 * getSession(true) ---> Si la session no existe te la crea
		 * getSession(false) --> Te coge la session activa si no existe es null
		 */
		session.setMaxInactiveInterval(60 * 60 * 15);
	}
}
