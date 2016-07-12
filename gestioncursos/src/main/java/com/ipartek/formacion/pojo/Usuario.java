package com.ipartek.formacion.pojo;

public class Usuario {
	private String sessionId;
	private String userName;
	private String userPassword;
	private String nickname;

	public Usuario(){
		setUserName("");
		setUserPassword("");
		setNickname("");
	}

	

	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
