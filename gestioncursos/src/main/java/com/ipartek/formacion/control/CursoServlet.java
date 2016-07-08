package com.ipartek.formacion.control;

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
import com.ipartek.formacion.services.AlumnoService;
import com.ipartek.formacion.services.AlumnoServiceImp;
import com.ipartek.formacion.services.CursoService;
import com.ipartek.formacion.services.CursoServiceImp;
import com.ipartek.formacion.services.Horas;
import com.ipartek.formacion.services.ModuloService;
import com.ipartek.formacion.services.ModuloServiceImp;
import com.ipartek.formacion.services.TipoCurso;
import com.ipartek.formacion.services.Util;

/**
 * Servlet implementation class CursoServlet
 */
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int id = -1;
    private int operacion = -1;
    private RequestDispatcher rd = null;
    private CursoService cService = CursoServiceImp.getInstance();
    private AlumnoService aService = AlumnoServiceImp.getInstance();
    private ModuloService mService = ModuloServiceImp.getInstance();
    private List<Curso> cursos = null;
    private Curso curso = null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			recogerId(request);
			request.setAttribute(Constantes.ATT_LISTA_TIPO_CURSO, Constantes.LISTA_TIPOCURSO);
			request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, aService.getAll());
			request.setAttribute(Constantes.ATT_LISTADO_MODULOS, mService.getAll());
			if(id < 0){//REDIGIRIMOS PARA UN CREATE
				rd = request.getRequestDispatcher(Constantes.JSP_CURSO);
			}else{//REDIGIMOS PARA UNA UPDATE
				getById(request);
			}
		} catch(Exception e){
			getAll(request);
		}
		rd.forward(request, response);
		
	}

	private void getById(HttpServletRequest request) {
	
		curso = cService.getById(id);
		System.out.println(curso.getCodigo());
		request.setAttribute(Constantes.ATT_CURSO, curso);
		rd = request.getRequestDispatcher(Constantes.JSP_CURSO);
	}

	private void getAll(HttpServletRequest request) {

		cursos = cService.getAll();
		request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Procesaremos el DELETE, UPDATE y CREATE
		//1º recoger datos del objeto curso
		String op = request.getParameter(Constantes.PAR_OPERACION);
		try{
			
			operacion = Integer.parseInt(op);
		
			switch(operacion){
				case Constantes.OP_CREATE:
					recogerDatos(request);
					cService.create(curso);
				break;
				case Constantes.OP_DELETE:
					recogerId(request);
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
		rd.forward(request, response);
	}

	private void recogerId(HttpServletRequest request) {
		id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		
	}

	private void recogerDatos(HttpServletRequest request) {
		curso = new Curso();
		recogerId(request);
		curso.setCodigo(id);
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		String referencia = request.getParameter(Constantes.PAR_REFERENCIA);
		curso.setReferencia(referencia);
		//parse para tipo de curso
		TipoCurso tipocurso = Util.parseTipo(request.getParameter(Constantes.PAR_TIPOCURSO));
		curso.setTc(tipocurso);
		curso.setNombre(nombre);
		//metodo para cargar el mapa de alumno
		String[] codAlumnos = request.getParameterValues(Constantes.PAR_LISTADO_ALUMNOS);
		Map <String,Alumno> alumnos = getAlumnos(codAlumnos);
		curso.setAlumnos(alumnos);
		//metodo para cargar el mapa de modulo
		String[] codModulos = request.getParameterValues(Constantes.PAR_LISTADO_MODULOS);
		Map <Integer,Modulo> modulos = getModulos(codModulos);
		curso.setModulos(modulos);
		
	}

	private Map<Integer, Modulo> getModulos(String[] codModulos) {
		Map<Integer, Modulo> modulos = null;
		modulos = new HashMap<Integer, Modulo>();
		for (String codModulo: codModulos){
			Modulo modulo = null;
			int codigo = Integer.parseInt(codModulo);
			modulo = mService.getByID(codigo);
			modulos.put(modulo.getCodigo(), modulo);
		}
		return modulos;
	}

	private Map<String, Alumno> getAlumnos(String[] codAlumnos) {
		Map <String,Alumno> alumnos = null;
		alumnos = new HashMap<String, Alumno>();
		for (String codAlumno: codAlumnos){
			Alumno alumno = null;
			int codigo = Integer.parseInt(codAlumno);
			alumno = aService.getById(codigo);
			alumnos.put(alumno.getDni(), alumno);
		}
		return alumnos;
	}

}
