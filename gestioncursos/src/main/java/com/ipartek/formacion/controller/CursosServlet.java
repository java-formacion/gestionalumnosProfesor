package com.ipartek.formacion.controller;

import java.io.IOException;
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
 * @author Curso Servlet implementation class CursosServlet
 */
public class CursosServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static CursoService cService = CursoServiceImp.getInstance();
  private static AlumnoService aService = AlumnoServiceImp.getInstance();
  private static ModuloService mService = ModuloServiceImp.getInstance();
  private final static Logger LOG = Logger.getLogger(CursosServlet.class);
  private Curso curso = null;
  private List<Curso> cursos = null;
  private List<Alumno> alumnos = null;
  private List<Modulo> modulos = null;
  private int id = -1;
  private int operacion = -1;
  private RequestDispatcher rwd;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CursosServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * @throws ServletException
   *           excepcion servlet
   * @throws IOException
   *           excepcion input/output
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      cargarListadoAlumnos(request);
      cargarListadoModulos(request);
      recogerId(request);
      if (this.id < 0) {
        rwd = request.getRequestDispatcher(Constantes.JSP_CURSO);
      } else {
        getById(request);
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
  private void cargarListadoModulos(HttpServletRequest request) {
    modulos = mService.getAll();
    request.setAttribute(Constantes.ATT_LISTADO_MODULOS, modulos);
  }

  /**
   * 
   * @param request
   *          peticion
   */
  private void cargarListadoAlumnos(HttpServletRequest request) {
    alumnos = aService.getAll();
    request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
  }

  /**
   * 
   * @param request
   *          peticion
   */
  private void getById(HttpServletRequest request) {
    curso = cService.getById(id);
    request.setAttribute(Constantes.ATT_CURSO, curso);
    rwd = request.getRequestDispatcher(Constantes.JSP_CURSO);
  }

  /**
   * 
   * @param request
   *          peticion
   */
  private void getAll(HttpServletRequest request) {
    cursos = cService.getAll();
    request.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
    rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   * @param request
   *          peticion
   * @param response
   *          respuesta
   * @throws ServletException
   *           excepcion servlet
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
        cService.createCurso(curso);
        break;
      case Constantes.OP_UPDATE:
        recogerDatos(request);
        cService.update(curso);
        break;
      case Constantes.OP_DELETE:
        recogerId(request);
        cService.delete(this.id);
        break;
      default:
        break;
      }
    } catch (NumberFormatException e) {
      LOG.error("La operacion " + this.operacion + " no es valida");
    } catch (Exception e) {
    }
    getAll(request);
    rwd.forward(request, response);
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
   */
  private void recogerDatos(HttpServletRequest request) {
    curso = new Curso();
    recogerId(request);

    curso.setCodigo(this.id);
    String nombre = request.getParameter(Constantes.PAR_NOMBRE);
    Map<String, Alumno> alumnos = Util.parseAlumnos(request
        .getParameterValues(Constantes.PAR_ALUMNOS));
    Map<Integer, Modulo> modulos = Util.parseModulos(request
        .getParameterValues(Constantes.PAR_MODULOS));
    TipoCurso tipo = Util.parseTipo(request.getParameter(Constantes.PAR_TIPO));
    curso.setNombre(nombre);
    curso.setAlumnos(alumnos);
    curso.setModulos(modulos);
    curso.setTipo(tipo);

  }

}
