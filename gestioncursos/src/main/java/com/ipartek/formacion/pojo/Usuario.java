package com.ipartek.formacion.pojo;

import java.util.Date;

public class Usuario {
	public static final int ID_USUARIO = -1;
	private String sessionId;
	private String alias;
	private String password;
	private Date fechaConexion;

	public Usuario() {
		setIdSession("");
		setAlias("");
		setPassword("");
		setFechaConexion(new Date());

	}

	public Date getFechaConexion() {
		return fechaConexion;
	}

	public void setFechaConexion(Date fechaConexion) {
		this.fechaConexion = fechaConexion;
	}

	public String getIdSession() {
		return sessionId;
	}

	public void setIdSession(String idUsuario) {
		this.sessionId = idUsuario;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
