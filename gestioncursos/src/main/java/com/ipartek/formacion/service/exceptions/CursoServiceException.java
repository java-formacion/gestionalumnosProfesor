package com.ipartek.formacion.service.exceptions;

public class CursoServiceException extends Exception{
	public final static int CODIGO_CURSO_NO_ECONTRADO= 100;
	public final static String MSG_CURSO_NO_ENCONTRADO = "El curso no se ha encontrado.";
	private int codigo;
	private String mensaje;
	public CursoServiceException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	public CursoServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public CursoServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public CursoServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public CursoServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
