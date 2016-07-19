package com.ipartek.formacion.controller.exception;

import com.ipartek.formacion.pojo.Alumno;

public class AlumnoError extends Alumno {
  // Map<String,Mensaje>
  private String mensaje;

  public AlumnoError() {
    super();
    this.mensaje = "";

  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  @Override
  public void setCodigo(int codigo) {
    // TODO Auto-generated method stub
    super.setCodigo(codigo);

  }

  @Override
  public void setDni(String dni) {
    // TODO Auto-generated method stub
    // super.setDni(dni);
    this.dni = dni;
  }

}
