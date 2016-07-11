package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.pojo.Usuario;

public class LoginServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;  
	 private RequestDispatcher rwd = null;
	 private HttpSession session = null;
    
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = null;
		
		String usu = request.getParameter(Constantes.PAR_ALIAS); //recojo el parametro usuario
		String pass = request.getParameter(Constantes.PAR_PASSWORD); //recojo el parametro contraseña
		
		if("marta".equals(usu) && ("marta".equals(pass))){
			usuario = new Usuario();
			usuario.setAlias(usu);
			usuario.setPassword(pass);
			
			createSession(request); //creamos la sesion
			session.setAttribute("usuario", usuario); //guardo el objeto usuario
			
			rwd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
			rwd.forward(request,response);
		}else{
			
			createSession(request); //creamos la sesion
			
			String mensaje="Datos incorrectos";
			session.setAttribute(Constantes.ERROR_LOGIN, mensaje);
			//rwd = request.getRequestDispatcher(Constantes.JSP_INDEX);
			response.sendRedirect(Constantes.JSP_INDEX);
		}
		
		
	}
	
	private void createSession(HttpServletRequest request){
		
		session = request.getSession(true); 
		
		/*
		 * getSession(true)  --> Si la sesion no existe, la crea
		 * getSession(false) --> Te coge la sesion activa, si no existe la crea
		 */
		
		session.setMaxInactiveInterval(60*60*15); //la duracion de la sesion, se pone en milisegundos
		
	}

}
