package com.ipartek.formacion.control.exception;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Candidato;
import com.ipartek.formacion.pojo.excepciones.AlumnoServiceException;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;

public class AlumnoError extends Alumno{
	private String mensaje;
	public AlumnoError() throws CandidatoException{
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
		this.setCodigo(codigo);
	}
	@Override
	public void setDni(String dni) throws CandidatoException {
		// TODO Auto-generated method stub
		//super.setDni(dni);
		this.dni=dni;//Aqui sobrecargamos el metodo del padre y lo machacamos
		this.mensaje="DNI incorrecto";
	}
	
}
