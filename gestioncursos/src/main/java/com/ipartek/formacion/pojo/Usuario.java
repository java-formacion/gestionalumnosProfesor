package com.ipartek.formacion.pojo;

public class Usuario {

	private String user;
	private String password;
	private String nickname;
	
	
	public Usuario() {
		super();
		
		setUser("");
		setPassword("");
		setNickname("");
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
	
	
	
}
