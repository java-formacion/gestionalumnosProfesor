package com.ipartek.formacion.service.exceptions;

public class ModuloServiceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7061685425653593876L;
	public final static int CODIGO_MODULO_NO_ECONTRADO= 100;
	public final static String MSG_MODULO_NO_ENCONTRADO = "El modulo no se ha encontrado.";
	private int codigo;
	private String mensaje;
	public ModuloServiceException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	public ModuloServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public ModuloServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public ModuloServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ModuloServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
