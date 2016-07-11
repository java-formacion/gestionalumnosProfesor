package com.ipartek.formacion.service.excepciones;

public class ModuloException extends Exception{
	
	public static final int CODIGO_ERROR_INDEX = 101;
	public static final String MSG_ERROR_INDEX = "No existe el modulo";
	private int codigo;
	private String mensaje;
	
	
	public ModuloException(int codigo,String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		
	}
	
	public ModuloException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
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
	
	public ModuloException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		
		return this.getMessage();
	}
	
	
	
	

}
