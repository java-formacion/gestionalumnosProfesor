package com.ipartek.formacion.services;

public enum TipoCurso {
	
	LANBIDE(1,"Lanbide"),
	HOBETUZ(2,"Hobetuz"),
	TRIPARTITO(3,"Fundación tripartita");
	
	private int codigo;
	private String nombre;
	/**
	 * @param codigo
	 * @param nombre
	 */
	private TipoCurso(int codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
		
	
}
