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
import com.ipartek.formacion.pojo.Idioma;
import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;
import com.ipartek.formacion.service.Util;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private int id = -1, operacion = -1;
	 private RequestDispatcher rd = null;
	 private AlumnoService aService = new AlumnoServiceImp();
	 private CursoService cService = new CursoServiceImp();
	 private List<Alumno> alumnos = null;
	 private Alumno alumno = null;   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			recogerId(request);
			request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cService.getAll());
			
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
			if(Util.tryParseInt(op)){
				operacion = Integer.parseInt(op);
			}
			recogerId(request);
			//recogerDatosAlumno(request);
			switch (operacion) {
			case Constantes.OP_CREATE:
				recogerDatosAlumno(request);
				aService.createAlumno(alumno);
				break;
			case Constantes.OP_DELETE:
				aService.delete(id);
				break;
			case Constantes.OP_READ:
				
				break;
			case Constantes.OP_UPDATE:
				recogerDatosAlumno(request);
				aService.update(alumno);
				break;
			default:
				break;
			}
			getAll(request);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			
		} catch(NullPointerException e){
			
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
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		String dni = request.getParameter(Constantes.PAR_DNI);
		String apellidos = request.getParameter(Constantes.PAR_APELLIDOS);
		String[] idiomas = request.getParameterValues(Constantes.PAR_IDIOMA);
		List<Idioma> idi = Util.parseIdioma(idiomas);
		String idCurso = request.getParameter(Constantes.PAR_CURSO);
		String genero = request.getParameter(Constantes.PAR_GENERO);
		Curso curso = new Curso();
		curso.setCodigo(Integer.parseInt(idCurso));
		alumno.setCodigo(id);
		alumno.setNombre(nombre);
		alumno.setApellidos(apellidos);
		alumno.setDni(dni);
		alumno.setIdiomas(idi);
		alumno.setCurso(curso);
		alumno.setGenero(Util.parseGenero(genero));
		
	}

	private void recogerId(HttpServletRequest request) {
		id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		
	}

}
