package com.ipartek.formacion.pojo;

import java.util.Date;

public class Usuario {

	private String user;
	private String password;
	private String nickname;
	private String sessionId;
	private Date fechaConexion;
	
	
	

	public Usuario() {
		super();
		
		setUser("");
		setPassword("");
		setNickname("");
		setSessionId("");
		this.fechaConexion=new Date();
	}
	
	

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Date getFechaConexion() {
		return fechaConexion;
	}

	
	
}
