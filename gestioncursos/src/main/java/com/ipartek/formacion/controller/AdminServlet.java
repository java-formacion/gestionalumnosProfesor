package com.ipartek.formacion.controller;

//Esto solo sirve como seguridad

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
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AdminServlet.class); 
	private RequestDispatcher rd = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter(Constantes.PAR_IDSESSION) != null){
			expulsarUsuario(request);
		}
		cargarUsuario(request, response);
	}

	

	private void expulsarUsuario(HttpServletRequest request) {
		String idSesion = request.getParameter(Constantes.PAR_IDSESSION);
		ServletContext context = getServletContext();
		Map<String, HttpSession> sesiones = (Map<String, HttpSession>) context.getAttribute(Constantes.ATT_SESIONES);
		HttpSession session = sesiones.get(idSesion);
		if(session != null){
			session.invalidate();
			sesiones.remove(idSesion);
			context.setAttribute(Constantes.ATT_SESIONES, sesiones);
			Mensaje m = new Mensaje();
			m.setMsg("Se ha expulsado al usuario con existo");
			m.setType(Mensaje.MSG_TYPE_SUCCESS);
			request.setAttribute(Constantes.ATT_MENSAJE,m);
		}else{
			Mensaje m = new Mensaje();
			m.setMsg("Ha habido un error al expulsar al usuario");
			m.setType(Mensaje.MSG_TYPE_INFO);
			request.setAttribute(Constantes.ATT_MENSAJE, m);
		}
		
		
	}

	private void cargarUsuario(HttpServletRequest request,
			HttpServletResponse response) {
		rd = request.getRequestDispatcher(Constantes.JSP_ADMIN);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			log.info("No ha podido acceder al servlet");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter(Constantes.PAR_IDSESSION) != null){
			expulsarUsuario(request);
		}
		cargarUsuario(request, response);

	}

}
