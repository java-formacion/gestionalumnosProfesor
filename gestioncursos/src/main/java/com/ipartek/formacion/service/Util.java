package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Duracion;
import com.ipartek.formacion.pojo.Idioma;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.TipoCurso;

public class Util {
	private static final int LONGITUD_DNI = 9;
	private static AlumnoService aService = AlumnoServiceImp.getInstance();
	private static ModuloService mService = ModuloServiceImp.getInstance();

	public static boolean validarDni(String dni) {

		char[] letras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D',
				'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K',
				'E', 'T' };
		boolean valido = false;
		dni = dni.toUpperCase();
		if (dni.length() == 9) {
			char letra = dni.charAt(LONGITUD_DNI - 1);
			int numero = Integer.parseInt(dni.substring(0, LONGITUD_DNI - 1));

			if (letras[numero % 23] == letra) {
				valido = true;
			}
		}
		return valido;
	}

	public static Genero parseGenero(String codGenero) {
		Genero genero = Genero.OTROS;
		int codigo = Integer.parseInt(codGenero);
		if (codigo == Genero.MASCULINO.getCodigo()) {
			genero = Genero.MASCULINO;
		} else {
			if (codigo == Genero.FEMENINO.getCodigo()) {
				genero = Genero.FEMENINO;
			}
		}
		return genero;
	}

	public static Duracion parseDuracion(String codDuracion) {
		Duracion duracion = Duracion.H15;
		int codigo = Integer.parseInt(codDuracion);
		if (codigo == Duracion.H20.getCodigo()) {
			duracion = Duracion.H20;
		} else {
			if (codigo == Duracion.H45.getCodigo()) {
				duracion = Duracion.H45;
			} else {
				if (codigo == Duracion.H80.getCodigo()) {
					duracion = Duracion.H80;
				} else {
					if (codigo == Duracion.H90.getCodigo()) {
						duracion = Duracion.H90;
					}
				}
			}
		}
		return duracion;
	}

	public static TipoCurso parseTipo(String codTipo) {
		TipoCurso tipo = TipoCurso.LANBIDE;
		int codigo = Integer.parseInt(codTipo);
		if (codigo == TipoCurso.HOBETUZ.getCodigo()) {
			tipo = TipoCurso.HOBETUZ;
		} else {
			if (codigo == TipoCurso.FUNDACION.getCodigo()) {
				tipo = TipoCurso.FUNDACION;
			}
		}

		return tipo;
	}

	public static List<Idioma> parseIdiomas(String[] idiomas) {
		List<Idioma> aux = null;
		aux = new ArrayList<Idioma>();
		for (int i = 0; i < idiomas.length; i++) {
			if (tryParseInt(idiomas[i])) {
				Idioma idioma = Idioma.CASTELLANO;
				int codigoIdioma = Integer.parseInt(idiomas[i]);
				if (codigoIdioma == Idioma.EUSKERA.getCodigo()) {
					idioma = Idioma.EUSKERA;
				} else {
					if (codigoIdioma == Idioma.INGLES.getCodigo()) {

						idioma = Idioma.INGLES;
					}
				}
				aux.add(idioma);
			}
		}
		return aux;
	}

	public static Idioma parseIdioma(String codIdiomas) {
		Idioma idioma = null;
		if (tryParseInt(codIdiomas)) {
			idioma = Idioma.CASTELLANO;
			int codigoIdioma = Integer.parseInt(codIdiomas);
			if (codigoIdioma == Idioma.EUSKERA.getCodigo()) {
				idioma = Idioma.EUSKERA;
			} else {
				if (codigoIdioma == Idioma.INGLES.getCodigo()) {

					idioma = Idioma.INGLES;
				}
			}
		}

		return idioma;
	}

	public static Map<String, Alumno> parseAlumnos(String[] alumnos) {
		Map<String, Alumno> aux = null;
		aux = new HashMap<String, Alumno>();
		if (alumnos != null) {
			for (String codAlumno : alumnos) {
				if (tryParseInt(codAlumno)) {
					int codigo = Integer.parseInt(codAlumno);
					Alumno alumno = null;
					alumno = aService.getById(codigo);
					if (alumno != null) {
						aux.put(alumno.getDni(), alumno);
					}
				}

			}
		}
		return aux;
	}

	public static Map<Integer, Modulo> parseModulos(String[] modulos) {
		Map<Integer, Modulo> aux = null;
		aux = new HashMap<Integer, Modulo>();
		if (modulos != null) {
			for (int i = 0; i < modulos.length; i++) {
				if (tryParseInt(modulos[i])) {
					int codigo = Integer.parseInt(modulos[i]);
					Modulo modulo = null;
					modulo = mService.getById(codigo);
					if (modulo != null) {
						aux.put(modulo.getCodigo(), modulo);
					}
				}

			}
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
