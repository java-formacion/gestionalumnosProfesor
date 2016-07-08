package com.ipartek.formacion.services.gestionficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.ipartek.formacion.pojo.DatoFichero;

public class GestionFicheroServiceImp implements GestionFicherosService {

	private DatoFichero datos;
	public GestionFicheroServiceImp(DatoFichero datos) {
		this.datos = datos;
	}
	
	public DatoFichero getDatos() {
		return datos;
	}

	public void setDatos(DatoFichero datos) {
		this.datos = datos;
	}

	@Override
	public List<Serializable> leerFichero() {
		List<Serializable> lista = null;
		String path = datos.getRuta()+File.pathSeparator+datos.getNombre()+File.pathSeparator+datos.getExtension();
		File archivo = new File(path);
		FileInputStream fileInput;
		ObjectInputStream input;
		try{ 
			fileInput = new FileInputStream(archivo);
			input = new ObjectInputStream(fileInput);
			lista =(List<Serializable>) input.readObject();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void guardarFichero(List<Serializable> lista) {
		
		String path = datos.getRuta()+File.pathSeparator+datos.getNombre()+File.pathSeparator+datos.getExtension();
		File archivo = new File(path);
		FileOutputStream fileoutput;
		ObjectOutputStream out;
		try{
			fileoutput = new FileOutputStream(archivo);
			out = new ObjectOutputStream(fileoutput);
			out.writeObject(lista);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}

	}

}
