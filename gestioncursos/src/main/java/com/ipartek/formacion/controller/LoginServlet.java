package com.ipartek.formacion.controller;

import java.awt.Checkbox;
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
	RequestDispatcher rd = null;
	HttpSession session = null;
	private static final Logger log = Logger.getLogger(LoginServlet.class);

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
		if(cookies != null)
		{
			
			String nUsuario="", passWord="";
			for(Cookie cookie: cookies){
				if (cookie.getName().equals(Constantes.COOKIE_USERNAME)){
					nUsuario = cookie.getValue();
				}
				else
				{
					if (cookie.getName().equals(Constantes.COOKIE_PASSWORD)){
						passWord = cookie.getValue();
					}
					else
					{
						if (cookie.getName().equals(Constantes.COOKIE_NICKNAME)){
								passWord = cookie.getValue();
						}
					}
				}
			}
			if(!"".equals(nUsuario)&&!"".equals(passWord)){
				createSession(request);
			rd = request.getRequestDispatcher(Constantes.JSP_INDEX);
			rd.forward(request, response);

			}
			log.trace("Hay galletas");
		}
		Usuario usuario = null;
		String userName = request.getParameter(Constantes.PAR_USERNAME);
		String pass = request.getParameter(Constantes.PAR_PASSWORD);
		String[] checkboxes = request.getParameterValues(Constantes.PAR_REMEMBER);
		
			if(Constantes.LOGIN_NAME.equals(userName)&&Constantes.LOGIN_PASS.equals(pass)){
			createSession(request);
			usuario = new Usuario();
			usuario.setUserName(userName);
			usuario.setUserPassword(pass);
			usuario.setNickname(Constantes.LOGIN_NICK);
			usuario.setSessionid(session.getId());
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			if(checkboxes!=null && checkboxes.length==1){
				
				Cookie cookieNombre = new Cookie(Constantes.COOKIE_USERNAME, usuario.getUserName());
				Cookie cookiePass = new Cookie(Constantes.COOKIE_PASSWORD, usuario.getUserPassword());
				Cookie cookieNick = new Cookie(Constantes.COOKIE_NICKNAME, usuario.getNickname());
				
				cookieNombre.setMaxAge(3600*24);
				cookiePass.setMaxAge(60*60*24);
				response.addCookie(cookieNombre);
				response.addCookie(cookiePass);
			}
			rd = request.getRequestDispatcher(Constantes.JSP_INDEX);
			rd.forward(request, response);
		}else{
			createSession(request);
			//	rd = request.getRequestDispatcher(Constantes.JSP_INDEX);
			Mensaje mensaje = new Mensaje();
			mensaje.setMsg("Usuario y/o contraseña incorrectos");
			mensaje.setType(Mensaje.MSG_TYPE_DANGER);
			//request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			session.setAttribute(Constantes.ATT_MENSAJE, mensaje);
			response.sendRedirect(Constantes.JSP_INDEX);
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