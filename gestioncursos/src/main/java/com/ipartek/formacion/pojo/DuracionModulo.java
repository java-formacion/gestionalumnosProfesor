package com.ipartek.formacion.pojo;

public enum DuracionModulo {
	HORAS15(1, 15), 
	HORAS20(2, 20),
	HORAS30(3, 30),
	HORAS45(4, 45),
	HORAS80(5, 80),
	HORAS90(6, 90);
	
	private int codigo;
	private int valor;
	
	DuracionModulo(int codigo, int valor){
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
