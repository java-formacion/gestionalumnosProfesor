package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Esta clase es la encargada de realizar las conexiones y desconexiones a BBDD.
 * 
 * @author Curso
 */
public class ConexionDBImp implements ConexionDB {
	private static final Logger LOG = Logger.getLogger(ConexionDBImp.class);
	private static Connection conexion;
	private static ConexionDBImp INSTANCE = null;

	private ConexionDBImp() {
		conexion = null;
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
		String url = "jdbc:mysql://localhost:3306/gestioncursos";
		String user = "ususario";
		String password = "bd_usuario";
		if (conexion != null) {
			try {
				Class.forName(driver);// registra la libreria de la bbdd
				conexion = DriverManager.getConnection(url);
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
			} catch (SQLException e) {
				LOG.error(e.getMessage() + "error conexion BBDD");
			}
		}

	}

	@Override
	public void desconectar() {
		if (conexion != null) {
			try {
				conexion.close();

			} catch (SQLException e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	@Override
	public Connection getConexion() {
		return this.conexion;
	}

}
