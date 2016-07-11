package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.TipoCurso;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;
import com.ipartek.formacion.service.ModuloService;
import com.ipartek.formacion.service.ModuloServiceImp;
import com.ipartek.formacion.service.Util;


public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static CursoService cService = new CursoServiceImp();  
    private static ModuloService mService = new ModuloServiceImp();
    private static AlumnoService aService = new AlumnoServiceImp();
    private List<Curso> cursos = null;
    private Curso curso = null;
    private RequestDispatcher rwd = null;
    private int id = -1;
    private int operacion = -1;
    
    //si recibo un paremetro hago getbyid,sino getall
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
  			recogerId(request); //recibe como paremetro el id, porque en listado.jsp le hemos llamado id.Getparameter recibe siempre un string, por eso hay que pasarlo a integer
  			request.setAttribute(Constantes.ATT_LISTADO_MODULOS, mService.getAll()); //pasamos el listado de codigos de modulos 
  			request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, aService.getAll()); //pasamos el listado de codigos de alumnos
  			if(id<0){ //REDIRIGIMOS PARA UN CREATE
  				rwd = request.getRequestDispatcher(Constantes.JSP_MODULO); //a jsp_alumno no le paso el alumno, xq en jsp_alumno si no recibe alumno lo que hace es create
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
		curso = cService.getById(id);
		request.setAttribute(Constantes.ATT_CURSO, curso );
		rwd = request.getRequestDispatcher(Constantes.JSP_CURSO);
	}


	private void getAll(HttpServletRequest request) {
		cursos = cService.getAll();
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
		rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Procesaremos el DELETE , UPDATE Y CREATE
		//1º recoger datos del objeto curso
		String op = request.getParameter(Constantes.PAR_OPERACION);
		 try{
			operacion = Integer.parseInt(op);
			
			switch (operacion) {
			
				case Constantes.OP_CREATE:
						recogerDatos(request);
						cService.createCurso(curso);
					break;
					
				case Constantes.OP_UPDATE:
						recogerDatos(request);
						cService.update(curso);
					break;
					
				case Constantes.OP_DELETE:
						recogerId(request);
						cService.deleteCurso(id);
				break;
	
				default:
					break;
			}
		 }catch(NumberFormatException e){
			 
		 }catch(Exception e){
			 
		 }
		
		getAll(request);
		rwd.forward(request, response);
		
	}


	private void recogerId(HttpServletRequest request) {
	
		id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		
		
	
	}


	private void recogerDatos(HttpServletRequest request) {
		curso = new Curso();
		
		recogerId(request);
		curso.setCodigo(id); //la variable id es global y se modifica desde recogerId
		
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		curso.setNombre(nombre);
		
		String codTipoCurso = request.getParameter(Constantes.PAR_TIPOCURSO);
		TipoCurso tipo = Util.parseTipoCurso(codTipoCurso);
		curso.setTipo(tipo);
		
		String[] codAlumnos = request.getParameterValues(Constantes.PAR_ALUMNOS); 
		Map<String,Alumno> alumnos = getAlumnos(codAlumnos); 
		curso.setAlumnos(alumnos);
		
		String[] codModulos = request.getParameterValues(Constantes.PAR_MODULOS);
		Map<Integer,Modulo> modulos = getModulos(codModulos);
		curso.setModulos(modulos);
		
	}

	//método para cargar los alumnos en el mapa 
	private Map<String, Alumno> getAlumnos(String[] codAlumnos) {
		
		Map<String,Alumno> alumnos = null;
		alumnos = new HashMap<String, Alumno>();
		
		for(String codAlumno: codAlumnos){
			Alumno alumno = null;
			int codigo =Integer.parseInt(codAlumno);
			alumno = aService.getById(codigo);
			alumnos.put(alumno.getDni(), alumno);
		}
		
		return alumnos;
	}
	
	//método para cargar los modulos en el mapa 
		private Map<Integer, Modulo> getModulos(String[] codModulos) {
			
			Map<Integer,Modulo> modulos = null;
			modulos = new HashMap<Integer, Modulo>();
			
			for(String codModulo: codModulos){
				Modulo modulo = null; //creo el objeto modulo
				int codigo =Integer.parseInt(codModulo); //parseo el codModulo a int
				modulo = mService.getById(codigo); //en modulo hago getById para saber que modulo es
				modulos.put(modulo.getCodigo(), modulo);
			}
			
			return modulos;
		}

}
