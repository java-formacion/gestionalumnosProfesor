package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.exception.AlumnoError;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Idioma;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;
import com.ipartek.formacion.service.Util;

/**
 * @author Curso Servlet implementation class AlumnosServlet
 */
public class AlumnosServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static AlumnoService aService = AlumnoServiceImp.getInstance();
  private static CursoService cService = CursoServiceImp.getInstance();
  private List<Curso> cursos = null;
  private Alumno alumno = null;
  private List<Alumno> alumnos = null;
  private int id = -1;
  private int operacion = -1;
  private RequestDispatcher rwd;
  private static final Logger LOG = Logger.getLogger(AlumnosServlet.class);

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AlumnosServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * @throws ServletException
   *           excepcion de servlet
   * @throws IOException
   *           excepcion input/output
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      recogerId(request);
      if (this.id < 0) {
        rwd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);
      } else {
        getById(request);
        cargarListadoCursos(request);
      }

    } catch (Exception e) {
      getAll(request);
    }
    rwd.forward(request, response);
  }

  /**
   * 
   * @param request
   *          peticion
   */
  private void cargarListadoCursos(HttpServletRequest request) {
    cursos = cService.getAll();
    request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
  }

  /**
   * 
   * @param request
   *          peticion
   */
  private void getById(HttpServletRequest request) {
    alumno = aService.getById(id);
    request.setAttribute(Constantes.ATT_ALUMNO, alumno);
    rwd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);

  }

  /**
   * 
   * @param request
   *          peticion
   */
  private void getAll(HttpServletRequest request) {

    alumnos = aService.getAll();
    request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
    rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * @throws ServletException
   *           excepcion de servlet
   * @throws IOException
   *           excepcion input/output
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String op = request.getParameter(Constantes.PAR_OPERACION);
    try {
      this.operacion = Integer.parseInt(op);

      switch (this.operacion) {
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
        aService.delete(this.id);
        break;
      default:
        break;
      }
      getAll(request);
    } catch (CandidatoException e) {
      LOG.error("Error: datos del formulario invalidos");
      AlumnoError aError;
      try {
        aError = new AlumnoError();
        aError = recogerDatosError(request);
        aError.setMensaje(e.getMessage());
        cargarListadoCursos(request);
        request.setAttribute(Constantes.ATT_ALUMNO, aError);
      } catch (CandidatoException e1) {
      }

      rwd = request.getRequestDispatcher(Constantes.JSP_ALUMNO);
    } catch (Exception e) {
    }

    rwd.forward(request, response);
  }

  /**
   * 
   * @param request
   *          peticion
   * @return alumnoError
   * @throws CandidatoException
   *           excepcion al crear errorAlumno
   */
  private AlumnoError recogerDatosError(HttpServletRequest request) throws CandidatoException {
    AlumnoError alError = new AlumnoError();
    alError.setCodigo(this.id);
    alError.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
    alError.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
    alError.setDni(request.getParameter(Constantes.PAR_DNI));
    Date fecha = recogerFecha(request);
    alError.setfNacimiento(fecha);
    alError.setGenero(Util.parseGenero(request.getParameter(Constantes.PAR_GENERO)));
    alError.setIdiomas(Util.parseIdiomas(request.getParameterValues(Constantes.PAR_IDIOMA)));
    String idCurso = request.getParameter(Constantes.PAR_CURSO);
    Curso curso = new Curso();
    curso.setCodigo(Integer.parseInt(idCurso));
    alError.setCurso(curso);
    return alError;
  }

  /**
   * 
   * @param request
   *          peticion
   * @return fecha
   * @throws CandidatoException
   *           excepcion fecha no valida
   */
  private Date recogerFecha(HttpServletRequest request) throws CandidatoException {
    GregorianCalendar calendar;
    int mes, dia, year;
    mes = Integer.parseInt(request.getParameter(Constantes.PAR_MES)) - 1;
    dia = Integer.parseInt(request.getParameter(Constantes.PAR_DIA));
    year = Integer.parseInt(request.getParameter(Constantes.PAR_ANYO));
    calendar = new GregorianCalendar(year, mes, dia);
    return calendar.getTime();

  }

  /**
   * 
   * @param request
   *          peticion
   */
  private void recogerId(HttpServletRequest request) {
    this.id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));

  }

  /**
   * 
   * @param request
   *          peticion
   * @throws CandidatoException
   *           excepcion datos de alumno no validos
   * @throws Exception
   */
  private void recogerDatos(HttpServletRequest request) throws CandidatoException {
    alumno = new Alumno();
    recogerId(request);
    alumno.setCodigo(this.id);
    String nombre = request.getParameter(Constantes.PAR_NOMBRE);
    String apellidos = request.getParameter(Constantes.PAR_APELLIDOS);

    String dni = request.getParameter(Constantes.PAR_DNI);
    Date fecha = recogerFecha(request);
    String genero = request.getParameter(Constantes.PAR_GENERO);
    String[] idiomas = request.getParameterValues(Constantes.PAR_IDIOMA);
    String idCurso = request.getParameter(Constantes.PAR_CURSO);
    Curso curso = new Curso();
    curso.setCodigo(Integer.parseInt(idCurso));
    List<Idioma> idi = Util.parseIdiomas(idiomas);

    alumno.setNombre(nombre);
    alumno.setApellidos(apellidos);
    alumno.setDni(dni); // salta error NullPointerException
    alumno.setfNacimiento(fecha);
    alumno.setGenero(Util.parseGenero(genero));
    alumno.setIdiomas(idi);
    alumno.setCurso(curso);

  }
}
