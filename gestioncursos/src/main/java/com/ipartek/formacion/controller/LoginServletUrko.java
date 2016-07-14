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

public class LoginServletUrko {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd = null;
	HttpSession session = null;
	
	private String nUsuario = "";
	private String passWord = "";
	private String nickName = "";
	
	private Usuario user = null;
	
	Cookie cookieUserName = null;
	Cookie cookiePassword = null;
	Cookie cookieNickName = null;
	
	private static final Logger log = Logger.getLogger(LoginServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private boolean cargarCookies(HttpServletRequest request) {
		boolean cargado = false;

		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(Constantes.COOKIE_USERNAME)){
					nUsuario = cookie.getValue();
				}else{
					if(cookie.getName().equals(Constantes.COOKIE_PASSWORD)){
						passWord = cookie.getValue();
					} else{
						if(cookie.getName().equals(Constantes.COOKIE_NICKNAME)){
							nickName = cookie.getValue();
						}
					}
				}
			}
			
			if(!"".equals(nUsuario)&&!"".equals(passWord)){
				cargado = true;
			}
		}
		return cargado;
	}
	
	private void cargarDatosCookies() {
		log.trace(nUsuario + " " + passWord);
		user = new Usuario();
		user.setUserName(nUsuario);
		user.setUserPassword(passWord);
		user.setNickname(nickName);
	}
	
	private void cargarParametros(HttpServletRequest request) {
		user = new Usuario();
		user.setUserName(request.getParameter(Constantes.PAR_USERNAME));
		user.setUserPassword(request.getParameter(Constantes.PAR_PASSWORD));
		user.setNickname("Alumno");
	}
	
	private void generarCookies(HttpServletResponse response) {
		// En la cookie se guardara el nombre, contraseña y nickname
		cookieUserName = new Cookie(Constantes.COOKIE_USERNAME, user.getUserName());
		cookiePassword = new Cookie(Constantes.COOKIE_PASSWORD, user.getUserPassword());
		cookieNickName = new Cookie(Constantes.COOKIE_NICKNAME, user.getNickname());

		response.addCookie(cookieUserName);
		response.addCookie(cookiePassword);
		response.addCookie(cookieNickName);
	}

	private void procesarLogin(HttpServletRequest request) {
		createSession(request);
		rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (cargarCookies(request)){
			cargarDatosCookies();
		}else{
			if(request.getParameter(Constantes.PAR_USERNAME)!=null){
				cargarParametros(request);
			} else{
				response.sendRedirect(Constantes.JSP_INDEX);
			}
		}
		
		String username = request.getParameter(Constantes.PAR_USERNAME);		
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		
		String user = "Tamasco";
		String pass = "123456";
		
		String user2 = "admin";
		String pass2 = "654321";
		
		if(this.user!=null && user.equals(username) && pass.equals(password) || user2.equals(username) && pass2.equals(password)){
			generarCookies(response);
			
			String[] checkboxes = request.getParameterValues(Constantes.PAR_REMEMBER);
			if(checkboxes!=null && checkboxes.length==1){
				cookieUserName.setMaxAge(24*60*60);
				cookiePassword.setMaxAge(24*60*60);
				cookieNickName.setMaxAge(24*60*60);
			} else{
				cookieUserName.setMaxAge(0);
				cookiePassword.setMaxAge(0);
				cookieNickName.setMaxAge(0);
			}
			
			procesarLogin(request);
			rd.forward(request, response);
		} else{
			if(user!=null){
				createSession(request);
				Mensaje mensaje = new Mensaje();
				mensaje.setMsg("Usuario y/o contrase&ntilde;a incorrectos");
				mensaje.setType(Mensaje.MSG_TYPE_DANGER);
				
				session.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			}
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
