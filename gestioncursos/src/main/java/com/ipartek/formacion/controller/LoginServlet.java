package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.pojo.Mensaje;
import com.ipartek.formacion.pojo.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario dummy=null;
	private Usuario usr=null;
	private RequestDispatcher rwd=null;
	HttpSession session=null;
	
	
	
	
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
		
		
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dummy.setPassword("asdfqwer");
		dummy.setUser("admin");
		usr=new Usuario();
		usr.setUser(request.getParameter(Constantes.PAR_USER));
		usr.setPassword(request.getParameter(Constantes.PAR_PASSWORD));
		usr.setNickname("General");
		if(dummy.getPassword().equals(usr.getPassword())&&dummy.getUser().equals(usr.getPassword())){
			rwd=request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
			
		}else {
			rwd=request.getRequestDispatcher("index.jsp");
			Mensaje msg=new Mensaje();
			msg.setMsg("Usuario y/o contraseña incorrectos");
			msg.setType(Mensaje.MSG_TYPE_DANGER);
			request.setAttribute(Constantes.ATT_MENSAJE, msg);
		}
		rwd.forward(request, response);
	}

	private void createSession(HttpServletRequest request){
		session=request.getSession(true);
		//true --> si la sesion no existe te la crea 
		//false--> Te coge la sesion activa si no existe
		session.setMaxInactiveInterval(60*60*15);
	}
	


}
