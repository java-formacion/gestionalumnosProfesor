package com.ipartek.formacion.pojo.excepciones;

public class ModuloServiceException extends Exception {
	
	public static final int CODIGO_ERROR_INDEX=300;
	public static final String MSG_ERROR_INDEX="Modulo no encontrado";

	private int codigo;
	private String msg;
	/**
	 * 
	 */
	public ModuloServiceException(int codigo,String msg) {
		super();
		this.codigo=codigo;
		this.msg=msg;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ModuloServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public ModuloServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public ModuloServiceException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public ModuloServiceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.msg;
	}
}
