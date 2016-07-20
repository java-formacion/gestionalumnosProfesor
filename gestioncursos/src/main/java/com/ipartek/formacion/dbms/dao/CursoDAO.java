package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Curso;

/**
 * 
 * @author Curso
 *
 */
public interface CursoDAO {
  /**
   * @param codigo
   *          int codigoCurso
   * @return curso
   */
  public Curso getById(int codigo);

  /**
   * 
   * @param curso
   *          Curso
   * @return curso actualizado
   */
  public Curso update(Curso curso);

  /**
   * 
   * @param curso
   *          Curso
   * @return curso creado
   */
  public Curso create(Curso curso);

  /**
   * 
   * @param codigo
   *          codigo curso
   */
  public void delete(int codigo);

  /**
   * 
   * @return lista de cursos
   */
  public List<Curso> getAll();
}