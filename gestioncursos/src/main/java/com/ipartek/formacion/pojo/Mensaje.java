package com.ipartek.formacion.pojo;

public class Mensaje {
	public static final String MSG_TYPE_SUCCESS = "success";
	public static final String MSG_TYPE_INFO = "info";
	public static final String MSG_TYPE_WARNING = "warning";
	public static final String MSG_TYPE_DANGER = "danger";


	String msg; // literal del mensaje
	String type; // tipo de mensaje [success,info,warning,danger]
	int code; // codigo http


	public Mensaje() {
		super();
		setCode(500);
		setMsg("");
		setType(MSG_TYPE_DANGER);
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