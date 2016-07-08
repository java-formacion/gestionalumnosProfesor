package com.ipartek.formacion.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ipartek.formacion.services.Genero;
import com.ipartek.formacion.services.Idioma;
import com.ipartek.formacion.services.TipoCurso;

public class Curso {
	public static final int CODIGO_CURSO = -1;
	protected int codigo;
	protected String nombre, referencia;
	protected TipoCurso tc;
	private Map<Integer,Modulo>modulos;
	private Map<String,Alumno>alumnos;
	/*Mapa de Alumnos dni (String)
	 * ServiceCurso(I) ---> Imp darDeAlta (int codigo,Alumno alumno) void
	 * 					---> Imp darDeBaja	(int codigo,String dni) void
	 * 
	 */
	public Curso() {
		super();
		setCodigo(CODIGO_CURSO);
		setNombre("");
		setReferencia("RF432");
		setTc(tc.LANBIDE);
		modulos = new HashMap<Integer, Modulo>();
		alumnos = new HashMap<String, Alumno>();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean bool=false;
		if (obj instanceof Curso){
			Curso c = (Curso) obj;
			if (c.getCodigo()==this.codigo){
				bool=true;
			}
		}
		return bool;
	}

	public Map<String, Alumno> getAlumnos() {
		return alumnos;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public void setAlumnos(Map<String, Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Map<Integer, Modulo> getModulos() {
		return modulos;
	}

	public TipoCurso getTc() {
		return tc;
	}

	public void setTc(TipoCurso tc) {
		this.tc = tc;
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
}
