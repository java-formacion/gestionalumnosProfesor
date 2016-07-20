package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Curso;

/**
 * 
 * @author Curso
 *
 */
public interface CursoService {
  /**
   * 
   * @param curso
   *          Curso
   * @return curso
   */
  public Curso createCurso(Curso curso);

  /**
   * 
   * @param codigo
   *          int
   * @return curso
   */
  public Curso getById(int codigo);

  /**
   * 
   * @param codigo
   *          int
   */
  public void delete(int codigo);

  /**
   * 
   * @return lista de cursos
   */
  public List<Curso> getAll();

  /**
   * 
   * @param curso
   *          Curso
   * @return curso
   */
  public Curso update(Curso curso);

  // public void darDeAlta(Alumno alumno, int codigo);
  //
  // public void darDeBaja(String dni, int codigo);
  //
  // public void darDeAlta(Alumno alumno);
  //
  // public void darDeBaja(Alumno alumno);
}
