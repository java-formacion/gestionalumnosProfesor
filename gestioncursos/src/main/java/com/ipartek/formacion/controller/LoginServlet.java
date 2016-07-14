package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Idioma;
import com.ipartek.formacion.pojo.Mensaje;
import com.ipartek.formacion.pojo.Usuario;
import com.ipartek.formacion.service.Util;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger("ACCESOS");
	private Usuario user = null;
	private String alias = "";
	private String password = "";
	private RequestDispatcher rwd;
	private HttpSession session = null;
	private Cookie cookieNombre = null;
	private Cookie cookiePass = null;

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
		if (cargarCookies(request)) {
			cargarDatosCookies();

		} else {
			if (request.getParameter(Constantes.PAR_USUARIO) != null) {
				cargarParametros(request);

			}
		}
		if (user != null && "imanol".equals(user.getAlias())
				&& "1111".equals(user.getPassword())) {
			String[] checkboxes = request
					.getParameterValues(Constantes.PAR_RECUERDA);
			generarCookies(response);
			if (checkboxes != null && checkboxes.length == 1) {
				cookieNombre.setMaxAge(24 * 60 * 60);
				cookiePass.setMaxAge(60 * 24 * 60);
			} else {
				cookieNombre.setMaxAge(0);
				cookiePass.setMaxAge(0);
			}
			response.addCookie(cookiePass);
			response.addCookie(cookieNombre);

			procesarLogin(request);
			rwd.forward(request, response);
		} else {
			if (user != null) {
				Mensaje mensaje = new Mensaje();
				mensaje.setMsg("Usuario y/o contraseña incorrectos");
				mensaje.setTipo(Mensaje.MSG_TYPE_ERROR);
				session.setAttribute(Constantes.MSG_ERROR, mensaje);
			}
			response.sendRedirect(Constantes.JSP_INDEX);
		}

	}

	private void generarCookies(HttpServletResponse response) {
		cookieNombre = new Cookie("usuario", user.getAlias());
		cookiePass = new Cookie("password", user.getPassword());
	}

	private void procesarLogin(HttpServletRequest request) {
		createSession(request);
		user.setIdSession(session.getId());
		session.setAttribute(Constantes.ATT_USUARIO, user);
		rwd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);

		// request.setAttribute(Constantes.ATT_MENSAJE, mensaje);

	}

	private void cargarDatosCookies() {
		log.trace(alias + " " + password);
		user = new Usuario();
		user.setAlias(alias);
		user.setPassword(password);
	}

	private void cargarParametros(HttpServletRequest request) {
		user = new Usuario();
		user.setAlias(request.getParameter(Constantes.PAR_USUARIO));
		user.setPassword(request.getParameter(Constantes.PAR_PASSWORD));
		Idioma idioma =   Util.parseIdioma(request.getParameter(Constantes.PAR_LOCALE));
		user.setIdioma(idioma);

	}

	private boolean cargarCookies(HttpServletRequest request) {
		boolean cargado = false;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("usuario")) {
					alias = cookie.getValue();
				} else {
					if (cookie.getName().equals("password")) {
						password = cookie.getValue();
					}
				}
			}
			if (!"".equals(alias) && !"".equals(password)) {
				cargado = true;
			}
		}
		return cargado;
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
