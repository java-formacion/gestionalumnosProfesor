package com.ipartek.formacion.control;

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
import javax.servlet.http.HttpSessionContext;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Mensaje;
import com.ipartek.formacion.pojo.Usuario;
import com.ipartek.formacion.services.AlumnoService;
import com.ipartek.formacion.services.AlumnoServiceImp;
import com.ipartek.formacion.services.CursoService;
import com.ipartek.formacion.services.CursoServiceImp;
import com.ipartek.formacion.services.Util;

/**
 * Servlet implementation class AdministracionServlet
 */
public class AdministracionServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 private int id = -1;
	 private int operacion = -1;
	 private List<Usuario> usuarios = null;
	 private final static Logger log = Logger.getLogger(AdministracionServlet.class);
	 private RequestDispatcher rd = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter(Constantes.PAR_SESIONID)!=null){
			expulsarUsuario(request);
		}
		
		
		cargarListausuarios(request);
		
		rd.forward(request, response);
	}

	private void expulsarUsuario(HttpServletRequest request) {
		String sesionId=null;
		sesionId= request.getParameter(Constantes.PAR_SESIONID);
		ServletContext context =getServletContext();
		Map<String,HttpSession> sesiones = (Map<String,HttpSession>) context.getAttribute(Constantes.ATT_LISTA_SESIONES);
		HttpSession sesion = sesiones.get(sesionId);
		Mensaje m = new Mensaje();
		if(sesion!=null){
			sesion.invalidate();
			sesiones.remove(sesionId);
			context.setAttribute(Constantes.ATT_LISTA_SESIONES, sesiones);
			m.setMsg("Usuario expulsado");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);		
		} else{		
			m.setMsg("La operación no se ha realizado con exito, contacte con el administrador");
			m.setType(Mensaje.MSG_TYPE_ERROR);
			log.info("Error al expulsar la sessionID"+sesionId);
		}
		request.setAttribute(Constantes.ATT_MENSAJE, m);
	}

	private void cargarListausuarios(HttpServletRequest request) {
		rd = request.getRequestDispatcher(Constantes.JSP_ADMIN);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
