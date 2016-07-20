package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;

/**
 * 
 * @author Curso
 *
 */
public interface AlumnoService {
  /**
   * 
   * @param alum
   *          Alumno
   * @return alumno creado
   */
  public Alumno createAlumno(Alumno alum);

  /**
   * 
   * @param codigo
   *          int
   * @return alumno
   */
  public Alumno getById(int codigo);

  /**
   * 
   * @param codigo
   *          int
   */
  public void delete(int codigo);

  /**
   * 
   * @return lista de alumnos
   */
  public List<Alumno> getAll();

  /**
   * 
   * @param alumno
   *          Alumno
   * @return alumno actualizado
   */
  public Alumno update(Alumno alumno);

}
