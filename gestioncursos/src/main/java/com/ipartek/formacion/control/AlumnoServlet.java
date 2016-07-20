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
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.services.AlumnoService;
import com.ipartek.formacion.services.AlumnoServiceImp;
import com.ipartek.formacion.services.CursoService;
import com.ipartek.formacion.services.CursoServiceImp;
import com.ipartek.formacion.services.Idioma;
import com.ipartek.formacion.services.Util;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int id = -1;
	private RequestDispatcher rd = null;
	private CursoService cService = CursoServiceImp.getInstance();
	private AlumnoService aService = AlumnoServiceImp.getInstance();
	private List<Alumno> alumnos = null;
	private Alumno alumno = null;
	private int operacion = -1;
	private final static Logger LOG = Logger.getLogger(AlumnoServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			recogerId(request);
			LOG.trace("primer id " + id);
			request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cService.getAll());
			LOG.trace("segundo id " + id);
			if (id < 0) {// REDIGIRIMOS PARA UN CREATE

				rd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);
			} else {// REDIGIMOS PARA UNA UPDATE
				LOG.trace(id);
				getById(request);
			}

		} catch (Exception e) {
			getAll(request);
		}
		rd.forward(request, response);
	}

	private void recogerId(HttpServletRequest request) {
		if (Util.tryParseInt(request.getParameter(Constantes.PAR_CODIGO)))
			id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
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
		LOG.trace(id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter(Constantes.PAR_OPERACION);
		try {
			if (Util.tryParseInt(op))
				operacion = Integer.parseInt(op);
			recogerId(request);
			switch (operacion) {
			case Constantes.OP_CREATE:
				recogerDatosAlumno(request);
				aService.createAlumno(alumno);
				LOG.trace(alumno.toString());
				break;
			case Constantes.OP_DELETE:
				aService.delete(id);
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
			LOG.error(e.getMessage());
		} catch (NullPointerException e) {
			LOG.error(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage());

		}
		rd.forward(request, response);
	}

	private void recogerDatosAlumno(HttpServletRequest request) throws CandidatoException {
		alumno = new Alumno();
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		String dni = request.getParameter(Constantes.PAR_DNI);
		String apellidos = request.getParameter(Constantes.PAR_APELLIDOS);
		String genero = request.getParameter(Constantes.PAR_GENERO);
		String[] idiomas = request.getParameterValues(Constantes.PAR_IDIOMA);
		String idCurso = request.getParameter(Constantes.PAR_CURSO);
		List<Idioma> idi = Util.parseIdioma(idiomas);
		Curso curso = new Curso();
		curso.setCodigo(Integer.parseInt(idCurso));

		alumno.setCodigo(id);
		alumno.setNombre(nombre);
		alumno.setApellidos(apellidos);
		alumno.setDni(dni);
		alumno.setCurso(curso);
		alumno.setIdiomas(idi);
		alumno.setGenero(Util.parseGenero(genero));

	}

}
