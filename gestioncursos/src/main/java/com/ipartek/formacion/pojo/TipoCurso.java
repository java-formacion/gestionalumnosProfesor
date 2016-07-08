package com.ipartek.formacion.pojo;

public enum TipoCurso {

	LANBIDE(1,"Lanbide"),
	HOBETUZ(2,"Hobetuz"),
	FTRIPARTITA(3,"FormacionTripartita");
	
	private int codigo;
	private String valor;
	
	private TipoCurso(int codigo, String valor) {
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
