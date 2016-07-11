package com.ipartek.formacion.pojo;

import java.util.HashMap;
import java.util.Map;

public class Curso {
	public static final int CODIGO_CURSO = -1;
	private int codigo;
	private String nombre;
	private Map<Integer,Modulo>modulos;
	private Map<String,Alumno>alumnos;
	private TipoCurso tipo;
	private String referencia;
	/*Mapa de Alumnos dni (String)
	 * ServiceCurso(I) ---> Imp darDeAlta (int codigo,Alumno alumno) void
	 * 					---> Imp darDeBaja	(int codigo,String dni) void
	 * 
	 */
	public Curso() {
		super();
		setCodigo(CODIGO_CURSO);
		setNombre("");
		modulos = new HashMap<Integer,Modulo>();
		alumnos = new HashMap<String, Alumno>();
		setTipo(TipoCurso.LANBIDE);
		setReferencia("");
	}
	
	public Map<String, Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Map<String, Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Map<Integer, Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(Map<Integer, Modulo> modulos) {
		this.modulos = modulos;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoCurso getTipo() {
		return tipo;
	}

	public void setTipo(TipoCurso tipo) {
		this.tipo = tipo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}
