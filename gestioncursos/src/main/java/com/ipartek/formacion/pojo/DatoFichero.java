package com.ipartek.formacion.pojo;

public class DatoFichero {
	private String ruta;
	private String extension;
	private String nombreFichero;
	
	
	//CONSTRUCTOR (inicializar siempre los atributos)
	public DatoFichero(){
		super();
		setRuta("");
		setExtension("");
		setNombreFichero("");
	}
	
	//GETTERS Y SETTERS
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getNombreFichero() {
		return nombreFichero;
	}
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	
	

}
