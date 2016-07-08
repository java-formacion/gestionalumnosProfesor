/**
 * 
 */
package com.ipartek.formacion.pojo.exception;

/**
 * @author Curso
 *
 */
public class CursoException extends Exception{	
	
	public static final int CODIGO_ERROR_INDEX_CURSO=103;
	public static final String MSG_ERROR_INDEX_CURSO="El índice del Curso no es válido";
	
	private int codigo;
	private String mensaje;
	/**
	 * @param codigo
	 * @param mensaje
	 */
	public CursoException(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	/**
	 * 
	 */
	public CursoException() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CursoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 * @param cause
	 */
	public CursoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 */
	public CursoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param cause
	 */
	public CursoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
