package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.controller.exception.AlumnoError;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private int id = -1, operacion = -1;
	 private RequestDispatcher rd = null;
	 private AlumnoService aService = new AlumnoServiceImp();
	 private List<Alumno> alumnos = null;
	 private Alumno alumno = null;   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			recogerId(request);
			if(id<0)
			{//redireccion a un create
				rd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);
			}
			else
			{//Redireccion a una update
				getById(request);
			}
			
		}catch(Exception e){
			getAll(request);
		}
		rd.forward(request, response); 
	}

	private void getAll(HttpServletRequest request) {
		alumnos = aService.getAll();
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
	}

	private void getById(HttpServletRequest request) {
		alumno = aService.getById(id);
		request.setAttribute(Constantes.ATT_ALUMNO, alumno);
		rd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter(Constantes.PAR_OPERACION);
		recogerId(request);
		try {
			operacion = Integer.parseInt(op);
			recogerDatosAlumno(request);
			switch (operacion) {
			case Constantes.OP_CREATE:
				aService.createAlumno(alumno);
				break;
			case Constantes.OP_DELETE:
				aService.delete(id);
				break;
			case Constantes.OP_READ:
				
				break;
			case Constantes.OP_UPDATE:
				aService.update(alumno);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		} catch (CandidatoException e){
		
			AlumnoError aError;
			try {
				aError = new AlumnoError();
				aError = recogerDatosError(request);
				aError.setMensaje(e.getMessage());
				request.setAttribute(Constantes.ATT_ALUMNO, aError);
				rd=request.getRequestDispatcher(Constantes.JSP_ALUMNO);
			} catch (CandidatoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (Exception e){
			getAll(request);
			
		}
		rd.forward(request, response);
	}
	
	private AlumnoError recogerDatosError(HttpServletRequest request) throws CandidatoException {
		// TODO Auto-generated method stub
		AlumnoError aError = new AlumnoError();
		aError.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
		aError.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
		aError.setDni(request.getParameter(Constantes.PAR_DNI));
		aError.setCodigo(id);
		return aError;
	}

	
	
	private void recogerDatosAlumno(HttpServletRequest request) throws CandidatoException {
		// TODO Auto-generated method stub
		alumno = new Alumno();
		alumno.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
		alumno.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
		alumno.setDni(request.getParameter(Constantes.PAR_DNI));
		alumno.setCodigo(id);
	}

	private void recogerId(HttpServletRequest request) {
		id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		
	}

}
