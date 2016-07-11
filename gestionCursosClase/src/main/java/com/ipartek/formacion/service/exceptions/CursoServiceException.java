package com.ipartek.formacion.service.exceptions;

public class CursoServiceException extends Exception{
	public final static int CODIGO_CURSO_NO_ECONTRADO= 100;
	public final static String MSG_CURSO_NO_ENCONTRADO = "El curso no se ha encontrado.";
	private int codigo;
	private String mensaje;
	public CursoServiceException(int codigo, String mensaje) {
		super(mensaje);
		this.setCodigo(codigo);
		this.setMensaje(mensaje);
	}
	public CursoServiceException(final String message, final Throwable cause,
		final boolean enableSuppression,final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public CursoServiceException(final String message,final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public CursoServiceException(final String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public CursoServiceException(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
