package com.ipartek.formacion.controller.exception;

import java.util.Date;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.exception.CandidatoException;

public class AlumnoError extends Alumno {

	private String mensaje;
	
	public AlumnoError() throws CandidatoException {
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
	public void setfNacimiento(Date fNacimiento) throws CandidatoException {
		super.setfNacimiento(fNacimiento);
	}

	@Override
	public void setDni(String dni) throws CandidatoException {
		//super.setDni(dni);
		this.dni = dni;
	}

	@Override
	public void setCodigo(int codigo) {
		super.setCodigo(codigo);
	}
	
}
