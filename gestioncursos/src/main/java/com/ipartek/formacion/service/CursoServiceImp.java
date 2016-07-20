package com.ipartek.formacion.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.dao.CursoDAO;
import com.ipartek.formacion.dbms.dao.CursoDAOImp;
import com.ipartek.formacion.pojo.Curso;

/**
 * 
 * @author Curso
 *
 */
public class CursoServiceImp implements CursoService {

  private final static Logger LOG = Logger.getLogger(CursoServiceImp.class);
  private static CursoServiceImp INSTANCE = null;
  private CursoDAO cursoDao;

  /**
 * 
 */
  private CursoServiceImp() {
    cursoDao = CursoDAOImp.getInstance();
  }

  /**
   * 
   * @return intance
   */
  public static CursoServiceImp getInstance() {
    if (INSTANCE == null) {
      createInstance();
    }
    return INSTANCE;
  }

  /**
 * 
 */
  private synchronized static void createInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CursoServiceImp();
    }
  }

  /**
   * @Override
   * @return no devuelve nada
   * @throws CloneNotSupportedException
   *           no se puede clonar el objeto
   */
  protected Object clone() throws CloneNotSupportedException {
    LOG.error("Error al clonar");
    throw new CloneNotSupportedException();
  }

  /**
   * @Override
   * @param curso
   *          Curso
   * @return curso
   */
  public Curso createCurso(Curso curso) {
    Curso cur = cursoDao.create(curso);
    return cur;
  }

  /**
   * @Override
   * @param codigo
   *          int
   * @return curso
   */
  public Curso getById(int codigo) {
    Curso curso = cursoDao.getById(codigo);
    return curso;
  }

  /**
   * @Override
   * @param codigo
   *          int
   */
  public void delete(int codigo) {
    cursoDao.delete(codigo);
  }

  /**
   * @Override
   * @return lista de cursos
   */
  public List<Curso> getAll() {
    return cursoDao.getAll();
  }

  /**
   * @Override
   * @param curso
   *          Curso
   * @return curso
   */
  public Curso update(Curso curso) {
    Curso cur = cursoDao.update(curso);
    return cur;
  }

  // /**
  // * @Override
  // *
  // */
  // public void darDeAlta(Alumno alumno, int codigo) {
  // Curso curso = getById(codigo);
  // curso.getAlumnos().put(alumno.getDni(), alumno);
  // update(curso);
  //
  // }

  // /**
  // * @Override
  // */
  // public void darDeBaja(String dni, int codigo) {
  // Curso curso = getById(codigo);
  // curso.getAlumnos().remove(dni);
  // update(curso);
  //
  // }

  // /**
  // * @Override
  // */
  // public void darDeAlta(Alumno alumno) {
  // Curso curso = getById(alumno.getCurso().getCodigo());
  // curso.getAlumnos().put(alumno.getDni(), alumno);
  // update(curso);
  //
  // }

  // /**
  // * @Override
  // */
  // public void darDeBaja(Alumno alumno) {
  // Curso curso = getById(alumno.getCurso().getCodigo());
  // curso.getAlumnos().remove(alumno.getDni());
  // update(curso);
  //
  // }

}
