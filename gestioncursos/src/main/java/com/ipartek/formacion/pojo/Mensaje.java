package com.ipartek.formacion.pojo;

public class Mensaje {
	public static final String MSG_TYPE_SUCCESS = "success";
	public static final String MSG_TYPE_INFO = "info";
	public static final String MSG_TYPE_WARNING = "warning";
	public static final String MSG_TYPE_ERROR = "error";
	public static final int QUINIENTOS = 500;

	String msg;
	String type;
	int code;

	public Mensaje() {
		super();
		setCode(QUINIENTOS);
		setMsg("");
		type = MSG_TYPE_WARNING;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
