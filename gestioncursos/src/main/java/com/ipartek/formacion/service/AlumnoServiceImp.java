package com.ipartek.formacion.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.dao.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.pojo.Alumno;

/**
 * 
 * @author Curso
 *
 */
public class AlumnoServiceImp implements AlumnoService {
  private final static Logger LOG = Logger.getLogger(AlumnoServiceImp.class);
  private static AlumnoServiceImp INSTANCE = null;
  private AlumnoDAO alumDao;

  /**
 * 
 */
  private AlumnoServiceImp() {
    alumDao = AlumnoDAOImp.getInstance();
  }

  /**
   * 
   * @return AlumnoServiceImp instance
   */
  public static AlumnoServiceImp getInstance() {
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
      INSTANCE = new AlumnoServiceImp();
    }
  }

  /**
   * @Override
   * @return nada
   * @throws CloneNotSupportedException
   *           no permite clonar
   * 
   */
  protected Object clone() throws CloneNotSupportedException {
    LOG.error("Error al clonar");
    throw new CloneNotSupportedException();
  }

  /**
   * @Override
   * @param alumno
   *          Alumno
   * @return alumno
   * 
   */
  public Alumno createAlumno(Alumno alumno) {
    Alumno alum = alumDao.create(alumno);
    return alum;
  }

  /**
   * @Override
   * @param codigo
   *          codigo de alumno
   * @return Alumno
   */
  public Alumno getById(int codigo) {
    Alumno alum = alumDao.getById(codigo);
    return alum;
  }

  /**
   * @Override
   * @param codigo
   *          codigo de alumno
   */
  public void delete(int codigo) {
    alumDao.delete(codigo);

  }

  /**
   * @Override
   * @return Lista de todos los alumnos
   */
  public List<Alumno> getAll() {
    return alumDao.getAll();
  }

  /**
   * @Override
   * @param alumno
   *          alumno a modificar
   * @return alumno modificado
   */
  public Alumno update(Alumno alumno) {
    Alumno alum = alumDao.update(alumno);
    return alum;
  }

}
