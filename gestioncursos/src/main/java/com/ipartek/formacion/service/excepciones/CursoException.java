package com.ipartek.formacion.service.excepciones;

public class CursoException extends Exception{
	
	public static final int CODIGO_ERROR_CURSO_INDEX = 102;
	public static final String MSG_ERROR_CURSO_INDEX = "No existe el curso";
	private int codigo;
	private String mensaje;
	
	public CursoException(int codigo,String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		
	}
	
	public CursoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
	public CursoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public CursoException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public CursoException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		
		return this.mensaje;
	}

}
