package com.ipartek.formacion.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Curso {
	public static final int CODIGO_CURSO = -1;
	private int codigo;
	private String nombre;
	private String codPatrocinador;
	private String codTipoCurso;
	private String referencia;
	private Date finicio;
	private Date fFin;
	private String nombreTipoCurso;
	private TipoCurso tipo;
	/*
	 * 	private Map<Integer,Modulo>modulos;
	private Map<String,Alumno>alumnos;
	 * */


	
	
	/*Mapa de Alumnos dni (String)
	 * ServiceCurso(I) ---> Imp darDeAlta (int codigo,Alumno alumno) void
	 * 					---> Imp darDeBaja	(int codigo,String dni) void
	 * 
	 */
	public Curso() {
		super();
		setCodigo(CODIGO_CURSO);
		setNombre("");
		/*
		 * 		modulos = new HashMap<Integer,Modulo>();
		alumnos = new HashMap<String, Alumno>();
		 * */

		tipo = TipoCurso.LANBIDE;
		setReferencia("");
	}
	
	/*	public Map<String, Alumno> getAlumnos() {
		return alumnos;
	}
	 * 	public void setAlumnos(Map<String, Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	 public Map<Integer, Modulo> getModulos() {
    return modulos;
  }

  public void setModulos(Map<Integer, Modulo> modulos) {
    this.modulos = modulos;
  }
	 * */




	public TipoCurso getTipo() {
		return tipo;
	}

	public String getCodPatrocinador() {
    return codPatrocinador;
  }

  public void setCodPatrocinador(String codPatrocinador) {
    this.codPatrocinador = codPatrocinador;
  }

  public String getCodTipoCurso() {
    return codTipoCurso;
  }

  public void setCodTipoCurso(String codTipoCurso) {
    this.codTipoCurso = codTipoCurso;
  }

  public Date getFinicio() {
    return finicio;
  }

  public void setFinicio(Date finicio) {
    this.finicio = finicio;
  }

  public Date getfFin() {
    return fFin;
  }

  public void setfFin(Date fFin) {
    this.fFin = fFin;
  }

  public String getNombreTipoCurso() {
    return nombreTipoCurso;
  }

  public void setNombreTipoCurso(String nombreTipoCurso) {
    this.nombreTipoCurso = nombreTipoCurso;
  }

  public static int getCodigoCurso() {
    return CODIGO_CURSO;
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

	@Override
	public boolean equals(Object obj) {
		boolean igual = false;
		if(obj instanceof Curso){
			//Curso c = (Curso) obj;
			if(((Curso) obj).getCodigo()==this.codigo){
				igual = true;
			}
			
		}
		return igual;
	}
	
}
