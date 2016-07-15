package com.ipartek.formacion.service.excepciones;

/**
 * 
 * @author Curso
 *
 */
public class CursoServiceImpException extends Exception {

  private static final long serialVersionUID = 1L;
  public static final int CODIGO_ERROR_INDEX_INVALIDO = 42;
  public static final String MSG_ERROR_INDEX_INVALIDO = "No existe ningun curso con ese codigo";
  private int codigo;
  private String mensaje;

  /**
   * 
   * @param codigo
   *          int
   * @param mensaje
   *          String
   */
  public CursoServiceImpException(int codigo, String mensaje) {
    super();
    this.codigo = codigo;
    this.mensaje = mensaje;
  }

  /**
   * @Override
   * @return mensaje
   */
  public String getMessage() {
    return this.mensaje;
  }

  /**
   * @param arg0
   *          String
   * @param arg1
   *          Throwable
   * @param arg2
   *          boolean
   * @param arg3
   *          boolean
   */
  public CursoServiceImpException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

  /**
   * @param arg0
   *          String
   * @param arg1
   *          Throwable
   */
  public CursoServiceImpException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  /**
   * @param arg0
   *          String
   */
  public CursoServiceImpException(String arg0) {
    super(arg0);
  }

  /**
   * @param arg0
   *          Throwable
   */
  public CursoServiceImpException(Throwable arg0) {
    super(arg0);
  }

}
