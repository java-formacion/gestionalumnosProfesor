package com.ipartek.formacion.service.excepciones;

public class AlumnoException extends Exception{
	
	public static final int CODIGO_ERROR_ALUMNO_INDEX = 104;
	public static final String MSG_ERROR_ALUMNO_INDEX = "No existe el alumno";
	private int codigo;
	private String mensaje;
	
	public AlumnoException(int codigo,String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	public AlumnoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	
	public AlumnoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	public AlumnoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param cause
	 */
	public AlumnoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.mensaje;
	}
	
	

}
