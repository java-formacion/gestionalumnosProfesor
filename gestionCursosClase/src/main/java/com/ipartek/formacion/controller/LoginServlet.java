package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			String username="", password="";
			for(Cookie cookie: cookies)
			{
				if(cookie.getName().equals("cookieUsername"))
				{
					username = cookie.getValue();
				}
				else
				{
					if(cookie.getName().equals("cookiePassword"))
					{
						password = cookie.getValue();
					}
				}
			}
			if(!"".equals(username) && !"".equals(password))
			{
				createSession(request);
				//TODO falta redireccionar
			}
		}
		
		Usuario usuario = null;
		String userName = request.getParameter(Constantes.PAR_USERNAME);
		String pass = request.getParameter(Constantes.PAR_PASSWORD);
		//checboxes van siempre en array sea 1 o mas
		String[] checkboxes = request.getParameterValues(Constantes.PAR_REMEMBER);
		
		if("julen".equals(userName)&&"julen".equals(pass)){
			createSession(request);
			usuario = new Usuario();
			usuario.setUserName(userName);
			usuario.setUserPassword(pass);
			usuario.setNickname("JRC");
			String codidioma = request.getParameter(Constantes.PAR_IDIOMA);
			Idioma idioma = Util.parseIdioma(codidioma);
			usuario.setIdioma(idioma);
			usuario.setSessionID(session.getId());
			createSession(request);
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			if(checkboxes != null && checkboxes.length==1)
			{
				Cookie cookieUsername = new Cookie(Constantes.ATT_USUARIO, usuario.getUserName());
				Cookie cookiePassword = new Cookie(Constantes.ATT_PASSWORD, usuario.getUserPassword());
				cookieUsername.setMaxAge(24*60*60);
				cookiePassword.setMaxAge(24*60*60);
				
				response.addCookie(cookiePassword);
				response.addCookie(cookieUsername);
			}
			rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
			rd.forward(request, response);
		}
		else
		{
			if("user2".equals(userName)&&"user2".equals(pass))
			{
				createSession(request);
				usuario = new Usuario();
				usuario.setUserName(userName);
				usuario.setUserPassword(pass);
				usuario.setNickname("usr");
				usuario.setSessionID(session.getId());
				createSession(request);
				session.setAttribute(Constantes.ATT_USUARIO, usuario);
				rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
				rd.forward(request, response);
			}
			else
			{
				createSession(request);
				
				Mensaje mensaje = new Mensaje();
				mensaje.setMsg("Usuario y/o contraseña incorrectos");
				mensaje.setType(Mensaje.MSG_TYPE_DANGER);
				//request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
				session.setAttribute(Constantes.ATT_MENSAJE, mensaje);
				response.sendRedirect("index.jsp");
			}
			
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
