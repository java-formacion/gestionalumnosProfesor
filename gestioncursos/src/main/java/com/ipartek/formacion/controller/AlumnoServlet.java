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
import com.ipartek.formacion.pojo.exception.CursoException;
import com.ipartek.formacion.pojo.exception.ModuloException;
import com.ipartek.formacion.pojo.exception.AlumnoException;
import com.ipartek.formacion.service.exceptions.AlumnoServiceException;
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
	private int id=-1;
	private RequestDispatcher rwd=null;
	private AlumnoService aService=AlumnoServiceImp.getInstance();
	private List <Alumno> alumnos=null;
	private CursoService cService=CursoServiceImp.getInstance();
	private Alumno alumno=null;
	private int operacion=-1;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo=request.getParameter(Constantes.PAR_CODIGO);
		try {
			recogerId(request);
			request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cService.getAll());
			if (id<0) {//REDIRIGE AL CREATE
				rwd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);
			}else {//REDIRIGE AL UPDATE
				getById(request);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			getAll(request);
		}
		rwd.forward(request, response);
	}


	private void getById(HttpServletRequest request) throws AlumnoException {
		alumno = aService.getById(id);
		request.setAttribute(Constantes.ATT_ALUMNO, alumno);
		rwd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);
	}


	private void getAll(HttpServletRequest request) {
		alumnos = aService.getAll();
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
		rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op=request.getParameter(Constantes.PAR_OPERACION);
		try {
			if (Util.tryParseInt(op)) {
				operacion=Integer.parseInt(op);
			}else{
				
			}
			
			
			recogerId(request);
			switch (operacion) {
			case Constantes.OP_CREATE:
				recogerDatos(request);
				aService.createAlumno(alumno);
				
				break;
			case Constantes.OP_DELETE:
				recogerId(request);
				aService.deleteAlumno(alumno.getCodigo());
				
				break;
			case Constantes.OP_UPDATE:
				recogerDatos(request);
				aService.updateAlumno(alumno);
				
				break;
			default:
				break;
			}
			getAll(request);
		} catch (NumberFormatException e) {


		} catch (NullPointerException e) {


		}catch (CandidatoException e) {
			//Hay un error en los datos que se nos envian
			try {
				//sin adaptar
				AlumnoError aE=new AlumnoError();
				aE=recogerDatosError(request);
				aE.setMensaje(e.getMessage());
				request.setAttribute(Constantes.ATT_ALUMNO, aE);
				rwd=request.getRequestDispatcher(Constantes.JSP_ALUMNO);
				
			} catch (CandidatoException e1) {
				e1.printStackTrace();
			}
			
		}  catch (Exception e) {
			
			rwd.forward(request, response);
		}
		
		//rwd=request.getRequestDispatcher(Constantes.JSP_ALUMNO);
		rwd.forward(request, response);
		
		
		
	}


	private AlumnoError recogerDatosError(HttpServletRequest request) throws CandidatoException {
		AlumnoError aE=new AlumnoError();
		String name=request.getParameter(Constantes.PAR_NOMBRE);
		
		String surname=request.getParameter(Constantes.PAR_APELLIDOS);
		
		String dni=request.getParameter(Constantes.PAR_DNI);
		
		aE.setCodigo(id);
		aE.setNombre(name);
		aE.setApellidos(surname);
		aE.setDni(dni);
		return aE;
	}


	private void recogerId(HttpServletRequest request) {
		
		id=Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
	}


	
	private void recogerDatos(HttpServletRequest request) throws CandidatoException {
		alumno=new Alumno();
		recogerId(request);
		alumno.setCodigo(id);
		
		String name=request.getParameter(Constantes.PAR_NOMBRE);
		alumno.setNombre(name);
		
		String surname=request.getParameter(Constantes.PAR_APELLIDOS);
		alumno.setApellidos(surname);
		
		String dni=request.getParameter(Constantes.PAR_DNI);
		alumno.setDni(dni);
		
		String [] idiomas=request.getParameterValues(Constantes.PAR_IDIOMA);
		List<Idioma>iAux=Util.parseIdioma(idiomas);
		alumno.setIdiomas(iAux);
		
		String genero=request.getParameter(Constantes.PAR_GENERO);
		alumno.setGenero(Util.parseGenero(genero));
		
		String idCurso=request.getParameter(Constantes.PAR_CURSO);
		Curso c=new Curso();
		c.setCodigo(Integer.parseInt(idCurso));
		alumno.setCursoMat(c);
		
		
		
	}

}
