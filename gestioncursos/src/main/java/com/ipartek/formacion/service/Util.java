package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojo.DuracionModulo;
import com.ipartek.formacion.pojo.Genero;
import com.ipartek.formacion.pojo.Idioma;
import com.ipartek.formacion.pojo.TipoCurso;

public class Util {
	private Util() {
	}

	private static final int LONGITUD_DNI = 9;

	public static boolean validarDni(String dni) {
		boolean valido = false;
		dni = dni.toUpperCase();

		int nDni = Integer.parseInt(dni.substring(0, LONGITUD_DNI - 1));
		char lDni = dni.substring(dni.length() - 2, dni.length() - 1).charAt(0);
		if (calcularLetra(nDni) == lDni) {
			valido = true;
		}

		return valido;
	}

	private static char calcularLetra(int nDni) {
		char letra = 0;
		final char[] letras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P',
				'D', 'X', 'B', 'N', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K',
				'E', 'T' };
		letra = letras[nDni % letras.length];
		return letra;
	}

	public static Genero parseGenero(String genero) {
		Genero aux = Genero.MASCULINO;
		int codigo = Integer.parseInt(genero);
		if (codigo == Genero.FEMENINO.getCodigo()) {
			aux = Genero.FEMENINO;
		}
		return aux;
	}

	public static DuracionModulo parseDuracion(String duracion) {
		DuracionModulo d = DuracionModulo.HORAS15;
		int codigo = Integer.parseInt(duracion);

		if (codigo == DuracionModulo.HORAS20.getCodigo()) {
			d = DuracionModulo.HORAS20;
		} else {
			if (codigo == DuracionModulo.HORAS45.getCodigo()) {
				d = DuracionModulo.HORAS45;
			} else {
				if (codigo == DuracionModulo.HORAS80.getCodigo()) {
					d = DuracionModulo.HORAS80;
				} else {
					if (codigo == DuracionModulo.HORAS90.getCodigo()) {
						d = DuracionModulo.HORAS90;
					}

				}
			}
		}

		return d;
	}

	public static TipoCurso parseTipoCurso(String codigo) {
		TipoCurso tipo = TipoCurso.LANBIDE;
		int cod = Integer.parseInt(codigo);
		if (TipoCurso.FUNDACION_TRIPARTITA.getCodigo() == cod) {
			tipo = TipoCurso.FUNDACION_TRIPARTITA;
		} else {
			if (TipoCurso.HOBETUZ.getCodigo() == cod) {
				tipo = TipoCurso.HOBETUZ;
			}
		}

		return tipo;
	}

	public static Idioma parseIdioma(String idioma) {

		Idioma auxiliar = Idioma.CASTELLANO;
		int codigoIdioma = Integer.parseInt(idioma);
		if (codigoIdioma == Idioma.EUSKERA.getCodigo()) {
			auxiliar = Idioma.EUSKERA;
		} else {
			if (codigoIdioma == Idioma.INGLES.getCodigo()) {
				auxiliar = Idioma.INGLES;
			}
		}

		return auxiliar;
	}

	public static List<Idioma> parseIdioma(String[] idiomas) {
		List<Idioma> aux = null;
		aux = new ArrayList<Idioma>();
		for (String idioma2 : idiomas) {
			Idioma idioma = Idioma.CASTELLANO;
			int codigoIdioma = Integer.parseInt(idioma2);
			if (codigoIdioma == Idioma.EUSKERA.getCodigo()) {
				idioma = Idioma.EUSKERA;
			} else {
				if (codigoIdioma == Idioma.INGLES.getCodigo()) {
					idioma = Idioma.INGLES;
				}
			}
			aux.add(idioma);
		}
		return aux;
	}

	public static boolean tryParseInt(String cadena) {
		boolean exito = true;

		try {
			Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			exito = false;
		}
		return exito;
	}
}
