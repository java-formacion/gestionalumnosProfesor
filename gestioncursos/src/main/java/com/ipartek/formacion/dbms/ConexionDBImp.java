package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/*
 * 
 * Esta clase es la encargada de realizar las conexiones y desconexiones a BBDD.
 * 
 */

public class ConexionDBImp implements ConexionDB{
	private static final Logger LOG = Logger.getLogger(ConexionDBImp.class);
	private static Connection conexion = null;
	private static ConexionDBImp INSTANCE = null; //SINGLETON
	
	private ConexionDBImp(){ //se llama a conectar() xq al crear una instancia del objeto, se entiende que es para conectar a la BBDD.
		conexion = null;
		conectar();
	}
	
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConexionDBImp();
		}
	}

	public static ConexionDBImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}
	 
	 

	@Override
	public void conectar() {
		String driver = "com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/gestioncursos";
		String user ="usuario";
		String password="usuario";
		if (conexion == null) {
			try {
				Class.forName(driver);
				conexion = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
			} catch (SQLException e) {
				LOG.error(e.getMessage() + "error conexion BBDD");
			}
		}
		
	}

	@Override
	public void desconectar() {
		if (conexion != null){
			try {
				conexion.close();
				conexion = null;
			} catch (SQLException e) {
				LOG.error(e.getMessage());
			}
		}
	}

	@Override
	public Connection getConexion() {
		conectar(); //aqui se llama tb a conectar() x si se nos olvida ponerlo en algun sitio
		return conexion;
	}

	
	
	
	

}
