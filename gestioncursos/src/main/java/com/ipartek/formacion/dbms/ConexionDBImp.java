package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConexionDBImp implements ConexionDB{
/**
 * @author Álvaro
 * esta clase es la encargada de realizar las conexiones a la bbdd
 */
  private static final Logger LOG=Logger.getLogger(ConexionDBImp.class);
  private  Connection conexion;
  private static ConexionDBImp INSTANCE=null;
  ;
  private ConexionDBImp(){
    conexion=null;
    conectar();
  }
  

  private synchronized static void createInstance() {
    if (INSTANCE == null) { 
        INSTANCE = new ConexionDBImp();
    }
}
  public static ConexionDBImp getInstance() {
    if (INSTANCE == null){
      createInstance();
    } 
    return INSTANCE;
}
  @Override
  public void conectar() {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/gestioncursos";
    String user="admin";
    String pass="admin";
    if (conexion!=null) {
      try {
        Class.forName(driver);
        conexion=DriverManager.getConnection(url, user, pass);
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        LOG.error(e.getMessage());
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        LOG.error(e.getMessage()+" error en conexion BBDD");
      }
      
    }
    
  }




  @Override
  public void desconectar() {
    if (conexion!=null) {
      try {
        conexion.close();
        conexion=null;
      } catch (SQLException e) {
        
        LOG.error(e.getMessage());
      }
      
    }
  }

    public  Connection getConexion() {
      conectar();
      return conexion;
  }

  
}
