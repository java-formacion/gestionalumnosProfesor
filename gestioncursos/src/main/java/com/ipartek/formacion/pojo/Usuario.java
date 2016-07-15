package com.ipartek.formacion.pojo;

public class Usuario {
	private String user = null;
	private String pass = null;
	private String nick = null;
	private String sessionId = null;
	private String idioma = null;

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

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
		setIdioma("");
	}

	public Usuario(String user, String pass, String nick, String sessionId, String idioma) {
		super();
		this.nick = nick;
		this.user = user;
		this.pass = pass;
		this.sessionId = sessionId;
		this.idioma = idioma;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
