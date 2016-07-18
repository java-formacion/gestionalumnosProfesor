package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;

/**
 * 
 * @author Curso
 *
 */
public interface AlumnoDAO {
  /**
   * @param codigo
   *          int codigoAlumno
   * @return alumno
   */
  public Alumno getById(int codigo);

  /**
   * 
   * @param alumno
   *          Alumno
   * @return alumno actualizado
   */
  public Alumno update(Alumno alumno);

  /**
   * 
   * @param alumno
   *          Alumno
   * @return alumno creado
   */
  public Alumno create(Alumno alumno);

  /**
   * 
   * @param codigo
   *          codigo alumno
   */
  public void delete(int codigo);

  /**
   * 
   * @return lista de alumnos
   */
  public List<Alumno> getAll();
}
