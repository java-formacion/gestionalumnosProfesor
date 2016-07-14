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

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd = null;
	HttpSession session = null;
	private Logger log = Logger.getLogger(LoginServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			String nUsuario = "", password = "";
			for(Cookie cookie: cookies){
				if (cookie.getName().equals("usuario")){
					nUsuario = cookie.getValue();
				}
				else{
					if(cookie.getName().equals("password")){
						password = cookie.getValue();
					}
				}
			}
			if(!"".equals(nUsuario)&&!"".equals(password)){
				createSession(request);
				Usuario usuario = new Usuario();
				usuario.setUserName(nUsuario);
				usuario.setUserPassword(password);
				usuario.setNickname("Profe");
				usuario.setSessionId(session.getId());
				session.setAttribute(Constantes.ATT_USUARIO, usuario);
				rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
				rd.forward(request, response);
			}
			
			log.trace("");
		}
		
		Usuario usuario = null;
		String userName = request.getParameter(Constantes.PAR_USERNAME);
		String pass = request.getParameter(Constantes.PAR_PASSWORD);
		String[] checkboxes = request.getParameterValues(Constantes.PAR_RECUERDAME);
		
		if("urko".equals(userName)&&"urko".equals(pass)){
			createSession(request);
			usuario = new Usuario();
			usuario.setUserName(userName);
			usuario.setUserPassword(pass);
			usuario.setNickname("Profe");
			usuario.setSessionId(session.getId());
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			if(checkboxes != null && checkboxes.length == 1){
				Cookie cookieNombre = new Cookie("usuario", usuario.getUserName());
				cookieNombre.setMaxAge(24*60*60); 
				Cookie cookiePass = new Cookie("password", usuario.getUserPassword());
				cookiePass.setMaxAge(24*60*60); 
				response.addCookie(cookieNombre);
				response.addCookie(cookiePass);
				
			}
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
		session.setMaxInactiveInterval(60*60*15);
	}
}

