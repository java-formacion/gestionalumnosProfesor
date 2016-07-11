package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.controller.exception.AlumnoError;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;
import com.ipartek.formacion.service.Idiomas;
import com.ipartek.formacion.service.Util;

/**
 * Servlet implementation class AlumnosServlet
 */
public class AlumnosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static AlumnoService aService = new AlumnoServiceImp().getInstance();  
    private static CursoService cService = new CursoServiceImp().getInstance();
    private List<Alumno> alumnos = null;
    private Alumno alumno = null;
    private RequestDispatcher rwd = null;
    private int id = -1;   
    private int operacion = -1;
   
  //si recibo un paremetro hago getbyid,sino getall
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		
  		try{
  			recogerId(request); //recibe como paremetro el id, porque en listado.jsp le hemos llamado id.Getparameter recibe siempre un string, por eso hay que pasarlo a integer
  			request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cService.getAll()); //en el jsp alumno necesito la lista de cursos para mostrarlos en el select option, la forma de obtenerlo es desde aqui, desde el Servlet(nunca desde AlumnoServiceImp)
  			if(id<0){ //REDIRIGIMOS PARA UN CREATE
  				rwd = request.getRequestDispatcher(Constantes.JSP_ALUMNO); //a jsp_alumno no le paso el alumno, xq en jsp_alumno si no recibe alumno lo que hace es create
  			}else{ //REDIRIGIMOS PARA UNA UPDATE
  				getById(request); //si recibe un parametro (un id)hace getById
  			}
  		}
  		catch (Exception e){
  			getAll(request);//si lo anterior falla xq no recibe parametro (un id), entra aqui y hace getAll
  		}
  		
  		rwd.forward(request,response);
  		
  	}


  	private void getById(HttpServletRequest request) {
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
  			//Procesaremos el DELETE , UPDATE Y CREATE
  			//1º recoger datos del objeto curso
  			String op = request.getParameter(Constantes.PAR_OPERACION);
  			
  				//System.out.println(op);
  			try{
  				if(Util.tryParseInt(op)){
  					operacion = Integer.parseInt(op);
  				}
  				
  				switch (operacion) {
  				
					case Constantes.OP_CREATE:
							recogerDatos(request);
							aService.createAlumno(alumno);
						break;
						
					case Constantes.OP_UPDATE:
							recogerDatos(request);
							aService.update(alumno);
						break;
						
					case Constantes.OP_DELETE:
							recogerId(request);
							aService.deleteAlumno(id);
					break;
		
					default:
						break;
				}
  				getAll(request);
  					
  			}catch(NumberFormatException e){
  				
  			}catch(NullPointerException e){
  				
  			}catch(CandidatoException e){	
  				//Hay un error en los datos que se nos envian
  				try {
					AlumnoError alumnoError = new AlumnoError();
					alumnoError = recogerDatosError(request);
					alumnoError.setMensaje(e.getMessage());
					request.setAttribute(Constantes.ATT_ALUMNO, alumnoError);
					rwd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);
				} catch (CandidatoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}catch(Exception e){
  				
  				
  			}
  		
  			rwd.forward(request, response);
  				
  	}
  	
  	private void recogerId(HttpServletRequest request) {
		
		//if(Util.tryParseInt(request.getParameter(Constantes.PAR_CODIGO))){
			id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		//}
	}


	private void recogerDatos(HttpServletRequest request) throws CandidatoException {
		alumno = new Alumno();
		
		recogerId(request);
		
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		String apellidos = request.getParameter(Constantes.PAR_APELLIDOS);
		String dni = request.getParameter(Constantes.PAR_DNI);
		String[] idiomas = request.getParameterValues(Constantes.PAR_IDIOMA);
		List<Idiomas> idi = Util.parseIdioma(idiomas);
		String idCurso = request.getParameter(Constantes.PAR_CURSO);
		Curso curso = new Curso();
		curso.setCodigo(Integer.parseInt(idCurso));
		String genero = request.getParameter(Constantes.PAR_GENERO);
		
		
		
		alumno.setCodigo(id);
		alumno.setNombre(nombre);
		alumno.setApellidos(apellidos);
		alumno.setDni(dni);
		alumno.setIdiomas(idi);
		alumno.setCurso(curso);
		alumno.setGenero(Util.parseGenero(genero));
		
	}
	
	private AlumnoError recogerDatosError(HttpServletRequest request) throws CandidatoException {
		AlumnoError alError= new AlumnoError();
		
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		String apellidos = request.getParameter(Constantes.PAR_APELLIDOS);
		String dni = request.getParameter(Constantes.PAR_DNI);
		alError.setNombre(nombre);
		alError.setApellidos(apellidos);
		alError.setDni(dni);
		
		return alError;
		
	}
	
	

  	
  	

  }
