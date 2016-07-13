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
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd = null;
	private static final Logger log = Logger.getLogger(AdminServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter(Constantes.PAR_SESSIONID)!=null){
			expulsarUsuario(request);
		}

		cargarListadoUsuarios(request);

		rd.forward(request, response);
	}



	private void expulsarUsuario(HttpServletRequest request) {
		String sesionId = request.getParameter(Constantes.PAR_SESSIONID);
		ServletContext context = getServletContext();
		Map<String,HttpSession> sesiones =(Map<String, HttpSession>) context.getAttribute(Constantes.ATT_LISTADO_SESIONES);
		HttpSession session = sesiones.get(sesionId);
		Mensaje m = new Mensaje();
		if(session !=null){
			session.invalidate();
			sesiones.remove(sesionId);
			context.setAttribute(Constantes.ATT_LISTADO_SESIONES, sesiones);
			m.setMsg("Usuario Expulsado");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);
		}else{
			m.setMsg("La operación no ha podido ser realizada con éxito contacte con el administrador del sistema");
			m.setType(Mensaje.MSG_TYPE_DANGER);
			log.info("error al expulsar "+sesionId);
		}
		request.setAttribute(Constantes.ATT_MENSAJE, m );
	}



	private void cargarListadoUsuarios(HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
