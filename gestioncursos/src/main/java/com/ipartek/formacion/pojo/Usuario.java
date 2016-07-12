package com.ipartek.formacion.pojo;

public class Usuario {
	private String user=null;
	private String pass=null;
	private String nick=null;
	private String sessionId=null;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * @param user
	 * @param pass
	 */
	public Usuario() {
		super();
		setNick("");
		setUser("");
		setPass("");
		setSessionId("");
	}
	
	public Usuario(String user, String pass, String nick, String sessionId) {
		super();
		setNick(nick);
		setUser(user);
		setPass(pass);
		setSessionId(sessionId);
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}
