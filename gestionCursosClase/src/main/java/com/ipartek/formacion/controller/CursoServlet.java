package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CursoService cService = new CursoServiceImp();
	private List<Curso> cursos= null;
	private Curso curso = null;
	private RequestDispatcher rwd = null;
	private int id = -1;
	private int operacion = -1;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {		
			recogerId(request);
			if(id<0)
			{
				rwd = request.getRequestDispatcher(Constantes.JSP_CURSO);
			}
			else
			{
				getById(request);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			getAll(request);
		}
		rwd.forward(request, response);
	}

	private void getById(HttpServletRequest request) {
		curso = cService.getById(id);
		request.setAttribute(Constantes.ATT_CURSO, curso);
		rwd = request.getRequestDispatcher(Constantes.JSP_CURSO);
	}

	private void getAll(HttpServletRequest request) {
		cursos = cService.getAll();
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
		rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter(Constantes.PAR_OPERACION);
		try{
			
			operacion = Integer.parseInt(op);
			recogerId(request);
			switch(operacion){
				case Constantes.OP_CREATE:
					recogerDatos(request);
					cService.create(curso);
					break;
				case Constantes.OP_DELETE:
System.out.println(id);					
					cService.delete(id);
					break;
				case Constantes.OP_UPDATE:
					recogerDatos(request);
					cService.update(curso);
					break;
			}
		} catch (NumberFormatException e){
			//TODO alguien nos toquetea los argumentos del form
		}
		
		getAll(request);
		rwd.forward(request, response);
	}

	private void recogerDatos(HttpServletRequest request) {
		curso = new Curso();
		recogerId(request);
		curso.setCodigo(id);
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		curso.setNombre(nombre);
	}
	
	private void recogerId(HttpServletRequest request) {
		id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		
	}

}
