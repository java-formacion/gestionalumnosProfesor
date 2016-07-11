package com.ipartek.formacion.pojo;

import java.util.List;

public class Modulo {
	public static final int CODIGO_MODULO = -1;
	
	private int codigo;
	private String nombre;
	private String referencia;
	private Horas horas;
	/**
	 * 
	 */
	public Modulo() {
		super();
		setCodigo(-1);
		setNombre("");
		setReferencia("");
		setHoras(Horas.h45);
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
	public Horas getHoras() {
		return horas;
	}
	public void setHoras(Horas horas) {
		this.horas = horas;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
	
	
	
	
}
