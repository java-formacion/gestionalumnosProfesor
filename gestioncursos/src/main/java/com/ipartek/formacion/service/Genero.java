package com.ipartek.formacion.service;

/**
 * 
 * @author Curso
 *
 */
public enum Genero {
  MASCULINO(1, "Hombre"), FEMENINO(2, "Mujer"), OTROS(3, "Otros");
  private int codigo;
  private String valor;

  /**
   * @param codigo
   *          int
   * @param valor
   *          String
   */
  Genero(int codigo, String valor) {
    this.codigo = codigo;
    this.valor = valor;
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
   * @return valor
   */
  public String getValor() {
    return valor;
  }

  /**
   * @param valor
   *          String
   */
  public void setValor(String valor) {
    this.valor = valor;
  }

}
