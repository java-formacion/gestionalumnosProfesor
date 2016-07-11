package com.ipartek.formacion.pojo;

public enum TipoCurso {
	LANBIDE(1, "Lanbide"), HOBETUZ(2, "Hobetuz"), FUNDACION(3,
			"Fundacion Tripartita");
	private int codigo;
	private String tipo;

	TipoCurso(int codigo, String tipo) {
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
