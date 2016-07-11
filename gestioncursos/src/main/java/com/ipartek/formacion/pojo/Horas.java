package com.ipartek.formacion.pojo;

public enum Horas {
	
	h15(1,15),h20(2,20), h45(3,45), h80(4,80),h90(5,90);
	
	private int codigo;
	private int duracion;
	Horas(int codigo, int duracion){
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
