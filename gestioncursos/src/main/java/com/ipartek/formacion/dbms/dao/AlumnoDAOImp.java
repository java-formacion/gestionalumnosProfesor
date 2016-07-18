package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;

public class AlumnoDAOImp implements AlumnoDAO {
	
	private static final Logger LOG = Logger.getLogger(ConexionDBImp.class);

	@Override
	public Alumno getById(int codigo) {
		
		Alumno alumno = null;
		
		// TODO es singleton: va a tener el atributo de conexion
		String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, dni_nie, fNacimiento, email, telefono, codGenero, g.nombre as 'nGenero"
				+ "		FROM alumno a "
				+ "		INNER JOIN genero g ON g.codGenero=a.codGenero"
				+ "		WHERE codAlumno = " + codigo;
		
		
		ConexionDB dbConnection = ConexionDBImp.getInstance();				
		dbConnection.conectar();
		Connection conexion = dbConnection.getConexion();
		
		
		try {
			PreparedStatement pSmt = conexion.prepareStatement(sql);
			ResultSet rs = pSmt.executeQuery();
			
			while(rs.next()){
				alumno = parseAlumno(rs);
			}
			
		} catch (SQLException e) {
			LOG.error("Error en AlumnoDAOImp.java - SQLException - getById(): " + e.getMessage());
		}
		
		return alumno;
	}

	private Alumno parseAlumno(ResultSet rs) {
		Alumno alumno = null;
		alumno = new Alumno();
		
		try {
			alumno.setCodigo(rs.getInt("codAlumno"));
			alumno.setNombre(rs.getString("nAlumno"));
			alumno.setApellidos(rs.getString("apellidos"));
			
			/*
			alumno.setDni(rs.getString("dni_nie"));
			alumno.setfNacimiento(rs.getDate("fNacimiento"));
			alumno.setEmail(rs.getString("email"));
			alumno.setTelefono(rs.getString("telefono"));
			alumno.setGenero(rs.getInt("nGenero"));
			*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alumno;
	}

	@Override
	public Alumno update(Alumno alumno) {
		
		return null;
	}

	@Override
	public Alumno insert(Alumno alumno) {
		
		return null;
	}

	@Override
	public void delete(int codigo) {

	}

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = null;
		String sql = "{call getAllAlumno()}";
		ConexionDB myConexion = ConexionDBImp.getInstance();
		Connection conection = myConexion.getConexion();
		
		try {
			Alumno alumno = null;
			CallableStatement cSmt = conection.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			alumnos = new ArrayList<Alumno>();
			
			while(rs.next()){
				alumno = parseAlumno(rs);
				alumnos.add(alumno);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alumnos;
	}
}
