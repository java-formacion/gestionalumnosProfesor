package com.ipartek.formacion.pojo;

public enum DuracionModulo {

	HORAS15(1,15),
	HORAS20(2,20),
	HORAS45(3,45),
	HORAS80(4,80),
	HORAS90(5,90);
	private int codigo;
	private int duracionHoras;
	DuracionModulo(int codigo,int duracionHoras){
		this.codigo = codigo;
		this.duracionHoras = duracionHoras;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getDuracionHoras() {
		return duracionHoras;
	}
	public void setDuracionHoras(int duracionHoras) {
		this.duracionHoras = duracionHoras;
	}
}
