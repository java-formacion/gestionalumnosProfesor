package com.ipartek.formacion.service;

public class Util {
	private static final int LONGITUD_DNI = 9;
	public static boolean validarDni(String dni){
		boolean valido = true;
		dni = dni.toUpperCase();
		
		int nDni = Integer.parseInt(dni.substring(0, LONGITUD_DNI-1));
		char lDni = dni.substring(dni.length()-2, dni.length()-1).charAt(0);
		if(calcularLetra(nDni)==lDni){
			valido = true;
		}
			
		return valido;
	}
	private static char calcularLetra(int nDni){
		char letra = 0;
		final char [] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'};
		letra = letras[nDni % letras.length];
		return letra;
	}
}
