package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.Constantes;
import com.mysql.jdbc.Driver;

/*
 * @author Erasmo.
 */

// La clase inplementa a su interfaz e importa los metodos de la interfaz (en este caso ConexionDB).

// Esta clase sera la encargada de implementar las conexiones y desconexiones a base de datos.
public class ConexionDBImp implements ConexionDB {
	private static final Logger LOG = Logger.getLogger(ConexionDBImp.class);

	// SINGLETON
	private Connection conexion;
	private static ConexionDBImp INSTANCE = null;
	
	private ConexionDBImp(){
		conexion =null;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConexionDBImp();
		}
	}

	public static ConexionDBImp getInstance() {
		if (INSTANCE == null)
			{createInstance();}
		return INSTANCE;
	}

	// FIN SINGLETON

	@Override
	public void conectar() {
String driver = "com.mysql.jdbc.Driver";
String url="jdbc:mysql://192.168.0.8:3306/gestioncursos";
String user="usuario";
String password="1234";
		if (conexion == null) {
try {
	Class.forName(driver);
	conexion = DriverManager.getConnection(url,user,password);
} catch (ClassNotFoundException e) {
	LOG.error(e.getMessage());
}catch (SQLException e) {
	LOG.error(e.getMessage()+ " error conexion base de datos");
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

	@Override
	public Connection getConexion()
		{
			return this.conexion;
		}

}
