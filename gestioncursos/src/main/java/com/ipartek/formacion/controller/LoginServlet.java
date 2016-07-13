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
	RequestDispatcher rd = null;
	HttpSession session = null;
	private static Logger log=Logger.getLogger(LoginServlet.class);

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

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			String nombreUser="", password="";
			for(Cookie cook:cookies){
				if(cook.getName().equals("usuario")){
					nombreUser=cook.getValue();
				}else{
					if(cook.getName().equals("password")){
						password=cook.getValue();
					}
				}
				
				if(!"".equals(nombreUser)&&!"".equals(password)){
					createSession(request);
					//TODO crear el objeto usuario
					Usuario usuario;
					usuario=new Usuario();
					
					rd=request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
					rd.forward(request, response);
				}
				log.trace("hay galletas");
			}
		}
		
		Usuario usuario = null;
		String userName = request.getParameter(Constantes.PAR_USERNAME);
		String pass = request.getParameter(Constantes.PAR_PASSWORD);
		String[] checkboxes=request.getParameterValues(Constantes.PAR_REMEMBER);
		
		if("urko".equals(userName)&&"urko".equals(pass)){
			createSession(request);
			usuario = new Usuario();
			usuario.setUserName(userName);
			usuario.setUserPassword(pass);
			usuario.setNickname("Profe");
			usuario.setSessionid(session.getId());
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			if(checkboxes!=null && checkboxes.length==1){
				//crear cookie y guardar datos
				Cookie cookieNombre=new Cookie("usuario", usuario.getUserName());
				Cookie cookiePass=new Cookie("contrasena", usuario.getUserPassword());
			
				cookieNombre.setMaxAge(24*60*60); //guarda la cppkie durante 24h (pasado a milisegundos)
				cookiePass.setMaxAge(60*24*60);
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





