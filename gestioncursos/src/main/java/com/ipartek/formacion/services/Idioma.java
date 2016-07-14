package com.ipartek.formacion.services;

public enum Idioma {
	CASTELLANO(1,"castellano","es_ES","es"),
	INGLES(2,"English","en_EN","en"),
	EUSKERA(3,"euskera","eu_ES","eu");
	
	private int codigo;
	private String nombre;
	private String locale;
	private String codIdioma;
	
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
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCodIdioma() {
		return codIdioma;
	}
	public void setCodIdioma(String codIdioma) {
		this.codIdioma = codIdioma;
	}
	/**
	 * @param codigo
	 * @param nombre
	 * @param locale
	 * @param codIdioma
	 */
	private Idioma(int codigo, String nombre, String locale, String codIdioma) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.locale = locale;
		this.codIdioma = codIdioma;
	}
	
	
	
	

}
