package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author Borja Garduño
 *
 * Esta clase es la encargada de gestionar 
 * las conexiones y desconexiones de las Bases de Datos.
 */

public class ConexionDBImp implements ConexionDB {

	private Connection conexion;
	private static ConexionDBImp INSTANCE = null;
	
	private static final Logger LOG = Logger.getLogger(ConexionDBImp.class);
	
	private ConexionDBImp(){
		conexion = null;
		conectar();
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
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/gestioncursos";
		String user = "root";
		String password = "";
		
		if(conexion==null){
			try {
				Class.forName(driver);
				conexion = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				LOG.error("Error - ClassNotFoundException: " + e.getMessage());
			} catch (SQLException e) {
				LOG.error("Error - SQLException : " + e.getMessage());
			}
		}
	}

	@Override
	public void desconectar() {
		if(conexion!=null){
			try {
				conexion.close();
				conexion = null;
			} catch (SQLException e) {
				LOG.error("Error - SQLException: " + e.getMessage());
			}
		}
	}
	
	@Override
	public Connection getConexion() {
		conectar();
		return conexion;
	}
}
