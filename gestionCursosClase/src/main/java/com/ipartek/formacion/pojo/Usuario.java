package com.ipartek.formacion.pojo;

public class Usuario {

	private String userName;
	private String userPassword;
	private String nickname;
	public String sessionID;
	public String Idioma;

	public Usuario(){
		setUserName("");
		setUserPassword("");
		setNickname("");
		setSessionID("");
		setIdioma("");
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
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public String getIdioma() {
		return Idioma;
	}

	public void setIdioma(String idioma) {
		Idioma = idioma;
	}
}
