package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;
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
import com.ipartek.formacion.pojo.Usuario;

/**
 * Servlet implementation class AdministracionServlet
 */
public class AdministracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher rwd = null;
	
	private int id = -1;
	private List<Usuario> usuarios = null;
	private Usuario usuario = null;
	
	private static final Logger log = Logger.getLogger(AlumnoServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministracionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter(Constantes.PAR_SESSIONID)!=null){
			expulsarUsuario(request);
		}

		cargarListadoUsuarios(request);
		rwd.forward(request, response);
	}
	
	private void expulsarUsuario(HttpServletRequest request) {
		String sessionID = request.getParameter(Constantes.PAR_SESSIONID);
		ServletContext context = getServletContext();
		Map<String, HttpSession> sesiones = (Map<String, HttpSession>) context.getAttribute(Constantes.ATT_LISTADO_SESIONES);
		HttpSession session = sesiones.get(sessionID);
		
		Mensaje m = new Mensaje();
		
		if(session!=null){
			session.invalidate();
			sesiones.remove(sessionID);
			context.setAttribute(Constantes.ATT_LIST_USUARIOS, sesiones);

			m.setMsg("Usuario Expulsado");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);
		} else{
			m.setMsg("La operacion no ha podido completarse. Contacte con el administrador del sistema.");
			m.setType(Mensaje.MSG_TYPE_DANGER);

			log.info("Error al expulsar usuario. SessionID: " + sessionID);
		}
		
		request.setAttribute(Constantes.ATT_MENSAJE, m);
	}

	private void cargarListadoUsuarios(HttpServletRequest request) {
		rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
