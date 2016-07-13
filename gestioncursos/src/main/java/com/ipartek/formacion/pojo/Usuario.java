package com.ipartek.formacion.pojo;

import java.util.Date;

public class Usuario {
	private String userName;
	private String userPassword;
	private String nickname;
	private String sessionid;
	private Date fConexion;
	
	public Usuario(){
		setSessionid("");
		setUserName("");
		setUserPassword("");
		setNickname("");
		this.setfConexion(new Date());
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Date getfConexion() {
		return fConexion;
	}

	public void setfConexion(Date fConexion) {
		this.fConexion = fConexion;
	}
}
