package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Mensaje;

/**
 * Servlet implementation class AdministracionServlet
 */
public class AdministracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RequestDispatcher rd;
	private static final Logger log = Logger.getLogger(AdministracionServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter(Constantes.PAR_SESSIONID)!=null){
			expulsarUsuario(request);
		}
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
		rd.forward(request, response);
	}
	private void expulsarUsuario(HttpServletRequest request) {
		String sesionId = request.getParameter(Constantes.PAR_SESSIONID);
	
		ServletContext context = getServletContext();
		Map<String,HttpSession> sesiones =(Map<String, HttpSession>) context.getAttribute(Constantes.ATT_LISTA_SESIONES);
		HttpSession session = sesiones.get(sesionId);
		Mensaje m = new Mensaje();
		if(session !=null){
			session.invalidate();
			sesiones.remove(sesionId);
			context.setAttribute(Constantes.ATT_LISTA_SESIONES, sesiones);
			m.setMsg("Usuario Expulsado");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);
		}else{
			m.setMsg("La operación no ha podido ser realizada con éxito contacte con el administrador del sistema");
			m.setType(Mensaje.MSG_TYPE_DANGER);
			log.info("error al expulsar "+sesionId);
		}
		request.setAttribute(Constantes.ATT_MENSAJE, m );
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
