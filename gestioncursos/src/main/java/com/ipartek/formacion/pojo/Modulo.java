/**
 * 
 */
package com.ipartek.formacion.pojo;

import java.io.Serializable;

/**
 * @author Curso
 *
 */
public class Modulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_MODULO=-1;
	private int codigo;
	private String nombre;
	private String refModulo;
	private Duracion durModulo;
	
	
	/**
	 * 
	 */
	public Modulo() {
		super();
		setCodigo(CODIGO_MODULO);
		setNombre("");
		
	}
	/**
	 * @return the codigoModulo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombreModulo
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombreModulo the nombreModulo to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getRefModulo() {
		return refModulo;
	}
	public void setRefModulo(String refModulo) {
		this.refModulo = refModulo;
	}
	public Duracion getDurModulo() {
		return durModulo;
	}
	public void setDurModulo(Duracion durModulo) {
		this.durModulo = durModulo;
	}
	
	
	
	
	
}
