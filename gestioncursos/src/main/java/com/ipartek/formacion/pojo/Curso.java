/**
 * 
 */
package com.ipartek.formacion.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Curso
 *
 */
public class Curso {

	public static final int CODIGO_CURSO=-1;
	private int codigo;
	private String nombre;
	private Map<Integer,Modulo>modulos;
	private Map<String,Alumno>alumnos;
	private TipoCurso tipoCurso;
	private int duracionCurso;
	private String refCurso;
	
	/**
	 * Mapa de alumnos dni(String)
	 * ServiceAlumno(i)--->IMP
	 * void darDeAlta(alumno)/void dar de baja(String dni)
	 * 
	 */
	public Curso() {
		super();
		setCodigo(CODIGO_CURSO);
		setNombre("");
		modulos=new HashMap<Integer,Modulo>();
		alumnos=new HashMap<String,Alumno>();
	}
	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}
	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}
	public int getDuracionCurso() {
		return duracionCurso;
	}
	public void setDuracionCurso(int duracionCurso) {
		
		this.duracionCurso = duracionCurso;
	}
	public String getRefCurso() {
		return refCurso;
	}
	public void setRefCurso(String refCurso) {
		this.refCurso = refCurso;
	}
	/**
	 * @return the alumnos
	 */
	public Map<String, Alumno> getAlumnos() {
		return alumnos;
	}
	/**
	 * @param alumnos the alumnos to set
	 */
	public void setAlumnos(Map<String, Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	/**
	 * @return the modulos
	 */
	public Map<Integer, Modulo> getModulos() {
		return modulos;
	}
	/**
	 * @param modulos the modulos to set
	 */
	public void setModulos(Map<Integer, Modulo> modulos) {
		this.modulos = modulos;
	}
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public boolean equals(Object obj) {
		boolean igual=false;
		if (obj instanceof Curso) {
			Curso c=(Curso)obj;
			if (c.getCodigo()==this.codigo) {
				igual=true;
			}
		}
		return igual;
	}
	
	
}
