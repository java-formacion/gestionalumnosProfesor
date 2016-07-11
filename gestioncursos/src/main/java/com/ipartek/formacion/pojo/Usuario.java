package com.ipartek.formacion.pojo;


public class Usuario {
	public static final int CODIGO_USU = -1;
	
	private int idUsuario;
	private String alias;
	private String password;
	
	public Usuario() {
		super();
		setIdUsuario(CODIGO_USU);
		setAlias("");
		setPassword("");
		
	}
	
	

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
