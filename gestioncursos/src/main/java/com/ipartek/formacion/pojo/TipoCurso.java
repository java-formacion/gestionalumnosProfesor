package com.ipartek.formacion.pojo;

public enum TipoCurso {
	LANBIDE(1,"Lanbide"), 
	HOBETUZ(2, "Hobetuz"),
	FORMACION_TRIPARTITA(3, "Formacion Tripartita");
	
	private int codigo;
	private String valor;
	
	TipoCurso(int codigo, String valor){
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
