//CLASE QUE CONTROLA LOS ERRORES DEL ALUMNO EN EL FORMULARIO

package com.ipartek.formacion.controller.exception;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;

public class AlumnoError extends Alumno{
	private String mensaje; //variable para guardar el error
	
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
	public void setCodigo(int codigo) {
		
	}

	@Override //sobrecargando el método del padre
	public void setDni(String dni) throws CandidatoException {
		//accedo al atributo con dni porque ha heredado los atributos de candidato como protected
		//super.setDni(dni);
		this.dni = dni;
	}
	
	

}
