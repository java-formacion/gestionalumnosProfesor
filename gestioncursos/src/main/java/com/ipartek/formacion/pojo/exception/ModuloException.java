/**
 * 
 */
package com.ipartek.formacion.pojo.exception;

/**
 * @author Curso
 *
 */
public class ModuloException extends Exception {

	public static final int CODIGO_ERROR_INDEX_MODULO=102;
	public static final String MSG_ERROR_INDEX_MODULO="El índice del módulo no es válido";
	
	private int codigo;
	private String mensaje;
	/**
	 * 
	 */
	
	
	public ModuloException() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param codigo
	 * @param mensaje
	 */
	public ModuloException(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public ModuloException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public ModuloException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public ModuloException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public ModuloException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
