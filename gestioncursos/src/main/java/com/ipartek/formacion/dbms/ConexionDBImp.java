package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConexionDBImp implements ConexionDB{
/**
 * @author Álvaro
 * esta clase es la encargada de realizar las conexiones a la bbdd
 */
  private static final Logger LOG=Logger.getLogger(ConexionDBImp.class);
  private Connection conexion;
  private static ConexionDBImp INSTANCE=null;
  private ConexionDBImp(){
    conexion=null;
  }
  
  private synchronized static void createInstance() {
    if (INSTANCE == null) { 
        INSTANCE = new ConexionDBImp();
    }
}
  public static ConexionDBImp getInstance() {
    if (INSTANCE == null) createInstance();
    return INSTANCE;
}
  @Override
  public void conectar() {
    String driver="com.mysql.jdbc.Driver";
    if (conexion!=null) {
      try {
        Class.forName(driver);
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        LOG.error(e.getMessage());
      }
      
    }
    
  }




  @Override
  public void desconectar() {
    if (conexion!=null) {
      try {
        conexion.close();
      } catch (SQLException e) {
        
        LOG.error(e.getMessage());
      }
      
    }
  }

    public Connection getConexion() {
    return conexion;
  }

  
}
