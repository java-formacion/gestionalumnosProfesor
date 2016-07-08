/**
 * 
 */
package com.ipartek.formacion.pojo.exception;

/**
 * @author Curso
 *
 */
public class CandidatoException extends Exception{

	public static final int CODIGO_ERROR_FECHA_NACIMIENTO =100;
	public static final String MSG_ERROR_FECHA_NACIMIENTO="La fecha introducida no es valida.";
	public static final int CODIGO_ERROR_DNI_INCORRECTO = 200;
	public static final String MSG_ERROR_DNI_INCORRECTO ="El DNI introducido no es válido";
	private int codigo;
	private String mensaje;
	
	public CandidatoException(int codigo, String mensaje){
		super(mensaje);
		this.codigo=codigo;
		this.mensaje=mensaje;
		
		
	}
		

	public CandidatoException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
		super(message, cause, enableSupression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	
	public CandidatoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public CandidatoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public CandidatoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
