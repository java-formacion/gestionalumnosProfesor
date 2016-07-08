package com.ipartek.formacion.services.gestionficheros;

import java.io.Serializable;
import java.util.List;

public interface GestionFicherosService {
	
	public List<Serializable> leerFichero();//T es generico, como no sabemos que devuelve, usamos este caracter comodin, cualquier clase
	//public List<T> leerFichero(); tambien nos sirve
	public void guardarFichero(List<Serializable> lista);

}
