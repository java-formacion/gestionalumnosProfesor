package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 * @author Curso
 *
 *         Clase encargada de realizar conexiones y desconexiones a la DB.
 */
public class ConexionDBImp implements ConexionDB {
  private Connection conexion;
  private static ConexionDBImp INSTANCE = null;
  private final static Logger LOG = Logger.getLogger(ConexionDBImp.class);

  /**
 * 
 */
  private ConexionDBImp() {
    conexion = null;
  }

  /**
   * 
   * @return INSTANCE
   */
  public static ConexionDBImp getInstance() {
    if (INSTANCE == null) {
      createInstance();
    }
    return INSTANCE;
  }

  /**
 * 
 */
  private synchronized static void createInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ConexionDBImp();
    }
  }

  /**
   * @Override
   * @return nada
   * @throws CloneNotSupportedException
   *           no se puede cñlonar
   */
  protected Object clone() throws CloneNotSupportedException {
    // TODO Auto-generated method stub
    throw new CloneNotSupportedException();
  }

  /**
   * @Override
   */
  public void conectar() {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/gestioncursos";
    String user = "usuario";
    String password = "usuario";
    if (conexion != null) {
      try {
        Class.forName(driver);
        conexion = DriverManager.getConnection(url, user, password);
      } catch (ClassNotFoundException e) {
        LOG.error(e.getMessage());
      } catch (SQLException e) {
        LOG.error(e.getMessage() + " error conexion BBDD");
      }
    }

  }

  /**
   * @Override
   */
  public void desconectar() {
    if (conexion != null) {
      try {
        conexion.close();
      } catch (SQLException e) {
        LOG.error(e.getMessage());
      }
    }
  }

  /**
   * 
   * @return conexion
   */
  public Connection getConexion() {
    return conexion;
  }

}
