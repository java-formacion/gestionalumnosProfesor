package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * esta clase es la encargada de realizar conex y desconex a BBDD.
 * @author Curso
 *
 */
public class ConexionDBImp implements ConexionDB {
    //private static final Logger LOG=Logger.getLogger(ConexionDB);
	private Connection conexion;
	//singleton
	private static ConexionDBImp INSTANCE=null;
		
	private  ConexionDBImp() {
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
		//si la conex es dist a nula,crear una
		if(conexion!=null){
			//Class.forName(driver);
		}
		
	}

	@Override
	public void desconectar() {
		if(conexion!=null){
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//LOG.error(e.getMessage());
			}
		}
		
	}

	
}
