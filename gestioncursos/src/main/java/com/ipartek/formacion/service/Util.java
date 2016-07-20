package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ipartek.formacion.pojo.Horas;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.TipoCurso;

public class Util {
	private static final int LONGITUD_DNI = 9;
	public static boolean validarDni(String dni){
		boolean x=false;
		dni = dni.toUpperCase();
		
		int nDni = Integer.parseInt(dni.substring(0, LONGITUD_DNI-1));
		char lDni = dni.charAt(LONGITUD_DNI-1);
		
		char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'};

		int resto = nDni % 23;
		
		if (lDni==letras[resto]){
			
			x=true;
		}
		
		return x;
	}
	
	public static Genero parseGenero(String genero){
		Genero aux = Genero.MASCULINO;
		int codigo = Integer.parseInt(genero);
		
		if(codigo == Genero.FEMENINO.getCodigo()){
			aux = Genero.FEMENINO;
		}
		
		return aux;
	}
	
	public static Horas parseHoras(String horas){
		Horas aux = Horas.h15;
		int codigo = Integer.parseInt(horas);
		
		if(codigo == Horas.h20.getCodigo()){
			aux = Horas.h20;
		}else{
			if(codigo == Horas.h45.getCodigo()){
				aux = Horas.h45;
			}else{
				if(codigo == Horas.h80.getCodigo()){
					aux = Horas.h80;
					}else{
						if(codigo == Horas.h90.getCodigo()){
							aux = Horas.h90;
						}
					}
				}
			}
		
		return aux;
	}
	
	public static TipoCurso parseTipoCurso(String codigo) {
		TipoCurso tipo = TipoCurso.LANBIDE;
		int cod = Integer.parseInt(codigo);
		
		if(cod == TipoCurso.HOBETUZ.getCodigo()){
			tipo = TipoCurso.HOBETUZ;
		}else{
			if(cod == TipoCurso.TRIPARTITA.getCodigo()){
				tipo = TipoCurso.TRIPARTITA;
			}
		}
		
		return tipo;
	}
	
	public static TipoCurso parseTipoCurso(int codigo) {
		TipoCurso tipo = TipoCurso.LANBIDE;
		
		if(codigo == TipoCurso.HOBETUZ.getCodigo()){
			tipo = TipoCurso.HOBETUZ;
		}else{
			if(codigo == TipoCurso.TRIPARTITA.getCodigo()){
				tipo = TipoCurso.TRIPARTITA;
			}
		}
		
		return tipo;
	}
	
	
	public static List<Idiomas> parseIdioma(String[] idiomas){
		List<Idiomas> aux = null;
		aux = new ArrayList<Idiomas>();
		for(int i=0;i<idiomas.length;i++)
		{
			Idiomas idioma = Idiomas.CASTELLANO;
			int codigoIdioma = Integer.parseInt(idiomas[i]); 
			if(codigoIdioma == Idiomas.EUSKERA.getCodigo())
			{
				idioma = Idiomas.EUSKERA;
			}else{
				if(codigoIdioma == Idiomas.INGLES.getCodigo())
			{
					idioma = Idiomas.INGLES;
			}
			
			aux.add(idioma);
		}
		
		}
		return aux;
	}
	
	public static Idiomas parseIdi(String idioma){
		Idiomas aux = null;
		aux = Idiomas.CASTELLANO;
		int cod = Integer.parseInt(idioma); 
		//System.out.println(codigoIdioma);
		
			if(cod == Idiomas.EUSKERA.getCodigo())
			{
				aux = Idiomas.EUSKERA;
			}else{
				if(cod == Idiomas.INGLES.getCodigo())
			{
					aux = Idiomas.INGLES;
			}
			}
		
		return aux;
	}
	

	
	
	//método para saber si al hacer parseint da error
	public static boolean tryParseInt(String cadena){
		boolean exito = true;
		try{
			Integer.parseInt(cadena);
		}catch(NumberFormatException e){
			exito = false;
		}
		
		return exito;
		
	}

	

	

}
	
