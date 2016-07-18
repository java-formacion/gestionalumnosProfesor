package com.ipartek.formacion.dbms;

import java.sql.Connection;

/**
 * 
 * @author Curso
 *
 */
public interface ConexionDB {
  /**
 * 
 */
  public void conectar();

  /**
   * 
   */
  public void desconectar();

  /**
   * @return conexion
   */
  public Connection getConexion();
}
