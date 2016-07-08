package com.ipartek.formacion.service.exceptions;

public class AlumnoServiceException  extends Exception {

	public final static int CODIGO_ALUMNO_NO_ECONTRADO= 100;
	public final static String MSG_ALUMNO_NO_ENCONTRADO = "El alumno no se ha encontrado.";
	private int codigo;
	private String mensaje;
	public AlumnoServiceException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	public AlumnoServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public AlumnoServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public AlumnoServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public AlumnoServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.mensaje;
	}
	
	 public final int getCodigo() {
	        return codigo;
	    }

	    public final void setCodigo(final int codigo) {
	        this.codigo = codigo;
	    }
	
}
