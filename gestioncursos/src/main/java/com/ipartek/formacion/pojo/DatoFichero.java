package com.ipartek.formacion.pojo;

public class DatoFichero {

	private String ruta;
	private String extension;
	private String nFichero;

	/**
	 * 
	 */
	public DatoFichero() {
		super();
		setRuta("");
		setExtension("");
		setnFichero("");
	}

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

	public String getnFichero() {
		return nFichero;
	}

	public void setnFichero(String nFichero) {
		this.nFichero = nFichero;
	}

}
