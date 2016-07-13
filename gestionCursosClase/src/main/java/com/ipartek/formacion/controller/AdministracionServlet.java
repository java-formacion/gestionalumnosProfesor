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

import com.ipartek.formacion.pojo.Usuario;

/**
 * Servlet implementation class AdministracionServlet
 */
public class AdministracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd = null;
	private int operacion = -1;
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
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter(Constantes.PAR_ID_SESION) != null)
		{
			expulsarUser(request);
		}
		
		usuariosGetAll(request);
		rd.forward(request, response);
	}

	private void expulsarUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String sessionid= request.getParameter(Constantes.PAR_ID_SESION);
		ServletContext contexto = getServletContext();
		Map<String, HttpSession> sessiones = (Map<String, HttpSession>) contexto.getAttribute(Constantes.ATT_SESIONES);
		HttpSession session = sessiones.get(sessionid);
		session.invalidate();
		sessiones.remove(sessionid);
	}

	private void usuariosGetAll(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ServletContext contexto = getServletContext();
		List<Usuario> usuarios = (List<Usuario>) contexto.getAttribute(Constantes.ATT_LIST_USUARIO);
		request.setAttribute(Constantes.ATT_LIST_USUARIO, usuarios);
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_USUARIOS);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
	}
}
