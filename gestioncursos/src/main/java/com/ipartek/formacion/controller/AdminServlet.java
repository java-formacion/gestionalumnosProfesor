package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.listener.SessionListener;
import com.ipartek.formacion.pojo.Mensaje;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rwd=null;
	private static final Logger log=Logger.getLogger(AdminServlet.class);
	
	
	
	
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//cargar dispatcher
		
		cargarListUsr(request);
		
		//redirigir a la jsp
		if(request.getParameter(Constantes.PAR_SESSIONID)!=null){
		kickUser(request);
		}
		rwd.forward(request, response);
	}

	private void kickUser(HttpServletRequest request) {
		String sessionId=request.getParameter(Constantes.PAR_SESSIONID);
		ServletContext sC=getServletContext();
		Map<String,HttpSession> sesiones=(HashMap<String,HttpSession>) sC.getAttribute(Constantes.ATT_LISTADO_SESIONES);
		HttpSession session=sesiones.get(sessionId);
		
		if (session!=null) {
			session.invalidate();	
			Mensaje m=new Mensaje();
			m.setMsg("usuario expulsado");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);
			request.setAttribute(Constantes.ATT_MENSAJE, m);
		}else {
			Mensaje m=new Mensaje();
			m.setMsg("La expulsión no se ha podido llevar a cabo, contacte con su administrador");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);
			request.setAttribute(Constantes.ATT_MENSAJE, m);			
			log.info("error al expulsar "+sessionId);
		}
		
		
	}

	private void cargarListUsr(HttpServletRequest request) {
		rwd=request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response){
		
		
		
		
		
		
	}

}
