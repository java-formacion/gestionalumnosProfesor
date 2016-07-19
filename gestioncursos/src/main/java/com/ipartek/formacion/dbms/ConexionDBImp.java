package com.ipartek.formacion.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.AdminServlet;
import com.ipartek.formacion.service.AlumnoServiceImp;
import com.mysql.jdbc.Driver;

public class ConexionDBImp implements ConexionDB{
	private static ConexionDBImp INSTANCE=null;
	private static Connection conexion;
	private static final Logger log=Logger.getLogger(ConexionDBImp.class);
	
	@Override
	public void conectar() {
		String url="jdbc:mysql://localhost:3306/gestioncursos";
		String user="usuario";
		String password="1q2w3e4r";
		String driver="com.mysql.jdbc.Driver";
		if (conexion==null) {
			try {
				Class.forName(driver);
				conexion=DriverManager.getConnection(url, user, password);
								
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage()+" error de conexion");
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
				log.error(e.getMessage());
			}
		}
		
	}

	@Override
	public Connection getConexion() {
		conectar();
		return conexion;
		
	}

	private ConexionDBImp(){
		conectar();
	}
	
	public static ConexionDBImp getInstance() {
		if (INSTANCE==null) { 
			INSTANCE=new ConexionDBImp();
		}
		return INSTANCE;
		
	}
	private synchronized static void createInstance(){
		if (INSTANCE==null) {
			INSTANCE=new ConexionDBImp();
		}
	}

}
