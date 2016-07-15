package com.ipartek.formacion.controller.exception;

import java.util.Date;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;

/**
 * 
 * @author Curso
 *
 */
public class AlumnoError extends Alumno {
  private String mensaje;

  /**
   * 
   * @throws CandidatoException
   *           excepcion
   * 
   */
  public AlumnoError() throws CandidatoException {
    super();
    this.mensaje = "";
  }

  /**
   * 
   * @return att mensaje
   */
  public String getMensaje() {
    return mensaje;
  }

  /**
   * 
   * @param mensaje
   *          att mensaje
   * 
   */
  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  /**
   * @Override
   * @param codigo
   *          att codigo
   */
  public void setCodigo(int codigo) {
    super.setCodigo(codigo);
  }

  /**
   * @Override
   * @param dni
   *          att dni
   * @throws CandidatoException
   *           excepcion dni invalido
   */
  public void setDni(String dni) throws CandidatoException {
    this.dni = dni;
  }

  /**
   * @Override
   * @param fNacimiento
   *          att fecha nacimiento
   * @throws CandidatoException
   *           excepcion fecha invalida
   */
  public void setfNacimiento(Date fNacimiento) throws CandidatoException {
    // TODO Auto-generated method stub
    this.fNacimiento = fNacimiento;
  }

}
