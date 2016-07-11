package com.ipartek.formacion.pojo;

public class Mensaje {
	public static final String MSG_TYPE_ERROR = "danger";
	public static final String MSG_TYPE_WARNING = "warning";
	public static final String MSG_TYPE_SUCCESS = "success";
	public static final String MSG_TYPE_INFO = "info";
	private String msg;
	private String tipo;

	public Mensaje() {
		setMsg("");
		setTipo("");
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
