package com.ipartek.formacion.pojo;

public class Usuario {
	private String userName;
	private String userPassword;
	private String nickname;
	private String sessionid;//id para luego echar al usuario
	public Usuario(){
		setUserName("");
		setUserPassword("");
		setNickname("");
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
	
}
