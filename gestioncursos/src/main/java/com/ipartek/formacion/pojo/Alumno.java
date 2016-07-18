package com.ipartek.formacion.pojo;

import com.ipartek.formacion.pojo.excepciones.CandidatoException;

/**
 * 
 * @author Curso
 *
 */
public class Alumno extends Candidato implements Comparable<Alumno> {
  private Curso curso;

  /**
   * @throws CandidatoException
   *           excepcion al crear alumno
   */

  public Alumno() {
    super();
    this.curso = new Curso();
  }

  /**
   * 
   * @return curso
   */
  public Curso getCurso() {
    return curso;
  }

  /**
   * 
   * @param curso
   *          curso
   */
  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  /**
   * @return datos
   */
  public String mostrarDatos() {
    return super.mostrarDatos() + " " + this.curso.getNombre();
  }

  /**
   * @Override
   * @param o
   *          objeto Alumno a comparar
   * @return igual
   */
  public int compareTo(Alumno o) {
    int igual = 0;
    if (o.getCodigo() == this.getCodigo()) {
      igual = 0;
    } else {
      if (o.getApellidos().compareToIgnoreCase(this.getApellidos()) > 0) {
        igual = 1;
      } else {
        igual = -1;
      }
    }
    return igual;

  }

}
