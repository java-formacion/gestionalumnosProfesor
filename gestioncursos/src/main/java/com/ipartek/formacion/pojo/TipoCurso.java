package com.ipartek.formacion.pojo;

public enum TipoCurso {
	FUNDACION_TRIPARTITA(1,"Fundación Tripartita"),
	HOBETUZ(2,"Hobetuz"),
	LANBIDE(3,"Lanbide");
	private int codigo;
	private String nombre;
	
	TipoCurso(int codigo, String nombre){
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
