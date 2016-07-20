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
	private RequestDispatcher rd = null;
	private HttpSession session = null;
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
	private Usuario user = null;
	private String nUsuario = "";
	private String passWord = "";
	Cookie cookieNombre = null;
	Cookie cookiePass = null;
	Cookie cookieIdioma = null;

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
		if (cargarCookies(request)) {
			cargarDatosCookies();

		} else {
			if (request.getParameter(Constantes.PAR_USERNAME) != null) {
				cargarParametros(request);

			}
		}
		if (user != null && "urko".equals(user.getUserName())
				&& "urko".equals(user.getUserPassword())) {
			generarCookies(response);

			String[] checkboxes = request
					.getParameterValues(Constantes.PAR_REMEMBER);
			if (checkboxes != null && checkboxes.length == 1) {
				cookieNombre.setMaxAge(60 * 60 * 24);
				cookiePass.setMaxAge(24 * 60 * 60);
			} else {
				cookieNombre.setMaxAge(0);
				cookiePass.setMaxAge(0);
			}
			response.addCookie(cookieNombre);
			response.addCookie(cookiePass);
			procesarLogin(request);
			session.setAttribute(Constantes.ATT_USUARIO, user);
			rd.forward(request, response);
		} else {
			if (user != null) {
				createSession(request);
				Mensaje mensaje = new Mensaje();
				mensaje.setMsg("Usuario y/o contraseña incorrectos");
				mensaje.setType(Mensaje.MSG_TYPE_DANGER);
				session.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			}
			response.sendRedirect(Constantes.JSP_HOME);
		}

	}

	private void generarCookies(HttpServletResponse response) {
		cookieNombre = new Cookie("c_usuario", user.getUserName());
		cookiePass = new Cookie("c_password", user.getUserPassword());
	}

	private void procesarLogin(HttpServletRequest request) {
		createSession(request);
		rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);

		// request.setAttribute(Constantes.ATT_MENSAJE, mensaje);

	}

	private void cargarDatosCookies() {
		LOG.trace(nUsuario + " " + passWord);
		user = new Usuario();
		user.setUserName(nUsuario);
		user.setUserPassword(passWord);
		user.setNickname("Profe");
	}

	private void cargarParametros(HttpServletRequest request) {
		user = new Usuario();
		user.setUserName(request.getParameter(Constantes.PAR_USERNAME));
		user.setUserPassword(request.getParameter(Constantes.PAR_PASSWORD));
		user.setNickname("Profe");
		String codIdioma = request.getParameter(Constantes.PAR_IDIOMA);
		Idioma idioma = Util.parseIdioma(codIdioma);
		user.setIdioma(idioma);
		// session.setAttribute(Constantes.ATT_USUARIO, usuario);
		// rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
	}

	private boolean cargarCookies(HttpServletRequest request) {
		boolean cargado = false;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {

				if (cookie.getName().equals("usuario")) {
					nUsuario = cookie.getValue();
				} else {
					if (cookie.getName().equals("password")) {
						passWord = cookie.getValue();
					}
				}
			}
			if (!"".equals(nUsuario) && !"".equals(passWord)) {
				cargado = true;
			}
		}
		return cargado;
	}

	private void createSession(HttpServletRequest request) {
		int duracion = 60 * 60 * 15;
		session = request.getSession(true);
		/*
		 * getSession(true) ---> Si la session no existe te la crea
		 * getSession(false) --> Te coge la session activa si no existe es null
		 */
		session.setMaxInactiveInterval(duracion);
	}
}
