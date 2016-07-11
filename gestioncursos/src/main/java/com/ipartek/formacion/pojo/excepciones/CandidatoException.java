package com.ipartek.formacion.pojo.excepciones;

public class CandidatoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_ERROR_FECHA_NACIMIENTO = 39;
	public static final String MSG_ERROR_FECHA_NACIMIENTO = "La fecha introducida no es válida";
	public static final int CODIGO_ERROR_DNI = 25;
	public static final String MSG_ERROR_DNI = "Dni invalido";
	private int codigo;
	private String mensaje;

	/**
	 * 
	 */
	public CandidatoException(int codigo, String mensaje) {
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
	public CandidatoException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CandidatoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public CandidatoException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public CandidatoException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
