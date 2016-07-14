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

import com.ipartek.formacion.pojo.Mensaje;
import com.ipartek.formacion.pojo.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario dummy = null;
	private Usuario usr = null;
	private RequestDispatcher rwd = null;
	HttpSession session = null;
	private String nUsuario = "";
	private String passWord = "";
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	Cookie cookieNombre=null;
	Cookie cookiePass=null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);

	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (cargarCookies(request)) {
			cargarDatosCookies();
		}else{
			if (request.getParameter(Constantes.PAR_USER)!=null) {
				cargarParametros(request);
			}
		}
		
		dummy = new Usuario();
		dummy.setPassword("asdfqwer");
		dummy.setUser("admin");
		nUsuario = request.getParameter(Constantes.PAR_USER);
		passWord = request.getParameter(Constantes.PAR_PASSWORD);
		
		usr = new Usuario();
		usr.setUser(nUsuario);
		usr.setPassword(passWord);
		usr.setNickname("General");
		// usr.setSessionId(session.getId());
		String[] checkboxes = request.getParameterValues(Constantes.PAR_REMEMBER);

		
		
		
		
		
		
		if (dummy.getPassword().equals(usr.getPassword()) && dummy.getUser().equals(usr.getUser())) {
			
			createSession(request);
			session.setAttribute(Constantes.ATT_USUARIO, usr);
			rwd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
			rwd.forward(request, response);

		} else {
			createSession(request);

			Mensaje msg = new Mensaje();
			msg.setMsg("Usuario y/o contraseña incorrectos");
			msg.setType(Mensaje.MSG_TYPE_DANGER);
			session.setAttribute(Constantes.ATT_MENSAJE, msg);
			response.sendRedirect(Constantes.JSP_INDEX);
		}
		if (checkboxes != null && checkboxes.length == 1) {
			
		}

	}
	private void generarCookies(HttpServletResponse response) {
		Cookie cookieNombre = new Cookie("usuario",usr.getUser());
		Cookie cookiePass = new Cookie("password",usr.getPassword());

		cookieNombre.setMaxAge(24*60*60);
		cookiePass.setMaxAge(60*24*60);
		response.addCookie(cookiePass);
		response.addCookie(cookieNombre);
	}
	private void procesarLogin(HttpServletRequest request) {
		createSession(request);
		rwd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);

		//request.setAttribute(Constantes.ATT_MENSAJE, mensaje);



	}

	private void cargarDatosCookies() {
		
		log.trace(nUsuario+" "+passWord);
		Usuario user = new Usuario();
		user.setUser(nUsuario);
		user.setPassword(passWord);
		user.setNickname("Profe");
	}

	private void cargarParametros(HttpServletRequest request) {
		Usuario user = new Usuario();
		user.setUser(request.getParameter(Constantes.PAR_USER));
		user.setPassword(request.getParameter(Constantes.PAR_PASSWORD));
		user.setNickname("Profe");
		//session.setAttribute(Constantes.ATT_USUARIO, usuario);
		//rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
	}

	private boolean cargarCookies(HttpServletRequest request) {
		boolean cargado = false;

		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals("usuario")){
					nUsuario = cookie.getValue();
				}else{
					if(cookie.getName().equals("password")){
						passWord = cookie.getValue();
					}
				}
			}
			if(!"".equals(nUsuario)&&!"".equals(passWord)){
				cargado = true;
			}
		}
		return cargado;
	}

	private void createSession(HttpServletRequest request) {
		session = request.getSession(true);
		// true --> si la sesion no existe te la crea
		// false--> Te coge la sesion activa si no existe
		session.setMaxInactiveInterval(60 * 60 * 15);
	}

}
