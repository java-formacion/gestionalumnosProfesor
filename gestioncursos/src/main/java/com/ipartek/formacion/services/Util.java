package com.ipartek.formacion.services;

import java.util.ArrayList;
import java.util.List;

public class Util {

	String dni;

	public static boolean validarDni(String dni) {
		boolean bool = false;
		dni = dni.toUpperCase();
		int numero = 0;
		int resto = 0;
		// comprobar validez Dni
		char[] letraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };
		numero = Integer.parseInt(dni.substring(0, dni.length() - 1));
		resto = numero % 23;
		if (letraDni[resto] == dni.substring(dni.length() - 1).charAt(0)) {
			bool = true;
		}

		return bool;
	}

	public static List<Idioma> parseIdioma(String[] idiomas) {
		List<Idioma> aux = null;
		aux = new ArrayList<Idioma>();
		for (int i = 0; i < idiomas.length; i++) {
			Idioma idioma = Idioma.CASTELLANO;
			int codigoIdioma = Integer.parseInt(idiomas[i]);
			if (codigoIdioma == Idioma.EUSKERA.getCodigo())
				idioma = Idioma.EUSKERA;
			else if (codigoIdioma == Idioma.INGLES.getCodigo())
				idioma = Idioma.INGLES;

			aux.add(idioma);
		}
		return aux;
	}

	public static boolean tryParseInt(String cadena) {
		boolean exito = true;
		int numero;
		try {
			numero = Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			exito = false;
		}
		return exito;
	}

	public static Genero parseGenero(String genero) {
		Genero aux = Genero.MASCULINO;
		int codigo = Integer.parseInt(genero);
		if (codigo == Genero.FEMENINO.getCodigo())
			aux = Genero.FEMENINO;
		return aux;
	}

	public static Horas parseDuracion(String duracion) {
		Horas d = Horas.QUINCE;
		int codigo = Integer.parseInt(duracion);
		if (codigo == Horas.VEINTE.getCodigo()) {
			d = Horas.VEINTE;
		} else {
			if (codigo == Horas.CUARENTAYCINCO.getCodigo()) {
				d = Horas.CUARENTAYCINCO;
			} else {
				if (codigo == Horas.OCHENTA.getCodigo()) {
					d = Horas.OCHENTA;
				} else {
					if (codigo == Horas.NOVENTA.getCodigo()) {
						d = Horas.NOVENTA;
					}

				}
			}
		}

		return d;
	}

	public static TipoCurso parseTipo(String tipo) {
		TipoCurso tc = TipoCurso.LANBIDE;
		int codigo = Integer.parseInt(tipo);
		if (codigo == TipoCurso.LANBIDE.getCodigo()) {
			tc = TipoCurso.LANBIDE;
		} else {
			if (codigo == TipoCurso.HOBETUZ.getCodigo()) {
				tc = TipoCurso.HOBETUZ;
			} else {
				if (codigo == TipoCurso.TRIPARTITO.getCodigo()) {
					tc = TipoCurso.TRIPARTITO;
				}
			}
		}

		return tc;
	}
}
