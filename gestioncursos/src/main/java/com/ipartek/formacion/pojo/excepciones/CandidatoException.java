package com.ipartek.formacion.pojo.excepciones;

/**
 * 
 * @author Curso
 *
 */
public class CandidatoException extends Exception {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  public static final int CODIGO_ERROR_FECHA_NACIMIENTO = 39;
  public static final String MSG_ERROR_FECHA_NACIMIENTO = "La fecha introducida no es válida";
  public static final int CODIGO_ERROR_DNI = 25;
  public static final String MSG_ERROR_DNI = "Dni invalido";
  private int codigo;
  private String mensaje;

  /**
   * @param codigo
   *          codigo de error
   * @param mensaje
   *          mensaje de error
   */
  public CandidatoException(int codigo, String mensaje) {
    super();
    this.setCodigo(codigo);
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
  private void setCodigo(int codigo) {
    this.codigo = codigo;
  }

}
