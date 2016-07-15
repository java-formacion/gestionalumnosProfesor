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
import com.ipartek.formacion.service.Util;

public class LoginServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;  
	 private RequestDispatcher rwd = null;
	 private HttpSession session = null;
	 private Usuario usuario = null;
	 private String nUsuario = "";
	 private String passWord = "";
	 Cookie cookieAlias = null;
	 Cookie cookiePass = null;
	 private static final Logger log = Logger.getLogger(LoginServlet.class);
    
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doProcess(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
		
	}
	
	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if (cargarCookies(request)){
			cargarDatosCookies();

		}else{
			if(request.getParameter(Constantes.PAR_USUARIO)!=null){
				cargarParametros(request);

			}
		}
		if(usuario!=null && "marta".equals(usuario.getAlias())&&"marta".equals(usuario.getPassword())){
			generarCookies(response);
			
			String[] checkboxes = request.getParameterValues(Constantes.PAR_REMEMBER);
			if(checkboxes!=null && checkboxes.length==1){
				cookieAlias.setMaxAge(60*60*24);
				cookiePass.setMaxAge(24*60*60);
			}else{
				cookieAlias.setMaxAge(0);
				cookiePass.setMaxAge(0);
			}
			response.addCookie(cookieAlias);
			response.addCookie(cookiePass);
			procesarLogin(request);
			session.setAttribute(Constantes.ATT_USUARIO, usuario);
			rwd.forward(request, response);
			
		}else{
			if(usuario!=null){
				createSession(request);
				Mensaje mensaje = new Mensaje();
				mensaje.setMsg("Usuario y/o contraseña incorrectos");
				mensaje.setType(Mensaje.MSG_TYPE_DANGER);
				session.setAttribute(Constantes.ERROR_LOGIN, mensaje);
				System.out.println("hola");
			}
			
			response.sendRedirect(Constantes.JSP_INDEX);
		 
		}
		
	}


	private void procesarLogin(HttpServletRequest request) {
		
		createSession(request);
		usuario.setIdSession(session.getId());
		session.setAttribute(Constantes.ATT_USUARIO, usuario);
		rwd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
		
	}
	
	private void cargarDatosCookies() {
		log.trace(nUsuario+" "+passWord);
		usuario = new Usuario();
		usuario.setAlias(nUsuario);
		usuario.setPassword(passWord);
	}
	

	private void generarCookies(HttpServletResponse response) {
		
		cookieAlias = new Cookie("alias", usuario.getAlias()); //cada atributo, una cookie diferente
		cookiePass = new Cookie("password", usuario.getPassword());
		
	}
	
	private void cargarParametros(HttpServletRequest request) {
		usuario = new Usuario();
		usuario.setAlias(request.getParameter(Constantes.PAR_ALIAS));
		usuario.setPassword(request.getParameter(Constantes.PAR_PASSWORD));
		usuario.setIdioma(Util.parseIdi(request.getParameter(Constantes.PAR_IDIOMA)));
		//session.setAttribute(Constantes.ATT_USUARIO, usuario);
		//	rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
	}
	
	private boolean cargarCookies(HttpServletRequest request) {
		boolean cargado = false;

		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals("alias")){
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


	private void createSession(HttpServletRequest request){
		
		session = request.getSession(true); 
		
		/*
		 * getSession(true)  --> Si la sesion no existe, la crea
		 * getSession(false) --> Te coge la sesion activa, si no existe es null
		 */
		
		session.setMaxInactiveInterval(60*60*15); //la duracion de la sesion, se pone en milisegundos
		
	}
	
	

}
