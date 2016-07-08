package com.ipartek.formacion.pojo;

public enum Idioma {
	CASTELLANO(1,"castellano","es_ES","es"),
	EUSKERA(2,"euskera","es_EU","eu"),
	INGLES(3,"english","en_EN","en");
	private int codigo;
	private String nombre;
	private String locale;
	private String codIdioma;
	Idioma(int codigo,String nombre,String locale,String codIdioma){
		this.codigo = codigo;
		this.nombre = nombre;
		this.locale = locale;
		this.codIdioma = codIdioma;
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
}
