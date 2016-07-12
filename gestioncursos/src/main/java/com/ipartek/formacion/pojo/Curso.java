package com.ipartek.formacion.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Curso {
	public static final int CODIGO_CURSO = -1;
	private int codigo;
	private String nombre;
	private Map<Integer,Modulo>modulos;
	private Map<String,Alumno>alumnos;
	/*Mapa de Alumnos dni (String)
	 * ServiceCurso(I) ---> Imp darDeAlta (int codigo,Alumno alumno) void
	 * 					---> Imp darDeBaja	(int codigo,String dni) void
	 * 
	 */
	
	private TipoCurso tipoCurso;	
	protected List<Modulo> modul;
	private String referencia;
	
	public Curso() {
		super();
		setCodigo(CODIGO_CURSO);
		setNombre("");
		modulos = new HashMap<Integer,Modulo>();
		alumnos = new HashMap<String, Alumno>();
		setReferencia("");
	}	
	
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	@Override
	public boolean equals(Object obj) {
		boolean igual = false;
		
		if(obj instanceof Curso){
			Curso c = (Curso) obj;
			if(c.getCodigo()==this.codigo){
				igual = true;
			}
		}
		
		return igual;
	}

	public List<Modulo> getModul() {
		return modul;
	}

	public void setModul(List<Modulo> modul) {
		this.modul = modul;
	}
}
