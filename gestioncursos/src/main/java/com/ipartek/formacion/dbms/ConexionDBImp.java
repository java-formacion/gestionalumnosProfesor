package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Esta clase es la encargada de realizar las conexiones y desconexiones a base
 * de datos.
 * 
 * @author Curso
 *
 */
public class ConexionDBImp implements ConexionDB {

	private Connection conexion;
	private static ConexionDBImp INSTANCE = null;
	private static final Logger LOG = Logger.getLogger(ConexionDBImp.class);

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
		String user = "Stukov";
		String password = "pass";

		if (conexion == null) {
			try {
				Class.forName(driver);// registramos el driver que tiene que
										// usar para conectarse
				conexion = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
			} catch (SQLException e) {
				LOG.error(e.getMessage() + " error en conexión a la BBDD");
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
	public Connection getConexion() {
		conectar();
		return this.conexion;
	}

}
