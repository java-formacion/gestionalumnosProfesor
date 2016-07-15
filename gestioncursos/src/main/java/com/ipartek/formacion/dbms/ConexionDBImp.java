package com.ipartek.formacion.dbms;

import java.sql.Connection;

public class ConexionDBImp implements ConexionDB{

	private Connection conexion;
	
	@Override
	public void conectar() {
		
		String driver="com.mysql.jdbc.Driver";
		if (conexion!=null) {
			
		}
		
	}

	@Override
	public void desconectar() {
		// TODO Auto-generated method stub
		
	}

}
