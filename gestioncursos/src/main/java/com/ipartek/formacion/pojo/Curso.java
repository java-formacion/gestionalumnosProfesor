package com.ipartek.formacion.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ipartek.formacion.service.Idiomas;

public class Curso {
	public static final int CODIGO_CURSO = -1;
	private int codigo;
	private String nombre;
	private TipoCurso tipo;
	private String referencia;
	
	private Map<Integer,Modulo>modulos; //el indice no puede ser int, suele ser Integer o String
	private Map<String, Alumno>alumnos; //mapa de alumnos con tipo de clave String que será el DNI
	/*
	 * Crear mapa de alumnos con clave DNI(String)
	 * La clase ServiceCurso(Interfaz) y ServiceCursoImp :
	 * ----darDeAlta (int codigo, Alumno alumno) void
	 * ----darDeBaja (int codigo,String dni)
	 */
	
	
	//constructor
	public Curso() {
		super();
		setCodigo(CODIGO_CURSO);
		setNombre("");
		setReferencia("");
		modulos = new HashMap<Integer, Modulo>(); //definimos el TIPO (HashMap) de mapa que queremos
		alumnos = new HashMap<String, Alumno>(); //definimos el TIPO (HashMap) de mapa que queremos
	}
	
	//getters y setters
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

	public Map<Integer, Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(Map<Integer, Modulo> modulos) {
		this.modulos = modulos;
	}

	public Map<String, Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Map<String, Alumno> alumnos) {
		this.alumnos = alumnos;
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

	@Override //método para comparar dos objetos según lo que queramos
	public boolean equals(Object obj) {
		boolean igual = false;
		if(obj instanceof Curso){
			Curso c = (Curso) obj; //hago el castin
			if(c.getCodigo()==this.codigo) 
			{
				igual = true;
			}
		}
		
		return igual;
	}
	
	
	

}
