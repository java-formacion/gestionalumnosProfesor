package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 * @author Julen Rodriguez Costa
 *Clase encargada de realizar la conexion y desconexion a BBDD.
 */
public class ConexionDBimp implements ConexionDB {

	private Connection conexion;
	private ConexionDBimp INSTANCE = null;
	
	private static final Logger LOG = Logger.getLogger(ConexionDB.class);
	
	private ConexionDBimp() {
		// TODO Auto-generated constructor stub
		 conexion = null;
	}
	
	@Override
	public void conectar() {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		if(conexion!=null)
		{
			try {
				
				Class.forName(driver);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				LOG.error(e.getMessage());			}
		}
	}

	@Override
	public void desconectar() {
		// TODO Auto-generated method stub
		if(conexion!=null)
		{
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOG.error(e.getMessage());
			}
		}
	}

}
