package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 * @author Curso Esta clase es la encargada de realizar conexión y desconexión a BBDD.
 *
 */

public class ConexionDBImp implements ConexionDB {
  private static Connection conexion;
  private static ConexionDBImp INSTANCE = null;
  private static final Logger LOG = Logger.getLogger(ConexionDBImp.class);

  private ConexionDBImp() {
    conectar();
  }

  public Connection getConexion() {
    conectar();
    return conexion;
  }

  public static ConexionDBImp getInstance() {
    if (INSTANCE == null) {
      createInstance();
    }

    return INSTANCE;
  }

  private synchronized static void createInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ConexionDBImp();
    }

  }

  @Override
  public void conectar() {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql.://localhost:3306/gestioncursos";
    String user = "bego";
    String password = "bego";
    if (conexion == null) {
      try {
        Class.forName(driver);
        conexion = DriverManager.getConnection(url, user, password);
      } catch (ClassNotFoundException e) {
        LOG.error(e.getMessage());
      } catch (SQLException e) {
        LOG.error(e.getMessage() + " " + "error conexion BBDD");
      }
    }
  }

  @Override
  public void desconectar() {

    if (conexion != null) {
      try {
        conexion.close();
        conexion = null;
      } catch (SQLException e) {
        LOG.error(e.getMessage());
      }
    }

  }

}
