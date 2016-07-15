package com.ipartek.formacion.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Curso
 *
 */
public class Curso {
  public static final int CODIGO_CURSO = -1;
  private int codigo;
  private String nombre;
  private Map<Integer, Modulo> modulos;
  private Map<String, Alumno> alumnos;
  private TipoCurso tipo;
  private String referencia;

  /**
   * 
   * Max alumnos: 15 Mapa de Alumnos dni (String) CursoService(I) --> Imp : darDeAlta (Alumno
   * alumno) void darDeBaja (String dni) void
   * 
   */
  public Curso() {
    super();
    setCodigo(CODIGO_CURSO);
    setNombre("");
    setTipo(TipoCurso.LANBIDE);
    setReferencia("");
    modulos = new HashMap<Integer, Modulo>();
    alumnos = new HashMap<String, Alumno>();
  }

  /**
   * 
   * @return codigo
   */
  public int getCodigo() {
    return codigo;
  }

  /**
   * 
   * @param codigo
   *          codigo
   */
  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  /**
   * 
   * @return nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * 
   * @param nombre
   *          nombre del curso
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * 
   * @return map de modulos
   */
  public Map<Integer, Modulo> getModulos() {
    return modulos;
  }

  /**
   * 
   * @param modulos
   *          map de modulos
   */
  public void setModulos(Map<Integer, Modulo> modulos) {
    this.modulos = modulos;
  }

  /**
   * 
   * @return map de alumnos
   */
  public Map<String, Alumno> getAlumnos() {
    return alumnos;
  }

  /**
   * 
   * @param alumnos
   *          map de alumnos
   */
  public void setAlumnos(Map<String, Alumno> alumnos) {
    this.alumnos = alumnos;
  }

  /**
   * 
   * @return tipo
   */
  public TipoCurso getTipo() {
    return tipo;
  }

  /**
   * 
   * @param tipo
   *          TipoCurso
   */
  public void setTipo(TipoCurso tipo) {
    this.tipo = tipo;
  }

  /**
   * 
   * @return referencia
   */
  public String getReferencia() {
    return referencia;
  }

  /**
   * 
   * @param referencia
   *          String
   */
  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  /**
   * @Override
   * 
   * @param obj
   *          Object
   * @return igual
   */
  public boolean equals(Object obj) {
    boolean igual = false;
    if (obj instanceof Curso) {
      Curso c = (Curso) obj;
      if (this.codigo == c.getCodigo()) {
        igual = true;
      }
    }
    return igual;
  }

  /**
   * @Override
   * 
   * 
   * @return codigoHash
   */
  public int hashCode() {
    return super.hashCode();
  }

}
