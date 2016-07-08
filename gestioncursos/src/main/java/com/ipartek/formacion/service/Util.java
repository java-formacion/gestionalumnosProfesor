package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ipartek.formacion.pojo.Duracion;
import com.ipartek.formacion.pojo.Genero;
import com.ipartek.formacion.pojo.Idioma;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.exception.ModuloException;

public class Util {

	private static final int DNI_MAX=99999999;
	private static final int DNI_SIZE=9;
	private static final int DIVIS=23;
	/**
	 * excepcion 
	 * alumnoserviceimp
	 * moduloserviceimp
	 * cursoserviceImp
	 * 		Get Index not -1
	 * @param lInf
	 * @param lSup
	 * @return
	 */
	
	public static int generarRandom(int lInf, int lSup){
		int random=-1;
		
		Random r=new Random();
		random=r.nextInt(lSup)+lInf;
		
		
		return random;
	}
	
	public static int numeroDni(){
		int dni=-1;
		
		dni=generarRandom(0,DNI_MAX);
				
		return dni;
	}
	
	public static String letraDni (int dni){
		
		String letra=null;
		int indice=dni%23;
		
		String [] letras={"t","r","w","a","g","m","y","f","p","d","x","b","n","j","z","s","q","v","h","l","c","k","e"};
		letra=letras[indice].toUpperCase();
			
		return letra;
	}
	
	public static String generaDni(){
		String dni=null;
		int nDni=numeroDni();
		String lDni=letraDni(nDni);
		dni=Integer.toString(nDni)+lDni;
		return dni;
	}
	
	public static boolean validarDni(String dni){
		boolean valido=true;
		String letra=dni.substring(8);
		int numero=Integer.parseInt(dni.substring(0, 8));
		
		if (DNI_SIZE!=dni.length()||!letra.equals(letraDni(numero))) {
			valido=false;
		}	
		return valido;
	}
	
	public static boolean contieneString(String contenedor, String contenido){
		boolean contiene=true;
		
		
		
		return contiene;
	}
	public static boolean empiezaPor(String contenedor, String contenido){
		boolean contiene = true;
		
		return contiene;
	}
	
	public static List<Idioma> parseIdioma(String []idiomas){
		List<Idioma> lIdioma=null;
		lIdioma=new ArrayList<Idioma>();
		for (String idi : idiomas) {
			Idioma idioma=Idioma.CASTELLANO;
			int codIdioma=Integer.parseInt(idi);
			if (codIdioma==Idioma.EUSKERA.getCodigo()) {
				idioma=Idioma.EUSKERA;
			}else if (codIdioma==Idioma.INGLES.getCodigo()) {
				idioma=Idioma.INGLES;
			}else if (codIdioma==Idioma.CASTELLANO.getCodigo()) {
				idioma=Idioma.CASTELLANO;
			}
			
			
			
			lIdioma.add(idioma);
		}
		return lIdioma;
	}
	
	public static Genero parseGenero(String gen){
		Genero genero=Genero.FEMENINO;;
		if (Genero.MASCULINO.getValor().equals(gen)) {
			genero=Genero.MASCULINO;
		}
		return genero;
	}
	
	public static Duracion parseDuracion(String duracion){
		Duracion d=Duracion.QUINCE;
		int val=Integer.parseInt(duracion);
		if (Duracion.VEINTE.getValor()==val) {
			d=Duracion.VEINTE;
		}else if (Duracion.CUARENTAYCINCO.getValor()==val) {
			d=Duracion.CUARENTAYCINCO;	
		}else if (Duracion.OCHENTA.getValor()==val) {
			d=Duracion.OCHENTA;
		}else if (Duracion.NOVENTA.getValor()==val) {
			d=Duracion.NOVENTA;
		}
		
		return d;
		
	}
	
	public static Map<Integer,Modulo> parseModulos(String [] cods) throws NumberFormatException, ModuloException{
		Map<Integer,Modulo>aux=new HashMap<Integer,Modulo>();
		Modulo m=null;
		ModuloService mS=ModuloServiceImp.getInstance();
		Integer c=-1;
		for (String s : cods) {
			c=Integer.parseInt(s);
			m=mS.getById(c);
			aux.put(c, m);	
			
		}
		return aux;
	}
	
	
	
	public static boolean tryParseInt(String str){
		boolean aux=true;
		int num;
		try {
			num=Integer.parseInt(str);
		} catch (NumberFormatException e) {
			aux=false;
		}
				
		return aux;
	}
	
}
