package com.ipartek.formacion.pojo;

import java.util.Date;

public class Usuario {
	private String sessionid;
	public String getSessionid() {
		return sessionid;
	}




	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	private String userName;
	private String userPassword;
	private String nickname;
	private Date fConexion;
	public Usuario(){
		setSessionid("");
		setUserName("");
		setUserPassword("");
		setNickname("");
		this.fConexion = new Date();
	}




	public Date getfConexion() {
		return fConexion;
	}




	public void setfConexion(Date fConexion) {
		this.fConexion = fConexion;
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
}
