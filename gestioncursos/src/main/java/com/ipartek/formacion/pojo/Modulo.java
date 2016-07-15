package com.ipartek.formacion.pojo;

import java.io.Serializable;

import com.ipartek.formacion.services.Horas;

public class Modulo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_MODULO = -1;
	private int codigo;
	private String nombre;
	private String referencia;
	private Horas duracion;

	public Modulo() {
		super();
		setCodigo(CODIGO_MODULO);
		setNombre("");
		setReferencia("");
		this.duracion = Horas.OCHENTA;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Horas getDuracion() {
		return duracion;
	}

	public void setDuracion(Horas duracion) {
		this.duracion = duracion;
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

}