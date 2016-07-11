package com.ipartek.formacion.pojo.excepciones;

public class CandidatoException extends Exception{
	
	public static final int CODIGO_ERROR_FECHA_NACIMIENTO = 100;
	public static final String MSG_ERROR_FECHA_NACIMIENTO = "La fecha introducida no es válida";
	public static final int CODIGO_ERROR_DNI = 105;
	public static final String MSG_ERROR_DNI = "La letra del dni no es correcta";
	private int codigo;
	private String mensaje;
	
	public CandidatoException(int codigo, String mensaje) {
		super();
		this.codigo=codigo;
		this.mensaje=mensaje;
		
	}
	
	public CandidatoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
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
	
	
	@Override
	public String getMessage() {
		
		return this.mensaje;
	}
	
	
	
	

}
