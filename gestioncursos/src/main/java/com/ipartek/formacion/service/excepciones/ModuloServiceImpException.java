package com.ipartek.formacion.service.excepciones;

public class ModuloServiceImpException extends Exception {
	public static final int CODIGO_ERROR_INDEX_INVALIDO = 45;
	public static final String MSG_ERROR_INDEX_INVALIDO = "No existe ningun modulo con ese codigo";
	private int codigo;
	private String mensaje;

	/**
	 * 
	 */
	public ModuloServiceImpException(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.mensaje;
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public ModuloServiceImpException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ModuloServiceImpException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ModuloServiceImpException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ModuloServiceImpException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
