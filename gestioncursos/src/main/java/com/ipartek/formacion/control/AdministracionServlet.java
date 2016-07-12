package com.ipartek.formacion.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Usuario;
import com.ipartek.formacion.services.AlumnoService;
import com.ipartek.formacion.services.AlumnoServiceImp;
import com.ipartek.formacion.services.CursoService;
import com.ipartek.formacion.services.CursoServiceImp;

/**
 * Servlet implementation class AdministracionServlet
 */
public class AdministracionServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 private int id = -1;
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
		// TODO Auto-generated method stub
		rd = request.getRequestDispatcher(Constantes.JSP_ADMIN);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
