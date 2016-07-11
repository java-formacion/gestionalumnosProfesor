package com.ipartek.formacion.pojo;

public enum Duracion {
	H15(1, 15), H20(2, 20), H45(3, 45), H80(4, 80), H90(5, 90);
	private int codigo;
	private int duracion;

	Duracion(int codigo, int duracion) {
		this.codigo = codigo;
		this.duracion = duracion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

}
