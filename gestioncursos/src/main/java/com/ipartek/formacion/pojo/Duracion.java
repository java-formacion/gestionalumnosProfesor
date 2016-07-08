package com.ipartek.formacion.pojo;

public enum Duracion {
	QUINCE(1,15),
	VEINTE(2,20),
	CUARENTAYCINCO(3,45),
	OCHENTA(4,80),
	NOVENTA(5,90);
	
	private int codigo;
	private int valor;
	
	private Duracion(int codigo, int valor) {
		this.codigo = codigo;
		this.valor = valor;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	
	
	
}
