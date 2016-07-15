package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/*
 * 
 * Esta clase es la encargada de realizar las conexiones y desconexiones a BBDD.
 * 
 */

public class ConexionDBImp implements ConexionDB{
	private static final Logger LOG = Logger.getLogger(ConexionDBImp.class);
	private Connection conexion;
	private static ConexionDBImp INSTANCE = null; //SINGLETON
	
	private ConexionDBImp(){
		conexion = null;
	}
	
	 public static synchronized  ConexionDBImp getInstance() {
	        if (INSTANCE == null) {
	        	INSTANCE = new ConexionDBImp();
	        }

	        return INSTANCE;
	    }
	

	@Override
	public void conectar() {
		if (conexion != null){
			
		}
		
	}

	@Override
	public void desconectar() {
		if (conexion != null){
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
