package com.ipartek.formacion.pojo;

/**
 * 
 * @author Curso
 *
 */
public class Modulo {
  public static final int CODIGO_MODULO = -1;
  private int codigo;
  private String nombre;
  private String uFormativa;
  private int duracion;
  private String referencia;

  /**
	 * 
	 */
  public Modulo() {
    super();
    setCodigo(CODIGO_MODULO);
    setNombre("");
    setDuracion(0);
    setuFormativa("");
    setReferencia("");
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
   * 
   * @return codigo
   */
  public int getCodigo() {
    return codigo;
  }

  /**
   * 
   * @param codigo
   *          int
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
   *          String
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * 
   * @return duracion
   */
  public int getDuracion() {
    return duracion;
  }

  /**
   * 
   * @param duracion
   *          int
   */
  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  /**
   * 
   * @return unidad formativa
   */
  public String getuFormativa() {
    return uFormativa;
  }

  /**
   * 
   * @param uFormativa
   *          String
   */
  public void setuFormativa(String uFormativa) {
    this.uFormativa = uFormativa;
  }

}
