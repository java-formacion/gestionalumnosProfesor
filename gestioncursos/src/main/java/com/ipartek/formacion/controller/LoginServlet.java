package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
	RequestDispatcher rd = null;
	HttpSession session = null;
	
	private static final Logger log = Logger.getLogger(LoginServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null){
			String nUsuario="", passWord="", nickName="";
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(Constantes.COOKIE_USERNAME)){
					nUsuario = cookie.getValue();
				} else{
					if(cookie.getName().equals(Constantes.COOKIE_PASSWORD)){
						passWord = cookie.getValue();
					} else{
						if(cookie.getName().equals(Constantes.COOKIE_NICKNAME)){
							nickName = cookie.getValue();
						}
					}
				}	
			}
			
			if(!"".equals(nUsuario) && !"".equals(passWord) && !"".equals(nickName)){
				createSession(request);
			}
		}
		*/		
		Usuario usuario = null;
		
		String username = request.getParameter(Constantes.PAR_USERNAME);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		String[] checkboxes = request.getParameterValues(Constantes.PAR_REMEMBER);
		
		Idioma idioma = null;
		try{
			String codIdioma = request.getParameter(Constantes.PAR_LANGUAGE);
			log.trace(codIdioma);
			idioma = Util.parseIdioma(codIdioma);
		} catch(Exception e){
			log.error(e.getMessage());
		}
		
		
		String user = "Tamasco";
		String pass = "123456";
		
		String user2 = "admin";
		String pass2 = "654321";
		
		String user3= "Borja";
		String pass3 = "1990";
		
		if(username!=null && password!=null){
			if(user.equals(username) && pass.equals(password) || 
					user2.equals(username) && pass2.equals(password) || 
					user3.equals(username) && pass3.equals(password)){
				createSession(request);
				
				usuario = new Usuario();
				usuario.setUserName(username);
				usuario.setUserPassword(password);
				usuario.setNickname("Alumno");
				usuario.setSessionid(session.getId());
				
				usuario.setIdioma(idioma);
				
				session.setAttribute(Constantes.ATT_USUARIO, usuario);
				
				if(checkboxes != null && checkboxes.length==1){
					// En la cookie se guardara el nombre, contraseña y nickname
					Cookie cookieUserName = new Cookie(Constantes.COOKIE_USERNAME, usuario.getUserName());
					Cookie cookiePassword = new Cookie(Constantes.COOKIE_PASSWORD, usuario.getUserPassword());
					Cookie cookieNickName = new Cookie(Constantes.COOKIE_NICKNAME, usuario.getNickname());
					
					cookieUserName.setMaxAge(24*60*60);
					cookiePassword.setMaxAge(24*60*60);
					cookieNickName.setMaxAge(24*60*60);
					
					response.addCookie(cookieUserName);
					response.addCookie(cookiePassword);
					response.addCookie(cookieNickName);
				}
				
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
		} else{
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