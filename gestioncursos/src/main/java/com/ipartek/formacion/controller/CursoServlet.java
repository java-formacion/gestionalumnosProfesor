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

import org.apache.log4j.Logger;

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

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static CursoService cService = new CursoServiceImp();
	private static ModuloService mService = new ModuloServiceImp();
	private static AlumnoService aService = new AlumnoServiceImp();
	
	private RequestDispatcher rwd = null;
	private List<Curso> cursos = null;
	private Curso curso = null;
	private int id = -1;
	private int operacion = -1;
	
	private final static Logger log = Logger.getLogger(CursoServlet.class);
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try{
			//id = Integer.parseInt(request.getParameter("id"));
			recogerId(request);
			//System.out.println(mService.getAll().size());
			request.setAttribute(Constantes.ATT_LISTADO_MODULOS, mService.getAll());
			request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, aService.getAll());
			
			if(id < 0){
				// Se redirige para realizar un CREATE
				rwd = request.getRequestDispatcher(Constantes.JSP_CURSO);
			} else{
				// Se redirige para realizar un UPDATE
				getById(request);
			}
		} 		
		catch(Exception e){
			getAll(request);
		}

		rwd.forward(request, response);
	}

	private void getAll(HttpServletRequest request) {
		cursos = cService.getAll();
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
		rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
	}

	private void getById(HttpServletRequest request) {
		curso = cService.getById(id);
		request.setAttribute(Constantes.ATT_CURSO, curso);
		rwd = request.getRequestDispatcher(Constantes.JSP_CURSO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Procesaremos el DELETE, UPDATE y CREATE
		// El CREATE se diferencia de las demas porque no lleva un codigo valido (-1)
		
		String op = request.getParameter(Constantes.PAR_OPERACION);
		
		try {
			if(Util.tryParseInt(op)){
				operacion = Integer.parseInt(op);
			}
			
			try{
				// 1- recoger los datos del objeto curso
				recogerId(request);
			} catch(Exception e){
				getAll(request);
			}
			
			// 2- diferencias la create de las demas
			switch(operacion){
				case Constantes.OP_CREATE:
					// CREATE
					recogerDatos(request);
					cService.create(curso);
					break;
				
				case Constantes.OP_DELETE:
					// DELETE
					cService.delete(id);
					break;
					
				case Constantes.OP_UPDATE:
					// UPDATE
					recogerDatos(request);
					cService.update(curso);
					break;
					
				case Constantes.OP_READ:
					break;
				
				default:
					break;
			
			}
		} catch (NumberFormatException e){
			log.error(e.getMessage());
		}
		catch (Exception e) {
			log.error(e.getMessage());
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
		
		curso.setCodigo(id);
		
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		curso.setNombre(nombre);
		
		String referencia = request.getParameter(Constantes.PAR_REFERENCIA);
		curso.setReferencia(referencia);
		
		String codTipoCurso = request.getParameter(Constantes.PAR_TIPO_CURSO);
		TipoCurso tipo = Util.parseTipoCurso(codTipoCurso);
		curso.setTipoCurso(tipo);
		
		// Metodo para cargar el mapa de alumnos
		String[] codAlumnos = request.getParameterValues(Constantes.PAR_LISTADO_ALUMNOS);
		Map<String,Alumno> alumnos = getAlumnos(codAlumnos);
		curso.setAlumnos(alumnos);
		
		// Metodo para cargar el mapa de modulos
		String[] codModulos = request.getParameterValues(Constantes.PAR_LISTADO_MODULOS);
		Map<Integer,Modulo> modulos = getModulos(codModulos);
		curso.setModulos(modulos);
	}

	private Map<Integer, Modulo> getModulos(String[] codModulos) {
		Map<Integer, Modulo> modulos = null;
		
		modulos = new HashMap<Integer, Modulo>();
		for(String codModulo: codModulos){
			Modulo modulo = null;
			modulo = mService.getById(Integer.parseInt(codModulo));
			modulos.put(modulo.getCodigo(), modulo);
		}
		
		return modulos;
	}

	private Map<String, Alumno> getAlumnos(String[] codAlumnos) {
		Map<String, Alumno> alumnos = null;
		
		alumnos = new HashMap<String, Alumno>();
		for(String codAlumno: codAlumnos){
			Alumno alumno = null;
			alumno = aService.getById(Integer.parseInt(codAlumno));
			alumnos.put(alumno.getDni(), alumno);
		}
		
		return alumnos;
	}

}
