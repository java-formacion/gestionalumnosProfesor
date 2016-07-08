package com.ipartek.formacion.pojo;

public enum Genero {
	MASCULINO(1,"Hombre"),
	FEMENINO(2,"Mujer"),
	OTROS(3,"Indefinido");
	
	private int codigo;
	private String valor;
	Genero(int codigo,String valor){
		this.codigo = codigo;
		this.valor = valor;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
